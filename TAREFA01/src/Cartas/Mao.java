package Cartas;
import Cartas.Deck;

public class Mao {
    private int quant;
    private int quantMax;
    private Carta[] cartas;

    public Mao(int quantMax){
        this.quant = 0;
        this.quantMax = quantMax;
        this.cartas = new Carta[quantMax];
    }

    public void addCarta(){
        if(quant<quantMax){
            cartas.add() Deck.puxaCarta();
        }
        
    }

}
