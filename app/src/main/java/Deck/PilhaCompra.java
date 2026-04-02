package Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import Cartas.Carta;
import Util.Cor;
import Util.Textos;

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

    public void shuffleAll(PilhaDescarte pilhaDescarte){ // reembaralha todas as cartas (mao, compra e descarte)
        cartas.addAll(pilhaDescarte.getPilha());
        Collections.shuffle(cartas);
        pilhaCartas.clear();
        pilhaDescarte.clear();
        pilhaCartas.addAll(cartas);
    }

    public void deckReset(PilhaDescarte pilhaDescarte){ // embaralha todas as cartas da pilha de descarte e adiciona a pilha de compra
        Cor.setLaranja(); 
        System.out.print("Embaralhando as cartas");
        Textos.printaBonito(" . . . ", 300, 0);
        Cor.reset();
        Textos.sleep(200);
        ArrayList<Carta> temp = pilhaDescarte.reset();
        this.pilhaCartas.addAll(temp);  
    }

    public Carta puxaCarta(PilhaDescarte pd){ //falta adicionar checagem se está vazio
        if (pilhaCartas.size() <= 0)
            deckReset(pd);
        return pilhaCartas.pop();
    }

    public void printDeck(){
        System.out.println("Deck completo: --------");
        for(int i = 0; i < cartas.size(); i++) System.out.println(cartas.get(i));
        System.out.println("-----------------------");
        Textos.sleep(200);
    }
}
