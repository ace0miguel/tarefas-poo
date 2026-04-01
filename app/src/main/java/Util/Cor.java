package Util;

public class Cor {
    public static String reset = "\u001B[0m";
    public static String vermelho = "\u001B[31m";
    public static String verde = "\u001B[32m";
    public static String azul = "\u001B[34m";
    public static String amarelo = "\u001B[33m";

    // printadores
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

    // setadores
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

    public static void txtReset() {
        System.out.print(reset);
    }

    //retornadores
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
}