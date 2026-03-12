package Telas;
import Cartas.*;
import Deck.*;
import Entidades.*;

import java.util.*;


public class Batalha {

    public static void iniciar(Heroi heroi, Carta cartaDano, Carta cartaEscudo, Inimigo... _inimigos){

        List<Inimigo> inimigos = new ArrayList<>(Arrays.asList(_inimigos)); // converte o array inimigos em arraylist para facilitar a manipulação.

        Scanner ler = new Scanner(System.in);

        //<-- AQUI: FALTA EMBARALHAR E TALS

        while(heroi.estaVivo() == true && inimigos.stream().anyMatch(i -> i.estaVivo() == true)){ // checa se o heroi ou ao menos um inimigo esta vivo
                
            Titulo.sleep(500); // delay no começo do turno

            int turno = 0; // 0: turno do heroi (talvez em algum momento o heroi possa não começar primeiro, talvez tenha que ser ajustado)
            
            Titulo.batalha(heroi, _inimigos);
            
            if (turno == 0){
                System.out.println(heroi.statusEnergia());
                int escolha = Mao.mostrar(); // VAI FUNCIONAR DPS QUE CRIAR A MÃO

                while(true){
                    if (escolha < 5 && escolha >= 0){
                        Mao.escolheCarta(escolha);
                        break;
                    } else if (escolha == 5) {
                        turno++;
                            break;
                    } 
                    
                    System.out.println("Valor inválido. Escolha novamente.");
                    
                    Telas.Titulo.sleep(700);
                }

            }

        System.out.println("DUELO ENCERRADO!");
        System.out.println();
        Titulo.sleep(1500);
        if(heroi.estaVivo() == false){
            System.out.println("VOCÊ MORREU");
        }
        else System.out.println("VOCÊ RECUPEROU O PÉROLA NEGRA!");
        System.out.println();
    }
}
