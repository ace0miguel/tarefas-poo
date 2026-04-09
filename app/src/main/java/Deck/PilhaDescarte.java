package Deck;

import java.util.ArrayList;
import java.util.Collections;

import Cartas.Carta;

public class PilhaDescarte {
    private ArrayList<Carta> cartas = new ArrayList<>();  

    public void descarta(Carta c){
        cartas.add(c);
    }

    public void recupera(Carta c, Mao mao){
        mao.addCartaEsp(c);
        cartas.remove(c);
    }

    public void recuperaUltima(Mao mao){
        if (cartas.size() > 0) {
            Carta c = cartas.get(cartas.size() - 1);
            mao.addCartaEsp(c);
            cartas.remove(cartas.size() - 1);
        }
    }

    public ArrayList<Carta> getPilha(){
        return cartas;
    }

    /** limpa a pilha de descarte e retorna as cartas contidas embaralhadas */
    public ArrayList<Carta> reset(){
        Collections.shuffle(cartas);
        ArrayList<Carta> temp = new ArrayList<>(this.cartas);
        cartas.clear();
        return temp;
    }

    /** limpa a pilha de descarte */
    public void clear(){
        cartas.clear();
    }
}
