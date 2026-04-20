package util;

import java.util.List;
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

    @SuppressWarnings("null")
    /** recebe uma lista e retorna um item aleatório contido nela dela */
    public static <T> T sorteiaDeLista(List<T> lista){   
        if (lista == null || lista.isEmpty()) {
            System.out.println("Tentou sortear de uma lista vazia");
            InputHandler.esperar();
            return null;
        }
        
        int index = gen.nextInt(lista.size());
        return lista.get(index);
    }
}
