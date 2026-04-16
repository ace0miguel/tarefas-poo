import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import entidades.Heroi; 

public class HeroiTest {

    @Test // Isso avisa o JUnit que o método abaixo é um teste
    public void danoAbsorvidoPorEscudo() {
        // 1. Preparar: Cria um heroi com nome, 10 de vida e 3 de energia
        Heroi h = new Heroi("Teste", 10, 3); 
        h.setEscudo(5); // e adiciona os 5 de escudo
        
        // 2. Agir: Herói toma 3 de dano
        h.receberDano(3); 
        
        // 3. Conferir: A vida do herói tem que continuar 10.
        assertEquals(10, h.getVida()); 
    }

    @Test
    public void gerenciarEnergia() {
        Heroi h = new Heroi("Teste", 10, 3);
        assertEquals(3, h.getEnergia()); // Começa com 3
        
        h.usarEnergia(2);
        assertEquals(1, h.getEnergia()); // Gastou 2, sobra 1
        
        h.ganhaEnergia(1);
        assertEquals(2, h.getEnergia()); // Ganhou 1, fica com 2
        
        h.resetarEnergia();
        assertEquals(3, h.getEnergia()); // Reseta para o máximo (3)
    }

    @Test
    public void curarVidaNaoPassaMaximo() {
        Heroi h = new Heroi("Teste", 50, 3); // Vida máxima é 50
        h.receberDano(20); // Vida cai para 30
        
        h.addVida(10);
        assertEquals(40, h.getVida()); // Curou 10, vai para 40
        
        h.addVida(100); 
        assertEquals(50, h.getVida()); // Tentou curar além do máximo, trava em 50
    }

    @Test
    public void gerenciarDinheiro() {
        Heroi h = new Heroi("Teste", 10, 3);
        assertEquals(0, h.getDinheiro()); // Começa zerado
        
        h.addDinheiro(50);
        assertEquals(50, h.getDinheiro());
        
        h.removeDinheiro(15);
        assertEquals(35, h.getDinheiro());
    }
}