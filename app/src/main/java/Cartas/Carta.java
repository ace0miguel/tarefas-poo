package Cartas;

import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Batalha;
// as classes filhas representam os diferentes tipos de carta, baseados nos tipos do jogo slay the spire.
public abstract class Carta {

    private String nome;
    private String descricao;
    private int custo;
    private boolean selfCast;

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

    public boolean getSelfCast(){
        return selfCast;
    }
    
    public void setSelfCast(boolean selfCast) {
        this.selfCast = selfCast;
    }
    
    public boolean podeGastar(Heroi heroi){
        return (heroi.getEnergia() > this.custo);
    }

    public abstract void usar(Heroi heroi, Entidade alvo, Batalha batalha);

    public abstract String descricao();
}
