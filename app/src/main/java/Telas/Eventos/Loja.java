package Telas.Eventos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Entidades.Heroi;
import Util.Arte;
import Util.Cor;
import Util.InputHandler;
import Util.Recompensas;
import Util.Textos;


public class Loja extends Evento{
    List<String> opcoes = new ArrayList<>(Arrays.asList("Poções de vida", "Pacotes de carta"));

    
    @Override 
    public void iniciar (Heroi heroi){
        this.heroi = heroi;
        String titulo = Textos.colorirPartes(Arte.loja, Cor.reset, Cor.ciano, 1);
        while (true){
            Textos.limpaTela();
            int escolha = InputHandler.selecionar(opcoes, true, titulo + Cor.txtAmareloClaro("\n\nVocê encontrou a loja! deseja comprar algo? "+ Cor.cinza + "[ Saldo atual: ( " + Cor.amarelo + heroi.getDinheiro() + Cor.cinza + " ) ]"));
            if (escolha == -1) break;
            switch (escolha) {
                case 0 ->{
                    List<String> opcoes = new ArrayList<>(Arrays.asList("Poção pequena (15 de vida, 10 reais)", "Poção média (25 de vida, 25 reais)", "Poção grande (40 de vida, 35 reais)"));
                    int escolha2 = InputHandler.selecionar(opcoes, true, Cor.txtAmareloClaro("\n\nQual poção deseja comprar? "+ Cor.cinza + "[ Saldo atual: ( " + Cor.amarelo + heroi.getDinheiro() + Cor.cinza + " ) ]"));
                    if (escolha2 == -1) break;
                    switch (escolha2) {
                        case 0 -> {
                            compraPoção(heroi, 15, 10);
                        }
                        case 1 -> {
                            compraPoção(heroi, 25, 25);  
                        }
                        case 2 -> {
                            compraPoção(heroi, 40, 35);  
                        }
                    }
                }
                case 1 -> {
                    List<String> opcoes = new ArrayList<>(Arrays.asList("Booster pack comum (35 reais)", "Booster pack incomum (50 reais)", "Booster pack raro (70 reais)"));
                    int escolha3 = InputHandler.selecionar(opcoes, true, Cor.txtAmareloClaro("\n\nQual Booster deseja comprar? "+ Cor.cinza + "[ Saldo atual: ( " + Cor.amarelo + heroi.getDinheiro() + Cor.cinza + " ) ]"));
                    if (escolha3 == -1) break;
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
    }

    public void compraBoosterPack(Heroi heroi, int raridade, int preco) {
        if (heroi.getDinheiro() >= preco) {
            heroi.gastaDinheiro(preco);
            Recompensas.ganharCartas(raridade, 3, heroi);
        } else {
            System.out.println(Cor.txtVermelho("Você não tem dinheiro suficiente!"));
            InputHandler.esperar();
        }  
    }

    public void compraPoção(Heroi heroi, int cura, int preco) {
        if (heroi.getDinheiro() >= preco) {
            heroi.gastaDinheiro(preco);
            heroi.ganhaVida(cura);
        } else {
            System.out.println(Cor.txtVermelho("Você não tem dinheiro suficiente!"));
            InputHandler.esperar();
        }  
    }

    @Override
    public String toString() {
        String retorno = Cor.txtCiano("Loja.");
        return retorno;
    }

    @Override
    public Loja criaCopia() {
        return new Loja();
    }

    
}