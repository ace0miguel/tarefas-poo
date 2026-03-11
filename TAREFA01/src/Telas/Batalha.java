package Telas;
import Bonecos.*;
import Cartas.*;
import java.util.*;


public class Batalha {

    public static void iniciar(Heroi heroi, Carta cartaDano, Carta cartaEscudo, Inimigo... _inimigos){

        List<Inimigo> inimigos = new ArrayList<>(Arrays.asList(_inimigos)); // converte o array inimigos em arraylist para facilitar a manipulação.

        Scanner ler = new Scanner(System.in);

        //<-- AQUI: FALTA CRIAR A MÃO, EMBARALHAR E TALS

        while(heroi.estaVivo() == true && inimigos.stream().anyMatch(i -> i.estaVivo() == true)){ // checa se o heroi ou ao menos um inimigo esta vivo
                
            Titulo.sleep(500);

            int turno = 0; // 0: turno do heroi (talvez em algum momento o heroi possa nao começar, talvez tenha que ser ajustado)
            
            Titulo.batalha(heroi, _inimigos);

            if (turno == 0){
                System.out.println(heroi.statusEnergia());
                int aux = Mao.mostrar(); // VAI FUNCIONAR DPS QUE CRIAR A MÃO

                // ---------> CODIGO VELHO, APAGAR DEPOIS QUE VERSIONAR O LAB 1 <----------------------------------------------------------------------------------

                System.out.println("3 - Encerrar turno");
                System.out.println();
                System.out.println("Escolha:");
                int opcao = ler.nextInt();
                ler.nextLine();
                System.out.println("=-=");
                System.out.println();
                if(opcao == 1){
                    if(cartaDano.podeGastar(heroi)) {
                        //cartaDano.usar(inimigo, heroi);
                    }
                    else {
                        System.out.println("Energia Insuficiente");
                        System.out.println();
                    }
                }
                if(opcao == 2){
                    if(cartaEscudo.podeGastar(heroi)){
                        //cartaEscudo.usar(heroi);
                    }
                    else {
                        System.out.println("Energia Insuficiente.");
                        System.out.println();
                        Titulo.sleep(1000);
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
        Titulo.sleep(1500);
        if(heroi.estaVivo() == false){
            System.out.println("VOCÊ MORREU");
        }
        else System.out.println("VOCÊ RECUPEROU O PÉROLA NEGRA!");
        System.out.println();
    }
}
