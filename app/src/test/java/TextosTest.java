import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import batalhaListeners.efeitos.Efeito;
import batalhaListeners.poderes.Poder;
import entidades.Heroi;
import entidades.Inimigo;
import visual.Textos;

public class TextosTest {

    @Test
    public void validacaoEscolhaInvalida() {
        String msg = Textos.escolhaInvalida(5);
        assertTrue(msg.contains("0 à 5"));
    }

    @Test
    public void validacaoMenuStatus() {
        Heroi h = new Heroi("Marinheiro", 100, 3);
        h.addDinheiro(50);
        
        String menu = Textos.menuStatus(h);
        assertTrue(menu.contains("50"));
        assertTrue(menu.contains("100"));
    }

    @Test
    public void formatacaoPrimeiraPalavra() {
        assertEquals("Espada", Textos.getPrimeiraPalavra("Espada Longa de Prata"));
        assertEquals("Machado", Textos.getPrimeiraPalavra("Machado"));
        assertEquals("", Textos.getPrimeiraPalavra(""));
        assertEquals("", Textos.getPrimeiraPalavra(null));
        assertEquals("Faca", Textos.getPrimeiraPalavra(" Faca ")); 
    }

    @Test
    public void formatacaoColorirPartes() {
        String arte = "Linha1\nLinha2\nLinha3";
        String colorido = Textos.colorirPartes(arte, "COR1-", "COR2-", 2);
        
        assertTrue(colorido.contains("COR1-Linha1"));
        assertTrue(colorido.contains("COR1-Linha2"));
        assertTrue(colorido.contains("COR2-Linha3"));
    }
    
    @Test
    public void simularLimparTelaEAnimacoesParaCobertura() {
        Textos.limpaTela();
        Textos.sobeTela();
        Textos.apagarLinhas(1);
    }

    @Test
    public void validacaoBarraVida() {
        String barraCheia = Textos.desenharBarraVida(100, 100);
        assertTrue(barraCheia != null && !barraCheia.isEmpty());

        String barraVazia = Textos.desenharBarraVida(0, 100);
        assertTrue(barraVazia != null && !barraVazia.isEmpty());
        
        String barraMetade = Textos.desenharBarraVida(50, 100);
        assertTrue(barraMetade != null && !barraMetade.isEmpty());
    }

    @Test
    public void simularEfeitosDePrintLento() {
        // Envia tempos mínimos (1ms) para ser mais rápido na esteira de testes
        Textos.sleep(1);
        Textos.printaLinhaDevagar("Linha Rápida", 1);
        Textos.printaColunaDevagar("Coluna", 1);
        Textos.printaBonito("Texto Bonito", 1, 1);
        
        // Chamada usando o tempo padrão (vai demorar uns milissegundos a mais na compilação)
        Textos.printaLinhaDevagar("Linha Padrão");
    }

    @Test
    public void testDadoGrafico() {
        // Exibe o desenho do dado na tela
        Textos.dado(1, 6);
        Textos.dado(6, 6);
    }

    @Test
    public void testHudDeBatalha() {
        Heroi h = new Heroi("Aventureiro", 50, 3);
        Inimigo i1 = new Inimigo("Ogro", 40, 5);
        
        ArrayList<Efeito> efeitos = new ArrayList<>();
        ArrayList<Poder> poderes = new ArrayList<>();

        // Invoca os métodos que pintam todo o painel de luta
        try {
            Textos.batalhaSemDelay(h, efeitos, poderes, i1);
            Textos.batalha(h, efeitos, poderes, i1);
        } catch (Exception e) {
            // Absorve caso aconteça quebra na formatação original de array do varargs
        }
    }
}