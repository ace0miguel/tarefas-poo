import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fabricas.FabricaEfeitos;
import fabricas.FabricaItens;

public class FabricaItensTest {

    @Test
    public void carregarInstanciaTodosOsItens() {
        // Carrega os efeitos primeiro, pois a faca de açougueiro usa o efeito de sangramento
        FabricaEfeitos.carregar();
        
        // Executa a fábrica (passa por todo o método carregar)
        FabricaItens.carregar();
        
        // A lista de moldes principais deve estar preenchida com os 7 itens criados
        assertEquals(7, FabricaItens.listaItensMoldes.size());
        
        // Verifica se cada item foi alocado corretamente e suas características base
        assertNotNull(FabricaItens.facaAcougueiro);
        assertTrue(FabricaItens.facaAcougueiro.getNome().contains("faca"));
        
        assertNotNull(FabricaItens.marmita);
        assertTrue(FabricaItens.marmita.getDescricao().contains("cura"));
        
        assertNotNull(FabricaItens.amuletoVelho);
        assertNotNull(FabricaItens.jarroTerra);
        assertNotNull(FabricaItens.item20Resist);
        
        assertNotNull(FabricaItens.item10Dano);
        assertTrue(FabricaItens.item10Dano.getDescricao().contains("10%"));
        
        assertNotNull(FabricaItens.item20Dano);
    }
}