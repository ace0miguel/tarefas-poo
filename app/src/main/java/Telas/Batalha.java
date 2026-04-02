package Telas;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Cartas.Carta;
import Cartas.CartaAtaqueComEfeito;
import Cartas.CartaPoder;
import Deck.Mao;
import Deck.PilhaCompra;
import Deck.PilhaDescarte;
import EfeitosDeStatus.AumentaDano;
import EfeitosDeStatus.DanosConstantes.DanoConstante;
import EfeitosDeStatus.DanosConstantes.Sangramento;
import EfeitosDeStatus.DanosConstantes.Veneno;
import EfeitosDeStatus.Efeito;
import EfeitosDeStatus.Escudo;
import Entidades.Entidade;
import Entidades.Heroi;
import Entidades.Inimigo;
import Poderes.Poder;
import Util.Arte;
import Util.Cor;
import Util.InputHandler;
import Util.Textos;

public class Batalha {

    // Carta generica
    Carta c;

    //efeitos de molde enquanto nao tem o json (esses aq sao pros inimigos)
    Efeito feridas = new DanoConstante("Feridas", "Causa 1 de dano por rodada ao alvo por 2 rodadas", 2, 1);
    Efeito pactoSinistro = new AumentaDano(Cor.txtCinza("Pacto Sinistro"), "Aumenta o dano causado em 2 por 2 rodadas", 2, 2);
    Efeito escudinho = new Escudo("Escudinho", "3 pontos de escudo", 0, 3);
    Efeito escudao = new Escudo("Escudinho", "7 pontos de escudo", 0, 7);

    // subscribers --------
    private ArrayList<Efeito> listaEfeitos = new ArrayList<>(); 
    private ArrayList<Poder> listaPoderes = new ArrayList<>();
    // -------------

    private int turno; // 0 -> heroi, 1 -> inimigos
    private Heroi heroi;
    private Mao mao;
    private PilhaCompra pilhaCompra;
    private Inimigo[] arrayInimigos; // inimigos em forma de array pq infelizmente as vezes precisa :(
    private List<Inimigo> inimigos; // inimigos em forma de lista pq é bom !
    private PilhaDescarte pilhaDescarte = new PilhaDescarte();
    private PilhaDescarte pilhaPoderes = new PilhaDescarte(); // <- cartas de poder vem pra cá!
    Scanner ler = InputHandler.getLeitor();

    // recebe pilha de compra(deck atual), heroi e inimigos, define as variáveis e chama a classe principal.
    public void iniciar(Heroi _heroi, PilhaCompra _pilhaCompra, Inimigo... _inimigos){
        
        arrayInimigos = _inimigos;
        inimigos = new ArrayList<Inimigo>(Arrays.asList(_inimigos)); // converte o array inimigos em arraylist para facilitar a manipulação.
        heroi = _heroi;
        pilhaCompra = _pilhaCompra;
        mao = new Mao();
        
        pilhaCompra.shuffleAll(pilhaDescarte);
        
        turno = 0; // 0: turno do heroi 
        
        for (Inimigo inimigo : inimigos) 
            inimigo.escolheAcao();

        batalha();
    }

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
                    Cor.printaAmarelo("- = - = - = - = - = - = - = - = - = - = - = - = -\n\n"); Textos.sleep(300);
                    linhaCimaPrintada = true;
                }
                efeito.aplicar();
                efeito.passaTurno(); 
            }
        }

        if (efeitoPrintado){
        Cor.printaAmarelo("\n- = - = - = - = - = - = - = -\n"); Textos.sleep(300);
        Cor.printaAmarelo("- = - = - = - = - = -\n"); Textos.sleep(300);
        InputHandler.esperar();
        }

        notificaMorte();
        limpaEfeitos();

        for (Poder poder : listaPoderes) // notifica os poderes
            poder.aplicar();

        mao.limpa(pilhaDescarte);
    }

    public void passaTurno(){
        turno = (turno == 0) ? 1 : 0;
        notificaMorte();
        if (turno == 0) passaRodada();
    }

    public void limpaEfeitos(){
        listaEfeitos.removeIf(efeito -> efeito.getDur() <= 0);
        listaEfeitos.removeIf(efeito -> efeito instanceof Sangramento && ((Sangramento) efeito).getStacks() <= 0);
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
            System.out.println("Tem que ser um número de 0 a "+(i-1)+", capitão!!");

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

            if ((e.getNome().equals(efeito.getNome()) || doisVeneno) && e.getAlvo() == efeito.getAlvo() && !e.getInsta()){
                // aqui entram os efeitos que possuem excessoes especificas no momento de aplicar repetidamente
                if (e instanceof Sangramento s){ 
                    s.setDur(efeito.getDur());
                    s.addStack();     
                    s.getAlvo().setSangrando(true);           
                    return;
                }
                e.setDur(e.getDur() + efeito.getDur());
                return;
            }
        }
        this.listaEfeitos.add(efeito);
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
                                Textos.printaLinhaDevagar(Cor.txtVerde(Arte.TOXICO));
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
                        System.out.println("Tem que ser um número de 0 a "+mao.getSize()+", capitão!!");
                        InputHandler.esperar();
                        Textos.limpaTela();
                        escolhaInvalida = false;  
                    }
                    Textos.batalhaSemDelay(heroi, listaEfeitos, listaPoderes, arrayInimigos);
                }

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

                    // energia suficiente -> notifica os efeitos no heroi -> executa a carta
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
                            efeito.updateOnHit();
                        }

                    // notifica os poderes com on hit
                    for (Poder poder : listaPoderes) 
                        poder.onHit(cartaEscolhida, heroi, alvoSelecionado, this); 

                    // lida com efeitos de uso instantaneo, como escudo ou purificar.
                    for (Efeito efeito : listaEfeitos) {
                        if (efeito.getInsta()){
                                efeito.aplicar(); 
                                efeito.setInsta(false);
                        }
                    }

                    listaEfeitos.removeIf(efeito -> efeito.getDur() <= 0 || efeito.getAlvo().getPurificar() == true);

                    notificaMorte();

                    if (!inimigos.stream().anyMatch(i -> i.estaVivo() == true)) break;

                    if (mao.getSize() == 0) mao.addCinco(pilhaCompra, pilhaDescarte); // se a mão esvaziar compra 5

                } else if (escolha == mao.getSize()) break;
            }                
        passaTurno();
    }

    public void turnoInimigos(){
        Textos.limpaTela();
        Cor.printaAmareloClaro("- = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = -\n"); Textos.sleep(300);
        Cor.printaAmareloClaro("- = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = -\n \n"); Textos.sleep(300);

        for (Inimigo inimigo : arrayInimigos) {
            int acao = inimigo.getNextAcao();
            switch (acao){
                case 0 -> inimigo.atacar(heroi);
                case 1 -> inimigo.atacarEfeito(heroi, this, feridas);
                case 2 -> {
                    inimigo.receberDano(2);
                    inimigo.receberEfeito(this, pactoSinistro);
                }
            }
            inimigo.ataqueRealizado();
            inimigo.escolheAcao(); // escolhe prox ação
        }
        
        Cor.printaAmareloClaro("\n- = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = -\n"); Textos.sleep(300);
        InputHandler.esperar();
        passaTurno();
    }

    public void batalha(){
        // loop principal: checa se o heroi ou ao menos um inimigo esta vivo
        while(heroi.estaVivo() == true && inimigos.stream().anyMatch(i -> i.estaVivo() == true)){

            if (turno == 0) turnoHeroi();
            else turnoInimigos();
        }
        // fim de batalha

        System.out.println();
        Cor.printaAmarelo("DUELO ENCERRADO!\n");
        System.out.println();
        Textos.sleep(1500);
        Textos.limpaTela();
        if(!heroi.estaVivo() == false){
            Textos.printaLinhaDevagar(Cor.txtRosa("VOCÊ RECUPEROU O PÉROLA NEGRA!"));
            Textos.printaLinhaDevagar(Arte.PEROLANEGRA);
        } else {
            Cor.printaVermelho("VOCÊ MORREU");
            System.out.println();
            Arte.printSans();
        }
        System.out.println();
    }
}
