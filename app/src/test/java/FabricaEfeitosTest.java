import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;

import fabricas.FabricaCartas;
import fabricas.FabricaEfeitos;

public class FabricaEfeitosTest {

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
    public void carregarInstanciaTodosOsEfeitos() {
        FabricaCartas.carregar();
        FabricaEfeitos.carregar();

        assertTrue(!((java.util.List<?>) getStaticField(FabricaEfeitos.class, "listaEfeitosMoldes")).isEmpty());
        assertNotNull(getStaticField(FabricaEfeitos.class, "sangramento"));
        assertNotNull(getStaticField(FabricaEfeitos.class, "veneno"));
        assertNotNull(getStaticField(FabricaEfeitos.class, "ego"));
        assertNotNull(getStaticField(FabricaEfeitos.class, "aumentaResistencia"));
        assertNotNull(getStaticField(FabricaEfeitos.class, "escudo10"));
        assertNotNull(getStaticField(FabricaEfeitos.class, "purificarEfeito"));
        assertNotNull(getStaticField(FabricaEfeitos.class, "ganhaEnergia2"));
        assertNotNull(getStaticField(FabricaEfeitos.class, "puxaCarta2"));
        assertNotNull(getStaticField(FabricaEfeitos.class, "escolheCarta"));
        assertNotNull(getStaticField(FabricaEfeitos.class, "ganhaAdaga"));
        assertNotNull(getStaticField(FabricaEfeitos.class, "descarta1"));
        assertNotNull(getStaticField(FabricaEfeitos.class, "recebeDanoPuro2"));
        assertNotNull(getStaticField(FabricaEfeitos.class, "gananciaEfeito"));
        assertNotNull(getStaticField(FabricaEfeitos.class, "ganhaResenhax"));
        assertNotNull(getStaticField(FabricaEfeitos.class, "ganhaClubex"));
    }
}
