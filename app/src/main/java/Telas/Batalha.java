package Telas;
import Deck.*;
import Entidades.*;
import Poderes.Poder;
import EfeitosDeStatus.*;
import Util.Arte;
import Util.Cor;
import Util.InputHandler;
import Util.Textos;

import java.util.*;
//import EfeitosDeStatus.Efeito;

import Cartas.Carta;
import Cartas.CartaPoder;

public class Batalha {

    //efeitos de molde enquanto nao tem o json (esses aq sao pros inimigos)
    Efeito feridas = new DanoConstante("Feridas", "Causa 1 de dano por rodada ao alvo", 3, 1);
    Efeito pactoSinistro = new AumentaDano("Pacto Sinistro", "Aumenta o dano causado em 2 por 2 rodadas", 2, 2);
    Efeito escudinho = new Escudo("Escudinho", "3 pontos de escudo", 0, 3);
    Efeito escudao = new Escudo("Escudinho", "7 pontos de escudo", 0, 7);

    // subscribers --------
    private List<Efeito> listaEfeitos = new ArrayList<>(); 
    private List<Poder> listaPoderes = new ArrayList<>();
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
        
        batalha();
    }

    public void passaRodada(){
        heroi.passaRodada(); // remove os bonus que acabam (escudo, etc) e reseta energia

        for (Inimigo inimigo : arrayInimigos) {
            inimigo.passaRodada();
        }

        mao.limpa(pilhaDescarte);
        
        for (Efeito efeito : listaEfeitos) {  // notifica os efeitos
            efeito.aplicar();
            efeito.passaTurno(); 
        }
        listaEfeitos.removeIf(efeito -> efeito.getDur() <= 0);

        for (Poder poder : listaPoderes) // notifica os poderes
            poder.aplicar();
    }

    public void passaTurno(){
        turno = (turno == 0) ? 1 : 0;
        notificaMorte();
        if (turno == 0) passaRodada();
    }

    public int selecionarAlvo(){ // falta adicionar checagem se ta selecionando um alvo valido
        int i = 0;
        System.out.println();
        System.out.println("Selecione o alvo:");
        System.out.println();

        for (Inimigo inimigo : inimigos) {
            if (inimigo.estaVivo()){
                System.out.println((""+i+" - "+inimigo.getNome()+""));
                i++;
            }
        }
        return ler.nextInt();
    }

    public void adicionarEfeito(Efeito efeito){
        for (Efeito e : listaEfeitos) {
            if (e.getNome().equals(efeito.getNome()) && e.getAlvo() == efeito.getAlvo()){
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
        // avisa os efeitos com aplicação quando o alvo morre, antes de remover da lista de inimigos
        List<Efeito> tempEfeitos = new ArrayList<>();
        for (Inimigo i : inimigos) {
            if (!i.estaVivo()){
                for (Efeito efeito : listaEfeitos) {
                    // veneno < ----
                    if (efeito instanceof Veneno && efeito.getAlvo() == i){
                        for (Inimigo inimigo2 : inimigos) {
                            if (inimigo2.estaVivo()) {
                                Efeito copia = efeito.criaCopia();
                                copia.setAlvo(inimigo2);
                                tempEfeitos.add(copia);
                                Cor.printaVerde("O veneno se espalhou!");
                                Textos.sleep(300);
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

            while(true){ // loop da escolha de ação
                Textos.limpaTela();
                Textos.batalha(heroi, listaEfeitos, listaPoderes, arrayInimigos);

                for (Inimigo inimigo : inimigos) {
                    inimigo.escolheAcao();
                    inimigo.anunciarAtaque();
                }

                System.out.println();
                System.out.println(heroi.statusEnergia()); 
                System.out.println();

                int escolha = mao.mostrar(); 

                // carta válida -> confere se não tem energia suficiente
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

                    /*  fiz nao vai servir agora mas talvez seja util em algum momento
                    for (Efeito efeito : listaEfeitos)
                        if (efeito.getAlvo() == heroi) efeito.onHit(cartaEscolhida); */

                    Entidade alvoSelecionado = heroi; // se nao mudar é pq é o heroi msm

                    // se não for selfcast ou poder pergunta o alvo
                    if (cartaEscolhida.getSelfCast() || cartaEscolhida instanceof CartaPoder)
                        cartaEscolhida.usar(heroi, heroi, this);
                    else {
                        int alvo = selecionarAlvo();
                        alvoSelecionado = inimigos.get(alvo);
                        cartaEscolhida.usar(heroi, alvoSelecionado, this); 
                    }

                    for (Poder poder : listaPoderes) // notifica os poderes
                        poder.onHit(cartaEscolhida, heroi, alvoSelecionado, this); 

                    // lida com efeitos de uso instantaneo, como escudo.
                    for (Efeito efeito : listaEfeitos) {
                        if (efeito.getInsta()){
                                efeito.aplicar(); 
                                efeito.setInsta(false);
                        }
                    }

                    listaEfeitos.removeIf(efeito -> efeito.getDur() <= 0 || efeito.getAlvo().getPurificar() == true);

                    notificaMorte();

                    if (mao.getSize() == 0) mao.addCinco(pilhaCompra, pilhaDescarte); // se a mão esvaziar compra 5

                } else if (escolha == mao.getSize()) break;
            }                
        passaTurno();
    }

    public void turnoInimigos(){
        for (Inimigo inimigo : arrayInimigos) {
            int acao = inimigo.getNextAcao();
            switch (acao){
                case 0 -> inimigo.atacar(heroi);
                case 1 -> inimigo.atacarEfeito(heroi, this, feridas);
                case 2 -> {
                    inimigo.receberDano(3);
                    inimigo.receberEfeito(this, pactoSinistro);
                }
            }
            inimigo.escolheAcao(); // escolhe prox ação
        }
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
        System.out.println("DUELO ENCERRADO!");
        System.out.println();
        Textos.sleep(1500);
        if(heroi.estaVivo() == false){
            System.out.println("VOCÊ MORREU");
            Arte.printSans();
        }
        
        else System.out.println("VOCÊ RECUPEROU O PÉROLA NEGRA!");
        System.out.println();
    }
}
