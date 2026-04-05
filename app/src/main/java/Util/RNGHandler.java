package Util;

import java.util.Random;

public class RNGHandler {
    private static Random gen = new Random();

    public static Random getGen() {
        return gen;
    }

    public static int valorAleatorio(int i){
        return gen.nextInt(i) + 1;
    }

    /*recebe uma porcentagem e retorna true or false dependendo se passou no teste
    exemplo: check(50) -> 50% de chance de retornar true, check(40) -> 40% de chance de retornar true*/
    public static boolean check(int valor){
        return (gen.nextInt(100) < valor);
    }
}
