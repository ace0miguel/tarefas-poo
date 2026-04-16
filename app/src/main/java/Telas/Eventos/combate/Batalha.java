package telas.eventos.combate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import baralho.Mao;
import baralho.PilhaCompra;
import baralho.PilhaDescarte;
import batalhaListeners.batalhaListener;
import batalhaListeners.efeitos.Efeito;
import batalhaListeners.itens.Item;
import batalhaListeners.poderes.Poder;
import cartas.Carta;
import cartas.CartaAtaqueComEfeito;
import entidades.Entidade;
import entidades.Heroi;
import entidades.Inimigo;
import telas.eventos.Evento;
import util.InputHandler;
import util.Recompensas;
import visual.Arte;
import visual.Cor;
import visual.Textos;

public class Batalha extends Evento {
    private int turno; // 0 -> heroi, 1 -> inimigos
    private boolean fimBatalha = false;

    private List<Inimigo> inimigos; // lista viva dos inimigos no combate atual
    private Inimigo[] moldesInimigos; // moldes imutaveis para recriar a batalha
    private List<Inimigo> novosInimigos = new ArrayList<>(); // lista de inimigos a serem adicionados na batalha quando possivel

    private int sleepFimRodada = 200; // tempo de sleep depois de printar as mensagens de fim de rodada
    private int recompensa = 0; // recompensa em dinheiro pela batalha, baseada no tier dos inimigos
    private int dificuldadeTotal = 0; // soma da dificuldade(tier) de cada inimigo
    private boolean primeiraRodada = true;

    private Mao mao = new Mao();
    private PilhaCompra pilhaCompra = new PilhaCompra();
    private PilhaDescarte pilhaDescarte = new PilhaDescarte();
    private PilhaDescarte pilhaConsumir = new PilhaDescarte(); // <- pra cartas que nao voltam pra sua mao durante o combate

    // subscribers --------
    private ArrayList<batalhaListener> subscribers = new ArrayList<>();
    private ArrayList<batalhaListener> novosSubscribers = new ArrayList<>();

    // listas pra printar o estado da batalha
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
        return inimigos.stream().filter(i -> i.estaVivo()).toArray(Inimigo[]::new);
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

        // garante q ele nao retorne estavivo ao ser removido sem morrer
        for (Inimigo inimigo : inimigos) { 
            if (inimigo.getRemoverDaBatalha()) {
                inimigo.setVida(0); 
            }
        }

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

    private void checkAcaoHeroi(){
        addNovosSubscribers();
        addNovosInimigos();
        limpaSubscribers();
        notificaMorte();
    }

    public void adicionarFuturoSubscriber(batalhaListener novo) {
        novosSubscribers.add(novo);
    }

    /** adiciona novos subscribers */
    private void addNovosSubscribers() {
        if (novosSubscribers.isEmpty()) {
            return;
        }

        for (batalhaListener novoSubscriber : novosSubscribers) {
            if (novoSubscriber != null) {
                adicionarSubscriber(novoSubscriber);
            }
        }
        
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

    private void inicializaItens(){
        for (Item item : heroi.getListaItens()) {
            item.setAlvo(heroi);
            adicionarSubscriber(item);
        }
    }

    /** recebe heroi, define as variáveis e chama a classe principal. */
    @Override
    public void iniciar(Heroi heroi){
        
        this.heroi = heroi;

        inicializaItens();

        // passa a referencia da mao e das pilhas pro heroi
        heroi.setMaoAtual(mao); 
        heroi.setPilhaCompra(pilhaCompra);
        heroi.setPilhaDescarte(pilhaDescarte);

        pilhaCompra.addBaralho(new ArrayList<>(heroi.getBaralho())); // pilha de compras recebe o baralho do heroi e embaralha
        pilhaCompra.shuffleStack();
        
        // resetando os bonus do heroi q possam ter sobrado da rodada passada
        heroi.passaRodada();
        heroi.passaRodada();
        heroi.resetaBuffs();
        heroi.resetEfeitos();

        turno = 0; // 0: turno do heroi 
        
        for (Inimigo inimigo : inimigos) 
            inimigo.escolheAcao();

        for (batalhaListener sub : subscribers) {
            sub.onBattleStart(this, heroi);
        }

        heroi.aplicaBonus();

        batalha();
    }

        public void batalha(){
        // loop principal: checa se o heroi ou ao menos um inimigo esta vivo
        while(heroi.estaVivo() == true && inimigos.stream().anyMatch(i -> i.estaVivo() == true && fimBatalha == false)){
            if (turno == 0 && !fimBatalha) turnoHeroi();
            else if (turno != 0 && !fimBatalha) turnoInimigos();
        }
        
        fimBatalha(); 
    }

    public void turnoHeroi(){
        if (primeiraRodada) {
            mao.inicioBatalha(pilhaCompra, pilhaDescarte);
            primeiraRodada = false;
        }
        else {
            mao.completaCinco(pilhaCompra, pilhaDescarte);
        }
        
        boolean primeiroLoop = true; // se true, printa a animaçao de batalha
        boolean escolhaInvalida = false; // se true, mostra escolha invalida

        // loop da escolha de ação
        while(true){ 
            Textos.limpaTela();

            if (notificaMorte() == 0) {
                return;
            }

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

            int escolha = mao.mostrar(); 

            if (escolha == mao.getSize()) // opção de passar o turno
                break; 

            if (escolha > mao.getSize() || escolha < 0){  // opçao inválida
                escolhaInvalida = true;
                continue;
            }


            if (usaCarta(escolha) == -1)
                continue;

            if (notificaMorte() == 0) {
                return;
            }

            // atualiza a lista de subscribers
            limpaSubscribers();
            addNovosSubscribers();

            // se conseguir usar todas as cartas da mao puxa mais 5 e ganha 2 de energia bonus, injeçao de dopamina assim q vc mantem um jogador preso
            if (mao.getSize() == 0){
                rodadaBonus();
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
        Textos.printaBonito(Cor.txtCinza(Arte.bordaHud9), 2,2); Textos.sleep(sleepFimRodada);

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

        System.out.println();
    
        passaTurno();
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
        heroi.fimBatalhaReset();

        for (batalhaListener sub : subscribers) {
            sub.onBattleEnd(this, heroi);
        }

        if(heroi.estaVivo()){
            vitoria();
        } else {
            derrota();
        }
    }

    /** troca de turno e se voltar pra vez do heroi chama o passaRodada  */
    public void passaTurno(){
        turno = (turno == 0) ? 1 : 0;

        if (notificaMorte() == 0) {
            return;
        }

        if (turno == 0) passaRodada();
        
        addNovosInimigos();
    }


    public void passaRodada(){
        heroi.resetEfeitos();

        for (Inimigo inimigo : inimigos) {
            inimigo.resetEfeitos();
        }
        
        limpaSubscribers();
        
        for (batalhaListener subscriber : subscribers) {
            Entidade alvo = subscriber.getAlvo();
            if (alvo != null && alvo.estaVivo()){     
                Textos.printaBonito(subscriber.getMsgFimRodada(this, heroi) + "\n", 5,2);
                subscriber.onRoundStart(this, heroi);
                Textos.sleep(sleepFimRodada);
            }
        }
        // se alguma msgfimrodada nao for vazio pula uma linha ( que linha horrenda meu deus do ceu )
        boolean msgPrintada = subscribers.stream().filter(s -> s.getAlvo() != null && s.getAlvo().estaVivo()).anyMatch(subscriber -> !subscriber.getMsgFimRodada(this, heroi).equals(""));
        if (msgPrintada) {
            System.out.println();
        }

        Textos.printaBonito(Cor.txtCinza(Arte.bordaHud9), 2,2);
        Textos.sleep(sleepFimRodada);
        InputHandler.esperar();

        limpaSubscribers();

        if (notificaMorte() == 0) {
            return;
        }

        // notifica possiveis meia vida
        for (Inimigo inimigo : inimigos) {
            inimigo.checkMeiaVida(inimigo, heroi, this);
        }
        heroi.checkMeiaVida(heroi, heroi, this);

        heroi.passaRodada(); // remove os bonus que acabam (escudo, etc) e reseta energia
    }

    /** aplica dano mitigado e notifica o alvo com a vida perdida */
    public int causarDano(Entidade alvo, int dano, Entidade atacante){
        int vidaPerdida = alvo.receberDanoRetornandoVidaPerdida(dano);
        notificarDanoRecebido(alvo, atacante, vidaPerdida);
        return vidaPerdida;
    }

    /** aplica dano direto e notifica o alvo com a vida perdida */
    public int causarDanoDireto(Entidade alvo, int dano, Entidade atacante){
        int vidaPerdida = alvo.receberDanoDiretoRetornandoVidaPerdida(dano);
        notificarDanoRecebido(alvo, atacante, vidaPerdida);
        return vidaPerdida;
    }

    /** notifica onReceivedHit */
    public void notificarDanoRecebido(Entidade alvo, Entidade atacante, int vidaPerdida){
        if (vidaPerdida <= 0) {
            return;
        }

        for (batalhaListener subscriber : subscribers) {
            if (subscriber.getAlvo() == alvo) {
                subscriber.onReceivedHit(this, heroi, atacante, vidaPerdida);
            }
        }
    }

    /** notifica onRemove e remove os subscribers*/
    public void limpaSubscribers(){     
        List<batalhaListener> toRemove = new ArrayList<>();

        for (batalhaListener subscriber : subscribers ) {
            if (subscriber.getRemover()){
                toRemove.add(subscriber);
            }
        }

        for (batalhaListener subscriber : toRemove) {
            subscriber.onRemove(this, heroi);
        }

        subscribers.removeAll(toRemove);

        listaEfeitos.removeIf(efeito -> !subscribers.contains(efeito));
        listaPoderes.removeIf(poder -> !subscribers.contains(poder));
    }

    /** retorna os subscribers da entidade passada */
    public List<batalhaListener> getSubscribers(Entidade alvo) {
        return subscribers.stream()
                .filter(subscriber -> subscriber.getAlvo() == alvo)
                .collect(Collectors.toList());
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
    public void adicionarSubscriber(batalhaListener novoSubscriber) { 
        for (batalhaListener subscriber : subscribers) {
            if (subscriber.addStack(this, novoSubscriber)){
                return;
            }
        }

        this.subscribers.add(novoSubscriber);
        novoSubscriber.onCreate(this, heroi);
        
        // coloca os subscribers que afetam o heroi por ultimo e depois ordenam pela prioridade.
        this.subscribers.sort(
            Comparator.comparing((batalhaListener subscriber) -> subscriber.getAlvo() == heroi)
                .thenComparing(batalhaListener::getPrioridade)
        );

        /** adiciona nas listas correspondentes, pra printar o estado da batalha corretamente */
        if (novoSubscriber instanceof Efeito e)
            this.listaEfeitos.add(e);
        else if (novoSubscriber instanceof Poder p)
            this.listaPoderes.add(p);
    }

    public void adicionarSubscribers(List<? extends batalhaListener> _subscribers){
        for (batalhaListener item : _subscribers) {
            adicionarSubscriber(item);
        }
    }
    

    /** avisa os efeitos com aplicação quando o alvo morre, antes de remover da lista de inimigos
     *  e retorna o tamanho da lista de inimigos.
     */
    public int notificaMorte(){

        // ve se morreu todo mundo ou se so sobraram inimigos passivos e ja retorna
        boolean todosMortos = true;
        for (Inimigo inimigo : inimigos) {
            if (inimigo.estaVivo() && !inimigo.getPassivo()) todosMortos = false;
        }

        if (todosMortos) {
            if (inimigos.stream().anyMatch(Inimigo::estaVivo)) {
                Textos.limpaTela();
                Cor.printaAmarelo("Todos os inimigos agressivos foram mortos!");
                InputHandler.esperar();
            }
            fimBatalha = true;
            return 0;
        }

        for (Inimigo i : inimigos) {
            if (!i.estaVivo()){
                for (batalhaListener subscriber : subscribers) {
                    subscriber.onDeath(this, i);
                }
            }
        } 

        inimigos.removeIf(inimigo -> inimigo.estaVivo() == false);

        addNovosSubscribers();

        return inimigos.size();
    }

    public void rodadaBonus(){
        Textos.sobeTela();

        Textos.printaLinhaDevagar(Cor.rosa + (Arte.bonus).repeat(15) + Cor.reset);

        heroi.ganhaEnergia(2);
        InputHandler.esperar();
        mao.addCinco(pilhaCompra, pilhaDescarte);
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
        for (batalhaListener subscriber : subscribers)
            subscriber.onHit(cartaEscolhida, heroi, alvoSelecionado, this);

        if (notificaMorte() == 0) {
            return 0;
        }

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

    /** exibe a mensagem de vitória e cuida da recompensa da batalha */
    public void vitoria(){
        String arteVitoria = Textos.colorirPartes(Arte.venceu2, Cor.amareloClaro, Cor.laranja, 5);
        Textos.printaLinhaDevagar(arteVitoria);
        System.out.println();
        InputHandler.esperar();
        Recompensas.ganharDinheiro(this.recompensa, heroi);
        Recompensas.ganharCarta(1, heroi);

        // recompensa baseada na dificuldade total da batalha
        switch (getNivelDificuldade()) {
            case 2 -> Recompensas.ganharOpcoes(1, 3, heroi);
            case 3 -> Recompensas.ganharCartas(2, 3, heroi);
            case 4 -> {
                Recompensas.ganharCartas(2, 3, heroi);
                Recompensas.ganharOpcoes(3, 5, heroi);
            }
            case 5 -> {
                Recompensas.ganharCartas(3, 5, heroi);
                Recompensas.ganharOpcoes(4, 3, heroi);
            }
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
        if (inimigos.stream().anyMatch(inimigo -> inimigo.getTier() == 5)) {
            return 5; // BOSS
        }
        else if (dificuldadeTotal < 3) {
            return 1; // trivial
        }
        else if (dificuldadeTotal < 6) {
            return 2; // normal
        }
        else if (dificuldadeTotal <= 7) {
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
            case 5 -> retorno += " <" + Cor.roxo + "BOSS" + Cor.reset + ">";
        }

        retorno += Cor.txtCinza(" VERSUS:");

        if (util.RNGHandler.check(30) && getNivelDificuldade() < 3) {
            return Cor.txtCinza("[ ? ]");
        }

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
