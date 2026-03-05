package Cartas;

import Bonecos.*;

public abstract class Carta {

    private String nome;
    private int custo;

    public Carta(String nome, int custo){
        this.nome = nome;
        this.custo = custo;
    }

    public String getNome(){
        return this.nome;
    }

    public int getCusto(){
        return this.custo;
    }

    public boolean podeGastar(Heroi heroi){
        int energiaAtual = heroi.getEnergia();
        if(energiaAtual < this.custo){
            return false;
        }
        else return true;
    }

    public abstract void usar(Heroi heroi, Inimigo inimigo);

}
