package Telas;
import Deck.*;
import Entidades.*;

import java.util.*;

public class Batalha {

    // receber pilha de compra(deck atual), heroi e inimigos
    public static void iniciar(Heroi heroi, PilhaCompra pilhaCompra, Inimigo... _inimigos){

        List<Inimigo> inimigos = new ArrayList<Inimigo>(Arrays.asList(_inimigos)); // converte o array inimigos em arraylist para facilitar a manipulação.
        Scanner ler = new Scanner(System.in);
        int turno = 0; // 0: turno do heroi 

        for (int i = 0; i < 5; i++) // adiciona 5 cartas à mão no começo da batalha
            Mao.addCarta();

        while(heroi.estaVivo() == true && inimigos.stream().anyMatch(i -> i.estaVivo() == true)){ // checa se o heroi ou ao menos um inimigo esta vivo
                
            Textos.sleep(500); // delay no começo do turno
            
            Textos.batalha(heroi, _inimigos);
            
            if (turno == 0){
                System.out.println(heroi.statusEnergia());
                int escolha = Mao.mostrar(); 

                while(true){
                    if (escolha < 5 && escolha >= 0){
                        Mao.escolheCarta(escolha);
                        Textos.batalha(heroi, _inimigos);
                        break;
                    } else if (escolha == 5) {
                        turno++;
                        Textos.batalha(heroi, _inimigos);
                        break;
                    } 
                    
                    System.out.println("Valor inválido. Escolha novamente.");
                    
                    Telas.Textos.sleep(700);
                }
            } else {
                for (int i = 0 ; i < inimigos.size() ; i++) inimigos.get(i).atacar(heroi); // por enquanto eles so atacam
            }
        }

        ler.close();

        System.out.println("DUELO ENCERRADO!");
        System.out.println();
        Textos.sleep(1500);
        if(heroi.estaVivo() == false)
            System.out.println("VOCÊ MORREU");

        else System.out.println("VOCÊ RECUPEROU O PÉROLA NEGRA!");
        System.out.println();
    }
}
