package Telas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Cartas.Carta;
import Entidades.Heroi;
import Util.Arte;
import Util.Cor;
import Util.InputHandler;
import static Util.Moldes.armadura;
import static Util.Moldes.beberVeneno;
import static Util.Moldes.corteDefensivo;
import static Util.Moldes.corteProfundo;
import static Util.Moldes.corteRapido;
import static Util.Moldes.corteVenenoso;
import static Util.Moldes.dedoNervosoCarta;
import static Util.Moldes.desprezo;
import static Util.Moldes.energiaGratis;
import static Util.Moldes.energizar;
import static Util.Moldes.escudoFerro;
import static Util.Moldes.escudoMadeira;
import static Util.Moldes.mestreLaminasCarta;
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

        Textos.limpaTela();
        int escolha = InputHandler.selecionar(decksPadrao, Cor.reset + "Escolha um baralho inicial: \n" + Cor.txtCinza("(se voce ganhar a primeira batalha eu te deixo personalizar o deck!)")); 

        switch (escolha){
            /*a ideia por tras desse deck aqui é q meio q vc tenta atingir um jackpot stackando mestre das laminas + usando cartas que ganham energia 
            + usando cartas baratas + usar a mao completa pra ganhar mais 2 de energia -> tentar repetir o ciclo -> turno infinito. dano infinito. dopamina infinita. */
            case 0 -> { 
                // 2 de cada corte + mestre laminas pra faze uns combao
                heroi.addCarta(corteVenenoso.criaCopia());
                heroi.addCarta(corteVenenoso.criaCopia());

                heroi.addCarta(corteProfundo.criaCopia());
                heroi.addCarta(corteProfundo.criaCopia());

                heroi.addCarta(corteRapido.criaCopia());
                heroi.addCarta(corteRapido.criaCopia());

                heroi.addCarta(corteDefensivo.criaCopia());
                heroi.addCarta(corteDefensivo.criaCopia());

                heroi.addCarta(mestreLaminasCarta.criaCopia());

                heroi.addCarta(purificar.criaCopia());
                heroi.addCarta(purificar.criaCopia());

                heroi.addCarta(armadura.criaCopia());

                heroi.addCarta(puxaCarta.criaCopia());
                heroi.addCarta(puxaCarta.criaCopia());   
                
                heroi.addCarta(energizar.criaCopia());
                heroi.addCarta(energizar.criaCopia());
                
                heroi.addCarta(puroOdio.criaCopia());

                heroi.addCarta(energiaGratis.criaCopia());

                heroi.addCarta(escudoFerro.criaCopia());
                heroi.addCarta(escudoFerro.criaCopia());
                heroi.addCarta(escudoMadeira.criaCopia());
            }
            /* deck de dano direto mesmo bem simples, 2 puro odio pra da mais dano ainda */
            case 1 -> {
                // 2 de cada tiro
                heroi.addCarta(tiro.criaCopia());
                heroi.addCarta(tiroEscopeta.criaCopia());
                heroi.addCarta(tiroCanhao.criaCopia());

                heroi.addCarta(tiro.criaCopia());
                heroi.addCarta(tiroEscopeta.criaCopia());
                heroi.addCarta(tiroCanhao.criaCopia());
                
                // 2 purificar
                heroi.addCarta(purificar.criaCopia());
                heroi.addCarta(purificar.criaCopia());

                heroi.addCarta(desprezo.criaCopia());
                heroi.addCarta(dedoNervosoCarta.criaCopia());

                heroi.addCarta(puroOdio.criaCopia());
                heroi.addCarta(puroOdio.criaCopia());

                heroi.addCarta(puxaCarta.criaCopia());   
                
                heroi.addCarta(energizar.criaCopia());
                heroi.addCarta(energizar.criaCopia());

                heroi.addCarta(energiaGratis.criaCopia());
                
                heroi.addCarta(escudoMadeira.criaCopia());
                heroi.addCarta(escudoMadeira.criaCopia());

                heroi.addCarta(armadura.criaCopia());
            }  
            /* muita carta roubada porem voce começa com um monte de maldiçoes (1/3 do deck atualmente)*/
            case 2 -> {
                heroi.addCarta(tiro);

                heroi.addCarta(tiroEscopeta.criaCopia());
                heroi.addCarta(tiroEscopeta.criaCopia());

                heroi.addCarta(corteVenenoso.criaCopia());
                heroi.addCarta(corteVenenoso.criaCopia());

                heroi.addCarta(corteProfundo.criaCopia());
                heroi.addCarta(corteProfundo.criaCopia());

                heroi.addCarta(corteRapido.criaCopia());
                heroi.addCarta(corteRapido.criaCopia());

                heroi.addCarta(desprezo.criaCopia());
                heroi.addCarta(desprezo.criaCopia());

                heroi.addCarta(mestreLaminasCarta.criaCopia());
                heroi.addCarta(dedoNervosoCarta.criaCopia());
                heroi.addCarta(mestreLaminasCarta.criaCopia());
                heroi.addCarta(dedoNervosoCarta.criaCopia());
                heroi.addCarta(mestreLaminasCarta.criaCopia());
                heroi.addCarta(dedoNervosoCarta.criaCopia());
                
                heroi.addCarta(purificar.criaCopia());
                heroi.addCarta(purificar.criaCopia());

                heroi.addCarta(puxaCarta.criaCopia()); 
                heroi.addCarta(puxaCarta.criaCopia()); 

                heroi.addCarta(energizar.criaCopia()); 
                heroi.addCarta(energizar.criaCopia()); 
              
                heroi.addCarta(energiaGratis.criaCopia());
                heroi.addCarta(energiaGratis.criaCopia());
                heroi.addCarta(energiaGratis.criaCopia());

                heroi.addCarta(puroOdio.criaCopia());
                heroi.addCarta(puroOdio.criaCopia());

                heroi.addCarta(armadura.criaCopia());
                heroi.addCarta(armadura.criaCopia());

                heroi.addCarta(escudoMadeira.criaCopia());
                heroi.addCarta(escudoMadeira.criaCopia());

                //maldiçoes...

                heroi.addCarta(sangrar.criaCopia());
                heroi.addCarta(beberVeneno.criaCopia());

                heroi.addCarta(sangrar.criaCopia());
                heroi.addCarta(beberVeneno.criaCopia());

                heroi.addCarta(sangrar.criaCopia());
                heroi.addCarta(beberVeneno.criaCopia());

                heroi.addCarta(sangrar.criaCopia());
                heroi.addCarta(beberVeneno.criaCopia());

                heroi.addCarta(sangrar.criaCopia());
                heroi.addCarta(beberVeneno.criaCopia());

                heroi.addCarta(sangrar.criaCopia());
                heroi.addCarta(beberVeneno.criaCopia());

                heroi.addCarta(sangrar.criaCopia());
                heroi.addCarta(beberVeneno.criaCopia());

                heroi.addCarta(sangrar.criaCopia());
                heroi.addCarta(beberVeneno.criaCopia());
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

    /* recebe uma lista de carta, divide em paginas e retorna uma lista de strings (pra usar no selecionar)*/
    public static List<List<String>> montaPaginas(List<Carta> cartas) {
        List<List<String>> listaCompleta = new ArrayList<>();

        // cria uma lista com o nome das cartas
        List<String> cartasString = new ArrayList<>();

        for (Carta carta : cartas) {
            cartasString.add(carta.getNome());
        }
        
        for (int i = 0; i < cartasString.size(); i += 7) {
            // garante que nao vai passar do tamanho da lista qnd chega na ultima pagina
            int fimPagina = Math.min(i + 7, cartasString.size()); 

            // cria a pagina com os itens de i ate fimPagina da lista de nome de cartas
            List<String> paginaAtual = new ArrayList<>(cartasString.subList(i, fimPagina));

            // tinha um if aqui pra deixar mais bonito e so printar quando precisa mas atrapalha depois na hora de lidar com a escolha do usuario
            // if (i != 0)
                paginaAtual.add(Cor.txtCinza("Página anterior"));
            // if (i + 7 < cartasString.size())
                paginaAtual.add(Cor.txtCinza("Próxima página"));
            
            listaCompleta.add(paginaAtual);
        }
        
        return listaCompleta;
    }

    // faz o menu de cartas usando a matriz, cuida da troca de cartas. true = baralho, false = inventario de cartas.
    public static void menuCartas(Heroi heroi, boolean baralho){
        int pagina = 0;
        Carta carta;
        while (true) { 
            List<List<String>> matrizPaginas = new ArrayList<>();

            // verifica se é pra usar o baralho ou o inventario de carta
            if (baralho) matrizPaginas.addAll(montaPaginas(heroi.getBaralho()));
            else matrizPaginas.addAll(montaPaginas(heroi.getInventarioCartas()));

            // quando vc tirar a ultima carta ele volta sozinho
            if (matrizPaginas.size() <= 0) break;

            // se vc acabar com as cartas de uma pagina ele volta pra anterior.
            if (pagina >= matrizPaginas.size())
                pagina --;

            List<String> paginaAtual = matrizPaginas.get(pagina);
            int opcao = InputHandler.selecionar(paginaAtual, true, "Página " + (pagina + 1));

            // -1: exit -> todas as cartas -> penultima e ultima: voltar e proxima
            if (opcao == -1) break;

            else if (opcao == paginaAtual.size() - 2) {
                if (pagina > 0)
                    pagina--;
                Textos.sleep(50);
                continue;
            }
            else if (opcao == paginaAtual.size() - 1){
                if (pagina < matrizPaginas.size() - 1)
                    pagina++;
                Textos.sleep(50);
                continue;
            }

            // cada pagina equivalem a 7 cartas
            int posicaoCarta = pagina*7 + opcao;

            // se chegou ate aqui é uma carta. ve se e inventario ou baralho e troca de um pro outro
            if (baralho)
                carta = heroi.getBaralho().get( posicaoCarta );
            else 
                carta = heroi.getInventarioCartas().get( posicaoCarta );

            heroi.trocaCarta(carta);
        }
    }
}
