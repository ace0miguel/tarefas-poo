package Deck;

import java.util.ArrayList;
import java.util.Collections;

import Cartas.Carta;

public class PilhaDescarte {
    private static ArrayList<Carta> cartas = new ArrayList<>();  

    public void descarta(Carta c){
        cartas.add(c);
    }

    public void recupera(Carta c, ArrayList<Carta> mao){
        mao.add(c);
        cartas.remove(c);
    }

    public static ArrayList<Carta> getPilha(){
        return cartas;
    }

    public static ArrayList<Carta> reset(){
        Collections.shuffle(cartas);
        ArrayList<Carta> temp = cartas;
        cartas.clear();
        return temp;
    }
}
