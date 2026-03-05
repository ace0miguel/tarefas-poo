import Bonecos.*;
import Cartas.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Heroi heroi = new Heroi("Capitão Jack Sparrow", 20, 0, 3);
        Inimigo inimigo = new Inimigo("Capitão Barbossa", 10, 0, 2);
        CartaDano cartaDano = new CartaDano("Tiro", 1, 2);
        CartaEscudo cartaEscudo = new CartaEscudo("Jarro de terra", 1, 3);
        Scanner ler = new Scanner(System.in);
        for (int i = 0; i < 100; ++i)  
            System.out.println();

        String titulo = """
         ██▓███   ██▓ ██▀███   ▄▄▄     ▄▄▄█████▓ ▄▄▄        ██████    ▓█████▄  ▒█████      ▄████▄   ▄▄▄       ██▀███   ██▓ ▄▄▄▄   ▓█████ 
        ▓██░  ██▒▓██▒▓██ ▒ ██▒▒████▄   ▓  ██▒ ▓▒▒████▄    ▒██    ▒    ▒██▀ ██▌▒██▒  ██▒   ▒██▀ ▀█  ▒████▄    ▓██ ▒ ██▒▓██▒▓█████▄ ▓█   ▀ 
        ▓██░ ██▓▒▒██▒▓██ ░▄█ ▒▒██  ▀█▄ ▒ ▓██░ ▒░▒██  ▀█▄  ░ ▓██▄      ░██   █▌▒██░  ██▒   ▒▓█    ▄ ▒██  ▀█▄  ▓██ ░▄█ ▒▒██▒▒██▒ ▄██▒███   
        ▒██▄█▓▒ ▒░██░▒██▀▀█▄  ░██▄▄▄▄██░ ▓██▓ ░ ░██▄▄▄▄██   ▒   ██▒   ░▓█▄   ▌▒██   ██░   ▒▓▓▄ ▄██▒░██▄▄▄▄██ ▒██▀▀█▄  ░██░▒██░█▀  ▒▓█  ▄ 
        ▒██▒ ░  ░░██░░██▓ ▒██▒ ▓█   ▓██▒ ▒██▒ ░  ▓█   ▓██▒▒██████▒▒   ░▒████▓ ░ ████▓▒░   ▒ ▓███▀ ░ ▓█   ▓██▒░██▓ ▒██▒░██░░▓█  ▀█▓░▒████▒
        ▒▓▒░ ░  ░░▓  ░ ▒▓ ░▒▓░ ▒▒   ▓▒█░ ▒ ░░    ▒▒   ▓▒█░▒ ▒▓▒ ▒ ░    ▒▒▓  ▒ ░ ▒░▒░▒░    ░ ░▒ ▒  ░ ▒▒   ▓▒█░░ ▒▓ ░▒▓░░▓  ░▒▓███▀▒░░ ▒░ ░
        ░▒ ░      ▒ ░  ░▒ ░ ▒░  ▒   ▒▒ ░   ░      ▒   ▒▒ ░░ ░▒  ░ ░    ░ ▒  ▒   ░ ▒ ▒░      ░  ▒     ▒   ▒▒ ░  ░▒ ░ ▒░ ▒ ░▒░▒   ░  ░ ░  ░
        ░░        ▒ ░  ░░   ░   ░   ▒    ░        ░   ▒   ░  ░  ░      ░ ░  ░ ░ ░ ░ ▒     ░          ░   ▒     ░░   ░  ▒ ░ ░    ░    ░   
                  ░     ░           ░  ░              ░  ░      ░        ░        ░ ░     ░ ░            ░  ░   ░      ░   ░         ░  ░
                                                                       ░                  ░                                     ░       
        """;

        System.out.println(titulo); // fonte: Bloody / site: https://patorjk.com/software/taag/
        Thread.sleep(2000);

        while(heroi.estaVivo()!= 0 && inimigo.estaVivo()!=0){
            
            Thread.sleep(500);
            int turno = 0; // 0: turno do heroi
            System.out.println("=-=");
            System.out.println();
            System.out.println(heroi.status());
            System.out.println("vs");
            System.out.println(inimigo.status());
            System.out.println();

            if (turno == 0){
                System.out.println(heroi.statusEnergia());
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
                        System.out.println();
                    }
                }
                if(opcao == 2){
                    if(cartaEscudo.podeGastar(heroi)){
                        cartaEscudo.usar(heroi);
                    }
                    else {
                        System.out.println("Energia Insuficiente.");
                        System.out.println();
                        Thread.sleep(1000);
                    }
                }
                if(opcao == 3){
                    heroi.resetarEnergia();
                    heroi.receberDano(2);
                    heroi.resetarEscudo();
                    turno = 1;
                    System.out.println("Você foi atacado!");
                }
                
            }
        }
        ler.close();
        System.out.println("DUELO ENCERRADO!");
        System.out.println();
        Thread.sleep(1500);
        if(heroi.estaVivo()==0){
            System.out.println("VOCÊ MORREU");
        }
        else System.out.println("VOCÊ RECUPEROU O PÉROLA NEGRA!");
        System.out.println();
    }
}
