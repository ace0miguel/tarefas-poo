import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fabricas.FabricaCartas;
import fabricas.FabricaEfeitos;
import fabricas.FabricaInimigos;

public class FabricaInimigosTest {

    @Test
    public void carregarInstanciaTodosOsInimigos() {
        // Antes de carregar inimigos, as cartas e efeitos que eles usam nas ações precisam existir
        FabricaCartas.carregar();
        FabricaEfeitos.carregar();
        
        // Executa a fábrica (cobre todas as linhas do carregar)
        FabricaInimigos.carregar();
        
        // A lista de moldes principais deve estar preenchida
        assertTrue(FabricaInimigos.listaInimigosMoldes.size() > 0);
        
        // --- Verifica a alocação e Tiers ---
        
        // Tier 1
        assertNotNull(FabricaInimigos.endrick);
        assertEquals(1, FabricaInimigos.endrick.getTier());
        assertNotNull(FabricaInimigos.amoeba);

        // Tier 2
        assertNotNull(FabricaInimigos.loudCoringa);
        assertEquals(2, FabricaInimigos.loudCoringa.getTier());
        assertNotNull(FabricaInimigos.barbossa);
        
        // Tier 3
        assertNotNull(FabricaInimigos.sabrinaCarpenter);
        assertEquals(3, FabricaInimigos.sabrinaCarpenter.getTier());
        
        // Tier 4 e Boss (Tier 5)
        assertNotNull(FabricaInimigos.paulAtreides);
        assertEquals(4, FabricaInimigos.paulAtreides.getTier());
        
        assertNotNull(FabricaInimigos.paulAtreidesSupremo);
        assertTrue(FabricaInimigos.paulAtreidesSupremo.getNome().contains("PAUL"));
        assertEquals(5, FabricaInimigos.paulAtreidesSupremo.getTier());
    }
}