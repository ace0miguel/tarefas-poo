package Telas.Eventos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import Cartas.Carta;
import Cartas.CartaAtaqueComEfeito;
import Deck.Mao;
import Deck.PilhaCompra;
import Deck.PilhaDescarte;
import Entidades.Entidade;
import Entidades.Heroi;
import Entidades.Inimigo;
import Subscribers.BatalhaSubscriber;
import Subscribers.EfeitosDeStatus.Efeito;
import Subscribers.EfeitosDeStatus.Energizar;
import Subscribers.EfeitosDeStatus.DanosConstantes.DanoConstante;
import Subscribers.EfeitosDeStatus.DanosConstantes.Veneno;
import Subscribers.EfeitosDeStatus.Instantaneos.Purificar;
import Subscribers.Poderes.Poder;
import Util.InputHandler;
import Util.Recompensas;
import Visual.Arte;
import Visual.Cor;
import Visual.Textos;

public class Batalha extends Evento {
    private int turno; // 0 -> heroi, 1 -> inimigos

    private List<Inimigo> inimigos; // lista viva dos inimigos no combate atual
    private Inimigo[] moldesInimigos; // moldes imutaveis para recriar a batalha
    private List<Inimigo> novosInimigos = new ArrayList<>(); // lista de inimigos a serem adicionados na batalha quando possivel

    private int recompensa = 0; // recompensa em dinheiro pela batalha, baseada no tier dos inimigos
    private int dificuldadeTotal = 0; // soma da dificuldade(tier) de cada inimigo

    private Mao mao = new Mao();
    private PilhaCompra pilhaCompra = new PilhaCompra();
    private PilhaDescarte pilhaDescarte = new PilhaDescarte();
    private PilhaDescarte pilhaConsumir = new PilhaDescarte(); // <- pra cartas que nao voltam pra sua mao durante o combate

    // subscribers --------
    private ArrayList<BatalhaSubscriber> subscribers = new ArrayList<>();
    private ArrayList<BatalhaSubscriber> novosSubscribers = new ArrayList<>();
    private ArrayList<Efeito> listaEfeitos = new ArrayList<>(); 
    private ArrayList<Poder> listaPoderes = new ArrayList<>();
    // -------------

    Scanner ler = InputHandler.getLeitor();

    private Inimigo[] copiarInimigos(Inimigo[] inimigosOriginais) {
        Inimigo[] copiados = new Inimigo[inimigosOriginais.length];
        for (int i = 0; i < inimigosOriginais.length; i++) {
            copiados[i] = inimigosOriginais[i].criaCopia();
        }
        return copiados;
    }

    /** retorna al ista de inimigos vivos convertidas pra array */
    private Inimigo[] inimigosAtuaisArray() {
        return inimigos.toArray(Inimigo[]::new);
    }

    /** retorna todos os inimigos e o heroi */
    private List<Entidade> listaEntidades() {
        List<Entidade> entidades = new ArrayList<>();
        entidades.add(heroi);
        entidades.addAll(inimigos);
        return entidades;
    }

    /** adiciona os inimigos na lista novosInimigos a batalha, remove os inimigos com flag pra remover e reseta a lista */
    private void addNovosInimigos() {
        inimigos.removeIf(Inimigo::getRemoverDaBatalha);

        if (novosInimigos.isEmpty()) {
            return;
        }

        for (Inimigo novoInimigo : novosInimigos) {
            if (novoInimigo != null) {
                novoInimigo.escolheAcao();
                inimigos.add(novoInimigo);
            }
        }
        
        novosInimigos.clear();
    }

    /** adiciona novos subscribers */
    private void addNovosSubscribers() {
        if (novosSubscribers.isEmpty()) {
            return;
        }

        for (BatalhaSubscriber novoSubscriber : novosSubscribers) {
            if (novoSubscriber != null) {
                adicionarSubscriber(novoSubscriber);
            }
        }
        
        subscribers.sort(Comparator.comparing(BatalhaSubscriber::getPrioridade));
        novosSubscribers.clear();
    }

    public Batalha(Inimigo... _inimigos){
        this.inimigos = new ArrayList<>(Arrays.asList(copiarInimigos(_inimigos)));
        this.moldesInimigos = copiarInimigos(_inimigos);
        for (Inimigo inimigo : inimigos) {
            recompensa += inimigo.getRecompensa();
            dificuldadeTotal += inimigo.getTier();
        }
    }

    public List<Inimigo> getInimigos() {
        return inimigos;
    }

    /** recebe heroi, define as variáveis e chama a classe principal. */
    @Override
    public void iniciar(Heroi heroi){
        
        this.heroi = heroi;

        // passa a referencia da mao e das pilhas pro heroi
        heroi.setMaoAtual(mao); 
        heroi.setPilhaCompra(pilhaCompra);
        heroi.setPilhaDescarte(pilhaDescarte);

        pilhaCompra.addBaralho(new ArrayList<>(heroi.getBaralho())); // pilha de compras recebe o baralho do heroi e embaralha
        pilhaCompra.shuffleAll(pilhaDescarte);
        
        // resetando os bonus do heroi q possam ter sobrado da rodada passada
        heroi.passaRodada();
        heroi.passaRodada();
        heroi.resetaBuffs();
        heroi.resetEfeitos();

        turno = 0; // 0: turno do heroi 
        
        for (Inimigo inimigo : inimigos) 
            inimigo.escolheAcao();

        batalha();
    }

    public void passaRodada(){
        heroi.resetEfeitos();

        for (Inimigo inimigo : inimigos) {
            inimigo.resetEfeitos();
        }
        
        limpaSubscribers();
        
        for (BatalhaSubscriber subscriber : subscribers) {
            subscriber.onRoundStart(this, heroi);
            Textos.printaBonito(subscriber.getMsgFimRodada(this, heroi), 2,2);
            Textos.sleep(300);
        }

        Textos.printaBonito(Cor.txtCinza( "\n" + Arte.bordaHud9), 2,2); Textos.sleep(300);
        InputHandler.esperar();

        if (notificaMorte() == 0) {
            fimBatalha();
        }

        limpaSubscribers();

        // notifica possiveis meia vida
        for (Inimigo inimigo : inimigos) {
            inimigo.checkMeiaVida(inimigo, heroi, this);
        }
        heroi.checkMeiaVida(heroi, heroi, this);

        heroi.passaRodada(); // remove os bonus que acabam (escudo, etc) e reseta energia
    }

    /** troca de turno e se voltar pra vez do heroi chama o passaRodada  */
    public void passaTurno(){
        turno = (turno == 0) ? 1 : 0;
        notificaMorte();
        if (turno == 0) passaRodada();
        
        addNovosInimigos();
    }

    /** notifica onRemove e remove os subscribers*/
    public void limpaSubscribers(){     
        for (BatalhaSubscriber subscriber : subscribers ) {
            if (subscriber.getRemover()){
                subscriber.onRemove(this, heroi);
            }
        }

        subscribers.removeIf(subscriber -> (subscriber.getRemover()== true));
    }

    /** printa os inimigos vivos, valida a escolha e retorna o q vc escolheu. retorno -1: voltar*/
    public int selecionarAlvo(){  // falta fazer uma opçao pra voltar caso ele mude de ideia sobre a carta!
        int opcao = -1;
        while (true) { 
            int i = 0;
            Textos.limpaTela();
            Cor.printaAmareloClaro("Selecione o alvo:");
            System.out.println();

            System.out.println((Cor.amarelo + "0 - " + Cor.cinza + "Voltar."));

            for (Inimigo inimigo : inimigos) {
                if (inimigo.estaVivo()){
                    Textos.sleep(30);
                    System.out.println((Cor.amarelo + ""+ (i + 1) +" - "+inimigo.getNomeColorido()+""));
                    i++;
                }
            }

            try {
                opcao = ler.nextInt();
                if (ler.hasNextLine()) {
                ler.nextLine();
            }
            } catch (Exception e) {
                if (ler.hasNextLine()) {
                ler.nextLine();
            }  
            }
            
            if (opcao == 0) break;
            
            if ((opcao >= 0 && opcao <= inimigos.size() && inimigos.get(opcao-1).estaVivo())) 
                break;

            System.out.println();
            Cor.printaAmarelo(Textos.escolhaInvalida(i));

            InputHandler.esperar();
        }
        return opcao - 1; 
    }

    /** adiciona um inimigo na lista de inimigos da batalha! */
    public void adicionarInimigo(Inimigo inimigo){
        if (inimigo != null) {
            novosInimigos.add(inimigo);
        }
    }

    /** adiciona um efeito na lista de efeitos e notifica onCreate */
    public void adicionarSubscriber(BatalhaSubscriber novoSubscriber) { 
        for (BatalhaSubscriber subscriber : subscribers) {
            if (subscriber.addStack(this, novoSubscriber)){
                return;
            }
        }

        this.subscribers.add(novoSubscriber);
        novoSubscriber.onCreate(this, heroi);
        this.subscribers.sort(Comparator.comparing(BatalhaSubscriber::getPrioridade)); // ordena os efeitos por prioridade pra sangramento sobrepor resistencia
    }
    

    /** avisa os efeitos com aplicação quando o alvo morre, antes de remover da lista de inimigos
     *  e retorna o tamanho da lista de inimigos.
     */
    public int notificaMorte(){

        // ve se morreu todo mundo e ja retorna
        boolean todosMortos = true;
        for (Inimigo inimigo : inimigos) {
            if (inimigo.estaVivo()) todosMortos = false;
        }
        if (todosMortos) return 0;

        // adicionar aqui nesse loop os efeitos que fazem algo quando o alvo morre
        for (Inimigo i : inimigos) {
            if (!i.estaVivo()){
                for (BatalhaSubscriber subscriber : subscribers) {
                    subscriber.onDeath(this, i);
                }
            }
        } 

        inimigos.removeIf(inimigo -> inimigo.estaVivo() == false);
        return inimigos.size();
    }

    public void turnoHeroi(){
        mao.addCinco(pilhaCompra, pilhaDescarte);
        boolean primeiroLoop = true; // se true, printa a animaçao de batalha
        boolean escolhaInvalida = false; // se true, mostra escolha invalida

        // loop da escolha de ação
        while(true){ 
            Textos.limpaTela();

            if (escolhaInvalida){
                System.out.println();
                System.out.println(Textos.escolhaInvalida(mao.getSize()));
                InputHandler.esperar();
                Textos.limpaTela();
                escolhaInvalida = false;  
            }

            // mostra a animaçao de batalha apenas caso seja o inicio da rodada e o jogador nao esteja usando deck teste
            printaBatalha(primeiroLoop);
            primeiroLoop = false;

            // trecos pra mostrar os buffs pra debugar
            // System.out.println(Cor.txtVermelho("resist extra" + heroi.getResistencia()));
            // System.out.println(Cor.txtLaranja("dano extra" + heroi.getDanoExtra()));

            int escolha = mao.mostrar(); 

            // avaliando as escolhas que não são cartas --

            if (escolha == mao.getSize()) break; // opção de passar o turno

            if (escolha > mao.getSize() || escolha < 0){ 
                escolhaInvalida = true;
                continue;
            }

            // -- 

            if (usaCarta(escolha) == -1)
                continue;

            if (notificaMorte() == 0) {
                fimBatalha();
            }

            // se conseguir usar todas as cartas da mao puxa mais 5 e ganha 2 de energia bonus, injeçao de dopamina assim q vc mantem um jogador preso
            if (mao.getSize() == 0){
                Textos.sobeTela();

                Textos.printaLinhaDevagar(Cor.rosa + (Arte.bonus).repeat(15) + Cor.reset);

                heroi.ganhaEnergia(2);
                InputHandler.esperar();
                mao.addCinco(pilhaCompra, pilhaDescarte);
            }

            // notifica possiveis meia vida
            for (Inimigo inimigo : inimigos) {
                inimigo.checkMeiaVida(inimigo, heroi, this);
            }
            heroi.checkMeiaVida(heroi, heroi, this);

            addNovosInimigos();
        }            
        mao.limpa(pilhaDescarte);    
        passaTurno();
    }


    public void turnoInimigos(){
        Textos.limpaTela();
        Textos.printaBonito(Cor.txtCinza(Arte.bordaHud9), 2,2); Textos.sleep(300);

        for (Inimigo inimigo : inimigos) {
            if (inimigo.estaVivo()){ // adicionei isso pq joguei uma partida aqui e tomei hit de um inimigo morto.
                inimigo.passaRodada(); // reseta os bonus (escudo por enquanto)
                inimigo.resultadoAcao(heroi); // printa oq ele ta fazendo (antes de fazer pq as vezes ele se mata)
                inimigo.realizarAcao(heroi, this); // faz oq ele ia fazer
                inimigo.escolheAcao(); // escolhe prox ação

                // checa possiveis meia vida
                inimigo.checkMeiaVida(inimigo, heroi, this);
                heroi.checkMeiaVida(heroi, heroi, this);
            }
        }
        
        InputHandler.esperar();
        passaTurno();
    }

    public int usaCarta(int escolha){
        Carta cartaEscolhida = mao.escolheCarta(escolha); 

        if (!cartaEscolhida.podeGastar(heroi)){
            System.out.println();
            System.out.println("Energia insuficiente");
            System.out.println();
            InputHandler.esperar();
            return -1;
        } 

        Entidade alvoSelecionado = heroi; // se nao mudar o alvo é pq o alvo é si mesmo

        // se não for selfcast pergunta o alvo. Se for ataque com efeito selfcast ataca o alvo e aplica o efeito em si mesmo.
        if ((cartaEscolhida.getSelfCast()) && !(cartaEscolhida instanceof CartaAtaqueComEfeito)) 
            {
            if (cartaEscolhida.temResenha())
                Textos.sobeTela();

            cartaEscolhida.usar(heroi, heroi, this);
        } else 
            {
            int alvo = selecionarAlvo();
            if (alvo == -1) 
                { // -1 é o codigo pra exit
                return -1;
            } 

            alvoSelecionado = inimigos.get(alvo);

            if (cartaEscolhida.temResenha())
                Textos.sobeTela();

            cartaEscolhida.usar(heroi, alvoSelecionado, this); 
        }

        if (cartaEscolhida.getUsoCancelado()) 
            {
            cartaEscolhida.setUsoCancelado(false);
            return -1;
        }

        // cartas com a flag consumir vao pra pilha secundaria e nao sao embaralhadas devolta
        if (cartaEscolhida.getConsumir())
            mao.removeCarta(escolha, pilhaConsumir);
        else 
            mao.removeCarta(escolha, pilhaDescarte);

        // notifica os efeitos com on hit
        for (BatalhaSubscriber subscriber : subscribers)
            subscriber.onHit(cartaEscolhida, heroi, alvoSelecionado, this);

        return 0;
    }

    public void printaBatalha (boolean primeiroLoop){
        if (primeiroLoop && !heroi.getTestMode()){
            Textos.batalha(heroi, listaEfeitos, listaPoderes, inimigosAtuaisArray());
            primeiroLoop = false;
        } else {
            Textos.batalhaSemDelay(heroi, listaEfeitos, listaPoderes, inimigosAtuaisArray());
        }
    }

    public void batalha(){
        // loop principal: checa se o heroi ou ao menos um inimigo esta vivo
        while(heroi.estaVivo() == true && inimigos.stream().anyMatch(i -> i.estaVivo() == true)){

            if (turno == 0) turnoHeroi();
            else turnoInimigos();
        }
        
        fimBatalha(); 
    }

    public void fimBatalha(){
        System.out.println();
        Cor.printaAmarelo("DUELO ENCERRADO!\n");
        System.out.println();

        Textos.sleep(1500);
        Textos.limpaTela();

        // tira as referencias de mao e pilha do heroi
        heroi.setMaoAtual(null);
        heroi.setPilhaCompra(null);
        heroi.setPilhaDescarte(null);

        if(heroi.estaVivo()){
            vitoria();
        } else {
            derrota();
        }
    }

    /** exibe a mensagem de vitória e cuida da recompensa da batalha */
    public void vitoria(){
        String arteVitoria = Textos.colorirPartes(Arte.venceu2, Cor.amareloClaro, Cor.laranja, 5);
        Textos.printaLinhaDevagar(arteVitoria);
        System.out.println();
        InputHandler.esperar();
        heroi.ganhaDinheiro(this.recompensa);
        Recompensas.ganharCarta(1, heroi);

        if (getNivelDificuldade() == 3) {
            System.out.println("Parabens por vencer uma batalha desafiadora!\n");
            Recompensas.ganharCarta(2, heroi);
        }

        if (getNivelDificuldade() == 4) {
            System.out.println("Parabens por vencer uma batalha de elite!\n");
            Recompensas.ganharCarta(3, heroi);
        }

    }

    /** exibe a mensagem de derrota e cuida da recompensa da batalha */
    public void derrota(){
        System.out.println();
        Textos.printaLinhaDevagar(Cor.txtCinza(Arte.sans2));
        System.out.println();
        System.out.println();
        Textos.printaLinhaDevagar(Cor.txtVermelho(Arte.VOCEMORREU));
        System.out.println();
        InputHandler.esperar("Pressione ENTER para aceitar a sua derrota!");
        System.exit(0);
    }

    public int getDificuldadeTotal() {
        return dificuldadeTotal;
    }

    public int getNivelDificuldade() {
        if (dificuldadeTotal < 4) {
            return 1; // trivial
        }
        else if (dificuldadeTotal < 7) {
            return 2; // normal
        }
        else if (dificuldadeTotal <= 8) {
            return 3; // desafiador
        }
        else {
            return 4; // elite
        }
    }

    @Override
    public String toString() {
        String retorno = Cor.txtVermelho("Batalha");
        
        switch (getNivelDificuldade()) {
            case 1 -> retorno += " <" + Cor.azul + "trivial" + Cor.reset + ">";
            case 2 -> retorno += " <" + Cor.verde + "normal" + Cor.reset + ">";
            case 3 -> retorno += " <" + Cor.amarelo + "desafiador" + Cor.reset + ">";
            case 4 -> retorno += " <" + Cor.vermelho + "elite" + Cor.reset + ">";
        }

        retorno += Cor.txtCinza(" VERSUS:");
        for (Inimigo inimigo : moldesInimigos) {
            retorno += " [ " + inimigo.getNome() + " ]";
        }
        return retorno;
    }

    @Override
    public Batalha criaCopia() {
        return new Batalha(moldesInimigos); 
    }
}
