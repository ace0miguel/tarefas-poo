package telas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import cartas.Carta;
import entidades.Heroi;
import util.InputHandler;
import util.Recompensas;
import visual.Arte;
import visual.Cor;
import visual.Textos;

import static fabricas.FabricaCartas.armadura;
import static fabricas.FabricaCartas.bombaSuprema;
import static fabricas.FabricaCartas.bombaVeneno;
import static fabricas.FabricaCartas.contratoSangue;
import static fabricas.FabricaCartas.corteDefensivo;
import static fabricas.FabricaCartas.corteProfundo;
import static fabricas.FabricaCartas.corteRapido;
import static fabricas.FabricaCartas.corteVenenoso;
import static fabricas.FabricaCartas.ecoDolor;
import static fabricas.FabricaCartas.egoCarta;
import static fabricas.FabricaCartas.energiaSupremo;
import static fabricas.FabricaCartas.energizar;
import static fabricas.FabricaCartas.mestreLaminasCarta;
import static fabricas.FabricaCartas.pactoSangue;
import static fabricas.FabricaCartas.presenteMaldito;
import static fabricas.FabricaCartas.puxaCarta;
import static fabricas.FabricaCartas.shieldao;
import static fabricas.FabricaCartas.shieldinho;
import static fabricas.FabricaCartas.tiro;
import static fabricas.FabricaCartas.tiroEscopeta;
import static fabricas.FabricaItens.*;

/** permite passar cartas do inventário para o baralho ou, por um preço, remover cartas do baralho */
public class DeckBuilder {
    private static List<String> decksPadrao = new ArrayList<>(); // decks padrao pra testagem ate ter o deckbuilder de vdd
    private static List<String> menuInicial = new ArrayList<>(Arrays.asList("inventario", "baralho " + Cor.txtAmareloClaro("(50 dolares pra remover)")));

    // limite minimo de cartas q vc precisa ter no baralho pra poder sair daqui.
    private static int limiteMinimoCartas = 10;


    /** exibe os decks padrao ja montados */
    public static void mostrarDecksPadrao(Heroi heroi){
        // nomes dos decks padrao
        decksPadrao.add(Cor.txtVermelho("Samurai: Cartas de corte ( menos dano, aplicam efeito )" + Cor.reset + " +" + Cor.rosa +" amuleto velho"));
        decksPadrao.add(Cor.txtAmarelo("John wick: Cartas de tiro ( mais dano direto, sem efeito )" + Cor.reset + " +" + Cor.rosa +" marmita"));
        decksPadrao.add(Cor.txtCinza("deck de teste (ativa o modo teste)"));

        Textos.limpaTela();
        int escolha = InputHandler.selecionar(decksPadrao, Cor.reset + "Escolha um baralho inicial: \n" + Cor.txtCinza("(voce ira ganhar mais carts pra personalizar depois!)")); 

        switch (escolha){
            /** deck inicial baseado em cortes */
            case 0 -> { 
                heroi.addCarta(corteVenenoso);
                heroi.addCarta(corteVenenoso);

                heroi.addCarta(corteProfundo);
                heroi.addCarta(corteProfundo);

                heroi.addCarta(corteRapido);
                heroi.addCarta(corteRapido);

                heroi.addCarta(corteDefensivo);
                heroi.addCarta(corteDefensivo);

                heroi.addCarta(shieldao);

                heroi.addCarta(shieldinho);
                heroi.addCarta(shieldinho);

                heroi.addCarta(energizar);

                Recompensas.ganharItemPassivoEsp(amuletoVelho, heroi);
            }
            /* deck de dano direto */
            case 1 -> {
                heroi.addCarta(tiro);
                heroi.addCarta(tiro);

                heroi.addCarta(tiro);
                heroi.addCarta(tiro);

                heroi.addCarta(tiroEscopeta);     
                heroi.addCarta(tiroEscopeta);
                
                heroi.addCarta(shieldinho);
                heroi.addCarta(shieldinho);

                heroi.addCarta(shieldao);
                heroi.addCarta(shieldao);

                heroi.addCarta(egoCarta);
                heroi.addCarta(armadura);

                Recompensas.ganharItemPassivoEsp(marmita, heroi);
                
            }  
            // deck pra testar carta
            case 2 -> {
                heroi.addCarta(ecoDolor);
                heroi.addCarta(ecoDolor);
                heroi.addCarta(ecoDolor);

                heroi.addCarta(mestreLaminasCarta);
                heroi.addCarta(mestreLaminasCarta);

                heroi.addCarta(bombaVeneno);
                heroi.addCarta(bombaVeneno);
                heroi.addCarta(bombaVeneno);
                heroi.addCarta(bombaVeneno);
                
                heroi.addCarta(bombaSuprema);
           
                heroi.addCarta(puxaCarta);
                heroi.addCarta(puxaCarta); 
                
                heroi.addCarta(corteDefensivo);
                heroi.addCarta(corteProfundo);
                heroi.addCarta(corteVenenoso);
                heroi.addCarta(corteRapido);

                heroi.addCarta(energiaSupremo);
                
                heroi.addCarta(pactoSangue);
                heroi.addCarta(pactoSangue);
                heroi.addCarta(pactoSangue);
                heroi.addCarta(pactoSangue);

                heroi.addCarta(presenteMaldito);
                heroi.addCarta(presenteMaldito);
                heroi.addCarta(presenteMaldito);

                heroi.addCarta(contratoSangue);
                heroi.addCarta(contratoSangue);

                heroi.addCarta(mestreLaminasCarta);
                heroi.addCarta(mestreLaminasCarta);

                // Recompensas.ganharItemPassivoEsp(facaAcougueiro, heroi);
                // Recompensas.ganharItemPassivoEsp(marmita, heroi);
                // Recompensas.ganharItemPassivoEsp(amuletoVelho, heroi);

                Recompensas.ganharItemAtivoEsp(cigarro, heroi);
                Recompensas.ganharItemAtivoEsp(cigarro, heroi);
                Recompensas.ganharItemAtivoEsp(vape, heroi);
                Recompensas.ganharItemAtivoEsp(vape, heroi);
                Recompensas.ganharItemAtivoEsp(vape, heroi);

                Recompensas.ganharItemAtivoEsp(pocaoCura40, heroi);

                Recompensas.ganharItemAtivoEsp(pocaoEnergia2, heroi);
                Recompensas.ganharItemAtivoEsp(pocaoEnergia2, heroi);
                

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
            int opcao = InputHandler.selecionar(menuInicial, true, Cor.txtAmareloClaro(Arte.deckBuilder) + "\n" + Textos.menuStatus(heroi), "voltar para o mapa.");

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
                Recompensas.gastarDinheiro(5, heroi);
                carta = heroi.getBaralho().get( posicaoCarta );
            }   
            else 
                carta = heroi.getInventarioCartas().get( posicaoCarta );

            heroi.trocaCarta(carta);
        }
    }
}
