package Cartas;

import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;
import Util.Cor;
import Util.Textos;
// as classes filhas representam os diferentes tipos de carta, baseados nos tipos do jogo slay the spire.
public abstract class Carta {

    private String nome;
    private String descricao;
    private int custo;
    private boolean selfCast;
    private String resenha = "";
    protected boolean efeitoEmArea = false; // so ta implementado pra ataque por enquanto, falta coloca no habilidade!
    protected boolean consumir = false; // se verdadeiro, a carta vai pra uma pilha de descarte especial ao ser usada. (nao volta pra mao)

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

    // Getters --------------------------------------

    public String getNome(){
        if (this instanceof CartaAtaque)
            return Cor.vermelho + this.nome + Cor.reset;

        else if (this instanceof CartaHabilidade)
            return Cor.azul + this.nome + Cor.reset;

        else if (this instanceof CartaPoder)
            return Cor.rosa + this.nome + Cor.reset;

        else if (this instanceof CartaMaldicao);
            return Cor.cinza + this.nome + Cor.reset;

        // return this.nome + Cor.reset;
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

    public int getTipo() {
        return tipo;
    }

    public String getResenha() {
        return resenha;
    }

    public boolean getEfeitoEmArea(){
        return this.efeitoEmArea;
    }

    public boolean getConsumir(){
        return this.consumir;
    }

    public boolean temResenha() {
        return !this.resenha.equals("");
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setEfeitoEmArea(boolean efeitoEmArea) {
        this.efeitoEmArea = efeitoEmArea;
    }

    public void setConsumir(boolean consumir) {
        this.consumir = consumir;
    }

    // ---------------------------------------------
    
    public boolean podeGastar(Heroi heroi){
        return (heroi.getEnergia() >= this.custo);
    }

    public void printaResenha(){
        if (!this.getResenha().equals("")){
                Textos.sleep(200);
                Textos.printaLinhaDevagar(this.getResenha());
                Textos.sleep(600);
                System.out.println();
            }
    }

    /**Verifica se o heroi tem energia. Se sim, aplica o efeito da carta e subtrai o custo da energia.*/
    public abstract void usar(Heroi heroi, Entidade alvo, Batalha batalha);

    /**Apenas aplica o efeito da carta.*/
    public abstract void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha); // so aplica o efeito da carta sem gastar nada

    
    public abstract String descricao();
    
    public abstract Carta criaCopia();
}
