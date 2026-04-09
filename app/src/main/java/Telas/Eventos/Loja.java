package Telas.Eventos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Entidades.Heroi;
import Util.Arte;
import Util.Cor;
import Util.InputHandler;
import Util.Textos;
import Util.Recompensas;
import Cartas.Carta;


public class Loja extends Evento{
    List<String> opcoes = new ArrayList<>(Arrays.asList("Poção", "Carta"));

    
    @Override 
    public void iniciar (Heroi heroi){
        this.heroi = heroi;
        Textos.sobeTela();
        int escolha = InputHandler.selecionar(opcoes, Cor.txtAmareloClaro("\n\nVocê encontrou a loja, deseja comprar algo?"));
        switch (escolha) {
            case 0 ->{
                List<String> opcoes = new ArrayList<>(Arrays.asList("Poção pequena", "Poção média", "Poção grande"));
                int escolha2 = InputHandler.selecionar(opcoes, Cor.txtAmareloClaro("\n\nQual poção deseja comprar?"));
                switch (escolha2) {
                    case 0 -> {
                        compraPoção(heroi, 15, 10);
                    }
                    case 1 -> {
                        compraPoção(heroi, 25, 20);  
                    }
                    case 2 -> {
                        compraPoção(heroi, 40, 35);  
                    }
                }
            }
            case 1 -> {
                List<String> opcoes = new ArrayList<>(Arrays.asList("Booster pack comum", "Booster pack incomum", "Booster pack raro", "Booster pack especial"));
                int escolha3 = InputHandler.selecionar(opcoes, Cor.txtAmareloClaro("\n\nQual Booster deseja comprar?"));
                switch (escolha3) {
                    case 0 -> {
                        compraBoosterPack(heroi, 1, 35);
                    }
                    case 1 -> {
                        compraBoosterPack(heroi, 2, 50); 
                    }
                    case 2 -> {
                        compraBoosterPack(heroi, 3, 70);   
                    }
                }
            }
        }
    }

    public void compraBoosterPack(Heroi heroi, int raridade, int preco) {
        if (heroi.getDinheiro() >= preco) {
            heroi.gastaDinheiro(preco);
            List<Carta> cartas = Recompensas.cartasAleatorias(raridade, 3);
            for (Carta carta : cartas) {
                heroi.addCartaInventario(carta);
                System.out.println(Cor.txtVerdeClaro("\n\nVocê comprou um booster pack e recebeu a carta " + carta.getNome() + "!"));
            }
        } else {
            System.out.println(Cor.txtVermelho("\n\nVocê não tem ouro suficiente!"));
        }  
    }

    public void compraPoção(Heroi heroi, int cura, int preco) {
        if (heroi.getDinheiro() >= preco) {
            heroi.gastaDinheiro(preco);
            heroi.setVida(heroi.getVida() + cura);
            System.out.println(Cor.txtVerdeClaro("\n\nVocê comprou uma poção e recuperou " + cura + " de vida!"));
        } else {
            System.out.println(Cor.txtVermelho("\n\nVocê não tem ouro suficiente!"));
        }  
    }

    @Override
    public String toString() {
        String retorno = Cor.txtVerde("Loja");
        return retorno;
    }
}