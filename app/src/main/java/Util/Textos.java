package Util;
import java.util.ArrayList;
import java.util.List;

import EfeitosDeStatus.Efeito;
import Entidades.Heroi;
import Entidades.Inimigo;
import Poderes.Poder;

public class Textos {

    public static void limpaTela(){
        try  {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e){}

        for(int i = 0; i < 100; i++) System.out.println();
    }

    public static void sleep(int time){ // tempo em ms
        try {
            Thread.sleep(time);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void batalha(Heroi heroi, ArrayList<Efeito> listaEfeitos, List<Poder> listaPoderes,  Inimigo... inimigos){
        limpaTela();
        boolean aux1 = false;
        boolean aux2 = false;

        Cor.printaMarrom("=-==-=-==-=-==-=-==-=-=-==-=-==-=-==-=-==-=-=-==-=-==-\n");
        Cor.printaMarrom("=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-\n");
        Cor.printaMarrom("=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-");

        sleep(100);

        System.out.println("\n");

        System.out.print(heroi.status());
        for (Poder poder : listaPoderes) {
            System.out.print(" > " + poder.getNome() +" ["+poder.getStacks()+"] " + Cor.txtPreto("|"));
        }

        System.out.println();

        for (Efeito efeito : listaEfeitos) {
            if (efeito.getAlvo() == heroi){
                System.out.print("(" + efeito.status() + ") ");
                aux1 = true;
            }
        }

        System.out.println();

        if (aux1) System.out.println();

        Cor.printaCinza("VERSUS!\n");

        System.out.println();

        for (int i = 0; i < inimigos.length; i++){
            if (inimigos[i].estaVivo()){
                System.out.println(inimigos[i].status());
                aux2 = false;

                for (Efeito efeito : listaEfeitos) {
                    if (efeito.getAlvo() == inimigos[i]){
                        System.out.print("(" + efeito.status() + ") ");
                        aux2 = true;
                    }
                }
                if (aux2) System.out.println();
                System.out.println();
            }  
        }

        sleep(100);

        Cor.printaMarrom("=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-\n");
        Cor.printaMarrom("=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-\n");
        Cor.printaMarrom("=-==-=-==-=-==-=-==-=-=-==-=-==-=-==-=-==-=-=-==-=-==-");
        System.out.println();
        System.out.println();

        sleep(400);

        for (Inimigo inimigo : inimigos) {
                inimigo.anunciarAtaque();
                sleep(300);
        }
                
        System.out.println();
        System.out.println(heroi.statusEnergia()); 
        System.out.println();

        sleep(300);
    }

    public static void batalhaSemDelay(Heroi heroi, ArrayList<Efeito> listaEfeitos, List<Poder> listaPoderes,  Inimigo... inimigos){
        limpaTela();
        boolean aux1 = false;
        boolean aux2 = false;

        Cor.printaMarrom("=-==-=-==-=-==-=-==-=-=-==-=-==-=-==-=-==-=-=-==-=-==-\n");
        Cor.printaMarrom("=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-\n");
        Cor.printaMarrom("=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-");

        System.out.println("\n");

        System.out.print(heroi.status());
        for (Poder poder : listaPoderes) {
            System.out.print(" > " + poder.getNome() +" ["+poder.getStacks()+"] " + Cor.txtPreto("|"));
        }

        System.out.println();

        for (Efeito efeito : listaEfeitos) {
            if (efeito.getAlvo() == heroi){
                System.out.print("(" + efeito.status() + ") ");
                aux1 = true;
            }
        }

        System.out.println();

        if (aux1) System.out.println();

        Cor.printaCinza("VERSUS!\n");

        System.out.println();

        for (int i = 0; i < inimigos.length; i++){
            if (inimigos[i].estaVivo()){
                System.out.println(inimigos[i].status());
                aux2 = false;

                for (Efeito efeito : listaEfeitos) {
                    if (efeito.getAlvo() == inimigos[i]){
                        System.out.print("(" + efeito.status() + ") ");
                        aux2 = true;
                    }
                }
                if (aux2) System.out.println();
                System.out.println();
            }  
        }

        Cor.printaMarrom("=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-\n");
        Cor.printaMarrom("=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-\n");
        Cor.printaMarrom("=-==-=-==-=-==-=-==-=-=-==-=-==-=-==-=-==-=-=-==-=-==-");
        System.out.println();
        System.out.println();

        for (Inimigo inimigo : inimigos) {
                inimigo.anunciarAtaque();
        }
                
        System.out.println();
        System.out.println(heroi.statusEnergia()); 
        System.out.println();
    }

    public static String desenharBarraVida(int vidaAtual, int vidaMax) {
        
        int tamanhoMax = 12; 
        
        double porcentagem = (double) vidaAtual / vidaMax;
        
        int blocosCheios = (int) Math.round(porcentagem * tamanhoMax);
        int blocosVazios = tamanhoMax - blocosCheios;

        StringBuilder barra = new StringBuilder();
        
        // parte cheia
        for (int i = 0; i < blocosCheios; i++) {
            barra.append("█"); 
        }

        // parte vazia
        for (int i = 0; i < blocosVazios; i++) {
            barra.append("░");
        }

        String barraFinal = barra.toString();
        
        if (porcentagem > 0.6) {
            barraFinal = Cor.verde + barraFinal + Cor.reset;
        } else if (porcentagem > 0.3) {
            barraFinal = Cor.amarelo + barraFinal + Cor.reset;
        } else {
            barraFinal = Cor.vermelho + barraFinal + Cor.reset;
        }

        return barraFinal + " " + vidaAtual + "/" + vidaMax;
    }
}
