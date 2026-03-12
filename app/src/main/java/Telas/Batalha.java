package Telas;
import Cartas.*;
import Deck.*;
import Entidades.*;

import java.util.*;


public class Batalha {

    public static void iniciar(Heroi heroi, Carta cartaDano, Carta cartaEscudo, Inimigo... inimigos){

        List<Inimigo> inimigos = new ArrayList<>(Arrays.asList(inimigos)); // converte o array inimigos em arraylist para facilitar a manipulação.

        Scanner ler = new Scanner(System.in);

        //<-- AQUI: FALTA EMBARALHAR E TALS

        while(heroi.estaVivo() == true && inimigos.stream().anyMatch(i -> i.estaVivo() == true)){ // checa se o heroi ou ao menos um inimigo esta vivo
                
            Titulo.sleep(500); // delay no começo do turno

            int turno = 0; // 0: turno do heroi 
            
            Titulo.batalha(heroi, inimigos);

            if (turno == 0){
                System.out.println(heroi.statusEnergia());
                Mao.mostrar(); // VAI FUNCIONAR DPS QUE CRIAR A MÃO

            }
        }
        ler.close();
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
