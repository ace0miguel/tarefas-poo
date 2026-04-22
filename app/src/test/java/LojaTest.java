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

        // Verifica se a cópia instanciou um objeto diferente
        assertNotSame(loja, copia);
        
        // Verifica se a toString está retornando validamente
        String descricao = loja.toString();
        assertNotNull(descricao);
        assertEquals(true, !descricao.isEmpty());
        assertEquals(true, descricao.contains("Loja"));
    }

    @Test
    public void compraPocaoComSucesso() {
        Heroi h = new Heroi("Comprador", 60, 3);
        h.addDinheiro(100); // Dá 100 de dinheiro para o heroi
        h.receberDano(40);  // Vida cai para 20
        
        Loja loja = new Loja();
        
        // Testando apenas a compra BEM SUCEDIDA, pois a tentativa de 
        // compra sem dinheiro engatilha InputHandler.esperar(), pausando o Gradle
        try {
            java.lang.reflect.Method compraCura = Loja.class.getMethod("compraCura", Heroi.class, int.class, int.class);
            compraCura.invoke(loja, h, 25, 40);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(e);
        }
        
        assertEquals(60, h.getDinheiro()); // Gastou 40 dos 100 reais (sobram 60)
        assertEquals(45, h.getVida());     // Curou 25 (de 20 para 45 de vida)
    }
}