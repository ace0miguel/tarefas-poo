import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertFalse;

import batalhaListeners.efeitos.buffs.AumentaDano;

public class AumentaDanoTest {

    @Test
    public void criarECopiarAumentaDano() {
        AumentaDano buff = new AumentaDano("Fúria", "Aumenta o dano", 3, 5);
        AumentaDano copia = (AumentaDano) buff.criaCopia();

        assertNotSame(buff, copia);
        assertEquals(buff.getNome(), copia.getNome());
    }


    @Test
    public void tentarAcumularBuffsDiferentes() {
        AumentaDano buff1 = new AumentaDano("Fúria", "...", 2, 5);
        AumentaDano buff2 = new AumentaDano("Foco", "...", 2, 5);
        
        assertFalse(buff1.addStack(null, buff2));
    }
}