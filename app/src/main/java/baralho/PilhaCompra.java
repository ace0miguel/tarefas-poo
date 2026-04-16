package baralho;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import cartas.Carta;
import visual.Cor;
import visual.Textos;

public class PilhaCompra {

    private Stack<Carta> cartas = new Stack<>();

    public void addCarta(Carta c){
        cartas.add(c);
    }

    /**adiciona uma carta na pilha de compras e a embaralha */
    public void addCartaPilha(Carta c){
        cartas.add(c);
        shuffleStack();
    }

    public void removeCarta(Carta c){
        cartas.remove(c);
    }

    public void addBaralho(List<Carta> baralho){
        cartas.clear();
        cartas.addAll(baralho);
    }

    public void shuffleStack(){ // embaralha a pilha de compras
        Collections.shuffle(this.cartas);
    }

    /** embaralha todas as cartas da pilha de descarte e adiciona a pilha de compra (O PRINCIPAL) */
    public void shuffle(PilhaDescarte pilhaDescarte){
        Cor.setLaranja(); 
        System.out.print("Embaralhando as cartas");
        Textos.printaBonito(" . . . ", 300, 0);
        Cor.reset();
        Textos.sleep(200);
        
        this.cartas.addAll(pilhaDescarte.reset());  
    }

    public Carta puxaCarta(PilhaDescarte pd){
        if (cartas.size() <= 0)
            shuffle(pd);
        return cartas.pop();
    }

    public void printDeck(){
        System.out.println("Deck completo: --------");
        for(int i = 0; i < cartas.size(); i++) System.out.println(cartas.get(i));
        System.out.println("-----------------------");
        Textos.sleep(200);
    }

    public Stack<Carta> getPilhaCartas() {
        return cartas;
    }
}
