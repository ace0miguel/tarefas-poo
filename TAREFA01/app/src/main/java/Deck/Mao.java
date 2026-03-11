package Deck;
import java.util.ArrayList;

import Cartas.Carta;

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

    public static void mostrar(){ // retorna o ultimo numero, para utilizaçao no menu no app.
        System.out.println("Mão atual:");
        int ultimoNumero = 0;
        for (int i = 0; i < cartas.size(); i++){
            Carta cartaAtual = cartas.get(i);
            System.out.println("["+i+"] - "+cartaAtual.getNome()+" [Custo: "+cartaAtual.getCusto()+"]");
            ultimoNumero = i;
        }
        System.out.println("["+ultimoNumero+"] - Encerrar turno");
    }

    public static Carta escolheCarta(int n){
        while (n >= cartas.size() || n < 0){ // nao sei se seria mais correto colocar essa logica aqui ou no app?
            System.out.println("Valor inválido. Escolha novamente.");
            mostrar(); 
            // falta fazer a parte de ler, nao lembro como faz, talvez seja melhor no app
        }
        Carta carta = cartas.get(n);
        cartas.remove(n);
        return carta;
    }

}
