import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import org.junit.jupiter.api.Test;

import batalhaListeners.efeitos.buffs.AumentaResistencia;

public class AumentaResistenciaTest {

    @Test
    public void criarECopiarAumentaResistencia() {
        AumentaResistencia buff = new AumentaResistencia("Pele de Pedra", "Aumenta resistência", 3, 10);
        AumentaResistencia copia = (AumentaResistencia) buff.criaCopia();

        assertNotSame(buff, copia);
        assertEquals(buff.getNome(), copia.getNome());
    }


    @Test
    public void tentarAcumularBuffsDiferentes() {
        AumentaResistencia buff1 = new AumentaResistencia("Defesa Absoluta", "...", 2, 5);
        AumentaResistencia buff2 = new AumentaResistencia("Guarda Leve", "...", 2, 2);
        
        // Não deve acumular porque os nomes são diferentes
        assertFalse(buff1.addStack(null, buff2));
    }
}