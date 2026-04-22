import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import batalhaListeners.poderes.Poder;
import cartas.CartaPoder;
import entidades.Heroi;
import entidades.Inimigo;
import telas.eventos.combate.Batalha;
import telas.eventos.Evento;

public class CartaPoderTest {

    private void setHeroi(Batalha batalha, Heroi heroi) {
        try {
            java.lang.reflect.Field field = Evento.class.getDeclaredField("heroi");
            field.setAccessible(true);
            field.set(batalha, heroi);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(e);
        }
    }

    // Poder falso apenas para testar a mecânica da carta
    class PoderTeste extends Poder {
        public PoderTeste(String nome, String desc) { super(nome, desc); }
        public PoderTeste(PoderTeste copia) { super(copia); }
        @Override public Poder criaCopia() { return new PoderTeste(this); }
        @Override public String status() { return ""; }
        @Override public int getPrioridade() { return 0; }
    }

    @Test
    public void criarECopiarCartaPoder() {
        PoderTeste poder = new PoderTeste("Força", "Aumenta atributos");
        CartaPoder carta = new CartaPoder("Forma Divina", "Ativa o poder", 2, poder);
        
        // CartaPoder sempre deve ser de uso único no combate e usada em si mesmo
        assertTrue(carta.isConsumir());
        assertTrue(carta.getSelfCast());

        CartaPoder copia = (CartaPoder) carta.criaCopia();
        assertNotSame(carta, copia);
        assertEquals(carta.getNome(), copia.getNome());
        assertTrue(copia.isConsumir());
    }

    @Test
    public void usarCartaComEnergiaESacrificio() {
        Heroi h = new Heroi("Mago", 50, 3);
        Inimigo i = new Inimigo("Ogro", 40, 5);
        Batalha b = new Batalha(i);
        setHeroi(b, h);

        PoderTeste poder = new PoderTeste("Poder Falso", "...");
        CartaPoder carta = new CartaPoder("Força Oculta", "...", 2, poder);
        carta.setSacrificio(5);

        carta.usar(b);

        assertEquals(1, h.getEnergia()); // Gastou 2 de energia
        assertEquals(45, h.getVida()); // Sofreu o dano de sacrifício
    }

    @Test
    public void usarCartaSemEnergiaSuficiente() {
        Heroi h = new Heroi("Mago", 50, 1); // Energia insuficiente para custo 2
        Inimigo i = new Inimigo("Ogro", 40, 5);
        Batalha b = new Batalha(i);
        setHeroi(b, h);

        PoderTeste poder = new PoderTeste("Poder Falso", "...");
        CartaPoder carta = new CartaPoder("Força Oculta", "...", 2, poder);
        carta.setSacrificio(5);

        carta.usar(b);

        assertEquals(1, h.getEnergia()); // Energia manteve
        assertEquals(50, h.getVida()); // Não sofreu sacrifício, pois não usou
    }

    @Test
    public void formatacaoDaDescricao() {
        PoderTeste poder = new PoderTeste("Aura", "...");
        CartaPoder carta = new CartaPoder("Invocar Aura", "Concede aura", 1, poder);
        carta.setSacrificio(2);
        carta.aplicarTag("Inata", true);

        String desc = carta.descricao();

        assertTrue(desc.contains("Invocar Aura"));
        assertTrue(desc.contains("Concede aura"));
        assertTrue(desc.contains("Inata"));
        assertTrue(desc.contains("Sacrifício: 2"));
        assertTrue(desc.contains("Aura"));
        assertTrue(desc.contains("custo: 1"));
    }
}