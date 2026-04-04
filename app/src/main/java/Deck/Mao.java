package Deck;
import java.util.ArrayList;
import java.util.Scanner;

import Cartas.Carta;
import Util.Cor;
import Util.InputHandler;
import Util.Textos;

/* Início do turno : puxa 5 carta;
 tudo que voce nao usar vai pra pilha de descarte;
acabou a pilha de compra: reembaralha a pilha de descarte */
public class Mao {
    // uma boa mudança seria adicionar um construtor com a pilha de compras e a pilha de descarte, ai nao teria q ficar passando toda vez que chama
    private int quantMax = 5;
    private ArrayList<Carta> cartas = new ArrayList<>(); 

    Scanner ler = InputHandler.getLeitor();

    public void addCarta(PilhaCompra pilhaCompra, PilhaDescarte pilhaDescarte){
        cartas.add(pilhaCompra.puxaCarta(pilhaDescarte));

        // nao precisa mais disso aqui embaixo
        // if(cartas.size() < quantMax){ 
        //     cartas.add(pilhaCompra.puxaCarta(pilhaDescarte));
        // }
        // else{
        //     System.out.println("Máximo de cartas na mão atingido.");
        // }
    }
    
    public void addCartas(PilhaCompra pilhaCompra, PilhaDescarte pilhaDescarte, int quantidade){
        for (int i = 0; i < quantidade; i++) // adiciona n cartas a mao
            this.addCarta(pilhaCompra, pilhaDescarte); 
    }

    public void addCinco(PilhaCompra pilhaCompra, PilhaDescarte pilhaDescarte){
        for (int i = 0; i < 5; i++) // adiciona 5 cartas à mão
            this.addCarta(pilhaCompra, pilhaDescarte);
    }

    public int getQuantMax() {
        return quantMax;
    }

    public int mostrar(){ // retorna a opção escolhida em forma de numero
        System.out.println("Mão atual:");
        int ultimoNumero = 0;
        for (int i = 0; i < cartas.size(); i++){
            Carta cartaAtual = cartas.get(i);
            System.out.println("["+i+"] > "+cartaAtual.descricao()+""); Textos.sleep(50);
            ultimoNumero = i;
        }
        ultimoNumero++;
        Cor.printaCinza("["+ultimoNumero+"] - Encerrar turno\n");
        int opcao = -1;

        try {
            opcao = ler.nextInt();
            ler.nextLine();
        } catch (Exception e) {
            ler.nextLine();
        }
        return opcao;
    }

    public void removeCarta(int opcao, PilhaDescarte pilhaDescarte){
        pilhaDescarte.descarta(cartas.get(opcao));
        cartas.remove(opcao);   
    }

    public void limpa(PilhaDescarte pilhaDescarte){
        for (Carta carta : cartas) {
            pilhaDescarte.descarta(carta);
        }
        
        cartas.clear();
    }

    public Carta escolheCarta(int opcao){ // retorna a opçao escolhida em forma de carta
        Carta carta = cartas.get(opcao);
        return carta;
    }

    public int getSize(){
        return cartas.size();
    }
}
