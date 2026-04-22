import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import cartas.Carta;
import fabricas.FabricaCartas;

public class FabricaCartasTest {

    @Test
    public void carregarInstanciaTodasAsCartas() {
        // Executa a fábrica (isso passará por todas as linhas do carregar)
        FabricaCartas.carregar();
        
        // A lista de moldes principais deve estar preenchida
        assertFalse(FabricaCartas.cartasEncontraveis.isEmpty());
        
        // Verifica se atributos básicos foram assinados corretamente
        assertNotNull(FabricaCartas.tiro);
        assertTrue(FabricaCartas.tiro.getNome().contains("Disparo")); // Usa contains por causa das Cores
        assertEquals(1, FabricaCartas.tiro.getCusto());
        
        // Verifica modificadores específicos (como Efeito em Área e Consumir)
        assertNotNull(FabricaCartas.bomba);
        assertTrue(FabricaCartas.bomba.isEfeitoEmArea());
        assertFalse(FabricaCartas.bomba.isConsumir());

        assertNotNull(FabricaCartas.presenteMaldito);
        assertTrue(FabricaCartas.presenteMaldito.isConsumir());
        assertEquals(3, FabricaCartas.presenteMaldito.getSacrificio()); // Custo de sangue
        
        // Verifica cartas especiais/maldições (elas não entram na listaMoldes, mas são estanciadas)
        assertNotNull(FabricaCartas.sangrar);
        assertEquals(1, FabricaCartas.sangrar.getCusto());
        
        // Verifica cartas de teste que foram inicializadas com atributos altissimos
        assertNotNull(FabricaCartas.bombaSuprema);
        assertTrue(FabricaCartas.bombaSuprema.isInata());
        assertEquals(Carta.raridades.IMPOSSIVEL.getValor(), FabricaCartas.bombaSuprema.getRaridade());
    }
}