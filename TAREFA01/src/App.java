import Bonecos.*;
import Cartas.*;

public class App {
    public static void main(String[] args) throws Exception {
        Personagem personagem = new Personagem("heroi", 20, 20);
        Inimigo inimigo = new Inimigo("inimigo", 5, 5);
        Carta carta = new Carta();
        CartaDano cartaDano = new CartaDano();
        CartaEscudo cartaEscudo = new CartaEscudo();

    while(personagem.estarVivo()!= 0 && inimigo.estarVivo()!=0){
        System.out.println("=-=");
        System.out.println();
        System.out.println();
    }
    }
}
