import Bonecos.*;
import Cartas.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        int turno = 0; // 0: turno do heroi
        Heroi heroi = new Heroi("heroi", 20, 0, 3);
        Inimigo inimigo = new Inimigo("inimigo", 5, 0, 2);
        Carta carta = new Carta();
        CartaDano cartaDano = new CartaDano("tiro", 1, 5);
        CartaEscudo cartaEscudo = new CartaEscudo("escudo", 1, 3);

        while(heroi.estaVivo()!= 0 && inimigo.estaVivo()!=0){

            System.out.println("=-=");
            System.out.println();
            System.out.println(heroi.status());
            System.out.println("vs");
            System.out.println(inimigo.status());
            System.out.println();

            if (turno == 0){
                System.out.println(cartaDano.descricao());
                System.out.println(cartaEscudo.descricao());
                System.out.println("3 - Encerrar turno");
                System.out.println();
                System.out.println("Escolha:");
                System.out.println("=-=");
                System.out.println();
                
            }
            
            

        }
    }
}
