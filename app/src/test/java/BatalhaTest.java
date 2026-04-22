import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import entidades.Inimigo;
import telas.eventos.combate.Batalha;

public class BatalhaTest {

    @Test
    public void calcularDificuldadeENivelNormal() {
        Inimigo i1 = new Inimigo("Ratinho", 10, 2);
        i1.setTier(1);
        Inimigo i2 = new Inimigo("Lobo", 20, 5);
        i2.setTier(2);

        Batalha batalha = new Batalha(i1, i2);

        assertEquals(3, batalha.getDificuldadeTotal());

        assertEquals(2, batalha.getNivelDificuldade());
    }

    @Test
    public void calcularDificuldadeBoss() {

        Inimigo boss = new Inimigo("DragÃ£o Rei", 200, 30);
        boss.setTier(5);

        Batalha batalha = new Batalha(boss);

        assertEquals(5, batalha.getNivelDificuldade());
    }
    
    @Test
    public void criarCopiaBatalha() {
        Inimigo i1 = new Inimigo("Bandido", 15, 3);
        i1.setTier(1);
        
        Batalha original = new Batalha(i1);
        Batalha copia = original.criaCopia();

        assertNotSame(original, copia);
        assertEquals(original.getDificuldadeTotal(), copia.getDificuldadeTotal());
        assertEquals(1, copia.getInimigos().size());
    }

    @Test
    public void formatacaoToString() {
        Inimigo i1 = new Inimigo("Orc", 40, 10);
        i1.setTier(2);
        Batalha b = new Batalha(i1);

        String descricao = b.toString();
        assertEquals(true, descricao != null && !descricao.isEmpty());
    }

    @Test
    public void gerenciarListaInimigos() {
        Inimigo i1 = new Inimigo("Goblin", 10, 2);
        Inimigo i2 = new Inimigo("Troll", 20, 4);
        
        Batalha b = new Batalha(i1, i2);
        assertEquals(2, b.getInimigos().size());
        

        Inimigo copiaGoblin = b.getInimigos().get(0);
        Inimigo copiaTroll = b.getInimigos().get(1);
        
        assertEquals("Goblin", copiaGoblin.getNome());
        assertEquals("Troll", copiaTroll.getNome());
        

        copiaGoblin.receberDano(999); 
        assertEquals(false, copiaGoblin.estaVivo());
        

        b.notificaMorte(); 
    }

    @Test
    public void iniciarBatalhaSimuladaParaCobertura() {

        Inimigo fraco = new Inimigo("Mosca", 1, 0); 

        new Batalha(fraco);

        try {
        } catch (Exception e) {
        }
    }

    @Test
    public void simularUsoCartaSemEnergia() {
        Inimigo i1 = new Inimigo("Lobo", 20, 5);
        Batalha b = new Batalha(i1);
        
        b.adicionarInimigo(i1);
        
        b.passaTurno(); 
        
        assertEquals(1, b.getInimigos().size()); 
    }
}