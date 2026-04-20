package fabricas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cartas.Carta;
import cartas.CartaAtaque;
import cartas.CartaAtaqueComEfeito;
import cartas.CartaHabilidade;
import cartas.CartaMaldicao;
import cartas.CartaPoder;
import static fabricas.FabricaEfeitos.aumentaResistencia;
import static fabricas.FabricaEfeitos.descarta1;
import static fabricas.FabricaEfeitos.ecoaDano50;
import static fabricas.FabricaEfeitos.efeitoEnergizado;
import static fabricas.FabricaEfeitos.efeitoPuxaCarta2;
import static fabricas.FabricaEfeitos.ego;
import static fabricas.FabricaEfeitos.escolheCarta;
import static fabricas.FabricaEfeitos.escudo10;
import static fabricas.FabricaEfeitos.escudo4;
import static fabricas.FabricaEfeitos.ganhaAdaga;
import static fabricas.FabricaEfeitos.ganhaClubex;
import static fabricas.FabricaEfeitos.ganhaEnergia1;
import static fabricas.FabricaEfeitos.ganhaEnergia2;
import static fabricas.FabricaEfeitos.ganhaEnergiaTest;
import static fabricas.FabricaEfeitos.ganhaResenhax;
import static fabricas.FabricaEfeitos.purificarEfeito;
import static fabricas.FabricaEfeitos.recebeDanoPuro2;
import static fabricas.FabricaEfeitos.sangramento;
import static fabricas.FabricaEfeitos.veneno;
import static fabricas.FabricaEfeitos.veneno4;
import static fabricas.FabricaPoderes.cartaAdicional;
import static fabricas.FabricaPoderes.dedoNervoso;
import static fabricas.FabricaPoderes.mestreLaminas;
import visual.Arte;
import visual.Cor;

public class FabricaCartas {
    public static Carta tiro;
    public static Carta tiroEscopeta;
    public static Carta tiroCanhao;
    public static Carta corteProfundo;
    public static Carta corteVenenoso;
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
    public static Carta energizar1;
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
    public static Carta ecoDolor;
    public static Carta reciclagem;
    public static Carta freestyle;
    public static Carta ferida;
    public static Carta nada;
    public static Carta espada;
    public static Carta adaga;
    public static Carta chuvaLaminas;

    public static List<Carta> listaCartasMoldes = new ArrayList<>();

    public static void carregar(){
        listaCartasMoldes.clear();

        // cartas ataque basicas


        adaga = new CartaAtaque("Adaga", "Uma lâmina descartavel", 0, 3);
        adaga.setTipo(2); adaga.setResenha(Arte.CORTE3); adaga.setRaridade(10); adaga.setConsumir(true);

        espada = new CartaAtaque("Espada", "Uma espada comum", 1, 5);
        espada.setTipo(2); espada.setResenha(Arte.CORTE4); espada.setRaridade(1);   

        tiro = new CartaAtaque("Disparo de revolver", "dispara um tiro com seu revolver", 1, 5);
        tiro.setTipo(1);
        tiro.setResenha(Arte.TIRO5); tiro.setRaridade(1);

        tiroEscopeta = new CartaAtaque("Disparo de escopeta", "dispara um cartucho de escopeta", 2, 12);
        tiroEscopeta.setTipo(1);
        tiroEscopeta.setResenha(Arte.TIRO1); tiroEscopeta.setRaridade(2);

        tiroCanhao = new CartaAtaque("Disparo de canhão", "dispara uma bala de canhão", 3, 18);
        tiroCanhao.setTipo(1);
        tiroCanhao.setResenha(Arte.TIRO3); tiroCanhao.setRaridade(2);

        bomba = new CartaAtaque("BOMBA!", "joga uma bomba que atinge TODOS os inimigos!", 2, 9); 
        bomba.setEfeitoEmArea(true); bomba.setRaridade(2);

        clubex = new CartaAtaque("Clubex", "Causa 26 pontos de dano a todos inimigos.", 4, 26);
        clubex.setEfeitoEmArea(true); clubex.setConsumir(true);

        // cartas ataque com efeito
        corteProfundo = new CartaAtaqueComEfeito("Espada muito afiada", "aplica sangramento", 1, 4, false, sangramento);
        corteProfundo.setTipo(2);
        corteProfundo.setResenha(Cor.txtVermelho(Arte.CORTE)); corteProfundo.setRaridade(1);

        corteVenenoso = new CartaAtaqueComEfeito("Espada envenenada", "aplica dois acumulos de veneno", 1, 3, false, veneno);
        corteVenenoso.setTipo(2);
        corteVenenoso.setResenha(Cor.txtVerdeClaro(Arte.CORTE2)); corteVenenoso.setRaridade(1);

        bombaVeneno = new CartaAtaqueComEfeito("BOMBA DE VENENO!", "jogue uma " + Cor.txtVerdeEscuro("BOMBA TÓXICA") + " que atinge TODOS os inimigos e aplica " + veneno.getNomeColorido(), 3, 6, false, veneno4);
        bombaVeneno.setEfeitoEmArea(true); bombaVeneno.setRaridade(2);

        desprezo = new CartaAtaqueComEfeito("Desprezo.", "causa muito dano porém irrita seu adversario", 4, 26, false, ego);
        desprezo.setResenha(Arte.DESPREZO); desprezo.setRaridade(2);

        presenteMaldito = new CartaAtaqueComEfeito("Presente sinistro", "Um presente de um anfitrião misterioso... " + Cor.txtVermelho("[Sacrifício: 5]"), 4, 24, false, sangramento);
        presenteMaldito.setRaridade(4);
        presenteMaldito.setConsumir(true); presenteMaldito.setSacrificio(5); presenteMaldito.setEfeitoEmArea(true);

        // cartas habilidade
        shieldinho = new CartaHabilidade("Shieldinho", "Ganhe 4 pontos de escudo", 1, true, escudo4); 
        shieldinho.setRaridade(1);

        shieldao = new CartaHabilidade("Shieldao", "Ganhe 10 pontos de escudo", 2, true, escudo10); 
        shieldao.setRaridade(1);

        armadura = new CartaHabilidade("Aura", "Reduz o dano recebido em 25% por 3 rodadas", 2, true, aumentaResistencia); 
        armadura.setRaridade(2);
        
        egoCarta = new CartaHabilidade("Ego.", "Cause 25% de dano extra por 4 rodadas", 2, true, ego); 
        egoCarta.setRaridade(2);

        purificar = new CartaHabilidade("RECEBA!", "Remove todos os efeitos aplicados em voce (incluindo positivos)", 3, true, purificarEfeito); 
        purificar.setResenha(Cor.txtAmarelo(Arte.RECEBA)); purificar.setRaridade(2);

        puxaCarta = new CartaHabilidade("Ganancia", "Puxe duas cartas da sua pilha de compras", 1, true, efeitoPuxaCarta2);
        puxaCarta.setRaridade(2);

        energizar1 = new CartaHabilidade("Cafeína", "Ganhe um acumulo de energizar.", 1, true, efeitoEnergizado); 
        energizar1.setRaridade(2);

        energiaGratis = new CartaHabilidade("Energia!", "Ganhe 1 ponto de energia", 0, true, ganhaEnergia1); 
        energiaGratis.setRaridade(2);
        
        chocolex = new CartaHabilidade("Chocolex", "Adiciona um Resenhax na sua pilha de compras.", 2, true, ganhaResenhax); 
        chocolex.setConsumir(true); chocolex.setRaridade(3);

        resenhax = new CartaHabilidade("Resenhax", "Adiciona um Clubex na sua pilha de compras.", 3, true, ganhaClubex); 
        resenhax.setConsumir(true); 

        pactoSangue = new CartaHabilidade("Pacto de sangue", "Veja sua pilha de compras e escolha uma carta para puxar.", 1, true, escolheCarta); 
        pactoSangue.setSacrificio(4); pactoSangue.setRaridade(2);

        ecoDolor = new CartaHabilidade("Eco Dolor", "Causa uma parte do dano acumulado durante a duraçao em dano direto no final da duraçao", 2, false, ecoaDano50);
        ecoDolor.setRaridade(3);

        reciclagem = new CartaHabilidade("Reciclagem", "Descarta uma carta da sua mão e ganha 2 pontos de energia", 1, true, descarta1, ganhaEnergia2);
        reciclagem.setRaridade(2);

        freestyle = new CartaHabilidade("Freestyle", "Puxe duas cartas aleatoria da sua pilha de compra e descarte uma da sua mão", 1, true, descarta1, efeitoPuxaCarta2);
        freestyle.setRaridade(2);

        chuvaLaminas = new CartaHabilidade("Chuva de lâminas", "Recebe 3 adagas", 1, true, ganhaAdaga, ganhaAdaga, ganhaAdaga);
        chuvaLaminas.setRaridade(2);

        // cartas poder
        dedoNervosoCarta = new CartaPoder("JOHN WICK", "Sempre que utilizar um disparo, utilize novamente.", 3, dedoNervoso); 
        dedoNervosoCarta.setRaridade(3); 

        mestreLaminasCarta = new CartaPoder("Mestre das lâminas", "Sempre que utilizar uma adaga, utilize novamente.", 3, mestreLaminas); 
        mestreLaminasCarta.setRaridade(3);

        contratoSangue = new CartaPoder("Contrato de sangue", "No início de cada turno, puxe 1 carta adicional e perca 1 ponto de vida.", 3, cartaAdicional); 
        contratoSangue.setRaridade(3);

        // cartas que nao entram na lista de moldes ---------

        // maldicoes
        sangrar = new CartaMaldicao("Sangrar.", "Recebe um acúmulo de sangramento.", 1, sangramento); 
        sangrar.setResenha(Cor.txtCinza(Arte.algoRuim));

        beberVeneno = new CartaMaldicao("Beber veneno.", "Recebe dois acúmulos de veneno.", 1, veneno); 
        beberVeneno.setResenha(Cor.txtCinza(Arte.algoRuim));

        ferida = new CartaMaldicao("Ferida", "No fim da rodada, receba 2 pontos de dano puro.", 2);
        ferida.setEfeitosOnLimpar(recebeDanoPuro2);

        nada = new CartaMaldicao("Nada", "Não faz nada", 1);
        nada.setResenha(Cor.txtCinza(Arte.nada));

        // cartas pro deck de teste
        bombaSuprema = new CartaAtaque("BOMBA!!!!!!!!!", "joga uma bomba que atinge TODOS os inimigos!", 0, 150); bombaSuprema.setEfeitoEmArea(true); 
        bombaSuprema.setRaridade(10); bombaSuprema.setInata(true);

        energiaSupremo = new CartaHabilidade("Energia!!!!!!!!!!!", "Ganhe muitos ponto de energia", 0, true, ganhaEnergiaTest); 
        energiaSupremo.setRaridade(10); energiaSupremo.setInata(true);

        


        listaCartasMoldes.addAll(Arrays.asList(tiro, tiroEscopeta, tiroCanhao, corteProfundo, 
            corteVenenoso, desprezo, armadura, shieldao, espada, chuvaLaminas,
            shieldinho, purificar, egoCarta, puxaCarta, energizar1, energiaGratis, dedoNervosoCarta, 
            mestreLaminasCarta, bomba, bombaVeneno, chocolex, contratoSangue, pactoSangue, ecoDolor, reciclagem, freestyle));
    }
}
