package Cartas;

import Entidades.*;

// as classes filhas representam os diferentes tipos de carta, baseados nos tipos do jogo slay the spire.
public abstract class Carta {

    private String nome;
    private String descricao;
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

    public String getDescricao() {
        return descricao;
    }
    
    public boolean podeGastar(Heroi heroi){
        int energiaAtual = heroi.getEnergia();
        if(energiaAtual < this.custo){
            return false;
        }
        else return true;
    }

    public abstract void usar(Heroi heroi, Inimigo inimigo);

    public abstract String descricao();
}
