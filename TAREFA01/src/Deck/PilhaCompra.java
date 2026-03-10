package Deck;
import java.util.*;

import Cartas.Carta;

public class PilhaCompra {
    private static ArrayList<Carta> cartas = new ArrayList<>();  
    private static Stack<Carta> pilhaCartas = new Stack<>();

    public static void addCarta(Carta c){
        cartas.add(c);
    }

    public static void removeCarta(Carta c){
        cartas.remove(c);
    }

    public static void shuffle(){
        Collections.shuffle(cartas);
    }

    public static void reShuffleDeck(ArrayList<Carta> mao){ // reembaralha só as cartas na pilha de compra e na mão
        ArrayList<Carta> temp = new ArrayList<>(pilhaCartas);
        temp.addAll(mao);
        Collections.shuffle(temp);
        pilhaCartas.clear();
        pilhaCartas.addAll(temp);
    }

    public static void shuffleAll(ArrayList<Carta> pilhaDescarte){ // reembaralha todas as cartas (mao, compra e descarte)
        cartas.addAll(pilhaDescarte);
        Collections.shuffle(cartas);
        pilhaCartas.clear();
        pilhaCartas.addAll(cartas);
    }

    public static void deckReset(){ // embaralha todas as cartas da pilha de descarte e adiciona a pilha de compra
        ArrayList<Carta> temp = PilhaDescarte.reset();
        pilhaCartas.addAll(temp);  
    }

    public static Carta puxaCarta(){ //falta adicionar checagem se está vazio
        return pilhaCartas.pop();
    }
}
