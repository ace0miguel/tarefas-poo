import Bonecos.*;
import Cartas.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Heroi heroi = new Heroi("heroi", 20, 0, 3);
        Inimigo inimigo = new Inimigo("inimigo", 10, 0, 2);
        CartaDano cartaDano = new CartaDano("tiro", 1, 2);
        CartaEscudo cartaEscudo = new CartaEscudo("escudo", 1, 3);
        Scanner ler = new Scanner(System.in);
        
        while(heroi.estaVivo()!= 0 && inimigo.estaVivo()!=0){
            
            int turno = 0; // 0: turno do heroi
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
                int opcao = ler.nextInt();
                ler.nextLine();
                System.out.println("=-=");
                System.out.println();
                if(opcao == 1){
                    if(cartaDano.podeGastar(heroi)) {
                        cartaDano.usar(inimigo, heroi);
                    }
                    else {
                        System.out.println("Energia Insuficiente");
                    }
                }
                if(opcao == 2){
                    if(cartaEscudo.podeGastar(heroi)){
                        cartaEscudo.usar(heroi);
                    }
                    else {
                        System.out.println("Energia Insuficiente");
                    }
                }
                if(opcao == 3){
                    heroi.resetarEnergia();
                    heroi.receberDano(2);
                    turno = 1;
                }
                
            }
        }
        ler.close();
        System.out.println("FIM DE JOGO");
        if(heroi.estaVivo()==0){
            System.out.println("VOCÊ MORREU");
        }
        else System.out.println("INIMIGO DERROTADO");
    }
}
