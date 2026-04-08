package Telas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import Cartas.Carta;
import Entidades.Heroi;
import Util.Arte;
import Util.Cor;
import Util.InputHandler;
import static Util.Moldes.armadura;
import static Util.Moldes.beberVeneno;
import static Util.Moldes.bomba;
import static Util.Moldes.bombaVeneno;
import static Util.Moldes.chocolex;
import static Util.Moldes.contratoSangue;
import static Util.Moldes.corteDefensivo;
import static Util.Moldes.corteProfundo;
import static Util.Moldes.corteRapido;
import static Util.Moldes.corteVenenoso;
import static Util.Moldes.dedoNervosoCarta;
import static Util.Moldes.desprezo;
import static Util.Moldes.energiaGratis;
import static Util.Moldes.energiaSupremo;
import static Util.Moldes.energizar;
import static Util.Moldes.escudoFerro;
import static Util.Moldes.escudoMadeira;
import static Util.Moldes.mestreLaminasCarta;
import static Util.Moldes.pactoSangue;
import static Util.Moldes.purificar;
import static Util.Moldes.puroOdio;
import static Util.Moldes.puxaCarta;
import static Util.Moldes.sangrar;
import static Util.Moldes.tiro;
import static Util.Moldes.tiroCanhao;
import static Util.Moldes.tiroEscopeta;
import Util.Textos;

public class DeckBuilder {
    private static List<String> decksPadrao = new ArrayList<>(); // decks padrao pra testagem ate ter o deckbuilder de vdd
    private static List<String> menuInicial = new ArrayList<>(Arrays.asList("inventario", "baralho"));

    // limite minimo de cartas q vc precisa ter no baralho pra poder sair daqui.
    private static int limiteMinimoCartas = 10;



    public static void mostrarDecksPadrao(Heroi heroi){
        // nomes dos decks padrao
        decksPadrao.add(Cor.txtVermelho("O MESTRE DAS LAMINAS: CORTES INFINITOS, DEBUFFS INFINITOS, RODADA INFIITA!"));
        decksPadrao.add(Cor.txtAmarelo("JOHN WICK: CAUSE MUITO DANO DIRETO EM UMA CHUVA DE BALAS!"));
        decksPadrao.add(Cor.txtRoxo("O AMALDIÇOADO: CARTAS MUITO FORTES, MAS TALVEZ UM PREÇO ALTO DEMAIS..."));
        decksPadrao.add(Cor.txtCinza("deck de teste nao usar fazendo favor obg."));

        Textos.limpaTela();
        int escolha = InputHandler.selecionar(decksPadrao, Cor.reset + "Escolha um baralho inicial: \n" + Cor.txtCinza("(se voce ganhar a primeira batalha eu te deixo personalizar o deck!)")); 

        switch (escolha){
            /*a ideia por tras desse deck aqui é q meio q vc tenta atingir um jackpot stackando mestre das laminas + usando cartas que ganham energia 
            + usando cartas baratas + usar a mao completa pra ganhar mais 2 de energia -> tentar repetir o ciclo -> turno infinito. dano infinito. dopamina infinita. */
            case 0 -> { 
                // 2 de cada corte + mestre laminas pra faze uns combao
                heroi.addCarta(corteVenenoso);
                heroi.addCarta(corteVenenoso);

                heroi.addCarta(corteProfundo);
                heroi.addCarta(corteProfundo);

                heroi.addCarta(corteRapido);
                heroi.addCarta(corteRapido);

                heroi.addCarta(corteDefensivo);
                heroi.addCarta(corteDefensivo);

                heroi.addCarta(mestreLaminasCarta);
                heroi.addCarta(mestreLaminasCarta);

                heroi.addCarta(purificar);
                heroi.addCarta(purificar);

                heroi.addCarta(armadura);

                heroi.addCarta(puxaCarta);
                heroi.addCarta(puxaCarta);   
                
                heroi.addCarta(energizar);
                heroi.addCarta(energizar);
                
                heroi.addCarta(puroOdio);
                heroi.addCarta(puroOdio);

                heroi.addCarta(energiaGratis);

                heroi.addCarta(escudoFerro);
                heroi.addCarta(escudoFerro);
                heroi.addCarta(escudoMadeira);

                heroi.addCarta(chocolex);
                heroi.addCarta(bombaVeneno);
                
                heroi.addCarta(pactoSangue);
                heroi.addCarta(pactoSangue);
            }
            /* deck de dano direto mesmo bem simples, 2 puro odio pra da mais dano ainda */
            case 1 -> {
                heroi.addCarta(tiro);
                heroi.addCarta(tiroEscopeta);
                heroi.addCarta(tiroCanhao);

                heroi.addCarta(tiro);
                heroi.addCarta(tiroEscopeta);
                heroi.addCarta(tiroCanhao);
                
                heroi.addCarta(purificar);
                heroi.addCarta(purificar);

                heroi.addCarta(desprezo);

                heroi.addCarta(bomba);
                heroi.addCarta(bomba);

                heroi.addCarta(dedoNervosoCarta);

                heroi.addCarta(puroOdio);
                heroi.addCarta(puroOdio);

                heroi.addCarta(puxaCarta);  

                heroi.addCarta(energizar);
                heroi.addCarta(energizar);

                heroi.addCarta(energiaGratis);
                
                heroi.addCarta(escudoMadeira);
                heroi.addCarta(escudoMadeira);

                heroi.addCarta(armadura);

                heroi.addCarta(chocolex);

                heroi.addCarta(bombaVeneno);

                heroi.addCarta(pactoSangue);
                heroi.addCarta(pactoSangue);
            }  
            /* muita carta roubada porem voce começa com um monte de maldiçoes (1/3 do deck atualmente)*/
            case 2 -> {
                heroi.addCarta(tiro);

                heroi.addCarta(tiroEscopeta);
                heroi.addCarta(tiroEscopeta);

                heroi.addCarta(corteVenenoso);
                heroi.addCarta(corteVenenoso);

                heroi.addCarta(corteProfundo);
                heroi.addCarta(corteProfundo);

                heroi.addCarta(corteRapido);
                heroi.addCarta(corteRapido);

                heroi.addCarta(desprezo);
                heroi.addCarta(desprezo);

                heroi.addCarta(bombaVeneno);
                heroi.addCarta(bombaVeneno);

                heroi.addCarta(bomba);

                heroi.addCarta(mestreLaminasCarta);
                heroi.addCarta(dedoNervosoCarta);
                heroi.addCarta(mestreLaminasCarta);
                heroi.addCarta(dedoNervosoCarta);
                heroi.addCarta(mestreLaminasCarta);
                heroi.addCarta(dedoNervosoCarta);
                
                heroi.addCarta(purificar);
                heroi.addCarta(purificar);

                heroi.addCarta(puxaCarta); 
                heroi.addCarta(puxaCarta); 

                heroi.addCarta(energizar); 
                heroi.addCarta(energizar); 
              
                heroi.addCarta(energiaGratis);
                heroi.addCarta(energiaGratis);
                heroi.addCarta(energiaGratis);

                heroi.addCarta(puroOdio);
                heroi.addCarta(puroOdio);

                heroi.addCarta(armadura);
                heroi.addCarta(armadura);

                heroi.addCarta(escudoMadeira);
                heroi.addCarta(escudoMadeira);

                heroi.addCarta(chocolex);

                //maldiçoes...

                heroi.addCarta(sangrar);
                heroi.addCarta(sangrar);

                heroi.addCarta(sangrar);
                heroi.addCarta(sangrar);
                
                heroi.addCarta(sangrar);
                heroi.addCarta(sangrar);

                heroi.addCarta(sangrar);
                heroi.addCarta(sangrar);

                heroi.addCarta(sangrar);
                heroi.addCarta(sangrar);

                heroi.addCarta(beberVeneno);
                heroi.addCarta(beberVeneno);

                heroi.addCarta(beberVeneno);
                heroi.addCarta(beberVeneno);

                heroi.addCarta(beberVeneno);
                heroi.addCarta(beberVeneno);

                heroi.addCarta(beberVeneno);
                heroi.addCarta(beberVeneno);

                
            }
            case 3 -> { // deck pra testa carta
                heroi.addCarta(bombaVeneno);
                heroi.addCarta(bombaVeneno);
                heroi.addCarta(bombaVeneno);

                heroi.addCarta(bomba);
                heroi.addCarta(bomba);
                
                heroi.addCarta(puxaCarta);
                heroi.addCarta(puxaCarta); 

                heroi.addCarta(energiaSupremo);
                heroi.addCarta(corteProfundo);

                heroi.addCarta(corteRapido);

                heroi.addCarta(pactoSangue);
                heroi.addCarta(pactoSangue);
                heroi.addCarta(pactoSangue);
                heroi.addCarta(pactoSangue);

                heroi.addCarta(contratoSangue);
            }
        }
        Textos.limpaTela();
    }

    /* ideia: menu de selecionar -> 0 - sair 1 - inventario 2 - baralho
    se vc entra no inventario vc escolhe uma carta e ela vai pro baralho e vice versa
    em um dos menus de carta -> 0 - sair(volta pro menu inicial), 1 - 7 cartas, 8 - pagina anterior, 9 - proxima pagina */
    public static void iniciar(Heroi heroi){
        while (true){
            int opcao = InputHandler.selecionar(menuInicial, true, Cor.txtAmareloClaro(Arte.deckBuilder));

            // escolheu voltar, sai do loop. mas so se tiver acima do limite minimo.
            if (opcao == -1) {
                if (heroi.getBaralho().size() >= limiteMinimoCartas) 
                    break;
                else{
                    System.out.println("eu nao vou te deixar sair daqui com menos de 10 cartas nesse baralho.");
                    InputHandler.esperar();
                }
            }
            // 0 -> inventario
            else if (opcao == 0) {
                if (heroi.getInventarioCartas().size() > 0) 
                    menuCartas(heroi, false);
                else{
                    System.out.println("Voce nao possui cartas no seu inventario.");
                    InputHandler.esperar();
                }
            }
            // 1 -> baralho
            else if (opcao == 1) {
                if (heroi.getBaralho().size() > 0) 
                    menuCartas(heroi, true);
                else{
                    System.out.println("Voce nao possui cartas no seu baralho.");
                    InputHandler.esperar();
                }
            }
        }
    }


    /** exibe o menu de cartas escolhido e cuida da troca de cartas
     * @param baralho = true: mostra o baralho, false: mostra o inventario de cartas
     */
    public static void menuCartas(Heroi heroi, boolean baralho){
        Carta carta;
        AtomicInteger pagina = new AtomicInteger(0);
        while (true) { 
            List<List<String>> matrizPaginas = new ArrayList<>();

            // verifica se é pra usar o baralho ou o inventario de carta
            if (baralho) matrizPaginas.addAll(InputHandler.montaPaginas(heroi.getBaralho()));
            else matrizPaginas.addAll(InputHandler.montaPaginas(heroi.getInventarioCartas()));

            int posicaoCarta = InputHandler.menu(matrizPaginas, pagina);

            // codigo pra voltar
            if (posicaoCarta == -1) break; // volta pro menu inicial

            // ve se e inventario ou baralho e troca de um pro outro
            if (baralho){
                if (heroi.getDinheiro() < 5) {
                    System.out.println("Dinheiro insuficiente");
                    InputHandler.esperar();
                    continue;
                }
                heroi.gastaDinheiro(5);
                carta = heroi.getBaralho().get( posicaoCarta );
            }   
            else 
                carta = heroi.getInventarioCartas().get( posicaoCarta );

            heroi.trocaCarta(carta);
        }
    }
}
