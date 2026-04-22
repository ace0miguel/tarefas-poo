import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fabricas.FabricaCartas;
import fabricas.FabricaEfeitos;
import fabricas.FabricaInimigos;
import telas.eventos.ArvoreEventos;
import telas.eventos.ArvoreEventos.tipoNode;
import telas.eventos.Evento;

public class ArvoreEventosTest {

    @BeforeEach
    public void setUp() {
        FabricaCartas.carregar();
        FabricaEfeitos.carregar();
        FabricaInimigos.carregar();
    }

    @Test
    public void escolherEventoRespeitaAndaresFixos() {
        ArvoreEventos arvore = new ArvoreEventos(3, 8);

        assertEquals(tipoNode.BATALHA_TRIVIAL, arvore.escolherEvento(0).getFirst());
        assertEquals(tipoNode.BATALHA_FINAL, arvore.escolherEvento(8).getFirst());
        assertEquals(tipoNode.RECOMPENSA, arvore.escolherEvento(4).getFirst());
        assertEquals(tipoNode.CURA, arvore.escolherEvento(7).getFirst());
    }

    @Test
    public void escolherEventoRetornaOpcoesAleatoriasEsperadas() {
        ArvoreEventos arvore = new ArvoreEventos(3, 12);

        List<tipoNode> inicio = arvore.escolherEvento(1);
        assertTrue(inicio.contains(tipoNode.BATALHA_TRIVIAL));

        List<tipoNode> meio = arvore.escolherEvento(3);
        assertTrue(meio.contains(tipoNode.BATALHA_TRIVIAL));
        assertTrue(meio.contains(tipoNode.BATALHA_MEDIA));
        assertTrue(meio.contains(tipoNode.EVENTO_NEUTRO));
        assertTrue(meio.contains(tipoNode.LOJA));
    }

    @Test
    public void expandirNoERecuperarFilhos() {
        ArvoreEventos arvore = new ArvoreEventos(3, 6);
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode(tipoNode.BATALHA_TRIVIAL);

        arvore.expandirNo(raiz);

        assertTrue(raiz.getChildCount() > 0);
        assertTrue(raiz.getChildCount() <= arvore.getNosPorFilho());

        List<DefaultMutableTreeNode> filhos = arvore.getFilhos(raiz);
        List<tipoNode> tipos = arvore.getTipoNodes(raiz);

        assertEquals(raiz.getChildCount(), filhos.size());
        assertEquals(raiz.getChildCount(), tipos.size());
    }

    @Test
    public void expandirNoNaoDuplicaQuandoJaExpandido() {
        ArvoreEventos arvore = new ArvoreEventos(3, 6);
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode(tipoNode.BATALHA_TRIVIAL);

        arvore.expandirNo(raiz);
        int qtdPrimeiraExpansao = raiz.getChildCount();
        arvore.expandirNo(raiz);

        assertEquals(qtdPrimeiraExpansao, raiz.getChildCount());
    }

    @Test
    public void criarArvoreCompletaComProfundidadeEsperada() {
        ArvoreEventos arvore = new ArvoreEventos(2, 3);
        DefaultMutableTreeNode raiz = arvore.criarArvore();

        assertNotNull(raiz);
        assertEquals(0, raiz.getLevel());

        DefaultMutableTreeNode n1 = (DefaultMutableTreeNode) raiz.getChildAt(0);
        DefaultMutableTreeNode n2 = (DefaultMutableTreeNode) n1.getChildAt(0);
        DefaultMutableTreeNode n3 = (DefaultMutableTreeNode) n2.getChildAt(0);

        assertEquals(3, n3.getLevel());
    }

    @Test
    public void eventosAleatoriosJuntaListasEVarargs() {
        ArvoreEventos arvore = new ArvoreEventos(2, 4);

        List<Evento> listaComposta = arvore.getEventosAleatorios(ArvoreEventos.lojas, ArvoreEventos.recompensas);
        assertEquals(ArvoreEventos.lojas.size() + ArvoreEventos.recompensas.size(), listaComposta.size());

        Evento e1 = ArvoreEventos.lojas.getFirst();
        Evento e2 = ArvoreEventos.recompensas.getFirst();
        List<Evento> listaVarargs = arvore.getEventosAleatorios(e1, e2);

        assertEquals(2, listaVarargs.size());
        assertTrue(listaVarargs.contains(e1));
        assertTrue(listaVarargs.contains(e2));
    }

    @Test
    public void tipoNodeForneceEventosValidos() {
        ArvoreEventos arvore = new ArvoreEventos(2, 4);

        assertFalse(tipoNode.BATALHA_TRIVIAL.getEventos(2).isEmpty());
        assertNotNull(tipoNode.LOJA.getEvento());
        assertTrue(tipoNode.EVENTO_NEUTRO.getQuantidadeEventos() > 0);

        assertTrue(arvore.getProfundidadeMax() > 0);
    }
}
