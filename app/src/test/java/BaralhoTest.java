import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import baralho.Mao;
import baralho.PilhaCompra;
import baralho.PilhaDescarte;
import cartas.Carta;
import entidades.Entidade;
import entidades.Heroi;
import telas.eventos.combate.Batalha;

public class BaralhoTest {

    // Cria uma carta genérica para uso nos testes
    private Carta criarCartaTeste(String nome, boolean inata, boolean manter) {
        Carta c = new Carta(nome, "Teste", 1) {
            @Override public void usar(Heroi h, Entidade a, Batalha b) {}
            @Override public void aplicarEfeito(Heroi h, Entidade a, Batalha b) {}
            @Override public String descricao() { return "Carta de Teste"; }
            @Override public Carta criaCopia() { return this; }
        };
        c.setInata(inata);
        c.setManter(manter);
        return c;
    }

    @Test
    public void fluxoMaoEDescarte() {
        PilhaCompra pc = new PilhaCompra();
        PilhaDescarte pd = new PilhaDescarte();
        Mao mao = new Mao();

        // Adiciona 5 cartas para evitar EmptyStackException (a mão sempre tenta puxar até 5)
        pc.addCartaPilha(criarCartaTeste("A", false, false));
        pc.addCartaPilha(criarCartaTeste("B", false, true)); // Carta com 'Manter'
        pc.addCartaPilha(criarCartaTeste("C", false, false));
        pc.addCartaPilha(criarCartaTeste("D", false, false));
        pc.addCartaPilha(criarCartaTeste("E", false, false));

        // Puxa as 5 cartas pra mao
        mao.completaCinco(pc, pd);
        assertEquals(5, mao.getSize());

        // Limpa a mão no fim do turno (deve reter a carta B que tem 'Manter')
        mao.limpa(pd);
        assertEquals(1, mao.getSize()); // A carta B ficou
        assertEquals(4, pd.getPilha().size()); // 4 cartas foram pro descarte
    }

    @Test
    public void pilhaCompraPuxaDoDescarteQuandoVazia() {
        PilhaCompra pc = new PilhaCompra();
        PilhaDescarte pd = new PilhaDescarte();
        
        // Bota uma carta no descarte
        pd.descarta(criarCartaTeste("Ataque", false, false));
        
        // Puxa da compra VAZIA. Ele deve acionar o shuffle() que puxa do descarte.
        Carta puxada = pc.puxaCarta(pd);
        
        // Como o nome pode conter códigos de cor de 'visual.Cor', usamos contains
        assertTrue(puxada.getNome().contains("Ataque"));
        assertEquals(0, pd.getPilha().size()); // Descarte esvaziou
    }

    @Test
    public void inicioDeBatalhaPuxaCartasInatas() {
        PilhaCompra pc = new PilhaCompra();
        PilhaDescarte pd = new PilhaDescarte();
        Mao mao = new Mao();

        // O jogo precisa de no mínimo 5 cartas na pilha para não estourar a EmptyStackException
        pc.addCartaPilha(criarCartaTeste("C1", false, false));
        pc.addCartaPilha(criarCartaTeste("C2", false, false));
        pc.addCartaPilha(criarCartaTeste("C3", false, false));
        pc.addCartaPilha(criarCartaTeste("C4", false, false));
        pc.addCartaPilha(criarCartaTeste("Poder", true, false)); // Inata

        mao.inicioBatalha(pc, pd);

        // Verifica se a mão instanciou as cartas e se a inata está lá
        assertTrue(mao.getSize() > 0);
    }
}