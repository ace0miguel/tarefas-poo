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
import EfeitosDeStatus.Instantaneos.AdicionaCarta;
import EfeitosDeStatus.Instantaneos.EscolheCarta;
import EfeitosDeStatus.Instantaneos.Escudo;
import EfeitosDeStatus.Instantaneos.GanhaEnergia;
import EfeitosDeStatus.Instantaneos.Purificar;
import EfeitosDeStatus.Instantaneos.PuxaCarta;
import Entidades.Inimigo;
import Poderes.CartaAdicional;
import Poderes.MaosLeves;
import Poderes.Poder;

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
    public static Carta chocolex;
    public static Carta resenhax;
    public static Carta clubex;
    public static Carta contratoSangue;
    public static Carta pactoSangue;
    public static Carta energiaSupremo;

    // efeitos ------------
    public static Efeito sangramento = new Sangramento("Sangramento", "Causa 1 de dano por rodada ao alvo", 3, 1);
    public static Efeito veneno = new Veneno("Veneno", "Causa sua duraçao em dano por rodada ao alvo", 2, 1);
    public static Efeito veneno4 = new Veneno("Veneno", "Causa sua duraçao em dano por rodada ao alvo", 4, 1);
    public static Efeito odioPuro = new AumentaDano("Ego", "Aumenta o dano causado em 1 por 3 rodadas", 3, 1);
    public static Efeito aumentaResistencia = new AumentaResistencia("Aura", "Reduz o dano recebido", 3, 2);
    public static Efeito escudinho = new Escudo("Ganho de escudo (3)", "3 pontos de escudo", 3);
    public static Efeito escudao = new Escudo("Ganho de escudo (7)", "7 pontos de escudo", 7);
    public static Efeito purificarEfeito = new Purificar("Purificar", "Remove todos os efeitos aplicados em voce (incluindo bons!)");
    public static Efeito feridas = new DanoConstante("Feridas", "Causa 1 de dano por rodada ao alvo por 2 rodadas", 2, 1);
    public static Efeito ganhaEnergia2 = new GanhaEnergia("Ganho de energia (2)", "Ganha 2 pontos de energia", 2);
    public static Efeito ganhaEnergia1 = new GanhaEnergia("Ganho de energia (1)", "Ganha 1 ponto de energia", 1);
    public static Efeito ganhaEnergiaTest = new GanhaEnergia("Ganho de energia (60)", "Ganha 60 pontos de energia", 60);
    public static Efeito pactoSinistro = new AumentaDano(Cor.txtCinza("Pacto Sinistro"), "Aumenta o dano causado em 2 por 2 rodadas", 2, 2);
    
    // novos efeitos
    public static Efeito efeitoPuxaCarta2 = new PuxaCarta("Puxa duas cartas", "Puxa duas cartas", 2);
    public static Efeito efeitoEnergizado = new Energizar("Energizado", "Ganhe dois pontos de energia na proxima rodada", 1, 2);
    public static AdicionaCarta ganhaResenhax = new AdicionaCarta("Resenhax", "Recebe um resenhax", null);
    public static AdicionaCarta ganhaClubex = new AdicionaCarta("Clubex", "Recebe um clubex", null);
    public static EscolheCarta escolheCarta = new EscolheCarta("Escolhe uma carta", "Escolha uma carta da pilha de compra");


    // poderes -------------
    public static Poder dedoNervoso = new MaosLeves(("JOHN WICK!"), "Sempre que atirar, ATIRE NOVAMENTE! pelo tanto de acumulos desse poder.", 1);
    public static Poder mestreLaminas = new MaosLeves(("MESTRE DAS LÂMINAS"), "Sempre que cortar, CORTE NOVAMENTE! pelo tanto de acumulos desse poder.", 2);
    public static Poder cartaAdicional = new CartaAdicional("CONTRATO DE SANGUE", "No início de cada turno, puxe 1 carta adicional e perca 1 ponto de vida.", 1);

    public static void carregar(){
        // cartas comuns ---
        tiro = new CartaAtaque("Tiro de revolver", "", 2, 5, 1); tiro.setResenha(Arte.TIRO5);
        tiroEscopeta = new CartaAtaque("Tiro de escopeta", "", 3, 8, 1); tiroEscopeta.setResenha(Arte.TIRO1);

        

        // cartas incomuns ---


        // cartas raras ---
        bomba = new CartaAtaque("BOMBA!", "joga uma bomba que atinge TODOS os inimigos!", 4, 8); bomba.setEfeitoEmArea(true);
        clubex = new CartaAtaque("Clubex", "[CONSUMIR] - Causa 20 pontos de dano a todos inimigos.", 4, 20); clubex.setEfeitoEmArea(true); clubex.setConsumir(true);

        tiroCanhao = new CartaAtaqueComEfeito("Tiro de canhão", "dispara uma bala de canhão", 4, 12, feridas, false, 1); tiroCanhao.setResenha(Arte.TIRO4);
        corteProfundo = new CartaAtaqueComEfeito("Corte profundo", "", 2, 3, sangramento, false, 2); corteProfundo.setResenha(Cor.txtVermelho(Arte.CORTE));
        corteVenenoso = new CartaAtaqueComEfeito("Corte venenoso", "", 1, 1, veneno, false, 2); corteVenenoso.setResenha(Cor.txtVerdeClaro(Arte.CORTE2));
        corteDefensivo = new CartaAtaqueComEfeito("Corte defensivo", "bate e ganha escudo!", 1, 1, escudinho, true, 2); corteDefensivo.setResenha(Cor.txtAzulClaro(Arte.CORTE5));
        corteRapido = new CartaAtaqueComEfeito("Corte rapido", "bate e ganha 1 ponto de energia!", 1, 1, ganhaEnergia1, true, 2); corteRapido.setResenha(Cor.txtAmareloClaro(Arte.CORTE4));
        desprezo = new CartaAtaqueComEfeito("Desprezo.", "causa muito dano porém irrita seu adversario", 4, 17, odioPuro, false); desprezo.setResenha(Arte.DESPREZO);
        bombaVeneno = new CartaAtaqueComEfeito("BOMBA DE VENENO!", "jogue uma " + Cor.txtVerdeEscuro("BOMBA TÓXICA") + " que atinge TODOS os inimigos e aplica " + veneno.getNomeColorido(), 4, 2, veneno4, false);
        bombaVeneno.setEfeitoEmArea(true);

        armadura = new CartaHabilidade("Aura", "Reduz o dano recebido", 2, aumentaResistencia, true);
        escudoMadeira = new CartaHabilidade("Postura de defesa", "da escudo", 0, escudinho, true);
        escudoFerro = new CartaHabilidade("Shieldão", "da MUITO escudo", 1, escudao, true);
        purificar = new CartaHabilidade("RECEBA!", "Remove todos os efeitos aplicados em voce (incluindo positivos)", 2, purificarEfeito, true); purificar.setResenha(Cor.txtAmarelo(Arte.RECEBA));
        puroOdio = new CartaHabilidade("Ego.", "Cause 1 de dano extra por 3 rodadas", 2, odioPuro, true);
        puxaCarta = new CartaHabilidade("Ganancia", "Puxe duas cartas da sua pilha de compras", 1, efeitoPuxaCarta2, true);
        energizar = new CartaHabilidade("ENERGIZAR!", "Ganhe mais 2 pontos de energia no começo da próxima rodada!", 1, efeitoEnergizado, true);
        energiaGratis = new CartaHabilidade("Energia!", "Ganhe 1 ponto de energia", 0, ganhaEnergia1, true);
        energiaSupremo = new CartaHabilidade("Energia!!!!!!!!!!!", "Ganhe muitos ponto de energia", 0, ganhaEnergiaTest, true);
        
        chocolex = new CartaHabilidade("Chocolex", "[CONSUMIR] - Adiciona um Resenhax na sua pilha de compras.", 2, ganhaResenhax, true); chocolex.setConsumir(true);
        resenhax = new CartaHabilidade("Resenhax", "[CONSUMIR] - Adiciona um Clubex na sua pilha de compras.", 3, ganhaClubex, true); resenhax.setConsumir(true);
        pactoSangue = new CartaHabilidade("Pacto de sangue", "Pague (1) vida e escolha uma carta.", 2, escolheCarta, true); pactoSangue.setSacrificio(1);

        dedoNervosoCarta = new CartaPoder("JOHN WICK", "[CONSUMIR] - Para cada acúmulo, atire novamente sempre que usar uma carta de tiro!", 2, dedoNervoso);
        mestreLaminasCarta = new CartaPoder("[CONSUMIR] - Mestre das lâminas", "Para cada acúmulo, corte novamente sempre que usar uma carta de corte!", 2, mestreLaminas);
        contratoSangue = new CartaPoder("[CONSUMIR] - Contrato de sangue", "No início de cada turno, puxe 1 carta adicional e perca 1 ponto de vida.", 3, cartaAdicional);

        sangrar = new CartaMaldicao("Sangrar.", "Sangra.", 1, sangramento, true); sangrar.setResenha(Cor.txtCinza(Arte.algoRuim));
        beberVeneno = new CartaMaldicao("Beber veneno.", "Bebe veneno.", 1, veneno, true); beberVeneno.setResenha(Cor.txtCinza(Arte.algoRuim));

        // inimigos --
        barbossa = new Inimigo("Capitão Hector Barbossa", 30, 6,
            new Acao.Atacar()
        ); barbossa.setTier(2);

        loudCoringa = new Inimigo("LOUD Coringa", 15, 3, 
            new Acao.Atacar(), new Acao.AtacarEfeito(sangramento), new Acao.AdicionarCarta(sangrar)
        ); loudCoringa.setTier(1);

        endrick = new Inimigo("Endrick", 12, 4,
            new Acao.Atacar(), new Acao.AtacarEfeito(veneno)
        ); endrick.setTier(1);

        drake = new Inimigo("Drake", 10, 2,
            new Acao.Atacar()
        ); drake.setTier(1);

        paulAtreides = new Inimigo("PAUL MUAD'DIB ATREIDES, DUKE OF ARRAKIS, LISAN AL GAIB", 45, 5, // ESSE AQUI E FORTE VIU MEIO QUE O BOSS
            new Acao.AtacarVidaPerdida(), new Acao.AdicionarCarta(beberVeneno), new Acao.ReceberEfeito(pactoSinistro)
        ); paulAtreides.setTier(3);

        sabrinaCarpenter = new Inimigo("SABRINA CARPENTER", 50, 6,  // ELA E FORTE TB
            new Acao.Atacar(), new Acao.AdicionarCarta(sangrar), new Acao.ReceberEfeito(odioPuro)
        ); sabrinaCarpenter.setTier(3);
            
        tripleT = new Inimigo("TUNG TUNG TUNG SAHUR", 67, 2, // OUTRO BOSS TA MUITO ROUBADO
            new Acao.AdicionarCarta(beberVeneno), new Acao.AdicionarCarta(sangrar), new Acao.AtacarEfeito(sangramento)
        ); tripleT.setTier(3);
        
        // efeitos que referenciam cartas precisam ser setados aqui, pq as cartas sao criadas depois.
        ganhaResenhax.setCarta(resenhax);
        ganhaClubex.setCarta(clubex);
        cartaAdicional.setSacrificio(1);

        // preencher as listas aqui embaixo sempre que adicionar algo ( na real nao ta mais precisando )

        // inimigos ------
        listaInimigosMoldes.addAll(Arrays.asList(barbossa, loudCoringa, endrick, drake, 
            paulAtreides, sabrinaCarpenter, tripleT));
        
        // efeitos ------
        listaEfeitosMoldes.addAll(Arrays.asList(sangramento, veneno, odioPuro, aumentaResistencia, 
            escudinho, escudao, purificarEfeito, feridas, ganhaEnergia2, 
            ganhaEnergia1, ganhaEnergiaTest, efeitoPuxaCarta2, efeitoEnergizado, 
            ganhaResenhax, ganhaClubex, escolheCarta, pactoSinistro, veneno4)); 
        
        // cartas -------
        listaCartasMoldes.addAll(Arrays.asList(tiro, tiroEscopeta, tiroCanhao, corteProfundo, 
            corteVenenoso, corteDefensivo, corteRapido, desprezo, armadura, escudoMadeira, 
            escudoFerro, purificar, puroOdio, puxaCarta, energizar, energiaGratis, dedoNervosoCarta, 
            mestreLaminasCarta, bomba, bombaVeneno, chocolex,
            contratoSangue, pactoSangue));
    }
}