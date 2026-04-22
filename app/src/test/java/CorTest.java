import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import visual.Cor;

public class CorTest {

    private final PrintStream saidaOriginal = System.out;
    private final ByteArrayOutputStream conteudoSaida = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        // Redireciona os prints do terminal para capturarmos no teste
        System.setOut(new PrintStream(conteudoSaida));
    }

    @AfterEach
    public void tearDown() {
        // Restaura o terminal ao normal
        System.setOut(saidaOriginal);
    }

    @Test
    public void formatacaoTxtRetornaStringColorida() {
        String texto = "teste";
        
        assertEquals(Cor.vermelho + texto + Cor.reset, Cor.txtVermelho(texto));
        assertEquals(Cor.azul + texto + Cor.reset, Cor.txtAzul(texto));
        assertEquals(Cor.verde + texto + Cor.reset, Cor.txtVerde(texto));
        assertEquals(Cor.amareloClaro + texto + Cor.reset, Cor.txtAmareloClaro(texto));
        assertEquals(Cor.cinza + texto + Cor.reset, Cor.txtCinza(texto));
        assertEquals(Cor.vinho + texto + Cor.reset, Cor.txtVinho(texto));
        assertEquals(Cor.marrom + texto + Cor.reset, Cor.txtMarrom(texto));
        
        // Cobre também os retornos direto das outras cores para %
        Cor.txtPreto(texto);
        Cor.txtCiano(texto);
        Cor.txtRosa(texto);
        Cor.txtRoxo(texto);
        Cor.txtLaranja(texto);
    }

    @Test
    public void metodosPrintaAdicionamCoresNoTerminal() {
        Cor.printaAmarelo("Ouro");
        assertTrue(conteudoSaida.toString().contains(Cor.amarelo + "Ouro" + Cor.reset));
        conteudoSaida.reset();

        Cor.printaVerdeClaroLn("Vida");
        assertTrue(conteudoSaida.toString().contains(Cor.verdeClaro + "Vida" + Cor.reset));
        // O método "Ln" adiciona uma quebra de linha (\n ou \r\n dependendo do SO)
        assertTrue(conteudoSaida.toString().endsWith(System.lineSeparator()));
        
        // Chamadas genéricas para cobrir as linhas
        Cor.printaAzulEscuro("A");
        Cor.printaMarromClaroLn("B");
        Cor.printaPreto("C");
        Cor.printaVerdeEscuroLn("D");
    }

    @Test
    public void metodosSetAlteramACorAtiva() {
        Cor.setVermelho();
        assertEquals(Cor.vermelho, conteudoSaida.toString());
        conteudoSaida.reset();

        Cor.setAzul();
        assertEquals(Cor.azul, conteudoSaida.toString());
        conteudoSaida.reset();

        Cor.reset();
        assertEquals(Cor.reset, conteudoSaida.toString());
        
        // Chamadas para cobrir linhas restantes
        Cor.setPreto();
        Cor.setCiano();
        Cor.setRoxo();
        Cor.setRosa();
        Cor.setLaranja();
    }
}