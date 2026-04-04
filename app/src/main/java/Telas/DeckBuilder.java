package Telas;

import java.util.ArrayList;
import java.util.List;

import Cartas.Carta;
import Cartas.CartaAtaque;
import Cartas.CartaAtaqueComEfeito;
import Cartas.CartaHabilidade;
import Cartas.CartaMaldicao;
import Cartas.CartaPoder;
import EfeitosDeStatus.Buffs.AumentaDano;
import EfeitosDeStatus.Buffs.AumentaResistencia;
import EfeitosDeStatus.DanosConstantes.DanoConstante;
import EfeitosDeStatus.DanosConstantes.Sangramento;
import EfeitosDeStatus.DanosConstantes.Veneno;
import EfeitosDeStatus.Efeito;
import EfeitosDeStatus.Energizar;
import EfeitosDeStatus.Instantaneos.Escudo;
import EfeitosDeStatus.Instantaneos.GanhaEnergia;
import EfeitosDeStatus.Instantaneos.Purificar;
import EfeitosDeStatus.Instantaneos.PuxaCarta;
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
    Efeito ganhaEnergia2 = new GanhaEnergia("Ganho de energia (2)", "Ganha 2 pontos de energia", 0, 2);
    Efeito ganhaEnergia1 = new GanhaEnergia("Ganho de energia (1)", "Ganha 1 ponto de energia", 0, 1);
    // novos efeitos!
    Efeito efeitoPuxaCarta2 = new PuxaCarta("Puxa duas cartas", "Puxa duas cartas", 0, 2);
    Efeito efeitoEnergizado = new Energizar("Energizado", "Ganhe dois pontos de energia na proxima rodada", 1, 2);

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
    // novas cartas!
    Carta puxaCarta;
    Carta energizar;
    Carta energiaGratis;
    Carta nada;

    public DeckBuilder(){
        decksPadrao.add(Cor.txtVermelho("MESTRE DAS LAMINAS: CORTES INFINITOS, DEBUFFS INFINITOS, RODADA INFIITA!"));
        decksPadrao.add(Cor.txtAmarelo("JOHN WICK: CAUSE MUITO DANO DIRETO EM UMA CHUVA DE BALAS!"));
        decksPadrao.add(Cor.txtVerde("BALANCEADO: NEM UM NEM OUTRO, NEM TA MUITO BALANCEADO INCLUSIVE!"));

        tiro = new CartaAtaque("Tiro de revolver", "", 2, 3, 1); tiro.setResenha(Arte.TIRO5);
        tiroEscopeta = new CartaAtaque("Tiro de escopeta", "", 3, 6, 1); tiroEscopeta.setResenha(Arte.TIRO1);

        tiroCanhao = new CartaAtaqueComEfeito("Tiro de canhão", "dispara uma bala de canhão", 4, 9, feridas, false, 1); tiroCanhao.setResenha(Arte.TIRO4);
        corteProfundo = new CartaAtaqueComEfeito("Corte profundo", "", 2, 2, sangramento, false, 2); corteProfundo.setResenha(Cor.txtVermelho(Arte.CORTE));
        corteVenenoso = new CartaAtaqueComEfeito("Corte venenoso", "", 1, 1, veneno, false, 2); corteVenenoso.setResenha(Cor.txtVerdeClaro(Arte.CORTE2));
        corteDefensivo = new CartaAtaqueComEfeito("Corte defensivo", "bate e ganha escudo!", 1, 1, escudinho, true, 2); corteDefensivo.setResenha(Cor.txtAzulClaro(Arte.CORTE5));
        corteRapido = new CartaAtaqueComEfeito("Corte rapido", "bate e ganha 1 ponto de energia!", 1, 1, ganhaEnergia1, true, 2); corteRapido.setResenha(Cor.txtAmareloClaro(Arte.CORTE4));
        desprezo = new CartaAtaqueComEfeito("DESPREZO", "causa muito dano porém irrita seu adversario", 4, 12, odioPuro, false); desprezo.setResenha(Arte.DESPREZO);

        armadura = new CartaHabilidade("Armadura", "reduz o dano recebido", 2, aumentaResistencia, true);
        escudoMadeira = new CartaHabilidade("Postura de defesa", "da escudo", 0, escudinho, true);
        escudoFerro = new CartaHabilidade("Aura de defesa", "da MUITO escudo", 1, escudao, true);
        purificar = new CartaHabilidade("Receba!", "Remove todos os efeitos aplicados em voce (incluindo positivos)", 2, purificarEfeito, true); purificar.setResenha(Cor.txtAmarelo(Arte.RECEBA));
        puroOdio = new CartaHabilidade("PURO ODIO", "Cause 1 de dano extra por 3 rodadas", 2, odioPuro, true);
        puxaCarta = new CartaHabilidade("Ganancia", "Puxe duas cartas da sua pilha de compras", 1, efeitoPuxaCarta2, true);
        energizar = new CartaHabilidade("ENERGIZAR!", "Ganhe mais 2 pontos de energia no começo da próxima rodada!", 1, efeitoEnergizado, true);
        energiaGratis = new CartaHabilidade("Energia gratis!", "Ganhe 1 ponto de energia", 0, ganhaEnergia1, true);
        
        dedoNervosoCarta = new CartaPoder("JOHN WICK", "Para cada acúmulo, atire novamente sempre que usar uma carta de tiro!", 2, dedoNervoso);
        mestreLaminasCarta = new CartaPoder("Mestre das lâminas", "Para cada acúmulo, corte novamente sempre que usar uma carta de corte!", 2, mestreLaminas);

        nada = new CartaMaldicao("NADA!", "NAO FAZ NADA!", 1, false);
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
