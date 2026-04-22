import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import fabricas.FabricaCartas;

public class FabricaCartasTest {

    @Test
    public void carregarInstanciaTodasAsCartas() {
        // Executa a fábrica (isso passará por todas as linhas do carregar)
        FabricaCartas.carregar();
        
        // A lista de moldes principais deve estar preenchida
        assertTrue(FabricaCartas.listaCartasMoldes.size() > 0);
        
        // Verifica se atributos básicos foram assinados corretamente
        assertNotNull(FabricaCartas.tiro);
        assertTrue(FabricaCartas.tiro.getNome().contains("Tiro")); // Usa contains por causa das Cores
        assertEquals(1, FabricaCartas.tiro.getCusto());
        
        // Verifica modificadores específicos (como Efeito em Área e Consumir)
        assertNotNull(FabricaCartas.bomba);
        assertTrue(FabricaCartas.bomba.getEfeitoEmArea());
        assertFalse(FabricaCartas.bomba.getConsumir());

        assertNotNull(FabricaCartas.presenteMaldito);
        assertTrue(FabricaCartas.presenteMaldito.getConsumir());
        assertEquals(5, FabricaCartas.presenteMaldito.getSacrificio()); // Custo de sangue
        
        // Verifica cartas especiais/maldições (elas não entram na listaMoldes, mas são estanciadas)
        assertNotNull(FabricaCartas.sangrar);
        assertEquals(1, FabricaCartas.sangrar.getCusto());
        
        // Verifica cartas de teste que foram inicializadas com atributos altissimos
        assertNotNull(FabricaCartas.bombaSuprema);
        assertTrue(FabricaCartas.bombaSuprema.getInata());
        assertEquals(10, FabricaCartas.bombaSuprema.getRaridade());
    }
}