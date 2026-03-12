package Deck;
import java.util.ArrayList;

import Cartas.Carta;
import Telas.Textos;

public class Mao {
    private static int quantMax = 5;
    private static ArrayList<Carta> cartas = new ArrayList<>(); 

    // public Mao(int quantMax){
    //     this.quantMax = quantMax;
    // }

    public static void addCarta(){
        if(cartas.size() < quantMax){
            cartas.add(PilhaCompra.puxaCarta());
        }
        else{
            System.out.println("Máximo de cartas na mão atingido.");
        }
    }

    public static int mostrar(){ // retorna a opção escolhida em forma de numero
        Textos.limpaTela();
        
        System.out.println("Mão atual:");
        int ultimoNumero = 0;
        for (int i = 0; i < cartas.size(); i++){
            Carta cartaAtual = cartas.get(i);
            System.out.println("["+i+"] - "+cartaAtual.getNome()+" [Custo: "+cartaAtual.getCusto()+"]");
            ultimoNumero = i;
        }
        System.out.println("["+ultimoNumero+"] - Encerrar turno");

        Scanner ler = new Scanner(System.in);
        int opcao = ler.nextInt();
        ler.nextLine();
        ler.close();

        return opcao;
    }

    public static Carta escolheCarta(int opcao){ // retorna a opçao escolhida em forma de carta
        Carta carta = cartas.get(opcao);
        cartas.remove(opcao);
        return carta;
    }

}
