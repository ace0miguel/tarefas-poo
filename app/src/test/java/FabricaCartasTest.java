import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import fabricas.FabricaCartas;

public class FabricaCartasTest {

    @Test
    public void carregarInstanciaTodasAsCartas() {

        FabricaCartas.carregar();
        

        assertFalse(FabricaCartas.cartasEncontraveis.isEmpty());
        

        assertNotNull(FabricaCartas.tiro);
        assertTrue(FabricaCartas.tiro.getNome().contains("Disparo"));
        assertEquals(1, FabricaCartas.tiro.getCusto());
        

        assertNotNull(FabricaCartas.bomba);
        assertTrue(FabricaCartas.bomba.isEfeitoEmArea());
        assertFalse(FabricaCartas.bomba.isConsumir());

        assertNotNull(FabricaCartas.presenteMaldito);
        assertTrue(FabricaCartas.presenteMaldito.isConsumir());
        assertEquals(3, FabricaCartas.presenteMaldito.getSacrificio());
        

        assertNotNull(FabricaCartas.sangrar);
        assertEquals(1, FabricaCartas.sangrar.getCusto());
        

        assertNotNull(FabricaCartas.bombaSuprema);
        assertTrue(FabricaCartas.bombaSuprema.isInata());
        assertEquals(99, FabricaCartas.bombaSuprema.getRaridade());
    }
}