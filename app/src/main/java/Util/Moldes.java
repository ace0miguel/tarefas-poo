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
import Entidades.Inimigo;
import Subscribers.EfeitosDeStatus.Buffs.AumentaDano;
import Subscribers.EfeitosDeStatus.Buffs.AumentaResistencia;
import Subscribers.EfeitosDeStatus.DanosConstantes.DanoConstante;
import Subscribers.EfeitosDeStatus.DanosConstantes.Sangramento;
import Subscribers.EfeitosDeStatus.DanosConstantes.Veneno;
import Subscribers.EfeitosDeStatus.Efeito;
import Subscribers.EfeitosDeStatus.Energizar;
import Subscribers.EfeitosDeStatus.Instantaneos.AdicionaCarta;
import Subscribers.EfeitosDeStatus.Instantaneos.EscolheCarta;
import Subscribers.EfeitosDeStatus.Instantaneos.Escudo;
import Subscribers.EfeitosDeStatus.Instantaneos.GanhaEnergia;
import Subscribers.EfeitosDeStatus.Instantaneos.Purificar;
import Subscribers.EfeitosDeStatus.Instantaneos.PuxaCarta;
import Subscribers.Itens.CartasInicioBatalha;
import Subscribers.Itens.CuraFimBatalha;
import Subscribers.Itens.EfeitoPorCusto;
import Subscribers.Itens.Item;
import Subscribers.Poderes.CartaAdicional;
import Subscribers.Poderes.MaosLeves;
import Subscribers.Poderes.Poder;
import Visual.Arte;
import Visual.Cor;

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
    public static Inimigo paulAtreidesSupremo;
    public static Inimigo sabrinaCarpenter;
    public static Inimigo tripleT;
    public static Inimigo loudSacy;
    public static Inimigo amoeba;

    public static Carta tiro;
    public static Carta tiroEscopeta;
    public static Carta tiroCanhao;
    public static Carta corteProfundo;
    public static Carta corteVenenoso;
    public static Carta corteDefensivo;
    public static Carta corteRapido;
    public static Carta desprezo;
    public static Carta armadura;
    public static Carta shieldinho;
    public static Carta shieldao;
    public static Carta purificar;
    public static Carta egoCarta;
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
    public static Carta presenteMaldito;
    public static Carta bombaSuprema;

    // efeitos ------------
    public static Efeito sangramento = new Sangramento("Sangramento", "Causa 1 de dano por rodada ao alvo", 3, 1);

    public static Efeito veneno1 = new Veneno("Veneno", "Causa sua duraçao em dano por rodada ao alvo", 1, 1);

    public static Efeito veneno = new Veneno("Veneno", "Causa sua duraçao em dano por rodada ao alvo", 2, 1);

    public static Efeito veneno4 = new Veneno("Veneno", "Causa sua duraçao em dano por rodada ao alvo", 4, 1);

    public static Efeito ego = new AumentaDano("Ego", "Aumenta o dano causado em 25% por 4 rodadas", 4, 25);

    public static Efeito aumentaResistencia = new AumentaResistencia("Aura", "Reduz o dano recebido em 25%", 3, 25);

    public static Efeito escudo2 = new Escudo("Ganho de escudo (2)", "2 pontos de escudo", 2);

    public static Efeito escudo4 = new Escudo("Ganho de escudo (4)", "4 pontos de escudo", 4);

    public static Efeito escudo10 = new Escudo("Ganho de escudo (10)", "10 pontos de escudo", 10);

    public static Efeito purificarEfeito = new Purificar("Purificar", "Remove todos os efeitos aplicados em voce (incluindo bons!)");

    public static Efeito feridas = new DanoConstante("Feridas", "Causa 1 de dano por rodada ao alvo por 2 rodadas", 2, 1);

    public static Efeito ganhaEnergia2 = new GanhaEnergia("Ganho de energia (2)", "Ganha 2 pontos de energia", 2);

    public static Efeito ganhaEnergia1 = new GanhaEnergia("Ganho de energia (1)", "Ganha 1 ponto de energia", 1);

    public static Efeito ganhaEnergiaTest = new GanhaEnergia("Ganho de energia (60)", "Ganha 60 pontos de energia", 60);

    public static Efeito pactoSinistro = new AumentaDano(Cor.txtCinza("Pacto Sinistro"), "Aumenta o dano causado em 2 por 3 rodadas", 3, 2); 
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

    // itens -------------

    public static Item facaAcougueiro = new EfeitoPorCusto("faca de açougueiro", "cartas de ataque custo 1 aplicam um acumulo de sangramento adicional", sangramento, 1);

    public static Item marmita = new CuraFimBatalha("marmita", "cura 6 pontos de vida no fim da batalha", 6);

    public static Item amuletoVelho = new CartasInicioBatalha("amuleto velho", "receba 2 cartas adicionais no inicio da batalha", 2);

    /** inicializa os moldes */
    public static void carregar(){
        /* base do balanceamento: carta de 1 de energia: 5 pontos de dano ou 4 de escudo
        um inimigo fraco deve ter por volta de uns 40 pontos de vida e causar uns 9 de dano
        tiros: cartas de ataque sem efeito secundário, cortes aplicam efeito (em geral)
        cartas com efeito adicional com o mesmo custo de energia devem causar menos dano
        cartas que custam mais de 1 de energia devem causar mais que o seu custo * 5 pontos de dano, pra compensar pela inflexibilidade*/

        // cartas ataque basicas
        tiro = new CartaAtaque("Tiro de revolver", "dispara um tiro com seu revolver", 1, 5, 1); 
        tiro.setResenha(Arte.TIRO5); tiro.setRaridade(1);

        tiroEscopeta = new CartaAtaque("Tiro de escopeta", "dispara um cartucho de escopeta", 2, 12, 1); 
        tiroEscopeta.setResenha(Arte.TIRO1); tiroEscopeta.setRaridade(2);

        tiroCanhao = new CartaAtaque("Tiro de canhão", "dispara uma bala de canhão", 3, 18, 1); 
        tiroCanhao.setResenha(Arte.TIRO3); tiroCanhao.setRaridade(2);

        bomba = new CartaAtaque("BOMBA!", "joga uma bomba que atinge TODOS os inimigos!", 3, 14); 
        bomba.setEfeitoEmArea(true); bomba.setRaridade(2);

        clubex = new CartaAtaque("Clubex", "Causa 26 pontos de dano a todos inimigos.", 4, 26);
        clubex.setEfeitoEmArea(true); clubex.setConsumir(true);

        // cartas ataque com efeito
        corteProfundo = new CartaAtaqueComEfeito("Corte profundo", "aplica sangramento", 1, 4, sangramento, false, 2); 
        corteProfundo.setResenha(Cor.txtVermelho(Arte.CORTE)); corteProfundo.setRaridade(1);

        corteVenenoso = new CartaAtaqueComEfeito("Corte venenoso", "aplica dois acumulos de veneno", 1, 3, veneno, false, 2); 
        corteVenenoso.setResenha(Cor.txtVerdeClaro(Arte.CORTE2)); corteVenenoso.setRaridade(1);

        corteDefensivo = new CartaAtaqueComEfeito("Corte defensivo", "ganha escudo!", 1, 3, escudo2, true, 2); 
        corteDefensivo.setResenha(Cor.txtAzulClaro(Arte.CORTE5)); corteDefensivo.setRaridade(1);

        corteRapido = new CartaAtaqueComEfeito("Corte rapido", "ganha 1 ponto de energia!", 1, 3, ganhaEnergia1, true, 2); 
        corteRapido.setResenha(Cor.txtAmareloClaro(Arte.CORTE4)); corteRapido.setRaridade(2);

        bombaVeneno = new CartaAtaqueComEfeito("BOMBA DE VENENO!", "jogue uma " + Cor.txtVerdeEscuro("BOMBA TÓXICA") + " que atinge TODOS os inimigos e aplica " + veneno.getNomeColorido(), 3, 6, veneno4, false);
        bombaVeneno.setEfeitoEmArea(true); bombaVeneno.setRaridade(2);

        desprezo = new CartaAtaqueComEfeito("Desprezo.", "causa muito dano porém irrita seu adversario", 4, 26, ego, false);
        desprezo.setResenha(Arte.DESPREZO); desprezo.setRaridade(2);

        presenteMaldito = new CartaAtaqueComEfeito("Presente sinistro", "Um presente de um anfitrião misterioso... " + Cor.txtVermelho("[Sacrifício: 5]"), 4, 24, sangramento, false); 
        presenteMaldito.setResenha((Arte.hahaha).repeat(18)); presenteMaldito.setRaridade(4); 
        presenteMaldito.setConsumir(true); presenteMaldito.setSacrificio(5); presenteMaldito.setEfeitoEmArea(true);

        // cartas habilidade
        shieldinho = new CartaHabilidade("Shieldinho", "4 pontos de escudo", 1, escudo4, true); 
        shieldinho.setRaridade(1);

        shieldao = new CartaHabilidade("Shieldao", "10 pontos de escudo", 2, escudo10, true); 
        shieldao.setRaridade(1);

        armadura = new CartaHabilidade("Aura", "Reduz o dano recebido em 25% por 3 rodadas", 2, aumentaResistencia, true); 
        armadura.setRaridade(2);
        
        egoCarta = new CartaHabilidade("Ego.", "Cause 25% de dano extra por 4 rodadas", 2, ego, true); 
        egoCarta.setRaridade(2);

        purificar = new CartaHabilidade("RECEBA!", "Remove todos os efeitos aplicados em voce (incluindo positivos)", 3, purificarEfeito, true); 
        purificar.setResenha(Cor.txtAmarelo(Arte.RECEBA)); purificar.setRaridade(2);

        puxaCarta = new CartaHabilidade("Ganancia", "Puxe duas cartas da sua pilha de compras", 1, efeitoPuxaCarta2, true);
        puxaCarta.setRaridade(2);

        energizar = new CartaHabilidade("ENERGIZAR!", "Ganhe mais 2 pontos de energia no começo da próxima rodada!", 2, efeitoEnergizado, true); 
        energizar.setRaridade(2);

        energiaGratis = new CartaHabilidade("Energia!", "Ganhe 1 ponto de energia", 0, ganhaEnergia1, true); 
        energiaGratis.setRaridade(2);
        
        chocolex = new CartaHabilidade("Chocolex", "Adiciona um Resenhax na sua pilha de compras.", 2, ganhaResenhax, true); 
        chocolex.setConsumir(true); chocolex.setRaridade(3);

        resenhax = new CartaHabilidade("Resenhax", "Adiciona um Clubex na sua pilha de compras.", 3, ganhaClubex, true); 
        resenhax.setConsumir(true); 

        pactoSangue = new CartaHabilidade("Pacto de sangue", "Pague (4) vida e escolha uma carta da sua pilha de compras.", 1, escolheCarta, true); 
        pactoSangue.setSacrificio(4); pactoSangue.setRaridade(2);

        // cartas poder
        dedoNervosoCarta = new CartaPoder("JOHN WICK", "Atire novamente sempre que usar uma carta de tiro!", 3, dedoNervoso); 
        dedoNervosoCarta.setRaridade(3); 

        mestreLaminasCarta = new CartaPoder("Mestre das lâminas", "Corte novamente sempre que usar uma carta de corte!", 3, mestreLaminas); 
        mestreLaminasCarta.setRaridade(3); mestreLaminasCarta.setManter(true);

        contratoSangue = new CartaPoder("Contrato de sangue", "No início de cada turno, puxe 1 carta adicional e perca 1 ponto de vida.", 3, cartaAdicional); 
        contratoSangue.setRaridade(3);

        // cartas que nao entram na lista de moldes ---------

        // maldicoes
        sangrar = new CartaMaldicao("Sangrar.", "Sangra.", 1, sangramento, true); sangrar.setResenha(Cor.txtCinza(Arte.algoRuim));
        beberVeneno = new CartaMaldicao("Beber veneno.", "Bebe veneno.", 1, veneno, true); beberVeneno.setResenha(Cor.txtCinza(Arte.algoRuim));

        // cartas pro deck de teste
        bombaSuprema = new CartaAtaque("BOMBA!!!!!!!!!", "joga uma bomba que atinge TODOS os inimigos!", 0, 150); bombaSuprema.setEfeitoEmArea(true); 
        bombaSuprema.setRaridade(10); bombaSuprema.setInata(true);

        energiaSupremo = new CartaHabilidade("Energia!!!!!!!!!!!", "Ganhe muitos ponto de energia", 0, ganhaEnergiaTest, true); 
        energiaSupremo.setRaridade(10); energiaSupremo.setInata(true);

        // inimigos --
        // tier 1 -----------------
        endrick = new Inimigo("Endrick", 50, 8,
            new Acao.Atacar(), new Acao.AtacarEfeito(veneno)
        ); endrick.setTier(1);

        // suporte
        drake = new Inimigo("Drake", 25, 6,
            new Acao.AplicarEfeitoAliadoMaisForte(ego), new Acao.AplicarEfeitoAliadoMaisForte(escudo10)
        ); drake.setTier(1);

        // duplica ao chegar na metade da vida
        amoeba = new Inimigo("Amoeba", 25, 4,
            new Acao.AtacarEfeito(veneno1)
        ); amoeba.setTier(1); amoeba.setAcaoMeiaVida(new Acao.multiplicar(2));

        // tier 2 -----------------
        // ganha escudo ao chegar na metade da vida 
        barbossa = new Inimigo("Capitão Hector Barbossa", 60, 10,
            new Acao.Atacar(), new Acao.AtacarEfeito(sangramento)
        ); barbossa.setTier(2); barbossa.setAcaoMeiaVida(new Acao.ReceberEfeito(escudo10));

        // assassino
        loudCoringa = new Inimigo("LOUD Coringa", 50, 8, 
            new Acao.AtacarVidaPerdida(), new Acao.AtacarEfeito(sangramento), new Acao.AdicionarCarta(sangrar)
        ); loudCoringa.setTier(2);

        // suporte, mas ataca 1 vez quando chega na meia vida
        loudSacy = new Inimigo("LOUD Sacy", 40,6, 
            new Acao.AdicionarCarta(sangrar), new Acao.AplicarEfeitoAliadoMaisForte(ego), 
            new Acao.AplicarEfeitoAliadoMaisForte(escudo10), new Acao.AplicarEfeitoAliadoMaisForte(pactoSinistro)
        ); loudSacy.setTier(2); loudSacy.setAcaoMeiaVida(new Acao.AtacarEfeito(sangramento));

        // tier 3 -----------------
        sabrinaCarpenter = new Inimigo("Sabrina Carpenter", 65, 15,
            new Acao.Atacar(), new Acao.AtacarEfeito(sangramento), new Acao.ReceberEfeito(ego)
        ); sabrinaCarpenter.setTier(3); sabrinaCarpenter.setAcaoMeiaVida(new Acao.ReceberEfeito(ego));
            
        tripleT = new Inimigo("Tung Tung Tung \"Triple T\" Sahur", 67, 10, // pouca vida mas bate forte
            new Acao.AdicionarCarta(beberVeneno), new Acao.AtacarEfeito(sangramento), new Acao.AtacarVidaPerdida()
        ); tripleT.setTier(3);

        // tier 4 (goats) -----------------
        paulAtreides = new Inimigo("Paul Muad'Dib Atreides", 80, 16, // ESSE AQUI E FORTE VIU MEIO QUE O BOSS
            new Acao.AtacarVidaPerdida(), new Acao.ReceberEfeito(pactoSinistro), new Acao.AtacarEfeito(sangramento), new Acao.AtacarVidaPerdida()
        ); paulAtreides.setTier(4);

        // tier 5 (boss de area) -----------------
        paulAtreidesSupremo = new Inimigo("PAUL MUAD'DIB ATREIDES, DUKE OF ARRAKIS, LISAN AL GAIB, KWISATZ HADERACH.", 100, 25, // ESSE AQUI E FORTE VIU MEIO QUE O BOSS
            new Acao.AtacarVidaPerdida(), new Acao.ReceberEfeito(pactoSinistro), new Acao.AtacarEfeito(sangramento), new Acao.AtacarVidaPerdida()
        ); paulAtreidesSupremo.setTier(5); paulAtreides.setAcaoMeiaVida(new Acao.ReceberEfeito(escudo10));

        // efeitos que referenciam cartas precisam ser setados aqui, pq as cartas sao criadas depois.
        ganhaResenhax.setCarta(resenhax);
        ganhaClubex.setCarta(clubex);
        cartaAdicional.setSacrificio(1);

        // preencher as listas aqui embaixo sempre que adicionar algo ( na real nao ta mais precisando )

        // inimigos ------
        listaInimigosMoldes.addAll(Arrays.asList(barbossa, loudCoringa, endrick, drake, 
            paulAtreides, sabrinaCarpenter, tripleT));
        
        // efeitos ------
        listaEfeitosMoldes.addAll(Arrays.asList(sangramento, veneno, veneno1, ego, aumentaResistencia, 
            escudo4, escudo10, purificarEfeito, feridas, ganhaEnergia2, 
            ganhaEnergia1, ganhaEnergiaTest, efeitoPuxaCarta2, efeitoEnergizado, 
            ganhaResenhax, ganhaClubex, escolheCarta, pactoSinistro, veneno4)); 
        
        // cartas -------
        listaCartasMoldes.addAll(Arrays.asList(tiro, tiroEscopeta, tiroCanhao, corteProfundo, 
            corteVenenoso, corteDefensivo, corteRapido, desprezo, armadura, shieldao, 
            shieldinho, purificar, egoCarta, puxaCarta, energizar, energiaGratis, dedoNervosoCarta, 
            mestreLaminasCarta, bomba, bombaVeneno, chocolex,contratoSangue, pactoSangue));
    }
}