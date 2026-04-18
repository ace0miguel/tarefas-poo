package fabricas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import batalhaListeners.itens.ativos.ItemAtivo;
import batalhaListeners.itens.ativos.PocaoVida;
import batalhaListeners.itens.passivos.AumentaDanoItem;
import batalhaListeners.itens.passivos.AumentaResistItem;
import batalhaListeners.itens.passivos.CartasInicioBatalha;
import batalhaListeners.itens.passivos.CuraFimBatalha;
import batalhaListeners.itens.passivos.EfeitoPorCusto;
import batalhaListeners.itens.passivos.ItemPassivo;

import static fabricas.FabricaEfeitos.sangramento;

public class FabricaItens {
    
    public static ItemPassivo facaAcougueiro;
    public static ItemPassivo marmita;
    public static ItemPassivo amuletoVelho;
    public static ItemPassivo jarroTerra;
    public static ItemPassivo item20Resist;
    public static ItemPassivo item10Dano;
    public static ItemPassivo item20Dano;

    public static ItemAtivo pocaoCura20;
    public static ItemAtivo pocaoCura30;
    public static ItemAtivo pocaoCura40;


    public static List<ItemPassivo> listaItensMoldes = new ArrayList<>();
    public static List<ItemAtivo> listaItensAtivosMoldes = new ArrayList<>();

    public static void carregar(){
        listaItensMoldes.clear();

        facaAcougueiro = new EfeitoPorCusto("Faca de açougueiro", "cartas de ataque custo 1 aplicam um acumulo de sangramento adicional", sangramento, 1);

        marmita = new CuraFimBatalha("Marmita", "cura 6 pontos de vida no fim da batalha", 6);

        amuletoVelho = new CartasInicioBatalha("Amuleto velho", "receba 2 cartas adicionais no inicio da batalha", 2);

        jarroTerra = new AumentaResistItem("Jarro de terra", "receba 10% de redução de dano", 10);

        item20Resist = new AumentaResistItem("Colete a prova de tudo", "receba 20% de redução de dano", 20);

        item10Dano = new AumentaDanoItem("Espada curta", "cause 10% de dano extra", 10);

        item20Dano = new AumentaDanoItem("Espada longa", "cause 20% de dano extra", 20);

        // itens ativos 

        pocaoCura20 = new PocaoVida("Poção de cura P", "Recupere 20 pontos de vida", 20);

        pocaoCura30 = new PocaoVida("Poção de cura M", "Recupere 30 pontos de vida", 30);

        pocaoCura40 = new PocaoVida("Poção de cura G", "Recupere 40 pontos de vida", 40);

        // listas 
        
        listaItensMoldes.addAll(Arrays.asList(facaAcougueiro, marmita, amuletoVelho, jarroTerra, item20Resist, item10Dano, item20Dano));
        listaItensAtivosMoldes.addAll(Arrays.asList(pocaoCura20, pocaoCura30, pocaoCura40));
    }
}
