package Telas;

import java.util.ArrayList;
import java.util.List;

import Entidades.Heroi;
import Util.Cor;
import Util.InputHandler;
import Util.Textos;
import static Util.Moldes.*;

public class DeckBuilder {
    List<String> decksPadrao = new ArrayList<>(); // decks padrao pra testagem ate ter o deckbuilder de vdd

    public DeckBuilder(){
        decksPadrao.add(Cor.txtVermelho("MESTRE DAS LAMINAS: CORTES INFINITOS, DEBUFFS INFINITOS, RODADA INFIITA!"));
        decksPadrao.add(Cor.txtAmarelo("JOHN WICK: CAUSE MUITO DANO DIRETO EM UMA CHUVA DE BALAS!"));
        decksPadrao.add(Cor.txtVerde("BALANCEADO: NEM UM NEM OUTRO, NEM TA MUITO BALANCEADO INCLUSIVE!"));
    }

    public void rodar(Heroi heroi){
        Textos.limpaTela();
        int escolha = InputHandler.selecionar(decksPadrao, Cor.txtCinza("Escolha um baralho:")); 

        switch (escolha){
            /*a ideia por tras desse deck aqui é q meio q vc tenta atingir um jackpot stackando mestre das laminas + usando cartas que ganham energia 
            + usando cartas baratas + usar a mao completa pra ganhar mais 2 de energia -> tentar repetir o ciclo -> turno infinito. dano infinito. dopamina infinita. */
            case 0 -> { 
                // 2 de cada corte + 2 mestre laminas pra faze uns combao
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

                heroi.addCarta(energiaGratis);
                heroi.addCarta(energiaGratis);

                heroi.addCarta(escudoFerro);
                heroi.addCarta(escudoFerro);
                heroi.addCarta(escudoMadeira);
            }
            /* deck de dano direto mesmo bem simples, 2 puro odio pra da mais dano ainda */
            case 1 -> {
                // 2 de cada tiro
                heroi.addCarta(tiro);
                heroi.addCarta(tiroEscopeta);
                heroi.addCarta(tiroCanhao);

                heroi.addCarta(tiro);
                heroi.addCarta(tiroEscopeta);
                heroi.addCarta(tiroCanhao);
                
                // 2 purificar
                heroi.addCarta(purificar);
                heroi.addCarta(purificar);

                heroi.addCarta(desprezo);
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
            }  
            /* tentei fazer esse ser um deck equilibrado nao sei se deu certo eu pq eu so jogo com o mestre das laminas honestamente */
            case 2 -> {
                heroi.addCarta(tiro);
                heroi.addCarta(tiro);
                heroi.addCarta(tiro);
                heroi.addCarta(tiro);

                heroi.addCarta(tiroEscopeta);
                heroi.addCarta(tiroEscopeta);
                heroi.addCarta(tiroEscopeta);

                heroi.addCarta(corteVenenoso);
                heroi.addCarta(corteVenenoso);
                heroi.addCarta(corteVenenoso);

                heroi.addCarta(corteProfundo);
                heroi.addCarta(corteProfundo);
                heroi.addCarta(corteProfundo);

                heroi.addCarta(desprezo);
                
                heroi.addCarta(purificar);
                heroi.addCarta(purificar);

                heroi.addCarta(puxaCarta); 
                heroi.addCarta(puxaCarta); 

                heroi.addCarta(energizar); 
                heroi.addCarta(energizar); 
              
                heroi.addCarta(energiaGratis);
                heroi.addCarta(energiaGratis);

                heroi.addCarta(puroOdio);
                heroi.addCarta(puroOdio);

                heroi.addCarta(armadura);
                heroi.addCarta(armadura);

                heroi.addCarta(escudoFerro);
                heroi.addCarta(escudoFerro);

                heroi.addCarta(escudoMadeira);
                heroi.addCarta(escudoMadeira);
            }
        }
        Textos.limpaTela();
    }
}
