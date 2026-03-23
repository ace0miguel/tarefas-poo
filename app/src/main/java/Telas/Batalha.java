package Telas;
import Deck.*;
import Entidades.*;
import Handlers.InputHandler;

import java.util.*;
//import EfeitosDeStatus.Efeito;
import Cartas.Carta;

public class Batalha {

    // receber pilha de compra(deck atual), heroi e inimigos
    public static void iniciar(Heroi heroi, PilhaCompra pilhaCompra, Inimigo... _inimigos){
        PilhaDescarte pilhaDescarte = new PilhaDescarte();
        
        List<Inimigo> inimigos = new ArrayList<Inimigo>(Arrays.asList(_inimigos)); // converte o array inimigos em arraylist para facilitar a manipulação.
        Scanner ler = InputHandler.getLeitor();
        Mao mao = new Mao(ler);
        
        pilhaCompra.shuffleAll(pilhaDescarte);
        
        int turno = 0; // 0: turno do heroi 
        
        while(heroi.estaVivo() == true && inimigos.stream().anyMatch(i -> i.estaVivo() == true)){ // checa se o heroi ou ao menos um inimigo esta vivo
            
            //heroi.resetarEnergia();
            Textos.sleep(500); // delay no começo do turno
            
            if (turno == 0){
                Textos.batalha(heroi, _inimigos);

                mao.addCinco(pilhaCompra, pilhaDescarte);

                while(true){ // loop da escolha de ação
                    
                    inimigos.getFirst().anunciarAtaque(); // anuncia a intencao

                    System.out.println();
                    System.out.println(heroi.statusEnergia()); 
                    System.out.println();

                    int escolha = mao.mostrar(); 
                    if (escolha < mao.getSize() && escolha >= 0){
                        Carta cartaEscolhida = mao.escolheCarta(escolha); 
                        if (cartaEscolhida.podeGastar(heroi)){//confere se tem energia
                            mao.removeCarta(escolha, pilhaDescarte);
                            cartaEscolhida.usar(heroi, inimigos.getFirst()); // por enquanto só tem um inimigo
                            
                            if (mao.getSize() == 0) mao.addCinco(pilhaCompra, pilhaDescarte);

                        } else {
                            System.out.println();
                            System.out.println("Energia insuficiente");
                            System.out.println();
                            continue;
                        }

                    } else if (escolha == mao.getSize()) {
                        turno = 1;
                        Textos.sleep(500);
                        System.out.println();   
                        System.out.println("O inimigo te atacou!");
                        System.out.println();
                        for(Inimigo inimigo : inimigos){
                            inimigo.passaTurno();
                        }    

                        mao.limpa(pilhaDescarte);
                        break;
                    } 
                    
                    else {
                        System.out.println();
                        System.out.println("Valor inválido. Escolha novamente.");
                        System.out.println();
                    }

                    Telas.Textos.sleep(700);
                }
            } 
            else {
                for (int i = 0 ; i < inimigos.size() ; i++) {
                    Inimigo inimigoAtual = inimigos.get(i);
                    inimigoAtual.escolheAcao();
                    if (inimigoAtual.getNextAcao() == 0) {
                        inimigoAtual.atacar(heroi);   
                    } else inimigoAtual.setUsaEscudo(true);
                }

                heroi.resetarEscudo();
                heroi.resetarEnergia();
                turno = 0;
            }
        }

        System.out.println();
        System.out.println("DUELO ENCERRADO!");
        System.out.println();
        Textos.sleep(1500);
        if(heroi.estaVivo() == false)
            System.out.println("VOCÊ MORREU");
        
        else System.out.println("VOCÊ RECUPEROU O PÉROLA NEGRA!");
        System.out.println();

        ler.close();
    }
}
