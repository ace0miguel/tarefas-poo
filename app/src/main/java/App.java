import Cartas.*;
import Entidades.*;
import Telas.*;
import Deck.*;
//import java.util.Scanner;

public class App {
    GerenciadorCartas gerenciadorCartas = new GerenciadorCartas();
    public static void main(String[] args) throws Exception {

        //instancias padrao pra teste por enquanto
        Heroi heroi = new Heroi("Capitão Jack Sparrow" );
        Inimigo inimigo = new Inimigo("Capitão Barbossa");
        CartaAtaque cartaDano = new CartaAtaque("Tiro", 1, 2);
        CartaHabilidade cartaEscudo = new CartaHabilidade("Jarro de terra", 1, 3);
        Textos.principal();
        Thread.sleep(2000);

        Batalha.iniciar(heroi,  inimigo);
        
    }
}
