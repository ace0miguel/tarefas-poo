package Util;

import java.util.ArrayList;
import java.util.Arrays;
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
import Entidades.Inimigo;
import Poderes.MaosLeves;
import Poderes.Poder;
import Util.Acao.Atacar;

/*  centraliza as instancias criadas, tudo é publico e static.
aqui so tem os moldes, sempre que for usar algo criar copia (todos tem o metodo criaCopia()) 
pra importar essa classe aqui usar import static (import static Util.Moldes.*) */
public class Moldes {
    public static List<Inimigo> listaInimigosMoldes = new ArrayList<>();
    public static List<Efeito> listaEfeitosMoldes = new ArrayList<>();
    public static List<Carta> listaCartasMoldes = new ArrayList<>();

    // instancias aqui guardadas pra teste

    // inimigos -----
    public static Inimigo barbossa;
    public static Inimigo loudCoringa;
    public static Inimigo endrick;
    public static Inimigo drake;
    public static Inimigo paulAtreides;
    public static Inimigo sabrinaCarpenter;
    public static Inimigo tripleT;
    
    // efeitos ------------
    public static Efeito sangramento = new Sangramento("Sangramento", "Causa 1 de dano por rodada ao alvo", 3, 1);
    public static Efeito veneno = new Veneno("Veneno", "Causa sua duraçao em dano por rodada ao alvo", 2, 1);
    public static Efeito veneno4 = new Veneno("Veneno", "Causa sua duraçao em dano por rodada ao alvo", 4, 1);
    public static Efeito odioPuro = new AumentaDano("Ódio Puro", "Aumenta o dano causado em 1 por 3 rodadas", 3, 1);
    public static Efeito aumentaResistencia = new AumentaResistencia("Armadura", "Reduz o dano recebido", 3, 2);
    public static Efeito escudinho = new Escudo("Ganho de escudo (3)", "3 pontos de escudo", 0, 3);
    public static Efeito escudao = new Escudo("Ganho de escudo (7)", "7 pontos de escudo", 0, 7);
    public static Efeito purificarEfeito = new Purificar("Purificar", "Remove todos os efeitos aplicados em voce (incluindo bons!)", 0);
    public static Efeito feridas = new DanoConstante("Feridas", "Causa 1 de dano por rodada ao alvo por 2 rodadas", 2, 1);
    public static Efeito ganhaEnergia2 = new GanhaEnergia("Ganho de energia (2)", "Ganha 2 pontos de energia", 0, 2);
    public static Efeito ganhaEnergia1 = new GanhaEnergia("Ganho de energia (1)", "Ganha 1 ponto de energia", 0, 1);
    public static Efeito pactoSinistro = new AumentaDano(Cor.txtCinza("Pacto Sinistro"), "Aumenta o dano causado em 2 por 2 rodadas", 2, 2);
    
    // novos efeitos
    public static Efeito efeitoPuxaCarta2 = new PuxaCarta("Puxa duas cartas", "Puxa duas cartas", 0, 2);
    public static Efeito efeitoEnergizado = new Energizar("Energizado", "Ganhe dois pontos de energia na proxima rodada", 1, 2);

    // poderes -------------
    public static Poder dedoNervoso = new MaosLeves(("JOHN WICK!"), "Sempre que atirar, ATIRE NOVAMENTE! pelo tanto de acumulos desse poder.", 1);
    public static Poder mestreLaminas = new MaosLeves(("MESTRE DAS LÂMINAS"), "Sempre que cortar, CORTE NOVAMENTE! pelo tanto de acumulos desse poder.", 2);

    // cartas -----------
    public static Carta tiro;
    public static Carta tiroEscopeta;
    public static Carta tiroCanhao;
    public static Carta corteProfundo;
    public static Carta corteVenenoso;
    public static Carta corteDefensivo;
    public static Carta corteRapido;
    public static Carta desprezo;
    public static Carta armadura;
    public static Carta escudoMadeira;
    public static Carta escudoFerro;
    public static Carta purificar;
    public static Carta puroOdio;
    public static Carta dedoNervosoCarta;
    public static Carta mestreLaminasCarta;
    // novas cartas
    public static Carta puxaCarta;
    public static Carta energizar;
    public static Carta energiaGratis;
    public static Carta sangrar;
    public static Carta beberVeneno;
    public static Carta bomba;
    public static Carta bombaVeneno;

    public static void carregar(){
        // cartas ---
        tiro = new CartaAtaque("Tiro de revolver", "", 2, 5, 1); tiro.setResenha(Arte.TIRO5);
        tiroEscopeta = new CartaAtaque("Tiro de escopeta", "", 3, 8, 1); tiroEscopeta.setResenha(Arte.TIRO1);
        bomba = new CartaAtaque("BOMBA!", "joga uma bomba que atinge TODOS os inimigos!", 5, 8); bomba.setEfeitoEmArea(true);

        tiroCanhao = new CartaAtaqueComEfeito("Tiro de canhão", "dispara uma bala de canhão", 4, 11, feridas, false, 1); tiroCanhao.setResenha(Arte.TIRO4);
        corteProfundo = new CartaAtaqueComEfeito("Corte profundo", "", 2, 3, sangramento, false, 2); corteProfundo.setResenha(Cor.txtVermelho(Arte.CORTE));
        corteVenenoso = new CartaAtaqueComEfeito("Corte venenoso", "", 1, 1, veneno, false, 2); corteVenenoso.setResenha(Cor.txtVerdeClaro(Arte.CORTE2));
        corteDefensivo = new CartaAtaqueComEfeito("Corte defensivo", "bate e ganha escudo!", 1, 1, escudinho, true, 2); corteDefensivo.setResenha(Cor.txtAzulClaro(Arte.CORTE5));
        corteRapido = new CartaAtaqueComEfeito("Corte rapido", "bate e ganha 1 ponto de energia!", 1, 1, ganhaEnergia1, true, 2); corteRapido.setResenha(Cor.txtAmareloClaro(Arte.CORTE4));
        desprezo = new CartaAtaqueComEfeito("Desprezo.", "causa muito dano porém irrita seu adversario", 4, 15, odioPuro, false); desprezo.setResenha(Arte.DESPREZO);
        bombaVeneno = new CartaAtaqueComEfeito("BOMBA DE VENENO!", "jogue uma " + Cor.txtVerdeEscuro("BOMBA TÓXICA") + " que atinge TODOS os inimigos e aplica " + veneno.getNomeColorido(), 4, 8, veneno4, false);
        bombaVeneno.setEfeitoEmArea(true);

        armadura = new CartaHabilidade("Ego", "Reduz o dano recebido", 2, aumentaResistencia, true);
        escudoMadeira = new CartaHabilidade("Postura de defesa", "da escudo", 0, escudinho, true);
        escudoFerro = new CartaHabilidade("Aura", "da MUITO escudo", 1, escudao, true);
        purificar = new CartaHabilidade("RECEBA!", "Remove todos os efeitos aplicados em voce (incluindo positivos)", 2, purificarEfeito, true); purificar.setResenha(Cor.txtAmarelo(Arte.RECEBA));
        puroOdio = new CartaHabilidade("PURO ODIO", "Cause 1 de dano extra por 3 rodadas", 2, odioPuro, true);
        puxaCarta = new CartaHabilidade("Ganancia", "Puxe duas cartas da sua pilha de compras", 1, efeitoPuxaCarta2, true);
        energizar = new CartaHabilidade("ENERGIZAR!", "Ganhe mais 2 pontos de energia no começo da próxima rodada!", 1, efeitoEnergizado, true);
        energiaGratis = new CartaHabilidade("Energia!", "Ganhe 1 ponto de energia", 0, ganhaEnergia1, true);
        
        dedoNervosoCarta = new CartaPoder("JOHN WICK", "Para cada acúmulo, atire novamente sempre que usar uma carta de tiro!", 2, dedoNervoso);
        mestreLaminasCarta = new CartaPoder("Mestre das lâminas", "Para cada acúmulo, corte novamente sempre que usar uma carta de corte!", 2, mestreLaminas);

        sangrar = new CartaMaldicao("Sangrar.", "Sangra.", 1, sangramento, true); sangrar.setResenha(Cor.txtCinza(Arte.algoRuim));
        beberVeneno = new CartaMaldicao("Beber veneno.", "Bebe veneno.", 1, veneno, true); beberVeneno.setResenha(Cor.txtCinza(Arte.algoRuim));

        // inimigos --
        barbossa = new Inimigo("Capitão Hector Barbossa", 30, 6,
            new Acao.AtacarVidaPerdida()
        );

        loudCoringa = new Inimigo("LOUD Coringa", 15, 3, 
            new Acao.Atacar(), new Acao.AtacarEfeito(sangramento), new Acao.AdicionarCarta(sangrar));   

        endrick = new Inimigo("Endrick", 12, 4,
            new Acao.Atacar(), new Acao.AtacarEfeito(veneno)
        );

        drake = new Inimigo("Drake", 10, 2,
            new Acao.Atacar()
        );

        paulAtreides = new Inimigo("PAUL MUAD'DIB ATREIDES, DUKE OF ARRAKIS, LISAN AL GAIB", 45, 7, // ESSE AQUI E FORTE VIU MEIO QUE O BOSS
            new Acao.AtacarVidaPerdida(), new Acao.AdicionarCarta(beberVeneno), new Acao.ReceberEfeito(pactoSinistro));

        sabrinaCarpenter = new Inimigo("SABRINA CARPENTER", 30, 6,  // ELA E FORTE TB
            new Acao.AtacarVidaPerdida(), new Acao.AdicionarCarta(beberVeneno), new Acao.ReceberEfeito(odioPuro));
            
        tripleT = new Inimigo("TUNG TUNG TUNG SAHUR", 67, 2, // OUTRO BOSS TA MUITO ROUBADO
            new Acao.AdicionarCarta(beberVeneno), new Acao.ReceberEfeito(odioPuro), new Acao.AdicionarCarta(sangrar), new Acao.Atacar());
        
        // preencher as listas aqui embaixo sempre que adicionar algo

        // inimigos ------
        listaInimigosMoldes.addAll(Arrays.asList(barbossa, loudCoringa, endrick, drake, 
            paulAtreides, sabrinaCarpenter, tripleT));
        
        // efeitos ------
        listaEfeitosMoldes.addAll(Arrays.asList(sangramento, veneno, odioPuro, aumentaResistencia, 
            escudinho, escudao, purificarEfeito, feridas, ganhaEnergia2, 
            ganhaEnergia1, efeitoPuxaCarta2, efeitoEnergizado, pactoSinistro));
        
        // cartas -------
        listaCartasMoldes.addAll(Arrays.asList(tiro, tiroEscopeta, tiroCanhao, corteProfundo, 
            corteVenenoso, corteDefensivo, corteRapido, desprezo, armadura, escudoMadeira, 
            escudoFerro, purificar, puroOdio, puxaCarta, energizar, energiaGratis, dedoNervosoCarta, 
            mestreLaminasCarta, sangrar));
    }
}