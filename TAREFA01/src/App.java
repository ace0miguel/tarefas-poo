import Bonecos.*;
import Cartas.*;
import Telas.*;
//import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        //instancias padrao pra teste por enquanto
        Heroi heroi = new Heroi("Capitão Jack Sparrow", 20, 0, 3);
        Inimigo inimigo = new Inimigo("Capitão Barbossa", 10, 0, 2);
        CartaDano cartaDano = new CartaDano("Tiro", 1, 2);
        CartaEscudo cartaEscudo = new CartaEscudo("Jarro de terra", 1, 3);

        Titulo.principal();
        Thread.sleep(2000);

        Batalha.iniciar(heroi, cartaDano, cartaEscudo, inimigo);
        
    }
}
