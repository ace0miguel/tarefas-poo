package Telas;
import Deck.*;
import Entidades.*;
import EfeitosDeStatus.*;
import Handlers.InputHandler;

import java.util.*;
//import EfeitosDeStatus.Efeito;

import Cartas.Carta;
import Cartas.CartaAtaque;

public class Batalha {

    private List<Efeito> listaEfeitos = new ArrayList<>(); // age como subscriber
    private int turno;
    private Heroi heroi;
    private Mao mao;
    private PilhaCompra pilhaCompra;
    private Inimigo[] arrayInimigos; // inimigos em forma de array pq infelizmente as vezes precisa
    private List<Inimigo> inimigos; // inimigos em forma de lista pq é bom
    private PilhaDescarte pilhaDescarte = new PilhaDescarte();
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
        heroi.resetarEnergia();
        heroi.resetarBonus();
        mao.limpa(pilhaDescarte);

        for (Efeito efeito : listaEfeitos) {  // notifica os efeitos
            efeito.aplicar();
            efeito.passaTurno(); 
        }
        listaEfeitos.removeIf(efeito -> efeito.getDur() <= 0);
    }

    public void passaTurno(){
        turno = (turno == 0) ? 1 : 0;
        if (turno == 0) passaRodada();
    }

    public int selecionarAlvo(){ // falta adicionar checagem se ta selecionando um alvo valido
        int i = 0;
        System.out.println();
        System.out.println("Selecione o alvo:");
        System.out.println();

        for (Inimigo inimigo : inimigos) {
            System.out.println((""+i+" - "+inimigo.getNome()+""));
            i++;
        }
        return ler.nextInt();
    }

    public void adicionarEfeito(Efeito efeito) {
        this.listaEfeitos.add(efeito);
    }

    public void turnoHeroi(){
        mao.addCinco(pilhaCompra, pilhaDescarte);

            while(true){ // loop da escolha de ação
                Textos.limpaTela();
                Textos.batalha(heroi, arrayInimigos);

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
                    mao.removeCarta(escolha, pilhaDescarte);

                    /*  fiz nao vai servir agora mas talvez seja util em algum momento
                    for (Efeito efeito : listaEfeitos)
                        if (efeito.getAlvo() == heroi) efeito.onHit(cartaEscolhida); */
                    
                    // se for carta ataque ele pede pra escolher um inimigo, falta generalizar tambem.
                    if (cartaEscolhida instanceof CartaAtaque)
                        cartaEscolhida.usar(heroi, inimigos.get(selecionarAlvo()), this); 
                    else
                         cartaEscolhida.usar(heroi, inimigos.getFirst() , this);
                    
                    inimigos.removeIf(inimigo -> inimigo.estaVivo() == false);

                    if (mao.getSize() == 0) mao.addCinco(pilhaCompra, pilhaDescarte); // se a mão esvaziar compra 5

                } else if (escolha == mao.getSize()) break;
            }                
        passaTurno();
    }

    public void turnoInimigos(){
        for (Inimigo inimigo : arrayInimigos) {
            int acao = inimigo.getNextAcao();
            if (acao == 0) inimigo.atacar(heroi);
            else inimigo.atacarEfeito(heroi, this);

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
        if(heroi.estaVivo() == false)
            System.out.println("VOCÊ MORREU");
        
        else System.out.println("VOCÊ RECUPEROU O PÉROLA NEGRA!");
        System.out.println();
    }
}
