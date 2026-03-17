import Cartas.*;
import Entidades.*;
import Telas.*;
import Deck.*;
//import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        GerenciadorCartas gerenciadorCartas = new GerenciadorCartas();
        PilhaCompra pilhaCompra = new PilhaCompra();

        gerenciadorCartas.carregarCartas(false);

        //instancias padrao pra teste por enquanto
        Heroi heroi = new Heroi("Capitão Jack Sparrow", 20, 3 );
        Inimigo inimigo = new Inimigo("Capitão Barbossa", 10, 3);

        //cartas padrao pra teste por enquanto
        for (int i = 0; i < 100; i++){
            pilhaCompra.addCarta( new CartaAtaque("Tiro", 1, 2) );
            pilhaCompra.addCarta( new CartaHabilidade("Jarro de terra", 1, 3) );
        }

        Textos.principal();
        Thread.sleep(2000);

        Batalha.iniciar(heroi, pilhaCompra, inimigo);
        
    }
}
