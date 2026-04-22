package baralho;

import java.util.ArrayList;
import java.util.Collections;

import cartas.Carta;

public class PilhaDescarte {
    private ArrayList<Carta> cartas = new ArrayList<>();  

    public void descarta(Carta c){
        cartas.add(c);
    }

    /** remove uma carta da pilha e adiciona a mao */
    public void recupera(Carta c, Mao mao){
        mao.addCartaEsp(c);
        cartas.remove(c);
    }

    /** remove a ultima carta da pilha e adiciona a mao */
    public void recuperaUltima(Mao mao){
        if (!cartas.isEmpty()) {
            Carta c = cartas.removeLast();
            mao.addCartaEsp(c);
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

    public void mostrar(){
        if (cartas.isEmpty()) {
            System.out.println("A pilha está vazia.");
            return;
        }

        for(int i = 0; i < cartas.size(); i++) {
            System.out.println(cartas.get(i));
            System.out.println();
        }
    }
    
    public int getSize(){
        return cartas.size();
    }
}
