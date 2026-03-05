package Cartas;
import java.util.ArrayList;

import Cartas.Deck;

public class Mao {
    private int quant;
    private int quantMax;
    private ArrayList<Carta> cartas; 

    public Mao(int quantMax){
        this.quant = 0;
        this.quantMax = quantMax;
        this.cartas = new ArrayList<>(); ;
    }

    public void addCarta(Deck deck){
        if(quant<quantMax){
            cartas.add(deck.puxaCarta());
        }
    }

        

}
