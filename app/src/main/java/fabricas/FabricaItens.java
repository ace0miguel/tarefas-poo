package fabricas;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import batalhaListeners.itens.ativos.EscolheCartaItem;
import batalhaListeners.itens.ativos.ItemAtivo;
import batalhaListeners.itens.ativos.PocaoEnergia;
import batalhaListeners.itens.ativos.PocaoVida;
import batalhaListeners.itens.ativos.RecuperaCarta;
import batalhaListeners.itens.passivos.AumentaDanoItem;
import batalhaListeners.itens.passivos.AumentaResistItem;
import batalhaListeners.itens.passivos.CartasInicioBatalha;
import batalhaListeners.itens.passivos.CuraFimBatalha;
import batalhaListeners.itens.passivos.EfeitoPorCusto;
import batalhaListeners.itens.passivos.EfeitoPorCusto.TipoComparacao;
import batalhaListeners.itens.passivos.EscudoInicioBatalha;
import batalhaListeners.itens.passivos.ItemPassivo;
import batalhaListeners.itens.passivos.ManterEnergia;
import batalhaListeners.itens.passivos.ManterEscudo;
import static fabricas.FabricaEfeitos.sangramento;

public class FabricaItens {
    
    public static ItemPassivo facaAcougueiro;
    public static ItemPassivo marmita;
    public static ItemPassivo amuletoVelho;
    public static ItemPassivo jarroTerra;
    public static ItemPassivo item20Resist;
    public static ItemPassivo item10Dano;
    public static ItemPassivo item20Dano;
    public static ItemPassivo itemManterEnergia;
    public static ItemPassivo itemManterEscudo;
    public static ItemPassivo item10EscudoInicial;

    public static ItemAtivo pocaoCura20;
    public static ItemAtivo pocaoCura30;
    public static ItemAtivo pocaoCura40;
    public static ItemAtivo pocaoEnergia2;
    public static ItemAtivo pocaoEnergia3;

    public static ItemAtivo cigarro;
    public static ItemAtivo vape;


    public static List<ItemPassivo> listaItensPassivos = new ArrayList<>();
    public static List<ItemAtivo> listaItensAtivos = new ArrayList<>();

    // metodos pra atualizar as listas de itens automaticamente, feitos com ajuda de ia, igual o do fabricaCartas

    private static void atualizarListaItensPassivos() {
        listaItensPassivos.clear();

        for (Field field : FabricaItens.class.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            if (!ItemPassivo.class.isAssignableFrom(field.getType())) {
                continue;
            }

            try {
                ItemPassivo item = (ItemPassivo) field.get(null);
                if (item != null) {
                    listaItensPassivos.add(item);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Erro ao montar lista de itens passivos", e);
            }
        }
    }

    private static void atualizarListaItensAtivos() {
        listaItensAtivos.clear();

        for (Field field : FabricaItens.class.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            if (!ItemAtivo.class.isAssignableFrom(field.getType())) {
                continue;
            }

            try {
                ItemAtivo item = (ItemAtivo) field.get(null);
                if (item != null) {
                    listaItensAtivos.add(item);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Erro ao montar lista de itens ativos", e);
            }
        }
    }


    public static void carregar(){
        // itens passivos

        facaAcougueiro = new EfeitoPorCusto("Faca de açougueiro", "Cartas de ataque com custo menor ou igual a 1 aplicam um acumulo de sangramento.", sangramento, 2, TipoComparacao.MENOR, 95);

        marmita = new CuraFimBatalha("Marmita", "Cura 6 pontos de vida no fim da batalha", 6, 95);

        amuletoVelho = new CartasInicioBatalha("Amuleto velho", "Receba 2 cartas adicionais no inicio da batalha", 2, 70);

        jarroTerra = new AumentaResistItem("Jarro de terra", "Receba 20% de dano reduzido", 20, 85);

        item20Resist = new AumentaResistItem("Colete a prova de tudo", "Receba 30% dano reduzido", 30, 115);

        item10Dano = new AumentaDanoItem("Espada curta", "Cause 20% de dano extra", 20, 85);

        item20Dano = new AumentaDanoItem("Espada longa", "Cause 30% de dano extra", 30, 115);

        itemManterEnergia = new ManterEnergia("Banana Prata", "Mantenha a energia restante no fim de cada rodada.", 100);

        item10EscudoInicial = new EscudoInicioBatalha("Estandarte", "Receba 10 pontos de escudo no inicio da batalha.", 10, 90);

        itemManterEscudo = new ManterEscudo("Boneco do luva de pedreiro", "Mantenha o escudo restante no fim de cada rodada.", 100);

        // itens ativos 

        pocaoCura20 = new PocaoVida("Poção de cura P", "Recupere 20 pontos de vida", 60, 20);

        pocaoCura30 = new PocaoVida("Poção de cura M", "Recupere 30 pontos de vida", 80, 30);

        pocaoCura40 = new PocaoVida("Poção de cura G", "Recupere 40 pontos de vida", 110, 40);

        pocaoEnergia2 = new PocaoEnergia("Pocão de energia P", "Ganhe 2 pontos de energia", 30, 2);

        pocaoEnergia3 = new PocaoEnergia("Pocão de energia G", "Ganhe 3 pontos de energia", 45, 3);

        cigarro = new RecuperaCarta("Marlboro red", "Escolha uma carta para recuperar da pilha de descarte", 40);

        vape = new EscolheCartaItem("Vape", "Escolha uma carta para puxar da pilha de compra", 35);

        atualizarListaItensPassivos();
        atualizarListaItensAtivos();
    }
}
