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
}
