package Util;
import Entidades.Heroi;
import Entidades.Inimigo;

public class Textos {

    public static void limpaTela(){
        for(int i = 0; i < 150; i++) System.out.println();
    }

    public static void sleep(int time){ // tempo em ms
        try {
            Thread.sleep(time);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void batalha(Heroi heroi, Inimigo... inimigos){
        System.out.println();
        System.out.println();

        System.out.println("=-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-=");
        System.out.println();
        System.out.println(heroi.status());
        System.out.println();
        System.out.println("vs");
        for(int i = 0; i < inimigos.length; i++){
            if (inimigos[i].estaVivo()){
                System.out.println();
                System.out.println(inimigos[i].status());
            }
        }
        System.out.println();
        System.out.println("=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=");
        System.out.println();
        System.out.println();
    }
}
