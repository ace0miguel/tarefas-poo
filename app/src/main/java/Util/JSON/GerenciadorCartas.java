package Util.JSON;
import java.util.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.*;

import Cartas.Carta;
import Cartas.CartaAtaque;
import Cartas.CartaHabilidade;
import Cartas.CartaPoder;

public class GerenciadorCartas {
    public static class cartaList{
    private List<CartaAtaque> cartaAtaque;
    private List<CartaHabilidade> cartaHabilidade;
    private List<CartaPoder> cartaPoder;
    }

    private Gson gson = new Gson();
    private cartaList catalogoCartas;

    /* true: carrega todas as cartas do jogo
       false: carrega as cartas em posse do jogador */
    public void carregarCartas(boolean todas){ 
        String local = (todas == true) 
        ? "./TAREFA01/app/src/main/resources/dados/todascartas.json" 
        : "./TAREFA01/app/src/main/resources/dados/cartas.json" ;

        try (FileReader reader = new FileReader(local)){
            catalogoCartas = gson.fromJson(reader, cartaList.class);
        } catch(FileNotFoundException e){
            System.out.println("arquivo nao encontrado");
        } catch(IOException e){
            System.out.println("erro na leitura do json");
        }
    }

    public ArrayList<Carta> getAllCartas(){
        ArrayList<Carta> todasCartas = new ArrayList<Carta>();
        if (catalogoCartas != null && catalogoCartas.cartaAtaque != null 
            && catalogoCartas.cartaPoder!= null 
            && catalogoCartas.cartaHabilidade != null ){
        todasCartas.addAll(catalogoCartas.cartaAtaque);
        todasCartas.addAll(catalogoCartas.cartaHabilidade);
        todasCartas.addAll(catalogoCartas.cartaPoder);
        }
        return todasCartas;
    }

    public ArrayList<CartaAtaque> getAtaque(){
        return (catalogoCartas != null && catalogoCartas.cartaAtaque != null) 
        ? new ArrayList<CartaAtaque>(catalogoCartas.cartaAtaque) 
        : new ArrayList<CartaAtaque>();
    }

    public ArrayList<CartaHabilidade> getHabilidade(){
        return (catalogoCartas != null && catalogoCartas.cartaHabilidade != null) 
        ? new ArrayList<CartaHabilidade>(catalogoCartas.cartaHabilidade) 
        : new ArrayList<CartaHabilidade>();
    }

    public ArrayList<CartaPoder> getPoder(){
        return (catalogoCartas != null && catalogoCartas.cartaPoder != null) 
        ? new ArrayList<CartaPoder>(catalogoCartas.cartaPoder) 
        : new ArrayList<CartaPoder>();
    }
}