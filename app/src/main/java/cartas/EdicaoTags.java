package cartas;

import static visual.Textos.menuStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import entidades.Heroi;
import util.InputHandler;
import util.Recompensas;
import visual.Cor;
import visual.Textos;

public class EdicaoTags {

    public static void iniciar(Heroi heroi) {
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
}
