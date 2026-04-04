package Telas.Eventos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import Cartas.Carta;
import Cartas.CartaAtaqueComEfeito;
import Cartas.CartaPoder;
import Deck.Mao;
import Deck.PilhaCompra;
import Deck.PilhaDescarte;
import EfeitosDeStatus.Buffs.AumentaDano;
import EfeitosDeStatus.DanosConstantes.DanoConstante;
import EfeitosDeStatus.DanosConstantes.Sangramento;
import EfeitosDeStatus.DanosConstantes.Veneno;
import EfeitosDeStatus.Efeito;
import EfeitosDeStatus.Instantaneos.Escudo;
import EfeitosDeStatus.Instantaneos.Purificar;
import Entidades.Entidade;
import Entidades.Heroi;
import Entidades.Inimigos.Inimigo;
import Poderes.Poder;
import Util.Arte;
import Util.Cor;
import Util.InputHandler;
import Util.Textos;

public class Batalha extends Evento {
    private int turno; // 0 -> heroi, 1 -> inimigos
    private Inimigo[] arrayInimigos; // inimigos em forma de array pq infelizmente as vezes precisa :(
    private List<Inimigo> inimigos; // inimigos em forma de lista pq é bom !

    private Mao mao = new Mao();
    private PilhaCompra pilhaCompra = new PilhaCompra();
    private PilhaDescarte pilhaDescarte = new PilhaDescarte();
    private PilhaDescarte pilhaPoderes = new PilhaDescarte(); // <- cartas de poder vem pra cá! (so da pra usar 1 vez por copia)

    // subscribers --------
    private ArrayList<Efeito> listaEfeitos = new ArrayList<>(); 
    private ArrayList<Poder> listaPoderes = new ArrayList<>();
    // -------------

    Scanner ler = InputHandler.getLeitor();

    public Batalha(Inimigo... _inimigos){
        this.arrayInimigos = _inimigos;
        inimigos = new ArrayList<>(Arrays.asList(arrayInimigos)); // converte o array inimigos em arraylist para facilitar a manipulação.
    }

    // Carta generica (ta servindo pro veneno)
    Carta c;

    //efeitos de molde enquanto nao tem o json (esses aq sao pros inimigos)
    Efeito feridas = new DanoConstante("Feridas", "Causa 1 de dano por rodada ao alvo por 2 rodadas", 2, 1);
    Efeito sangramento = new Sangramento("Sangramento", "Causa 1 de dano por rodada ao alvo", 3, 1);
    Efeito pactoSinistro = new AumentaDano(Cor.txtCinza("Pacto Sinistro"), "Aumenta o dano causado em 2 por 2 rodadas", 2, 2);
    Efeito escudinho = new Escudo("Escudinho", "3 pontos de escudo", 0, 3);
    Efeito escudao = new Escudo("Escudinho", "7 pontos de escudo", 0, 7);

    

    // recebe heroi, define as variáveis e chama a classe principal.
    @Override
    public void iniciar(Heroi heroi){
        this.heroi = heroi;
        pilhaCompra.addBaralho(heroi.getBaralho()); // pilha de compras recebe o baralho do heroi e embaralha
        pilhaCompra.shuffleAll(pilhaDescarte);
        
        // resetando os bonus do heroi q possam ter sobrado da rodada passada
        heroi.passaRodada();
        heroi.resetEfeitos();

        turno = 0; // 0: turno do heroi 
        
        for (Inimigo inimigo : inimigos) 
            inimigo.escolheAcao();

        batalha();
    }

    /* reseta os bonus do heroi, notifica os efeitos, printa os q precisarem, notifica possiveis mortes,
     limpa os efeitos que ja acabaram, notifica os poderes e esvazia a mao. */
    public void passaRodada(){
        heroi.passaRodada(); // remove os bonus que acabam (escudo, etc) e reseta energia
        heroi.resetEfeitos();

        boolean efeitoPrintado = false;
        boolean linhaCimaPrintada = false;

        for (Inimigo inimigo : arrayInimigos) {
            inimigo.passaRodada();
            inimigo.resetEfeitos();
        }
        
        limpaEfeitos();
        
        for (Efeito efeito : listaEfeitos) {  // notifica os efeitos
            if (efeito.getAlvo().estaVivo()){
                if (efeito instanceof DanoConstante) efeitoPrintado = true; // no momento so os efeito danoconstante tao printando, se mudar atualizar aqui!
                if (efeitoPrintado && !linhaCimaPrintada){
                    Textos.printaBonito(Cor.txtCinza( "\n" + Arte.bordaHud9), 2,2); Textos.sleep(300);
                    linhaCimaPrintada = true;
                }
                efeito.aplicar();
                efeito.passaTurno(); 
            }
        }

        if (efeitoPrintado){
        Textos.printaBonito(Cor.txtCinza( "\n" + Arte.bordaHud9), 2,2); Textos.sleep(300);
        InputHandler.esperar();
        }

        notificaMorte();
        limpaEfeitos();

        for (Poder poder : listaPoderes) // notifica os poderes
            poder.aplicar();
    }

    public void passaTurno(){
        turno = (turno == 0) ? 1 : 0;
        notificaMorte();
        if (turno == 0) passaRodada();
    }

    public void limpaEfeitos(){     
        for (Efeito efeito : listaEfeitos ) {
            if ((efeito.getDur() <= 0 || efeito.getStacks() <= 0 ||efeito.getAlvo().getPurificar() == true) && !(efeito instanceof Purificar)){
                efeito.acabar();
            }
        }
        listaEfeitos.removeIf(efeito -> (efeito.getDur() <= 0 || efeito.getStacks() <= 0 || efeito.getAlvo().getPurificar() == true) && !(efeito instanceof Purificar));

        for (Efeito efeito : listaEfeitos ) {
            if ((efeito instanceof Purificar)){
                efeito.acabar();
            }
        }
        listaEfeitos.removeIf(efeito -> (efeito instanceof Purificar));
    }

    public int selecionarAlvo(){  // falta fazer uma opçao pra voltar caso ele mude de ideia sobre a carta!
        int opcao = -1;
        while (true) { 
            int i = 0;
            System.out.println();
            Cor.printaAmarelo("Selecione o alvo:");
            System.out.println();

            for (Inimigo inimigo : inimigos) {
                if (inimigo.estaVivo()){
                    Textos.sleep(30);
                    System.out.println((""+i+" - "+inimigo.getNomeColorido()+""));
                    i++;
                }
            }

            try {
                opcao = ler.nextInt();
                ler.nextLine();
            } catch (Exception e) {
                ler.nextLine();  
            }

            if (opcao >= 0 && opcao < inimigos.size() && inimigos.get(opcao).estaVivo()) 
                break;

            System.out.println();
            Cor.printaAmarelo(Textos.escolhaInvalida(i-1));

            InputHandler.esperar();

            Textos.apagarLinhas(i + 8);
        }
        return opcao;
    }

    public void adicionarEfeito(Efeito efeito){

            // ESPAÇO PRA SETAR AS FLAGS!

            if (efeito instanceof Sangramento s){ // se for sangramento deixa o nomezinho vermelho
                s.getAlvo().setSangrando(true);
            }

            if (efeito instanceof Veneno v){ // se for veneno deixa o nomezinho verde
                v.getAlvo().setEnvenenado(true);
            }

            // FIM DO ESPAÇO PARA SETAR AS FLAGS
            
        for (Efeito e : listaEfeitos) {

            // se tiver outro efeito q espalha ou passa copia sozinho bota uma checagem aq tb pra nao duplicar
            boolean doisVeneno = e instanceof Veneno && efeito instanceof Veneno; 

            if ((e.getNome().equals(efeito.getNome()) || doisVeneno) && e.getAlvo() == efeito.getAlvo()){

                // efeitos q resetam duraçao ao inves de somar
                if (e.getResetDur()){ 
                    e.setDur(efeito.getDur());      
                }
                
                // efeitos q somam (padrao)
                else e.setDur(e.getDur() + efeito.getDur());

                e.addStack();
                return;
            }
        }
        this.listaEfeitos.add(efeito);

        efeito.onCreate();

        this.listaEfeitos.sort(Comparator.comparing(Efeito::getPrioridade)); // ordena os efeitos por prioridade pra sangramento sobrepor resistencia
    }

    public void adicionarPoder(Poder poder){
        for (Poder e : listaPoderes) {
            if (e.getNome().equals(poder.getNome())){
                e.stackar();
                return;
            }
        }
        this.listaPoderes.add(poder);
    }

    public void notificaMorte(){

        // ve se morreu todo mundo e ja retorna
        boolean todosMortos = true;
        for (Inimigo inimigo : inimigos) {
            if (inimigo.estaVivo()) todosMortos = false;
        }
        if (todosMortos) return;

        // avisa os efeitos com aplicação quando o alvo morre, antes de remover da lista de inimigos
        boolean venenoPrintado = false;
        List<Efeito> tempEfeitos = new ArrayList<>();
        for (Inimigo i : inimigos) {
            if (!i.estaVivo()){
                for (Efeito efeito : listaEfeitos) {
                    // veneno < ----
                    if (efeito instanceof Veneno && efeito.getAlvo() == i){
                        for (Inimigo inimigo2 : inimigos) {
                            if (inimigo2.estaVivo()) {
                                Efeito copia = efeito.criaCopia();
                                efeito.onHit(c, heroi, inimigo2, this); // usei uma carta generica pq nao importa pro onhit mas precisa passar
                                copia.setAlvo(inimigo2);
                                tempEfeitos.add(copia);                                
                            }
                            if (!venenoPrintado){
                                Textos.printaLinhaDevagar(Cor.txtVerdeEscuro(Arte.TOXICO));
                                InputHandler.esperar();
                                venenoPrintado = true;
                            }
                        }
                    }
                    // ----------
                }
            }
        }
        for (Efeito temp : tempEfeitos) {
            this.adicionarEfeito(temp);
        }   

        inimigos.removeIf(inimigo -> inimigo.estaVivo() == false);
    }

    public void turnoHeroi(){
        limpaEfeitos();
        heroi.resetarEnergia();

        mao.addCinco(pilhaCompra, pilhaDescarte);
        boolean primeiroLoop = true;
        boolean escolhaInvalida = false;

            while(true){ // loop da escolha de ação
                Textos.limpaTela();

                if (primeiroLoop){
                    Textos.batalha(heroi, listaEfeitos, listaPoderes, arrayInimigos);
                    primeiroLoop = false;
                } else {
                    if (escolhaInvalida){
                        System.out.println();
                        System.out.println(Textos.escolhaInvalida(mao.getSize()));
                        InputHandler.esperar();
                        Textos.limpaTela();
                        escolhaInvalida = false;  
                    }
                    Textos.batalhaSemDelay(heroi, listaEfeitos, listaPoderes, arrayInimigos);
                }

                // System.out.println("resist extra" + heroi.getResistencia());
                // System.out.println("dano extra" + heroi.getDanoExtra());

                int escolha = mao.mostrar(); 
                if (escolha > mao.getSize() || escolha < 0){ 
                    escolhaInvalida = true;
                    continue;
                }

                // chegou aqui -> opçao de carta válida -> confere se não tem energia suficiente ( mas vou deixa essa linha conferindo ai msm pra evita bug sla )
                if (escolha < mao.getSize() && escolha >= 0){
                    Carta cartaEscolhida = mao.escolheCarta(escolha); 
                    if (!cartaEscolhida.podeGastar(heroi)){
                        System.out.println();
                        System.out.println("Energia insuficiente");
                        System.out.println();
                        continue;
                    } 

                    // poderes nao voltam pra mao depois de usados
                    if (cartaEscolhida instanceof CartaPoder)
                        mao.removeCarta(escolha, pilhaPoderes);
                    else 
                        mao.removeCarta(escolha, pilhaDescarte);

                    Entidade alvoSelecionado = heroi; // se nao mudar é pq é o heroi msm

                    // se não for selfcast ou poder pergunta o alvo, ataque com efeito selfcast ataca o alvo e aplica o efeito em si mesmo.
                    if ((cartaEscolhida.getSelfCast() || cartaEscolhida instanceof CartaPoder) && !(cartaEscolhida instanceof CartaAtaqueComEfeito)) {
                        if (cartaEscolhida.temResenha())
                            Textos.limpaTela();
                        cartaEscolhida.usar(heroi, heroi, this);
                    } else {
                        int alvo = selecionarAlvo();
                        alvoSelecionado = inimigos.get(alvo);
                        if (cartaEscolhida.temResenha())
                            Textos.limpaTela();
                        cartaEscolhida.usar(heroi, alvoSelecionado, this); 
                    }

                    // notifica os efeitos com on hit
                    for (Efeito efeito : listaEfeitos)
                        if (efeito.getOnHit()){
                            efeito.onHit(cartaEscolhida, heroi, alvoSelecionado, this);
                        }

                    // notifica os poderes com on hit
                    for (Poder poder : listaPoderes) 
                        poder.onHit(cartaEscolhida, heroi, alvoSelecionado, this); 

                    // limpeza de efeitos esgotados    
                    limpaEfeitos();
                    notificaMorte();

                    if (!inimigos.stream().anyMatch(i -> i.estaVivo() == true)) break;

                    // se conseguir usar todas as cartas da mao puxa mais 5 e ganha 2 de energia bonus, injeçao de dopamina assim q vc mantem um jogador
                    if (mao.getSize() == 0){
                        Textos.limpaTela();
                        Textos.printaLinhaDevagar(Cor.rosa + Arte.cincoCartas + Cor.reset);
                        Textos.printaLinhaDevagar(Cor.rosa + Arte.doisEnergia + Cor.reset);
                        heroi.ganhaEnergia(2);
                        InputHandler.esperar();
                        mao.addCinco(pilhaCompra, pilhaDescarte);
                    }

                } else if (escolha == mao.getSize()) break;
            }            
        mao.limpa(pilhaDescarte);    
        passaTurno();
    }

    public void turnoInimigos(){
        Textos.limpaTela();
        Textos.printaBonito(Cor.txtCinza(Arte.bordaHud9), 2,2); Textos.sleep(300);

        for (Inimigo inimigo : arrayInimigos) {
            if (inimigo.estaVivo()){ // adicionei isso pq joguei uma partida aqui e tomei hit de um inimigo morto.
                int acao = inimigo.getNextAcao();
                inimigo.ataqueRealizado(heroi);
                switch (acao){
                    case 0 -> inimigo.atacar(heroi);
                    case 1 -> inimigo.atacarEfeito(heroi, this, sangramento);
                    case 2 -> {
                        inimigo.receberDano(2);
                        inimigo.receberEfeito(this, pactoSinistro);
                    }
                }
                inimigo.escolheAcao(); // escolhe prox ação
            }
        }
        
        Textos.printaBonito(Cor.txtCinza("\n" + Arte.bordaHud9), 2,2); Textos.sleep(300);
        InputHandler.esperar();
        passaTurno();
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

        if(!heroi.estaVivo() == false){
            Textos.printaLinhaDevagar(Arte.venceu2);
            System.out.println();
            InputHandler.esperar();
        } else {
            System.out.println();
            Textos.printaLinhaDevagar(Cor.txtCinza(Arte.sans2));
            System.out.println();
            System.out.println();
            Textos.printaLinhaDevagar(Cor.txtVermelho(Arte.VOCEMORREU));
            System.out.println();
            InputHandler.esperar();
            System.exit(0);
        }
    }

    @Override
    public String toString() {
        String retorno = Cor.txtVermelho("Batalha") + Cor.txtCinza(" VERSUS");

        for (Inimigo inimigo : arrayInimigos) {
            retorno += " [ " + inimigo.getNome() + " ]";
        }
        retorno += "\n";
        return retorno;
    }
}
