import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;

import entidades.Inimigo;
import fabricas.FabricaCartas;
import fabricas.FabricaEfeitos;
import fabricas.FabricaInimigos;

public class FabricaInimigosTest {

    private Object getStaticField(Class<?> type, String fieldName) {
        try {
            Field field = type.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(null);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(e);
        }
    }

    @Test
    public void carregarInstanciaTodosOsInimigos() {
        FabricaCartas.carregar();
        FabricaEfeitos.carregar();
        FabricaInimigos.carregar();

        assertTrue(!((java.util.List<?>) getStaticField(FabricaInimigos.class, "listaTodosInimigos")).isEmpty());

        assertNotNull(getStaticField(FabricaInimigos.class, "endrick"));
        assertEquals(1, ((Inimigo) getStaticField(FabricaInimigos.class, "endrick")).getTier());
        assertNotNull(getStaticField(FabricaInimigos.class, "amoeba"));
        assertNotNull(getStaticField(FabricaInimigos.class, "drake"));

        assertNotNull(getStaticField(FabricaInimigos.class, "loudCoringa"));
        assertEquals(2, ((Inimigo) getStaticField(FabricaInimigos.class, "loudCoringa")).getTier());
        assertNotNull(getStaticField(FabricaInimigos.class, "barbossa"));
        assertNotNull(getStaticField(FabricaInimigos.class, "loudSacy"));
        assertNotNull(getStaticField(FabricaInimigos.class, "kungFuPanda"));

        assertNotNull(getStaticField(FabricaInimigos.class, "sabrinaCarpenter"));
        assertEquals(3, ((Inimigo) getStaticField(FabricaInimigos.class, "sabrinaCarpenter")).getTier());
        assertNotNull(getStaticField(FabricaInimigos.class, "tripleT"));

        assertNotNull(getStaticField(FabricaInimigos.class, "paulAtreides"));
        assertEquals(4, ((Inimigo) getStaticField(FabricaInimigos.class, "paulAtreides")).getTier());

        assertNotNull(getStaticField(FabricaInimigos.class, "paulAtreidesSupremo"));
        assertTrue(((Inimigo) getStaticField(FabricaInimigos.class, "paulAtreidesSupremo")).getNome().contains("PAUL"));
        assertEquals(5, ((Inimigo) getStaticField(FabricaInimigos.class, "paulAtreidesSupremo")).getTier());
    }
}
