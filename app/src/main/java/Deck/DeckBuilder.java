package Deck;

import java.util.ArrayList;
import java.util.List;

import Cartas.Carta;
import Cartas.CartaAtaque;
import Cartas.CartaAtaqueComEfeito;
import Cartas.CartaHabilidade;
import Cartas.CartaPoder;
import EfeitosDeStatus.Buffs.AumentaDano;
import EfeitosDeStatus.Buffs.AumentaResistencia;
import EfeitosDeStatus.DanosConstantes.DanoConstante;
import EfeitosDeStatus.DanosConstantes.Sangramento;
import EfeitosDeStatus.DanosConstantes.Veneno;
import EfeitosDeStatus.Efeito;
import EfeitosDeStatus.Instantaneos.Escudo;
import EfeitosDeStatus.Instantaneos.GanhaEnergia;
import EfeitosDeStatus.Instantaneos.Purificar;
import Entidades.Heroi;
import Poderes.MaosLeves;
import Poderes.Poder;
import Util.Arte;
import Util.Cor;
import Util.InputHandler;
import Util.Textos;

public class DeckBuilder {
    List<String> decksPadrao = new ArrayList<>(); // decks padrao pra testagem ate ter o deckbuilder de vdd

    // instancias aqui guardadas pra teste
    
    // efeitos ------------
    Efeito sangramento = new Sangramento("Sangramento", "Causa 1 de dano por rodada ao alvo", 3, 1);
    Efeito veneno = new Veneno("Veneno", "Causa sua duraçao em dano por rodada ao alvo", 2, 1);
    Efeito odioPuro = new AumentaDano("Ódio Puro", "Aumenta o dano causado em 1 por 3 rodadas", 3, 1);
    Efeito aumentaResistencia = new AumentaResistencia("Armadura", "Reduz o dano recebido", 3, 2);
    Efeito escudinho = new Escudo("Ganho de escudo (3)", "3 pontos de escudo", 0, 3);
    Efeito escudao = new Escudo("Ganho de escudo (7)", "7 pontos de escudo", 0, 7);
    Efeito purificarEfeito = new Purificar("Purificar", "Remove todos os efeitos aplicados em voce (incluindo bons!)", 0);
    Efeito feridas = new DanoConstante("Feridas", "Causa 1 de dano por rodada ao alvo por 2 rodadas", 2, 1);
    Efeito ganhaEnergia2 = new GanhaEnergia("Ganho de energia (2)", "Ganha 2 ponto de energia", 0, 2);

    // poderes -------------
    Poder dedoNervoso = new MaosLeves(("JOHN WICK!"), "Sempre que atirar, ATIRE NOVAMENTE! pelo tanto de acumulos desse poder.", 1);
    Poder mestreLaminas = new MaosLeves(("MESTRE DAS LÂMINAS"), "Sempre que cortar, CORTE NOVAMENTE! pelo tanto de acumulos desse poder.", 2);

    // cartas -----------
    Carta tiro;
    Carta tiroEscopeta;
    Carta tiroCanhao;
    Carta corteProfundo;
    Carta corteVenenoso;
    Carta corteDefensivo;
    Carta corteRapido;
    Carta desprezo;
    Carta armadura;
    Carta escudoMadeira;
    Carta escudoFerro;
    Carta purificar;
    Carta puroOdio;
    Carta dedoNervosoCarta;
    Carta mestreLaminasCarta;


    public DeckBuilder(){
        decksPadrao.add("BALISTICO (FOCO EM DANO DIRETO)");
        decksPadrao.add("ARMAS BRANCAS (FOCO EM STACKAR DEBUFFS E SE DEFENDER)");
        decksPadrao.add("BALANCEADO (BALANCEADO)");

        tiro = new CartaAtaque("Tiro de revolver", "", 2, 3, 1); tiro.setResenha(Arte.TIRO5);
        tiroEscopeta = new CartaAtaque("Tiro de escopeta", "", 3, 6, 1); tiroEscopeta.setResenha(Arte.TIRO1);

        tiroCanhao = new CartaAtaqueComEfeito("Tiro de canhão", "dispara uma bala de canhão", 4, 9, feridas, false, 1); tiroCanhao.setResenha(Arte.TIRO4);
        corteProfundo = new CartaAtaqueComEfeito("Corte profundo", "", 1, 2, sangramento, false, 2); corteProfundo.setResenha(Cor.txtVermelho(Arte.CORTE));
        corteVenenoso = new CartaAtaqueComEfeito("Corte venenoso", "", 1, 1, veneno, false, 2); corteVenenoso.setResenha(Cor.txtVerdeClaro(Arte.CORTE2));
        corteDefensivo = new CartaAtaqueComEfeito("Corte defensivo", "bate e ganha escudo!", 1, 1, escudinho, true, 2); corteDefensivo.setResenha(Cor.txtAzulClaro(Arte.CORTE5));
        corteRapido = new CartaAtaqueComEfeito("Corte rapido", "bate e ganha 2 pontos de energia!", 1, 1, ganhaEnergia2, true, 2); corteRapido.setResenha(Cor.txtAmareloClaro(Arte.CORTE4));
        desprezo = new CartaAtaqueComEfeito("DESPREZO", "causa muito dano porém irrita seu adversario", 4, 10, odioPuro, false); desprezo.setResenha(Arte.DESPREZO);

        armadura = new CartaHabilidade("armadura", "reduz o dano recebido", 2, aumentaResistencia, true);
        escudoMadeira = new CartaHabilidade("Postura de defesa", "da escudo", 0, escudinho, true);
        escudoFerro = new CartaHabilidade("Aura de defesa", "da MUITO escudo", 1, escudao, true);
        purificar = new CartaHabilidade("Receba!", "Remove todos os efeitos aplicados em voce (incluindo positivos)", 2, purificarEfeito, true); purificar.setResenha(Cor.txtAmarelo(Arte.RECEBA));
        puroOdio = new CartaHabilidade("PURO ODIO", "Cause 1 de dano extra por 3 rodadas", 2, odioPuro, true);
    
        dedoNervosoCarta = new CartaPoder("JOHN WICK", "Para cada acúmulo, atire novamente sempre que usar uma carta de tiro!", 2, dedoNervoso);
        mestreLaminasCarta = new CartaPoder("Mestre das lâminas", "Para cada acúmulo, corte novamente sempre que usar uma carta de corte!", 1, mestreLaminas);
    }

    public void rodar(Heroi heroi){
        Textos.limpaTela();
        int escolha = InputHandler.selecionar(decksPadrao, "Por enquanto nao tem montador de deck vai ter que se contentar com isso:");

        switch (escolha){
            case 0 -> {
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

                heroi.addCarta(dedoNervosoCarta);
                heroi.addCarta(puroOdio);
                heroi.addCarta(escudoMadeira);
            }
            case 1 -> {
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

                heroi.addCarta(escudoFerro);
                heroi.addCarta(escudoMadeira);
            }
            case 2 -> {
                heroi.addCarta(tiro);
                heroi.addCarta(tiro);

                heroi.addCarta(tiroEscopeta);

                heroi.addCarta(corteVenenoso);
                heroi.addCarta(corteVenenoso);

                heroi.addCarta(corteProfundo);
                heroi.addCarta(corteProfundo);

                heroi.addCarta(purificar);
                heroi.addCarta(purificar);

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
