package Cartas;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;


import com.google.gson.*;

public class GerenciadorCartas {
    public static class cartaList{
    private List<CartaAtaque> cartaAtaqueList; // = new ArrayList<CartaAtaque>();
    private List<CartaHabilidade> cartaHabilidadeList; // = new ArrayList<CartaHabilidade>();
    private List<CartaPoder> cartasPoderList; // = new ArrayList<CartaPoder>();
    }

    Gson gson = new Gson();
    String jsonCartas = new String(Files.readAllBytes(Paths.get("./TAREFA01/app/src/main/resources/dados/cartas.json")));
    cartaList temp = gson.fromJson(jsonCartas, cartaList.class);

    



}