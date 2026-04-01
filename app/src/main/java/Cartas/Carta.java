package Cartas;

import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Batalha;
import Util.Cor;
import Util.Textos;
// as classes filhas representam os diferentes tipos de carta, baseados nos tipos do jogo slay the spire.
public abstract class Carta {

    private String nome;
    private String descricao;
    private int custo;
    private boolean selfCast;
    private String resenha = "";

    /*  -------------------------------------------------
    dicionario de tipos de ação:
    0 - nenhum
    1 - disparo
    2 - corte 
    ----------------------------------------------------- */
    protected int tipo = 0; 

    public Carta(String nome, String descricao, int custo){
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
    }

    public Carta(String nome, String descricao, int custo, int tipo){
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.tipo = tipo;
    }

    public String getNome(){
        if (this instanceof CartaAtaque)
            Cor.setVermelho();

        else if (this instanceof CartaHabilidade)
            Cor.setAzul();

        else if (this instanceof CartaPoder)
            Cor.setRoxo();
    
        return this.nome + Cor.reset;
    }

    // Getters --------------------------------------

    public int getCusto(){
        return this.custo;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean getSelfCast(){
        return selfCast;
    }

    public int getTipo() {
        return tipo;
    }

    public String getResenha() {
        return resenha;
    }

    // Setters --------------------------------------
    
    public void setSelfCast(boolean selfCast) {
        this.selfCast = selfCast;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public void setResenha(String resenha) {
        this.resenha = resenha;
    }


    // ---------------------------------------------
    
    public boolean podeGastar(Heroi heroi){
        return (heroi.getEnergia() >= this.custo);
    }

    public void printaResenha(){
        if (!this.getResenha().equals("")){
                Textos.sleep(300);
                System.out.println(this.getResenha());
                Textos.sleep(300);
                System.out.println();
            }
    }

    public abstract void usar(Heroi heroi, Entidade alvo, Batalha batalha);

    public abstract String descricao();

    public abstract void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha); // so aplica o efeito da carta sem gastar nada
}
