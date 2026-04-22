import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import org.junit.jupiter.api.Test;

import entidades.Heroi;
import telas.eventos.Loja;

public class LojaTest {

    @Test
    public void criacaoECopia() {
        Loja loja = new Loja();
        Loja copia = loja.criaCopia();

        assertNotSame(loja, copia);
        

        String descricao = loja.toString();
        assertNotNull(descricao);
        assertEquals(true, !descricao.isEmpty());
        assertEquals(true, descricao.contains("Loja"));
    }

    @Test
    public void compraPocaoComSucesso() {
        Heroi h = new Heroi("Comprador", 60, 3);
        h.addDinheiro(100);
        h.receberDano(40);
        
        Loja loja = new Loja();
        

        try {
            java.lang.reflect.Method compraCura = Loja.class.getMethod("compraCura", Heroi.class, int.class, int.class);
            compraCura.invoke(loja, h, 25, 40);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(e);
        }
        
        assertEquals(60, h.getDinheiro());
        assertEquals(45, h.getVida());
    }
}