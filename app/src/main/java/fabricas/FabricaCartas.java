package fabricas;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import cartas.Carta;
import cartas.Carta.raridades;
import cartas.CartaAtaque;
import cartas.CartaAtaqueComEfeito;
import cartas.CartaHabilidade;
import cartas.CartaMaldicao;
import cartas.CartaPoder;
import static fabricas.FabricaEfeitos.descarta1;
import static fabricas.FabricaEfeitos.ecoaDano50;
import static fabricas.FabricaEfeitos.energizado;
import static fabricas.FabricaEfeitos.escolheCarta;
import static fabricas.FabricaEfeitos.escudo10;
import static fabricas.FabricaEfeitos.escudo4;
import static fabricas.FabricaEfeitos.gananciaEfeito;
import static fabricas.FabricaEfeitos.ganhaAdaga;
import static fabricas.FabricaEfeitos.ganhaClubex;
import static fabricas.FabricaEfeitos.ganhaEnergia1;
import static fabricas.FabricaEfeitos.ganhaEnergia2;
import static fabricas.FabricaEfeitos.ganhaResenhax;
import static fabricas.FabricaEfeitos.purificarEfeito;
import static fabricas.FabricaEfeitos.puxaCarta2;
import static fabricas.FabricaEfeitos.recebeDanoPuro2;
import static fabricas.FabricaEfeitos.sangramento;
import static fabricas.FabricaEfeitos.veneno;
import static fabricas.FabricaPoderes.cartaAdicional;
import static fabricas.FabricaPoderes.mestreLaminas;
import visual.Arte;
import visual.Cor;

public class FabricaCartas {
    public static Carta tiro;
    public static Carta tiroEscopeta;
    public static Carta tiroCanhao;
    public static Carta corteProfundo;
    public static Carta corteVenenoso;
    public static Carta shieldinho;
    public static Carta shieldao;
    public static Carta purificar;
    public static Carta mestreLaminasCarta;
    // novas cartas
    public static Carta ganancia;
    public static Carta cafePilao;
    public static Carta energiaGratis;
    public static Carta sangrar;
    public static Carta beberVeneno;
    public static Carta bomba;
    public static Carta bombaVeneno;
    public static Carta chocolex;
    public static Carta resenhax;
    public static Carta clubex;
    public static Carta contratoSangue;
    public static Carta clarividencia;
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
    public static Carta sobrevivencia;

    public static List<Carta> cartasEncontraveis = new ArrayList<>();

    // metodo feito com assistencia de ia pra atualizar automaticamente a lista de moldes
    private static void atualizarListaCartasMoldes() {
        cartasEncontraveis.clear();

        for (Field field : FabricaCartas.class.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            if (!Carta.class.isAssignableFrom(field.getType())) {
                continue;
            }

            try {
                Carta carta = (Carta) field.get(null);
                if (carta != null && carta.getRaridade() != raridades.IMPOSSIVEL.getValor()) {
                    cartasEncontraveis.add(carta);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Erro ao montar lista de moldes de cartas", e);
            }
        }
    }

    public static void carregar(){
        cartasEncontraveis.clear();

        // cartas ataque basicas
        adaga = new CartaAtaque("Adaga", "Uma lâmina descartavel", 0, 3);
        adaga.setTipo(2); adaga.setResenha(Arte.CORTE3); adaga.setRaridade(raridades.IMPOSSIVEL); adaga.setConsumir(true);

        espada = new CartaAtaque("Espada", "Uma espada comum", 1, 5);
        espada.setTipo(2); espada.setResenha(Arte.CORTE4); espada.setRaridade(raridades.COMUM);   

        tiro = new CartaAtaque("Disparo de revolver", "dispara um tiro com seu revolver", 1, 5);
        tiro.setTipo(1);
        tiro.setResenha(Arte.TIRO5); tiro.setRaridade(raridades.COMUM);

        tiroEscopeta = new CartaAtaque("Disparo de escopeta", "dispara um cartucho de escopeta", 2, 12);
        tiroEscopeta.setTipo(1);
        tiroEscopeta.setResenha(Arte.TIRO1); tiroEscopeta.setRaridade(raridades.INCOMUM);

        tiroCanhao = new CartaAtaque("Disparo de canhão", "dispara uma bala de canhão", 3, 18);
        tiroCanhao.setTipo(1);
        tiroCanhao.setResenha(Arte.TIRO3); tiroCanhao.setRaridade(raridades.RARA);

        bomba = new CartaAtaque("BOMBA!", "joga uma bomba que atinge TODOS os inimigos!", 2, 9); 
        bomba.setEfeitoEmArea(true); bomba.setRaridade(raridades.RARA);

        clubex = new CartaAtaque("Clubex", "Causa 26 pontos de dano a todos inimigos.", 4, 26);
        clubex.setEfeitoEmArea(true); clubex.setConsumir(true); clubex.setRaridade(raridades.IMPOSSIVEL);

        // cartas ataque com efeito
        corteProfundo = new CartaAtaqueComEfeito("Espada muito afiada", "aplica sangramento", 1, 4, false, sangramento);
        corteProfundo.setTipo(2);
        corteProfundo.setResenha(Cor.txtVermelho(Arte.CORTE)); corteProfundo.setRaridade(raridades.COMUM);

        corteVenenoso = new CartaAtaqueComEfeito("Espada envenenada", "aplica dois acumulos de veneno", 1, 4, false, veneno);
        corteVenenoso.setTipo(2);
        corteVenenoso.setResenha(Cor.txtVerdeClaro(Arte.CORTE2)); corteVenenoso.setRaridade(raridades.COMUM);

        bombaVeneno = new CartaAtaqueComEfeito("BOMBA DE VENENO!", "jogue uma " + Cor.txtVerdeEscuro("BOMBA TÓXICA") + " que atinge TODOS os inimigos e aplica 2 acúmulos de" + veneno.getNomeColorido(), 2, 7, false, veneno);
        bombaVeneno.setEfeitoEmArea(true); bombaVeneno.setRaridade(raridades.INCOMUM);

        presenteMaldito = new CartaAtaqueComEfeito("Presente sinistro", "Causa 24 pontos de dano em área e aplica sangramento. " + Cor.txtVermelho("[Sacrifício: 3]"), 3, 24, false, sangramento);
        presenteMaldito.setRaridade(raridades.IMPOSSIVEL);
        presenteMaldito.setConsumir(true); presenteMaldito.setSacrificio(3); presenteMaldito.setEfeitoEmArea(true);

        // cartas habilidade
        shieldinho = new CartaHabilidade("Shieldinho", "Ganhe 4 pontos de escudo", 1, true, escudo4); 
        shieldinho.setRaridade(raridades.COMUM);

        sobrevivencia = new CartaHabilidade("Sobrevivência", "Ganhe 8 pontos de escudo e descarte 1 carta", 1, true, escudo4, escudo4, descarta1);
        sobrevivencia.setRaridade(raridades.INCOMUM);

        shieldao = new CartaHabilidade("Shieldao", "Ganhe 10 pontos de escudo", 2, true, escudo10); 
        shieldao.setRaridade(raridades.COMUM);

        purificar = new CartaHabilidade("RECEBA!", "Remove todos os efeitos aplicados em voce (incluindo positivos)", 3, true, purificarEfeito); 
        purificar.setResenha(Cor.txtAmarelo(Arte.RECEBA)); purificar.setRaridade(raridades.INCOMUM);

        ganancia = new CartaHabilidade("Ganancia", "Descarte todas as suas cartas e puxe a mesma quantidade da sua pilha de compras", 1, true, gananciaEfeito);
        ganancia.setRaridade(raridades.INCOMUM);

        cafePilao = new CartaHabilidade("Cafeína", "No inicio da próxima rodada, ganhe 2 pontos de energia.", 1, true, energizado); 
        cafePilao.setRaridade(raridades.INCOMUM);

        energiaGratis = new CartaHabilidade("Energia!", "Ganhe 1 ponto de energia", 0, true, ganhaEnergia1); 
        energiaGratis.setRaridade(raridades.INCOMUM);
        
        chocolex = new CartaHabilidade("Chocolex", "Adiciona um Resenhax na sua pilha de compras.", 2, true, ganhaResenhax); 
        chocolex.setConsumir(true); chocolex.setRaridade(raridades.RARA);

        resenhax = new CartaHabilidade("Resenhax", "Adiciona um Clubex na sua pilha de compras.", 3, true, ganhaClubex); 
        resenhax.setConsumir(true); resenhax.setRaridade(raridades.IMPOSSIVEL);

        clarividencia = new CartaHabilidade("Clarividencia", "Veja sua pilha de compras e escolha uma carta para puxar.", 1, true, escolheCarta); 
        clarividencia.setConsumir(true); clarividencia.setRaridade(raridades.INCOMUM);

        ecoDolor = new CartaHabilidade("Eco Dolor", "Causa uma parte do dano acumulado durante a duraçao em dano direto no final da duraçao", 2, false, ecoaDano50);
        ecoDolor.setRaridade(raridades.RARA);

        reciclagem = new CartaHabilidade("Reciclagem", "Descarta uma carta da sua mão e ganha 2 pontos de energia", 1, true, descarta1, ganhaEnergia2);
        reciclagem.setRaridade(raridades.INCOMUM);

        freestyle = new CartaHabilidade("Freestyle", "Puxe duas cartas aleatorias da sua pilha de compra e descarte uma da sua mão", 1, true, descarta1, puxaCarta2);
        freestyle.setRaridade(raridades.INCOMUM);

        chuvaLaminas = new CartaHabilidade("Chuva de lâminas", "Recebe 3 adagas", 1, true, ganhaAdaga, ganhaAdaga, ganhaAdaga);
        chuvaLaminas.setRaridade(raridades.INCOMUM);

        // cartas poder

        mestreLaminasCarta = new CartaPoder("Mestre das lâminas", "Sempre que utilizar um ataque cortante, ataque novamente.", 3, mestreLaminas); 
        mestreLaminasCarta.setRaridade(raridades.INCOMUM);

        contratoSangue = new CartaPoder("Contrato de sangue", "No início de cada turno, puxe 1 carta adicional e perca 1 ponto de vida.", 3, cartaAdicional); 
        contratoSangue.setRaridade(raridades.INCOMUM);

        // cartas que nao entram na lista de moldes ---------

        // maldicoes
        sangrar = new CartaMaldicao("Sangrar.", "Recebe um acúmulo de sangramento.", 1, sangramento); 
        sangrar.setResenha(Cor.txtCinza(Arte.algoRuim));

        beberVeneno = new CartaMaldicao("Beber veneno.", "Recebe dois acúmulos de veneno.", 1, veneno); 
        beberVeneno.setResenha(Cor.txtCinza(Arte.algoRuim));

        ferida = new CartaMaldicao("Ferida", "Caso essa carta fique em sua mão no fim da rodada, receba 2 pontos de dano puro.", 2);
        ferida.setEfeitosOnLimpar(recebeDanoPuro2);

        nada = new CartaMaldicao("Nada", "Não faz nada", 1);
        nada.setResenha(Cor.txtCinza(Arte.nada));

        // cartas pro deck de teste
        bombaSuprema = new CartaAtaque("BOMBA!!!!!!!!!", "joga uma bomba que atinge TODOS os inimigos!", 0, 150); bombaSuprema.setEfeitoEmArea(true); 
        bombaSuprema.setRaridade(raridades.IMPOSSIVEL); bombaSuprema.setInata(true);

        atualizarListaCartasMoldes();
    }
}
