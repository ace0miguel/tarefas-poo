package telas.eventos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import batalhaListeners.itens.Item;
import batalhaListeners.itens.ativos.ItemAtivo;
import batalhaListeners.itens.passivos.ItemPassivo;
import cartas.Carta;
import cartas.Carta.raridades;
import entidades.Heroi;

import static fabricas.FabricaCartas.cartasEncontraveis;
import static fabricas.FabricaItens.listaItensAtivos;
import static fabricas.FabricaItens.listaItensPassivos;
import util.InputHandler;
import util.Recompensas;
import visual.Arte;
import visual.Cor;
import visual.Textos;
import static visual.Textos.menuStatus;

/** evento aleatório onde o jogador pode pagar pra se curar ou pra receber cartas aleatórias */
public class Loja extends Evento{
    List<String> opcoesLoja = new ArrayList<>(Arrays.asList(
        "Sessão de spa (cura)", 
        "Cartas", 
        "Itens", 
        "Edição de tags"));

    @Override 
    public void iniciar (Heroi heroi){
        // sorteio dos itens disponiveis na loja
        List<Item> itensDisponiveis;
        itensDisponiveis = new ArrayList<>(listaItensAtivos);
        itensDisponiveis.addAll(listaItensPassivos);
        Collections.shuffle(itensDisponiveis);

        itensDisponiveis = itensDisponiveis.subList(0, Math.min(5, itensDisponiveis.size())); // seleciona 5 itens aleatórios para oferecer na loja

        // sorteio das cartas disponiveis na loja
        
        List<Carta> cartasDisponiveis = Recompensas.cartasAleatorias(raridades.COMUM, 6, cartasEncontraveis, true);

        this.heroi = heroi;
        String titulo = Textos.colorirPartes(Arte.loja, Cor.reset, Cor.ciano, 1);

        while (true){ // loop principal
            Textos.limpaTela();
            int escolha = InputHandler.selecionar(opcoesLoja, true, titulo + Cor.txtAmareloClaro("\n\nVocê encontrou a loja! deseja comprar algo? "
            + menuStatus(heroi)));
            if (escolha == -1) break;
            switch (escolha) {
                case 0 ->{ // CURA
                    List<String> opcoes = new ArrayList<>(Arrays.asList(
                    "Pacote básico (15 de vida, 10 reais)",
                    "Pacote medio (25 de vida, 25 reais)", 
                    "Pacote completo (40 de vida, 35 reais)",    
                    "Pacote deluxe (60 de vida, 50 reais)"));

                    int escolha2 = InputHandler.selecionar(opcoes, true, Cor.txtAmareloClaro("\n\nQual dos serviços voce deseja socilitar? "+ menuStatus(heroi)));
                    if (escolha2 == -1) break;
                    switch (escolha2) {
                        case 0 -> {
                            compraCura(heroi, 15, 15);
                        }
                        case 1 -> {
                            compraCura(heroi, 30, 25);  
                        }
                        case 2 -> {
                            compraCura(heroi, 45, 35);  
                        }
                        case 3 -> {
                            compraCura(heroi, 60, 50);  
                        }
                    }
                }
                case 1 -> { // CARTAS
                    List<String> opcoes = new ArrayList<>(Arrays.asList(
                        Cor.txtAzulClaro("Booster pack comum (35 reais)"), 
                        Cor.txtVerde("Booster pack incomum (55 reais)"), 
                        Cor.txtLaranja("Booster pack raro (100 reais)\n")));
                    
                        opcoes.addAll(cartasDisponiveis.stream().map(Carta::descricaoLoja).toList());

                    int escolha3 = InputHandler.selecionar(opcoes, true, Cor.txtAmareloClaro("\n\n O que deseja comprar? "+ menuStatus(heroi)));
                    if (escolha3 == -1) break;
                    switch (escolha3) {
                        case 0 -> {
                            compraBoosterPack(heroi, raridades.COMUM, 35);
                        }
                        case 1 -> {
                            compraBoosterPack(heroi, raridades.INCOMUM, 55); 
                        }
                        case 2 -> {
                            compraBoosterPack(heroi, raridades.RARA, 100);   
                        }
                        default -> {
                            compraCarta(heroi, cartasDisponiveis.get(escolha3 - 3), cartasDisponiveis.get(escolha3 - 3).getPreco());
                        }
                    }
                }
                case 2-> { // ITENS
                    List<String> opcoes = new ArrayList<>(itensDisponiveis.stream().map(i -> i.descricaoLoja()).toList());

                    int escolha2 = InputHandler.selecionar(opcoes, true, Cor.txtAmareloClaro("\n\nQual item deseja comprar? "+ menuStatus(heroi)));
                    if (escolha2 == -1) break;
                    switch (escolha2) {
                        case 0 -> {
                            compraItem(heroi, itensDisponiveis.get(0), itensDisponiveis.get(0).getCusto());
                        }
                        case 1 -> {
                            compraItem(heroi, itensDisponiveis.get(1), itensDisponiveis.get(1).getCusto());
                        }
                        case 2 -> {
                            compraItem(heroi, itensDisponiveis.get(2), itensDisponiveis.get(2).getCusto());
                        }
                    }
                }
                case 3-> {
                    edicaoTags(heroi);
                }
            }
        }
    }

    public void compraBoosterPack(Heroi heroi, raridades raridade, int preco) {
        if (heroi.getDinheiro() >= preco) {
            Recompensas.gastarDinheiro(preco, heroi);
            Recompensas.ganharCartas(raridade, 3, heroi);
        } else {
            System.out.println(Cor.txtVermelho("Você não tem dinheiro suficiente!"));
            InputHandler.esperar();
        }  
    }

    public void compraCura(Heroi heroi, int cura, int preco) {
        if (heroi.getDinheiro() >= preco) {
            Recompensas.gastarDinheiro(preco, heroi);
            Recompensas.ganharVida(cura, heroi);
        } else {
            System.out.println(Cor.txtVermelho("Você não tem dinheiro suficiente!"));
            InputHandler.esperar();
        }  
    }

    public void compraItem(Heroi heroi, Item item, int preco) {
        if (heroi.getDinheiro() >= preco) {
            Recompensas.gastarDinheiro(preco, heroi);

            switch (item) {
                case ItemAtivo i -> Recompensas.ganharItemAtivoEsp(i, heroi);
                case ItemPassivo i -> Recompensas.ganharItemPassivoEsp(i, heroi);
                default -> {
                }
            }
        } else {
            System.out.println(Cor.txtVermelho("Você não tem dinheiro suficiente!"));
            InputHandler.esperar();
        }  
    }

    public void compraCarta(Heroi heroi, Carta carta, int preco) {
        if (heroi.getDinheiro() >= preco) {
            Recompensas.gastarDinheiro(preco, heroi);
            Recompensas.ganharCartaEsp(carta, heroi);
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
                        Recompensas.gastarDinheiro(precoTags.getOrDefault(tagLimpa, 50), heroi);

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
                        Recompensas.gastarDinheiro(precoTags.getOrDefault(tagLimpa, 50), heroi);

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