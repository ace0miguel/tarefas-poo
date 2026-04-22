import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import fabricas.FabricaEfeitos;
import fabricas.FabricaItens;

public class FabricaItensTest {

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
    public void carregarInstanciaTodosOsItens() {
        FabricaEfeitos.carregar();
        FabricaItens.carregar();

        assertTrue(!((java.util.List<?>) getStaticField(FabricaItens.class, "listaItensPassivos")).isEmpty());
        assertTrue(!((java.util.List<?>) getStaticField(FabricaItens.class, "listaItensAtivos")).isEmpty());

        assertNotNull(getStaticField(FabricaItens.class, "facaAcougueiro"));
        assertTrue(getStaticField(FabricaItens.class, "facaAcougueiro").toString().toLowerCase().contains("faca"));

        assertNotNull(getStaticField(FabricaItens.class, "marmita"));
        assertTrue(getStaticField(FabricaItens.class, "marmita").toString().toLowerCase().contains("cura"));

        assertNotNull(getStaticField(FabricaItens.class, "amuletoVelho"));
        assertNotNull(getStaticField(FabricaItens.class, "jarroTerra"));
        assertNotNull(getStaticField(FabricaItens.class, "item20Resist"));

        assertNotNull(getStaticField(FabricaItens.class, "item10Dano"));
        assertTrue(getStaticField(FabricaItens.class, "item10Dano").toString().contains("20%"));

        assertNotNull(getStaticField(FabricaItens.class, "item20Dano"));
        assertNotNull(getStaticField(FabricaItens.class, "itemManterEnergia"));
        assertNotNull(getStaticField(FabricaItens.class, "itemManterEscudo"));
        assertNotNull(getStaticField(FabricaItens.class, "item10EscudoInicial"));

        assertNotNull(getStaticField(FabricaItens.class, "pocaoCura20"));
        assertNotNull(getStaticField(FabricaItens.class, "pocaoEnergia2"));
        assertNotNull(getStaticField(FabricaItens.class, "vape"));
    }
}
