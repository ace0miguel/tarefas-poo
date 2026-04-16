package Telas.Eventos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import Cartas.Carta;
import Entidades.Heroi;
import Util.InputHandler;
import Util.Recompensas;
import Visual.Arte;
import Visual.Cor;
import Visual.Textos;
import static Visual.Textos.menuStatus;

/** evento aleatório onde o jogador pode pagar pra se curar ou pra receber cartas aleatórias */
public class Loja extends Evento{
    List<String> opcoes = new ArrayList<>(Arrays.asList("Poções de vida", "Pacotes de carta", "Edição de tags"));

    
    @Override 
    public void iniciar (Heroi heroi){
        this.heroi = heroi;
        String titulo = Textos.colorirPartes(Arte.loja, Cor.reset, Cor.ciano, 1);
        while (true){
            Textos.limpaTela();
            int escolha = InputHandler.selecionar(opcoes, true, titulo + Cor.txtAmareloClaro("\n\nVocê encontrou a loja! deseja comprar algo? "
            + menuStatus(heroi)));
            if (escolha == -1) break;
            switch (escolha) {
                case 0 ->{
                    List<String> opcoes = new ArrayList<>(Arrays.asList("Poção P (15 de vida, 10 reais)", "Poção M (25 de vida, 25 reais)", "Poção L (40 de vida, 35 reais)", "Poção XL (60 de vida, 50 reais)"));
                    int escolha2 = InputHandler.selecionar(opcoes, true, Cor.txtAmareloClaro("\n\nQual poção deseja comprar? "+ menuStatus(heroi)));
                    if (escolha2 == -1) break;
                    switch (escolha2) {
                        case 0 -> {
                            compraPoção(heroi, 15, 15);
                        }
                        case 1 -> {
                            compraPoção(heroi, 30, 25);  
                        }
                        case 2 -> {
                            compraPoção(heroi, 45, 35);  
                        }
                        case 3 -> {
                            compraPoção(heroi, 60, 50);  
                        }
                    }
                }
                case 1 -> {
                    List<String> opcoes = new ArrayList<>(Arrays.asList("Booster pack comum (25 reais)", "Booster pack incomum (55 reais)", "Booster pack raro (100 reais)"));
                    int escolha3 = InputHandler.selecionar(opcoes, true, Cor.txtAmareloClaro("\n\nQual Booster deseja comprar? "+ menuStatus(heroi)));
                    if (escolha3 == -1) break;
                    switch (escolha3) {
                        case 0 -> {
                            compraBoosterPack(heroi, 1, 25);
                        }
                        case 1 -> {
                            compraBoosterPack(heroi, 2, 55); 
                        }
                        case 2 -> {
                            compraBoosterPack(heroi, 3, 100);   
                        }
                    }
                }
                case 2-> {
                    edicaoTags(heroi);
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

    public void edicaoTags(Heroi heroi){
        List<String> opcoes = new ArrayList<>(Arrays.asList("Adicionar tag", "Remover tag"));

        // criando a lista de preço para adicionar/remover uma tag
        Map<String, Integer> precoTags = new HashMap<>();
        precoTags.put("Area", 80);
        precoTags.put("Consumir", 50);
        precoTags.put("Manter", 45);
        precoTags.put("Inata", 45);
        precoTags.put("Sacrificio", 50);

        while (true){
            var escolha = InputHandler.selecionar(opcoes, true, Cor.txtAmareloClaro("O que você deseja fazer? " + menuStatus(heroi)));
            AtomicInteger pagina = new AtomicInteger(0);

            if (escolha == -1) return;

            List<List<String>> matrizPaginas = InputHandler.montaPaginas(heroi.getBaralho());

            int opcaoCarta = InputHandler.menu(matrizPaginas, pagina);

            if (opcaoCarta == -1) continue;

            Carta carta = heroi.getBaralho().get(opcaoCarta);

            switch (escolha) {
                case 0 -> { // ADICIONAR TAG
                    List<String> disponiveis = new ArrayList<>();

                    for (String t : carta.tagsCompraveis) {
                        if (!carta.getTags().contains(t)) {
                            disponiveis.add(t + " " + Cor.txtAmarelo("- " + precoTags.getOrDefault(t, 50) + " dol"));
                        }
                    }

                    if (disponiveis.isEmpty()) {
                        System.out.println(Cor.txtAmarelo("esta carta já possui todas as tags compráveis."));
                        InputHandler.esperar();
                        break;
                    }

                    int opcao = InputHandler.selecionar(disponiveis, true, Cor.txtAmareloClaro("escolha uma tag para " + Cor.txtVerde("adicionar:")));

                    if (opcao != -1) {
                        String tagEscolhida = disponiveis.get(opcao);
                        String tagLimpa = Textos.getPrimeiraPalavra(tagEscolhida);

                        if (precoTags.getOrDefault(tagLimpa, 50) > heroi.getDinheiro()) {
                            System.out.println(Cor.txtVermelho("Você não tem dinheiro suficiente para comprar essa tag!"));
                            InputHandler.esperar();
                            break;
                        }

                        carta.aplicarTag(tagLimpa, true);
                        heroi.gastaDinheiro(precoTags.getOrDefault(tagLimpa, 50));

                        System.out.println(Cor.txtReset("tag < " + Cor.txtRosa(tagLimpa) + " > adicionada com sucesso!"));
                        InputHandler.esperar();
                    }
                }
                case 1 -> { // REMOVER TAG
                    List<String> disponiveis = new ArrayList<>();

                    for (String t : carta.tagsRemoviveis) {
                        if (carta.getTags().contains(t)) {
                            disponiveis.add(t + " " + Cor.txtAmarelo(" - " + precoTags.getOrDefault(t, 50) + " dol"));
                        }
                    }

                    if (disponiveis.isEmpty()) {
                        System.out.println(Cor.txtAmarelo("esta carta não possui tags para remover!"));
                        InputHandler.esperar();
                        break;
                    }

                    int opcao = InputHandler.selecionar(disponiveis, true, Cor.txtAmareloClaro("escolha uma tag para " + Cor.txtVermelho("remover:")));

                    if (opcao != -1) {
                        String tagEscolhida = disponiveis.get(opcao);
                        String tagLimpa = Textos.getPrimeiraPalavra(tagEscolhida);

                        if (precoTags.getOrDefault(tagLimpa, 50) > heroi.getDinheiro()) {
                            System.out.println(Cor.txtVermelho("Você não tem dinheiro suficiente para comprar essa tag!"));
                            InputHandler.esperar();
                            break;
                        }

                        carta.aplicarTag(tagLimpa, false);
                        heroi.gastaDinheiro(precoTags.getOrDefault(tagLimpa, 50));

                        System.out.println(Cor.txtReset("tag < " + Cor.txtRosa(tagLimpa) + " > removida com sucesso!"));
                        InputHandler.esperar();
                    }
                }
            }   
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