import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fabricas.FabricaCartas;
import fabricas.FabricaEfeitos;

public class FabricaEfeitosTest {

    @Test
    public void carregarInstanciaTodosOsEfeitos() {
        // A fábrica de efeitos usa as cartas 'resenhax' e 'clubex', 
        // então garantimos que a fábrica de cartas rodou primeiro.
        FabricaCartas.carregar();
        
        // Executa a fábrica de efeitos (cobre todas as linhas do carregar)
        FabricaEfeitos.carregar();
        
        // Verifica se a lista principal não está vazia
        assertTrue(FabricaEfeitos.listaEfeitosMoldes.size() > 0);
        
        // Verifica a alocação na memória de diferentes categorias de efeitos
        // Danos Contínuos
        assertNotNull(FabricaEfeitos.sangramento);
        assertNotNull(FabricaEfeitos.veneno);
        
        // Buffs e Defesa
        assertNotNull(FabricaEfeitos.ego);
        assertNotNull(FabricaEfeitos.aumentaResistencia);
        assertNotNull(FabricaEfeitos.escudo10);
        
        // Utilitários 
        assertNotNull(FabricaEfeitos.purificarEfeito);
        assertNotNull(FabricaEfeitos.ganhaEnergia2);
        assertNotNull(FabricaEfeitos.efeitoPuxaCarta2);
        assertNotNull(FabricaEfeitos.escolheCarta);
        
        // Efeitos atrelados a cartas
        assertNotNull(FabricaEfeitos.ganhaResenhax);
        assertNotNull(FabricaEfeitos.ganhaClubex);
    }
}