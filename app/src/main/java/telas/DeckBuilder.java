package telas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import cartas.Carta;
import entidades.Heroi;
import static fabricas.FabricaCartas.bomba;
import static fabricas.FabricaCartas.bombaSuprema;
import static fabricas.FabricaCartas.bombaVeneno;
import static fabricas.FabricaCartas.chuvaLaminas;
import static fabricas.FabricaCartas.clarividencia;
import static fabricas.FabricaCartas.corteProfundo;
import static fabricas.FabricaCartas.corteVenenoso;
import static fabricas.FabricaCartas.ecoDolor;
import static fabricas.FabricaCartas.espada;
import static fabricas.FabricaCartas.freestyle;
import static fabricas.FabricaCartas.ganancia;
import static fabricas.FabricaCartas.mestreLaminasCarta;
import static fabricas.FabricaCartas.reciclagem;
import static fabricas.FabricaCartas.shieldinho;
import static fabricas.FabricaCartas.tiro;
import static fabricas.FabricaCartas.tiroEscopeta;
import static fabricas.FabricaItens.amuletoVelho;
import static fabricas.FabricaItens.marmita;
import util.InputHandler;
import util.Recompensas;
import visual.Arte;
import visual.Cor;
import visual.Textos;

/** permite passar cartas do inventário para o baralho ou, por um preço, remover cartas do baralho. tambem gerencia o deck inicial */
public class DeckBuilder {
    private static final List<String> DECKS_PADRAO = new ArrayList<>(); // decks padrao pra testagem ate ter o deckbuilder de vdd

    private static final List<String> OPCOES_MENU_INICIAL = new ArrayList<>(Arrays.asList(
    "inventario de cartas", 
    "baralho " + Cor.txtAmareloClaro("(50 dolares pra remover)"),
    "inventario de itens ativos",
    "inventario de itens passivos"
    ));

    // limite minimo de cartas q vc precisa ter no baralho pra poder sair daqui.
    private static final int LIMITE_MINIMO_DE_CARTAS = 10;


    /** exibe uma escolha entre os decks iniciais */
    public static void mostrarDecksPadrao(Heroi heroi){

        DECKS_PADRAO.add(Cor.txtVermelho("Kit tramontina" + Cor.reset + " + " + Cor.rosa +"amuleto velho"));
        DECKS_PADRAO.add(Cor.txtAmarelo("Kit john wick" + Cor.reset + " + " + Cor.rosa +"marmita"));
        DECKS_PADRAO.add(Cor.txtCinza("Terceira opçao secreta (DECK DE TESTE, ATIVA O MODO DEBUG)"));

        Textos.limpaTela();
        int escolha = InputHandler.selecionar(DECKS_PADRAO, Cor.reset + "Escolha um kit inicial: \n" + Cor.txtCinza("(voce ira ganhar mais carts pra personalizar depois!)")); 

        switch (escolha){
            /** deck inicial baseado em cortes */
            case 0 -> { 
                heroi.addCarta(espada);
                heroi.addCarta(espada);
                heroi.addCarta(espada);
                heroi.addCarta(espada);

                heroi.addCarta(corteVenenoso);
                heroi.addCarta(corteProfundo);

                heroi.addCarta(shieldinho);
                heroi.addCarta(shieldinho);
                heroi.addCarta(shieldinho);
                heroi.addCarta(shieldinho);

                heroi.addCarta(reciclagem);
                heroi.addCarta(chuvaLaminas);

                Recompensas.ganharItemPassivoEsp(amuletoVelho, heroi);
                
                Recompensas.ganharDinheiro(80, heroi);
            }
            /* deck de dano direto */
            case 1 -> {
                heroi.addCarta(tiro);
                heroi.addCarta(tiro);
                heroi.addCarta(tiro);
                heroi.addCarta(tiro);

                heroi.addCarta(tiroEscopeta);    
                heroi.addCarta(tiroEscopeta);     
                
                heroi.addCarta(bomba);
                heroi.addCarta(bombaVeneno);

                heroi.addCarta(shieldinho);
                heroi.addCarta(shieldinho);
                heroi.addCarta(shieldinho);
                heroi.addCarta(shieldinho);

                Recompensas.ganharItemPassivoEsp(marmita, heroi);

                Recompensas.ganharDinheiro(80, heroi);
            }  
            // deck pra testar carta
            case 2 -> {
                heroi.addCarta(ecoDolor);
                heroi.addCarta(ecoDolor);
                heroi.addCarta(ecoDolor);

                heroi.addCarta(mestreLaminasCarta);
                heroi.addCarta(mestreLaminasCarta);
                
                heroi.addCarta(bombaSuprema);
                
                heroi.addCarta(corteProfundo);
                heroi.addCarta(corteVenenoso);
                
                heroi.addCarta(clarividencia);
                heroi.addCarta(clarividencia);
                heroi.addCarta(clarividencia);
                heroi.addCarta(clarividencia);

                heroi.addCarta(ganancia);
                heroi.addCarta(ganancia);
                heroi.addCarta(ganancia);

                heroi.addCarta(reciclagem);
                heroi.addCarta(reciclagem);

                heroi.addCarta(freestyle);
                heroi.addCarta(freestyle);
                heroi.addCarta(freestyle);

                // Recompensas.ganharItemPassivoEsp(itemManterEnergia, heroi);
                // Recompensas.ganharItemPassivoEsp(itemManterEscudo, heroi);
                // Recompensas.ganharItemPassivoEsp(item10EscudoInicial, heroi);
                // Recompensas.ganharItemPassivoEsp(facaAcougueiro, heroi);

                // heroi.addItemAtivo(vape);
                // heroi.addItemAtivo(vape);
                // heroi.addItemAtivo(vape);
                // heroi.addItemAtivo(cigarro);
                // heroi.addItemAtivo(cigarro);
                // heroi.addItemAtivo(cigarro);

                // heroi.addItemPassivo(amuletoVelho);
                
                Recompensas.ganharDinheiro(300, heroi);
                heroi.setEnergiaMax(60);
                heroi.setTestMode(true);
            }
        }
        Textos.limpaTela();
    }

    /** inicia o menu, permitindo ao jogador alternar entre o inventário 
     * e o baralho atual, além de gerenciar a troca e remoção de cartas. O jogador só pode sair 
     * se o seu baralho contiver pelo menos o limite mínimo de cartas exigido.
     */
    public static void iniciar(Heroi heroi){
        while (true){
            int opcao = InputHandler.selecionar(OPCOES_MENU_INICIAL, true, Cor.txtAmareloClaro(Arte.deckBuilder) + "\n" + Textos.menuStatus(heroi), "voltar para o mapa.");

            // escolheu voltar, sai do loop. mas so se tiver acima do limite minimo.
            if (opcao == -1) {
                if (heroi.getBaralho().size() >= LIMITE_MINIMO_DE_CARTAS) 
                    break;
                else{
                    System.out.println("eu nao vou te deixar sair daqui com menos de 10 cartas nesse baralho.");
                    InputHandler.esperar();
                }
            }
            switch (opcao) {
                // 0 -> inventario de cartas
                case 0 -> {
                    if (!heroi.getInventarioCartas().isEmpty()) 
                        menuCartas(heroi, false);
                    else {
                        System.out.println("Voce nao possui cartas no seu inventario.");
                        InputHandler.esperar();
                    }
                }
                // 1 -> baralho
                case 1 -> {
                    if (!heroi.getBaralho().isEmpty()) 
                        menuCartas(heroi, true);
                    else {
                        System.out.println("Voce nao possui cartas no seu baralho.");
                        InputHandler.esperar();
                    }
                }
                // 2 -> inventario de itens ativos
                case 2 -> {
                    heroi.mostrarItensAtivos();

                    if (!heroi.getListaItensAtivos().isEmpty())
                        InputHandler.esperar();
                }
                // 3 -> inventario de itens passivos
                case 3 -> {
                    heroi.mostrarItensPassivos();

                    if (!heroi.getListaItensPassivos().isEmpty())
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
                Recompensas.gastarDinheiro(5, heroi);
                carta = heroi.getBaralho().get( posicaoCarta );
            }   
            else 
                carta = heroi.getInventarioCartas().get( posicaoCarta );

            heroi.trocaCarta(carta);
        }
    }
}
