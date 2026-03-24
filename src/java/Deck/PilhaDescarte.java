package Deck;

import java.util.ArrayList;
import java.util.Collections;

import Cartas.Carta;

public class PilhaDescarte {
    private ArrayList<Carta> cartas = new ArrayList<>();  

    public void descarta(Carta c){
        cartas.add(c);
    }

    public void recupera(Carta c, ArrayList<Carta> mao){
        mao.add(c);
        cartas.remove(c);
    }

    public ArrayList<Carta> getPilha(){
        return cartas;
    }

    public ArrayList<Carta> reset(){
        Collections.shuffle(cartas);
        ArrayList<Carta> temp = new ArrayList<>(this.cartas);
        cartas.clear();
        return temp;
    }

    public void clear(){
        cartas.clear();
    }
}
