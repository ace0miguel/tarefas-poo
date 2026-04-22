import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import visual.Cor;

public class CorTest {

    private final PrintStream saidaOriginal = System.out;
    private final ByteArrayOutputStream conteudoSaida = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(conteudoSaida));
    }

    @AfterEach
    public void tearDown() {
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

        assertTrue(conteudoSaida.toString().endsWith(System.lineSeparator()));
        

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
        

        Cor.setPreto();
        Cor.setCiano();
        Cor.setRoxo();
        Cor.setRosa();
        Cor.setLaranja();
    }
}