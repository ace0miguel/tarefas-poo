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
    /** custo em energia da carta */
    private int custo;
    /** se true, o efeito associado a carta é aplicado em si mesmo */
    private boolean selfCast;
    /** exibido ao utilizar a carta (de preferencia uma arte ascii) */
    private String resenha = "";
    /** true: afeta TODOS os inimigos */
    protected boolean efeitoEmArea = false;
    /** true: ao ser utilizada vai pra uma pilha de descartes separada */
    protected boolean consumir = false; // se verdadeiro, a carta vai pra uma pilha de descarte especial ao ser usada. (nao volta pra mao)
    //** vida perdida ao usar a carta. */
    protected int sacrificio = 0; // variavel pra definir um custo em vida ao se usar a carta.

    /**  -------------------------------------------------
    dicionario de tipos de ação:
    0 - nenhum
    1 - disparo
    2 - corte 
    ----------------------------------------------------- */
    protected int tipo = 0; 

    /** --------------------------------------------------
    dicionario de raridades:
    1 - comum
    2 - incomum 
    3 - rara
    4 - especial ?
    ----------------------------------------------------- */
    protected int raridade = 1;

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

    public Carta(Carta copia){
        this.nome = copia.nome;
        this.descricao = copia.descricao;
        this.custo = copia.custo;

        this.setSelfCast(copia.getSelfCast());
        this.tipo = copia.tipo;
        this.setResenha(copia.getResenha());
        this.consumir = copia.getConsumir();
        this.setEfeitoEmArea(copia.getEfeitoEmArea());
        this.setSacrificio(copia.sacrificio);
        this.raridade = copia.raridade;
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

    public int getSacrificio() {
        return sacrificio;
    }

    public int getRaridade() {
        return raridade;
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

    public void setSacrificio(int sacrificio) {
        this.sacrificio = sacrificio;
    }

    public void setRaridade(int raridade) {
        this.raridade = raridade;
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

    @Override
    public String toString(){
        return this.getNome() + " (Custo: " + this.getCusto() + Cor.txtAmarelo(" energia") +") - " + this.getDescricao();
    }
}
