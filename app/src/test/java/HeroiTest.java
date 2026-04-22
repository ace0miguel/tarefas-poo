import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import entidades.Heroi; 

public class HeroiTest {

    @Test
    public void danoAbsorvidoPorEscudo() {

        Heroi h = new Heroi("Teste", 10, 3); 
        h.setEscudo(5);
        

        h.receberDano(3); 
        

        assertEquals(10, h.getVida()); 
    }

    @Test
    public void gerenciarEnergia() {
        Heroi h = new Heroi("Teste", 10, 3);
        assertEquals(3, h.getEnergia());
        
        h.usarEnergia(2);
        assertEquals(1, h.getEnergia());
        
        h.ganhaEnergia(1);
        assertEquals(2, h.getEnergia());
        
        h.resetarEnergia();
        assertEquals(3, h.getEnergia());
    }

    @Test
    public void curarVidaNaoPassaMaximo() {
        Heroi h = new Heroi("Teste", 50, 3);
        h.receberDano(20);
        
        h.addVida(10);
        assertEquals(40, h.getVida());
        
        h.addVida(100); 
        assertEquals(50, h.getVida());
    }

    @Test
    public void gerenciarDinheiro() {
        Heroi h = new Heroi("Teste", 10, 3);
        assertEquals(0, h.getDinheiro());
        
        h.addDinheiro(50);
        assertEquals(50, h.getDinheiro());
        
        h.removeDinheiro(15);
        assertEquals(35, h.getDinheiro());
    }

    @Test
    public void passarTurnoZeraEscudoEBuffs() {
        Heroi h = new Heroi("Cavaleiro", 50, 3);
        

        h.ganharEscudo(15);
        h.setPurificar(true);
        
        assertEquals(15, h.getEscudo());
        assertEquals(true, h.getPurificar());
        

        h.passaTurno();
        

        assertEquals(0, h.getEscudo());
        assertEquals(false, h.getPurificar());
    }
}