package Deck;
import java.util.ArrayList;
import java.util.Scanner;

import Cartas.Carta;
//import Telas.Textos;

public class Mao {
    private int quantMax = 5;
    private ArrayList<Carta> cartas = new ArrayList<>(); 
    private Scanner ler;

    // public Mao(int quantMax){
    //     this.quantMax = quantMax;
    // }
    public Mao(Scanner ler){  // Construtor que recebe o Scanner
        this.ler = ler;
    }

    public void addCarta(PilhaCompra pilhaCompra){
        if(cartas.size() < quantMax){
            cartas.add(pilhaCompra.puxaCarta());
        }
        else{
            System.out.println("Máximo de cartas na mão atingido.");
        }
    }

    public int mostrar(){ // retorna a opção escolhida em forma de numero
        System.out.println("Mão atual:");
        int ultimoNumero = 0;
        for (int i = 0; i < cartas.size(); i++){
            Carta cartaAtual = cartas.get(i);
            System.out.println("["+i+"] - "+cartaAtual.getNome()+" [Custo: "+cartaAtual.getCusto()+"]");
            ultimoNumero = i;
        }
        ultimoNumero++;
        System.out.println("["+ultimoNumero+"] - Encerrar turno");
        int opcao = -1;

        try {
            opcao = ler.nextInt();
            ler.nextLine();
        } catch (Exception e) {
            ler.nextLine();
            System.out.println();
            System.out.println("Tem que ser um número de 0 a 5, capitão!!");
            System.out.println();
        }

        return opcao;
    }

    public Carta escolheCarta(int opcao){ // retorna a opçao escolhida em forma de carta
        Carta carta = cartas.get(opcao);
        //cartas.remove(opcao);  //remover aqui nao funciona, da bosta quando nao tem energia suficiente 
        return carta;
    }

    public void removeCarta(int opcao){
        cartas.remove(opcao);
    }

}
