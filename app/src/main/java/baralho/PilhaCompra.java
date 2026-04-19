package baralho;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import cartas.Carta;
import util.InputHandler;
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

    public Stack<Carta> getPilha() {
        return cartas;
    }

    /** Retorna uma visualizacao da pilha em ordem real de compra (topo primeiro). */
    public List<Carta> getCartas() {
        List<Carta> cartasOrdenadas = new ArrayList<>(cartas);
        Collections.reverse(cartasOrdenadas);
        return cartasOrdenadas;
    }

    /** Retorna uma visualizacao embaralhada sem alterar a pilha original. */
    public List<Carta> getCartasEmbaralhadas() {
        List<Carta> cartasEmbaralhadas = new ArrayList<>(cartas);
        Collections.shuffle(cartasEmbaralhadas);
        return cartasEmbaralhadas;
    }

    public Carta puxaCartaEsp(Carta c){
        this.cartas.remove(c);
        return c;
    }

    public void mostrar(){
        visual.Textos.limpaTela();

        if (cartas.isEmpty()) {
            System.out.println("A pilha de compras está vazia.");
            InputHandler.esperar();
            return;
        }

        List<Carta> cartasEmbaralhadas = getCartasEmbaralhadas();

        System.out.println(Cor.verdeClaro + "Pilha de compras" + Cor.reset);
        System.out.println();

        for(int i = 0; i < cartasEmbaralhadas.size(); i++) {
            System.out.println(cartasEmbaralhadas.get(i));
            System.out.println();
        }

        InputHandler.esperar();
    }

    public int getSize(){
        return cartas.size();
    }
}
