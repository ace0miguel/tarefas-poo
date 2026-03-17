package Telas;
import Deck.*;
import Entidades.*;

import java.util.*;

import Cartas.Carta;

public class Batalha {

    // receber pilha de compra(deck atual), heroi e inimigos
    public static void iniciar(Heroi heroi, PilhaCompra pilhaCompra, Inimigo... _inimigos){
        PilhaDescarte pilhaDescarte = new PilhaDescarte();
        
        List<Inimigo> inimigos = new ArrayList<Inimigo>(Arrays.asList(_inimigos)); // converte o array inimigos em arraylist para facilitar a manipulação.
        Scanner ler = new Scanner(System.in);
        Mao mao = new Mao(ler);
        
        pilhaCompra.shuffleAll(pilhaDescarte);
        for (int i = 0; i < 5; i++) // adiciona 5 cartas à mão no começo da batalha
            mao.addCarta(pilhaCompra);
        
        int turno = 0; // 0: turno do heroi 
        
        while(heroi.estaVivo() == true && inimigos.stream().anyMatch(i -> i.estaVivo() == true)){ // checa se o heroi ou ao menos um inimigo esta vivo
            
            //heroi.resetarEnergia();
            Textos.sleep(500); // delay no começo do turno
            Textos.batalha(heroi, _inimigos);
            
            if (turno == 0){
                System.out.println(heroi.statusEnergia()); 
                
                while(true){
                    int escolha = mao.mostrar(); 
                    if (escolha < 5 && escolha >= 0){
                        Carta cartaEscolhida = mao.escolheCarta(escolha); //aqui ja remove a carta
                        if (cartaEscolhida.podeGastar(heroi)){//confere se tem energia
                            mao.removeCarta(escolha);
                            cartaEscolhida.usar(heroi, inimigos.getFirst()); // tem q ver isso aqui, qual inimigo atacar, botei sempre o primeiro
                            mao.addCarta(pilhaCompra);
                        }
                        else {
                            System.out.println("Energia insuficiente");
                            continue;
                        }
                        //Textos.batalha(heroi, _inimigos);  //esse nao precisa eu acho, no terminal fica duplicado
                        break;
                    } else if (escolha == 5) {
                        turno = 1;
                        Textos.sleep(500);
                        System.out.println();
                        System.out.println("O inimigo te atacou!");
                        System.out.println();
                        //Textos.batalha(heroi, _inimigos);
                        break;
                    } 
                    
                    else System.out.println("Valor inválido. Escolha novamente.");
                    
                    Telas.Textos.sleep(700);
                }
            } 
            else {
                for (int i = 0 ; i < inimigos.size() ; i++) inimigos.get(i).atacar(heroi); // por enquanto eles so atacam
                heroi.resetarEscudo();
                heroi.resetarEnergia();
                turno = 0;
            }
        }

        
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
