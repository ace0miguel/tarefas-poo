package fabricas;

import java.util.ArrayList;
import java.util.List;

import batalhaListeners.poderes.CartaAdicional;
import batalhaListeners.poderes.MaosLeves;
import batalhaListeners.poderes.Poder;

public class FabricaPoderes {
    public static List<Poder> listaPoderesMoldes = new ArrayList<>();
    
    public static Poder dedoNervoso = new MaosLeves(("JOHN WICK!"), "Sempre que atirar, ATIRE NOVAMENTE! pelo tanto de acumulos desse poder.", 1);

    public static Poder mestreLaminas = new MaosLeves(("MESTRE DAS LÂMINAS"), "Sempre que cortar, CORTE NOVAMENTE! pelo tanto de acumulos desse poder.", 2);

    public static Poder cartaAdicional = new CartaAdicional("CONTRATO DE SANGUE", "No início de cada turno, puxe 1 carta adicional e perca 1 ponto de vida.", 1);

    public static void carregar(){
        listaPoderesMoldes.clear();
         
        cartaAdicional.setSacrificio(1);

        listaPoderesMoldes.addAll(java.util.Arrays.asList(dedoNervoso, mestreLaminas, cartaAdicional));
    }
}
