import static org.junit.jupiter.api.Assertions.assertEquals;

import baralho.Mao;
import baralho.PilhaCompra;
import baralho.PilhaDescarte;
import cartas.Carta;
import entidades.Entidade;
import entidades.Heroi;
import entidades.Inimigo;
import telas.eventos.combate.Batalha;

public class BaralhoTest {

    private Carta criarCartaTeste(String nome, boolean inata, boolean manter) {
        Carta c = new Carta(nome, "Teste", 1) {
            @Override public void usar(Batalha b) {}
            @Override public void aplicarEfeito(Heroi h, Entidade a, Batalha b) {}
            @Override public String descricao() { return "Carta de Teste"; }
            @Override public Carta criaCopia() { return this; }
        };
        c.setInata(inata);
        c.setManter(manter);
        return c;
    }

    @org.junit.jupiter.api.Test
    public void fluxoMaoEDescarte() {
        PilhaCompra pc = new PilhaCompra();
        PilhaDescarte pd = new PilhaDescarte();
        Mao mao = new Mao();

        pc.addCarta(criarCartaTeste("A", false, false));
        pc.addCarta(criarCartaTeste("B", false, true)); 
        pc.addCarta(criarCartaTeste("C", false, false));
        pc.addCarta(criarCartaTeste("D", false, false));
        pc.addCarta(criarCartaTeste("E", false, false));

        mao.completaCinco(pc, pd);
        assertEquals(5, mao.getSize());

        Batalha batalha = new Batalha(new Inimigo("Alvo", 10, 1));
        batalha.getPilhaDescarte().clear();
        mao.limpa(batalha);
        assertEquals(1, mao.getSize()); 
        assertEquals(4, batalha.getPilhaDescarte().getPilha().size());
    }

    @org.junit.jupiter.api.Test
    public void pilhaCompraPuxaDoDescarteQuandoVazia() {
        PilhaCompra pc = new PilhaCompra();
        PilhaDescarte pd = new PilhaDescarte();
        
        pd.descarta(criarCartaTeste("Ataque", false, false));
        
        Carta puxada = pc.puxaCarta(pd);
        
        org.junit.jupiter.api.Assertions.assertTrue(puxada.getNome().contains("Ataque"));
        assertEquals(0, pd.getPilha().size());
    }

    @org.junit.jupiter.api.Test
    public void inicioDeBatalhaPuxaCartasInatas() {
        PilhaCompra pc = new PilhaCompra();
        PilhaDescarte pd = new PilhaDescarte();
        Mao mao = new Mao();

        pc.addCarta(criarCartaTeste("C1", false, false));
        pc.addCarta(criarCartaTeste("C2", false, false));
        pc.addCarta(criarCartaTeste("C3", false, false));
        pc.addCarta(criarCartaTeste("C4", false, false));
        pc.addCarta(criarCartaTeste("Poder", true, false)); 

        mao.inicioBatalha(pc, pd);

        org.junit.jupiter.api.Assertions.assertTrue(mao.getSize() > 0);
    }
}