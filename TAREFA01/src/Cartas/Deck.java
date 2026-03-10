package Cartas;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Carta> cartas = new ArrayList<>();  
    private Stack<Carta> pilhaCartas = new Stack<>();

    public void shuffleDeck(){
        Collections.shuffle(cartas);
    }

    public void addCarta(Carta c){
        cartas.add(c);
    }

    public void removeCarta(Carta c){
        cartas.remove(c);
    }

    public void reShuffleDeck(){ // utilizar 
        ArrayList<Carta> temp = new ArrayList<>(pilhaCartas);
        
        cartas.addAll(pilhaCartas);
        shuffleDeck();
        pilhaCartas.addAll(cartas);
    }

    public Carta puxaCarta(){ //falta adicionar checagem se está vazio
        return this.pilhaCartas.pop();
    }
}
