import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import entidades.Inimigo;

public class InimigoTest {

    @Test
    public void calculoRecompensaPorTier() {
        Inimigo i = new Inimigo("Slime", 20, 5); // Ações omitidas para simplificar o teste de atributos básicos
        
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
        
        // Sem dano extra, o dano efetivo é igual ao dano base (10)
        assertEquals(10, i.getDanoEfetivo());
        
        // Fracionando o dano base (10 / 2 = 5)
        assertEquals(5, i.getFracaoDanoEfetivo(2));
    }

    @Test
    public void criarCopia() {
        Inimigo original = new Inimigo("Dragão", 100, 20);
        original.setTier(3);
        
        Inimigo copia = original.criaCopia();
        
        // Verifica se a cópia tem os mesmos atributos, mas é outra instância na memória
        assertNotSame(original, copia);
        assertEquals(original.getNome(), copia.getNome());
        assertEquals(original.getVidaMax(), copia.getVidaMax());
        assertEquals(original.getDano(), copia.getDano());
        assertEquals(original.getTier(), copia.getTier());
    }
}
