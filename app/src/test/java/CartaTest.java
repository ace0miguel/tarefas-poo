import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cartas.Carta;
import entidades.Entidade;
import entidades.Heroi;
import telas.eventos.combate.Batalha;

public class CartaTest {

    private Carta getCartaBase() {
        return new Carta("BÃ¡sica", "Apenas uma carta", 2) {
            @Override public void usar(Batalha b) {}
            @Override public void aplicarEfeito(Heroi h, Entidade a, Batalha b) {}
            @Override public String descricao() { return "Descricao base"; }
            @Override public Carta criaCopia() { return this; }
        };
    }

    @org.junit.jupiter.api.Test
    public void aplicarERemoverTags() {
        Carta c = getCartaBase();

        assertFalse(c.isEfeitoEmArea());
        assertFalse(c.getTags().contains("Area"));

        c.aplicarTag("Area", true);
        assertTrue(c.isEfeitoEmArea());
        assertTrue(c.getTags().contains("Area"));

        c.aplicarTag("Area", false);
        assertFalse(c.isEfeitoEmArea());
        assertFalse(c.getTags().contains("Area"));
    }

    @org.junit.jupiter.api.Test
    public void custoEVerificacaoUso() {
        Carta c = getCartaBase(); 
        Heroi h = new Heroi("Guerreiro", 50, 3);
        
        h.resetarEnergia(); 
        assertTrue(c.podeGastar(h));

        h.usarEnergia(3); 
        assertFalse(c.podeGastar(h));
    }

    @org.junit.jupiter.api.Test
    public void formataRaridade() {
        Carta c = getCartaBase();
        
        c.setRaridade(Carta.raridades.INCOMUM);
        assertTrue(c.getNomeRaridade().contains("BÃ¡sica"));
        
        c.setRaridade(Carta.raridades.RARA);
        assertTrue(c.getNomeRaridade().contains("BÃ¡sica"));
    }
}