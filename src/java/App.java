import Cartas.*;
import Deck.*;
import Entidades.*;
import Telas.*;

public class App {
    public static void main(String[] args) throws Exception {
        PilhaCompra pilhaCompra = new PilhaCompra();

        //instancias padrao pra teste por enquanto
        Heroi heroi = new Heroi("Capitão Jack Sparrow", 20, 5 );
        Inimigo inimigo = new Inimigo("Capitão Barbossa", 20, 3);

        //cartas padrao pra teste por enquanto
        for (int i = 0; i < 3; i++){
            pilhaCompra.addCarta( new CartaAtaque("Espada", 1, 2 ));
            pilhaCompra.addCarta( new CartaAtaque("Tiro", 2, 3) );
            pilhaCompra.addCarta( new CartaHabilidade("Escudo de madeira", 1, 2));
            pilhaCompra.addCarta( new CartaHabilidade("Escudo de ferro", 3, 5));
        }

        for(int i=0; i < 1; i++){
            pilhaCompra.addCarta( new CartaAtaque("CANHÃO", 4, 10));
        }

        Textos.principal();
        Thread.sleep(1500);

        Batalha.iniciar(heroi, pilhaCompra, inimigo);
        
    }
}
