package Deck;
import java.util.*;

import Cartas.Carta;

public class PilhaCompra {
    private ArrayList<Carta> cartas = new ArrayList<>();  
    private Stack<Carta> pilhaCartas = new Stack<>();

    public void addCarta(Carta c){
        cartas.add(c);
    }

    public void removeCarta(Carta c){
        cartas.remove(c);
    }

    public void shuffle(){
        Collections.shuffle(cartas);
    }

    public void reShuffleDeck(ArrayList<Carta> mao){ // reembaralha só as cartas na pilha de compra e na mão
        ArrayList<Carta> temp = new ArrayList<>(pilhaCartas);
        temp.addAll(mao);
        Collections.shuffle(temp);
        pilhaCartas.clear();
        pilhaCartas.addAll(temp);
    }

    public void shuffleAll(ArrayList<Carta> pilhaDescarte){ // reembaralha todas as cartas (mao, compra e descarte)
        cartas.addAll(pilhaDescarte);
        Collections.shuffle(cartas);
        pilhaCartas.clear();
        pilhaCartas.addAll(cartas);
    }

    public void deckReset(){ // embaralha todas as cartas da pilha de descarte e adiciona a pilha de compra
        ArrayList<Carta> temp = PilhaDescarte.reset();
        pilhaCartas.addAll(temp);  
    }

    public Carta puxaCarta(){ //falta adicionar checagem se está vazio
        
        return pilhaCartas.pop();
    }
}
