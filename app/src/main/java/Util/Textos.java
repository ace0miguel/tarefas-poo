package Util;
import Entidades.Heroi;
import Entidades.Inimigo;
import EfeitosDeStatus.Efeito;
import java.util.*;

public class Textos {

    public static void limpaTela(){
        try  {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e){}
    }

    public static void sleep(int time){ // tempo em ms
        try {
            Thread.sleep(time);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void batalha(Heroi heroi, List<Efeito> listaEfeitos, Inimigo... inimigos){
        System.out.println();
        System.out.println();

        System.out.println("=-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-=");
        System.out.println();
        System.out.print(heroi.status());
        for (Efeito efeito : listaEfeitos) {
            if (efeito.getAlvo() == heroi)
                System.out.print(" >" + efeito.status());
        }
        System.out.println("\n");

        System.out.println("VS");

        for(int i = 0; i < inimigos.length; i++){
            if (inimigos[i].estaVivo()){
                System.out.println();
                System.out.print(inimigos[i].status());
                for (Efeito efeito : listaEfeitos) {
                    if (efeito.getAlvo() == inimigos[i])
                    System.out.print(" > " + efeito.status());
                }
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=");
        System.out.println();
    }
}
