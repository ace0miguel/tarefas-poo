import org.junit.jupiter.api.Test;

import entidades.Heroi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import entidades.Inimigo;

public class InimigoTest {

    @Test
    public void calculoRecompensaPorTier() {
        Inimigo i = new Inimigo("Slime", 20, 5);
        
        i.setTier(1);
        assertEquals(15, i.getRecompensa());
        
        i.setTier(2);
        assertEquals(30, i.getRecompensa());
        
        i.setTier(3);
        assertEquals(60, i.getRecompensa());
    }

    @Test
    public void danoEfetivoEFracionado() {
        Inimigo i = new Inimigo("Orc", 40, 10);
        

        assertEquals(10, i.getDanoEfetivo());
        

        assertEquals(5, i.getFracaoDanoEfetivo(2));
    }

    @Test
    public void criarCopia() {
        Inimigo original = new Inimigo("DragÃ£o", 100, 20);
        original.setTier(3);
        
        Inimigo copia = original.criaCopia();
        

        assertNotSame(original, copia);
        assertEquals(original.getNome(), copia.getNome());
        assertEquals(original.getVidaMax(), copia.getVidaMax());
        assertEquals(original.getDano(), copia.getDano());
        assertEquals(original.getTier(), copia.getTier());
    }

    @Test
    public void passarRodadaZeraEscudoEBuffsDoInimigo() {
        Inimigo i = new Inimigo("Esqueleto", 30, 5);
        
        i.ganharEscudo(10);
        assertEquals(10, i.getEscudo());
        

        i.passaRodada();
        

        assertEquals(0, i.getEscudo());
    }

    @Test
    public void checkMeiaVidaNoTurno() {
        Inimigo i = new Inimigo("Zumbi", 40, 5);
        Heroi h = new Heroi("Teste", 30, 3);
        

        assertEquals(40, i.getVida());
        

        i.receberDanoDireto(25);
        assertEquals(15, i.getVida());
        

        i.checkMeiaVida(h, h, null); 
        

        assertEquals(15, i.getVida()); 
    }
}
