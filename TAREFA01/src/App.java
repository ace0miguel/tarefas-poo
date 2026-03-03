import Bonecos.*;
import Cartas.*;

public class App {
    public static void main(String[] args) throws Exception {
        Personagem personagem = new Personagem("heroi", 20, 0, 3);
        Inimigo inimigo = new Inimigo("inimigo", 5, 0);
        Carta carta = new Carta();
        CartaDano cartaDano = new CartaDano("tiro", 1, 5);
        CartaEscudo cartaEscudo = new CartaEscudo("escudo", 1, 3);

    while(personagem.estarVivo()!= 0 && inimigo.estarVivo()!=0){
        System.out.println("=-=");
        System.out.println();
        System.out.println(personagem.status());
        System.out.println("vs");
        System.out.println(inimigo.status());
        System.out.println();
        System.out.println(cartaDano.descricao());
        System.out.println(cartaEscudo.descricao());
        

    }
    }
}
