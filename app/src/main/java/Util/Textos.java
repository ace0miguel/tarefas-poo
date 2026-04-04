package Util;
import java.util.ArrayList;
import java.util.List;

import EfeitosDeStatus.Efeito;
import Entidades.Heroi;
import Entidades.Inimigo;
import Poderes.Poder;

public class Textos {

    public static String escolhaInvalida(int maxEscolha){
        return "Tem que ser um número de 0 a " + maxEscolha + ", capitao!";
    }

    public static void limpaTela(){
        try  { // nao ta funcionando isso aqui nao sla
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e){}

        for(int i = 0; i < 250; i++) System.out.println(); // printar 1 trilhao de linha nunca falha
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
        sleep(500);
        printaLinhaDevagar(Arte.tituloSombreado); sleep(700);
        System.out.println();
        // parte de cima da hud
        
        printaBonito(Cor.txtCinza(Arte.bordaHud1), 2, 0);

        // ---------------------------------

        String linhaStatus = heroi.status();
        for (Poder poder : listaPoderes) {
            linhaStatus += " > " + poder.getNome() + " [" + poder.getStacks() + "] " + Cor.txtCinza("|");
        }

        printaColunaDevagar(linhaStatus,8);
        sleep(25);
        
        for (Efeito efeito : listaEfeitos) {
            if (efeito.getAlvo() == heroi){
                System.out.print("(" + efeito.status() + ") "); sleep(25);
                aux1 = true;
            }
        }        
        System.out.println();

        if (aux1) System.out.println();

        System.out.println(Cor.txtCinza("VERSUS!\n")); sleep(25);

        for (int i = 0; i < inimigos.length; i++){
            if (inimigos[i].estaVivo()){
                printaColunaDevagar(inimigos[i].status(), 8); sleep(25);
                aux2 = false;

                for (Efeito efeito : listaEfeitos) {
                    if (efeito.getAlvo() == inimigos[i]){
                        System.out.print("(" + efeito.status() + ") "); sleep(25);
                        aux2 = true;
                    }
                }
                if (aux2) System.out.println();
                System.out.println();
            }  
        }

        sleep(25);

        // parte de baixo da hud
        
        printaBonito(Cor.txtCinza(Arte.bordaHud1), 2, 0);

        // ---------------------------------
        
        sleep(700);
        System.out.println();

        for (Inimigo inimigo : inimigos) {
                inimigo.anunciarAtaque();
        }

        sleep(500);

        System.out.println();
        System.out.println(heroi.statusEnergia()); 
        System.out.println();

        sleep(500);
    }

    public static void batalhaSemDelay(Heroi heroi, ArrayList<Efeito> listaEfeitos, List<Poder> listaPoderes,  Inimigo... inimigos){
        limpaTela();
        boolean aux1 = false;
        boolean aux2 = false;

        System.out.println((Arte.tituloSombreado));
        System.out.println();

        // parte de cima da hud

        System.out.print(Cor.txtCinza(Arte.bordaHud1));

        // ---------------------------------

        System.out.println();

        System.out.print(heroi.status());
        for (Poder poder : listaPoderes) {
            System.out.print(" > " + poder.getNome() +" ["+poder.getStacks()+"] " + Cor.txtCinza("|"));
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
        // parte de baixo da hud

        System.out.print(Cor.txtCinza(Arte.bordaHud1));

        // ---------------------------------

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
            barraFinal = Cor.verdeClaro + barraFinal + Cor.reset;
        } else if (porcentagem > 0.3) {
            barraFinal = Cor.amareloClaro + barraFinal + Cor.reset;
        } else {
            barraFinal = Cor.vermelho + barraFinal + Cor.reset;
        }

        return barraFinal + Cor.cinza + " " + vidaAtual + Cor.reset + " / " + Cor.reset + vidaMax + Cor.reset;
    }

    public static void printaLinhaDevagar(String texto) {
        int tempoBase = 45; 
        int taxaReducao = 2; 
        int tempoMinimo = 5; 

        String[] linhas = texto.split("\n");
        int tempo = Math.max(tempoMinimo, tempoBase - (linhas.length * taxaReducao));
        
        for (String linha : linhas) {
            System.out.println(linha);
            sleep(tempo);
        }
    }

    public static void printaColunaDevagar(String texto, int tempoMs) {
        String[] linhas = texto.split("\n");
        int maxCols = 0;

        for (String linha : linhas) {
            if (linha.length() > maxCols) {
                maxCols = linha.length();
            }
        }

        StringBuilder[] telaAtual = new StringBuilder[linhas.length];
        for (int i = 0; i < linhas.length; i++) {
            telaAtual[i] = new StringBuilder();
            System.out.println();
        }

        for (int col = 0; col < maxCols; col++) {           
            System.out.print("\u001B[" + linhas.length + "A");
            for (int row = 0; row < linhas.length; row++) {          
                if (col < linhas[row].length()) {
                    telaAtual[row].append(linhas[row].charAt(col));
                }
                System.out.println("\u001B[2K\r" + telaAtual[row].toString());
            }
            sleep(tempoMs);
        }
    }

    public static void printaBonito(String texto, int tempoLetra, int tempoLinha) {       
        String[] linhas = texto.split("\n");

        for (String linha : linhas) {
            for (int i = 0; i < linha.length(); i++) {                
                System.out.print(linha.charAt(i)); 
                sleep(tempoLetra); 
            }
            
            System.out.println();
            sleep(tempoLinha);
        }
    }


    public static void apagarLinhas(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            System.out.print("\u001B[1A\u001B[2K");
        }
        System.out.flush(); 
    }

    /* BARQUINHO Q EU TINHA FEITO PRA HUD ANTIGA TO COM DÓ DE APAGAR DEU MO TRABALHO :(

    // parte de cima do barco 
        System.out.println(Cor.txtMarrom("/".repeat(18)));

        System.out.println(Cor.txtMarrom("/".repeat(32)));
        
        System.out.println(Cor.txtMarromClaro("/".repeat(30)) + Cor.txtMarrom("/".repeat(24)));
        
        System.out.println(Cor.txtMarromClaro("/".repeat(52)) + Cor.txtMarrom("/".repeat(13)));

        System.out.println(Cor.txtMarromClaro("/".repeat(63)) + Cor.txtMarrom("/".repeat(7)));

        System.out.println(" ".repeat(67) + Cor.txtMarrom("////")); 
     
    // parte de baixo do barco
        System.out.println(Cor.txtMarromClaro("/".repeat(68)) + Cor.txtMarrom("////"));
        
        System.out.println(Cor.txtMarromClaro("/".repeat(63)) + Cor.txtMarrom("////"));
        
        System.out.println(Cor.txtMarromClaro("/".repeat(52)) + Cor.txtMarrom("////"));
        
        System.out.println(Cor.txtMarrom("/".repeat(32)));

        System.out.println(Cor.txtMarrom("/".repeat(18)));

        System.out.println();
     
    */
}
