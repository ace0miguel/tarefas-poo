import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import batalhaListeners.poderes.MaosLeves;
import cartas.Carta;
import entidades.Entidade;
import entidades.Heroi;
import entidades.Inimigo;
import telas.eventos.combate.Batalha;

public class MaosLevesTest {

    // Carta falsa usada para contar quantas vezes o "aplicarEfeito" é chamado
    class CartaTeste extends Carta {
        public int vezesAplicado = 0;
        private final int tipoFalso;

        public CartaTeste(int tipoFalso) {
            super("Carta Dublê", "...", 1);
            this.tipoFalso = tipoFalso;
        }

        @Override
        public int getTipo() {
            return this.tipoFalso;
        }

        @Override
        public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
            this.vezesAplicado++;
        }

        @Override
        public void usar(Batalha batalha) {}

        @Override
        public String descricao() { return ""; }

        @Override
        public Carta criaCopia() { return this; }
    }

    @Test
    public void validacaoBasicaECopia() {
        MaosLeves poder = new MaosLeves("Mãos Leves", "Desc", 1);
        MaosLeves copia = (MaosLeves) poder.criaCopia();

        assertNotSame(poder, copia);
        assertEquals(0, poder.getPrioridade());
        
        String status = poder.status();
        assertTrue(status.contains("tipo 1")); // Verifica se injetou o tipo certo no status
    }

    @Test
    public void onHitAcionaEfeitoApenasComMesmoTipo() {
        Heroi h = new Heroi("Atirador", 50, 3);
        Inimigo i = new Inimigo("Alvo", 50, 5);
        Batalha b = new Batalha(i);

        MaosLeves poder = new MaosLeves("Mãos Leves", "Desc", 2); // Ativado pelo tipo 2
        
        CartaTeste cartaIncorreta = new CartaTeste(1);
        CartaTeste cartaCorreta = new CartaTeste(2);

        // Dispara o OnHit com a carta de tipo errado (não deve fazer nada)
        poder.onHit(cartaIncorreta, h, i, b);
        assertEquals(0, cartaIncorreta.vezesAplicado);

        // Dispara o OnHit com a carta certa
        poder.onHit(cartaCorreta, h, i, b);
        
        // Deve ter aplicado o efeito o mesmo número de vezes dos Stacks do Poder
        assertEquals(poder.getStacks(), cartaCorreta.vezesAplicado);
    }
}