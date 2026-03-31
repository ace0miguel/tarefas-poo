package Handlers;

import java.util.Random;

public class RNGHandler {
    private static Random gen = new Random();

    public static Random getGen() {
        return gen;
    }
}
