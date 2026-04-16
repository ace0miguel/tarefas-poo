package fabricas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import batalhaListeners.itens.AumentaDanoItem;
import batalhaListeners.itens.AumentaResistItem;
import batalhaListeners.itens.CartasInicioBatalha;
import batalhaListeners.itens.CuraFimBatalha;
import batalhaListeners.itens.EfeitoPorCusto;
import batalhaListeners.itens.Item;

import static fabricas.FabricaEfeitos.sangramento;

public class FabricaItens {
    
    public static Item facaAcougueiro;
    public static Item marmita;
    public static Item amuletoVelho;
    public static Item jarroTerra;
    public static Item item20Resist;
    public static Item item10Dano;
    public static Item item20Dano;

    public static List<Item> listaItensMoldes = new ArrayList<>();

    public static void carregar(){
        listaItensMoldes.clear();

        facaAcougueiro = new EfeitoPorCusto("faca de açougueiro", "cartas de ataque custo 1 aplicam um acumulo de sangramento adicional", sangramento, 1);

        marmita = new CuraFimBatalha("marmita", "cura 6 pontos de vida no fim da batalha", 6);

        amuletoVelho = new CartasInicioBatalha("amuleto velho", "receba 2 cartas adicionais no inicio da batalha", 2);

        jarroTerra = new AumentaResistItem("jarro de terra", "receba 10% de redução de dano", 10);

        item20Resist = new AumentaResistItem("colete a prova de tudo", "receba 20% de redução de dano", 20);

        item10Dano = new AumentaDanoItem("to sem criatividade sei la espada", "cause 10% de dano extra", 10);

        item20Dano = new AumentaDanoItem("espada grande sei la", "cause 20% de dano extra", 20);


        listaItensMoldes.addAll(Arrays.asList(facaAcougueiro, marmita, amuletoVelho, jarroTerra, item20Resist, item10Dano, item20Dano));
    }
}
