package Util;

public class Cor {
    public static String reset = "\u001B[0m";
    
    public static String vermelho = "\u001B[31m";
    public static String verde = "\u001B[32m";
    public static String azul = "\u001B[34m";
    public static String amarelo = "\u001B[33m";

    public static String preto = "\u001B[30m";
    public static String cinza = "\u001B[90m"; 
    public static String roxo = "\u001B[35m"; 
    public static String rosa = "\u001B[95m"; 
    public static String laranja = "\u001B[38;5;208m"; 
    public static String marrom = "\u001B[38;5;130m";
    public static String marromClaro = "\u001B[38;5;137m";

    // ----------------------------------------------------------------------------------------

    public static void printaVerde(String printa) {
        System.out.print(verde + printa + reset);
    }

    public static void printaVermelho(String printa) {
        System.out.print(vermelho + printa + reset);
    }

    public static void printaAmarelo(String printa) {
        System.out.print(amarelo + printa + reset);
    }

    public static void printaAzul(String printa) {
        System.out.print(azul + printa + reset);
    }

    public static void printaPreto(String printa) {
        System.out.print(preto + printa + reset);
    }

    public static void printaCinza(String printa) {
        System.out.print(cinza + printa + reset);
    }

    public static void printaRoxo(String printa) {
        System.out.print(roxo + printa + reset);
    }

    public static void printaRosa(String printa) {
        System.out.print(rosa + printa + reset);
    }

    public static void printaLaranja(String printa) {
        System.out.print(laranja + printa + reset);
    }

    public static void printaMarrom(String printa) {
        System.out.print(marrom + printa + reset);
    }

    public static void printaMarromClaro(String printa) {
        System.out.print(marromClaro + printa + reset);
    }

    // ----------------------------------------------------------------------------------------

    public static void setVerde() {
        System.out.print(verde);
    }

    public static void setVermelho() {
        System.out.print(vermelho);
    }

    public static void setAmarelo() {
        System.out.print(amarelo);
    }

    public static void setAzul() {
        System.out.print(azul);
    }

    public static void setPreto() {
        System.out.print(preto);
    }

    public static void setCinza() {
        System.out.print(cinza);
    }

    public static void setRoxo() {
        System.out.print(roxo);
    }

    public static void setRosa() {
        System.out.print(rosa);
    }

    public static void setLaranja() {
        System.out.print(laranja);
    }

    public static void setMarrom() {
        System.out.print(marrom);
    }

    public static void setMarromClaro() {
        System.out.print(marromClaro);
    }

    public static void reset() {
        System.out.print(reset);
    }

    // ----------------------------------------------------------------------------------------

    public static String txtReset() {
        return reset;
    }
    public static String txtVerde(String txt) {
        return verde + txt + reset;
    }

    public static String txtVermelho(String txt) {
        return vermelho + txt + reset;
    }

    public static String txtAmarelo(String txt) {
        return amarelo + txt + reset;
    }

    public static String txtAzul(String txt) {
        return azul + txt + reset;
    }

    public static String txtPreto(String txt) {
        return preto + txt + reset;
    }

    public static String txtCinza(String txt) {
        return cinza + txt + reset;
    }

    public static String txtRoxo(String txt) {
        return roxo + txt + reset;
    }

    public static String txtRosa(String txt) {
        return rosa + txt + reset;
    }

    public static String txtLaranja(String txt) {
        return laranja + txt + reset;
    }

    public static String txtMarrom(String txt) {
        return marrom + txt + reset;
    }

    public static String txtMarromClaro(String txt) {
        return marromClaro + txt + reset;
    }
}