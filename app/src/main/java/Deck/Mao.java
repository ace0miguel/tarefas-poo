package Deck;
import java.util.ArrayList;
import java.util.Scanner;
import Handlers.InputHandler;

import Cartas.Carta;
//import Telas.Textos;

/* Início do turno : puxa 5 carta;
 tudo que voce nao usar vai pra pilha de descarte;
acabou a pilha de compra: reembaralha a pilha de descarte */
public class Mao {
    private int quantMax = 5;
    private ArrayList<Carta> cartas = new ArrayList<>(); 

    Scanner ler = InputHandler.getLeitor();

    public void addCarta(PilhaCompra pilhaCompra, PilhaDescarte pilhaDescarte){
        if(cartas.size() < quantMax){
            cartas.add(pilhaCompra.puxaCarta(pilhaDescarte));
        }
        else{
            System.out.println("Máximo de cartas na mão atingido.");
        }
    }

    public void addCinco(PilhaCompra pilhaCompra, PilhaDescarte pilhaDescarte){
        for (int i = 0; i < 5; i++) // adiciona 5 cartas à mão
            this.addCarta(pilhaCompra, pilhaDescarte);
    }

    public int mostrar(){ // retorna a opção escolhida em forma de numero
        System.out.println("Mão atual:");
        int ultimoNumero = 0;
        for (int i = 0; i < cartas.size(); i++){
            Carta cartaAtual = cartas.get(i);
            System.out.println("["+i+"] - "+cartaAtual.descricao()+"");
            ultimoNumero = i;
        }
        ultimoNumero++;
        System.out.println("["+ultimoNumero+"] - Encerrar turno");
        int opcao = -1;

        try {
            opcao = ler.nextInt();
            ler.nextLine();
        } catch (Exception e) {
            ler.nextLine();
            System.out.println();
            System.out.println("Tem que ser um número de 0 a 5, capitão!!");
            System.out.println();
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
        // removeCarta(opcao, pilhaDescarte);  
        return carta;
    }

    public int getSize(){
        return cartas.size();
    }
}
