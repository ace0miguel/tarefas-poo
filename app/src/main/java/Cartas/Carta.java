package Cartas;

import java.util.ArrayList;
import java.util.List;

import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;
import Visual.Cor;
import Visual.Textos;

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
    /** vida perdida ao usar a carta. */
    protected int sacrificio = 0; // variavel pra definir um custo em vida ao se usar a carta.

    /** lista de tags que sao exibidas antes da descriçao da carta */
    protected List<String> tags = new ArrayList<>();

    /** lista publica static que guarda todas as tags existentes e que podem ser compradas */
    public List<String> tagsCompraveis = new ArrayList<>(List.of("Area", "Consumir", "Manter", "Inata"));

    public List<String> tagsRemoviveis = new ArrayList<>(List.of("Area", "Consumir", "Manter", "Inata", "Sacrificio"));

    /** true: afeta TODOS os inimigos */
    protected boolean efeitoEmArea = false;
    /** true: ao ser utilizada vai pra uma pilha de descartes separada */
    protected boolean consumir = false; 
    /** true: não é descartada da mão até ser utilizada */
    protected boolean manter = false;
    /** true: sempre aparece na primeira mão em cada batalha */
    protected boolean inata = false;


    /** tipos de ação:
    0 - nenhum
    1 - disparo
    2 - corte */
    protected int tipo = 0;
    protected boolean usoCancelado = false;

    /** raridades:
    1 - comum
    2 - incomum 
    3 - rara
    4 - especial ? */
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

        // variaveis opcionais
        this.selfCast = copia.selfCast;
        this.tipo = copia.tipo;
        this.resenha = copia.resenha;
        
        this.sacrificio = copia.sacrificio;
        this.consumir = copia.consumir;
        this.efeitoEmArea = copia.efeitoEmArea;
        this.manter = copia.manter;
        this.inata = copia.inata;

        this.raridade = copia.raridade;
        this.tags = new ArrayList<>(copia.tags);
    }

    // Getters --------------------------------------

    /** retorna o nome colorido baseado no tipo da carta */
    public String getNome(){
        if (this instanceof CartaAtaqueComEfeito)
            return Cor.laranja + this.nome + Cor.reset;
        
        else if (this instanceof CartaAtaque)
            return Cor.vermelho + this.nome + Cor.reset;

        else if (this instanceof CartaHabilidade)
            return Cor.azul + this.nome + Cor.reset;

        else if (this instanceof CartaPoder)
            return Cor.rosa + this.nome + Cor.reset;

        else if (this instanceof CartaMaldicao)
            return Cor.cinza + this.nome + Cor.reset;

        return this.nome + Cor.reset;
    }

    /** retorna o nome colorido baseado na raridade da carta */
    public String getNomeRaridade(){
        return switch (raridade) {
            case 1 -> Cor.txtReset(this.nome);
            case 2 -> Cor.txtVerde(this.nome);
            case 3 -> Cor.txtLaranja(this.nome);
            case 4 -> Cor.txtRosa(this.nome);
            default -> Cor.txtCinza(this.nome);
        };
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

    public boolean getUsoCancelado() {
        return usoCancelado;
    }

    public boolean getManter() {
        return manter;
    }

    public boolean getInata() {
        return inata;
    }

    public List<String> getTags() {
        return tags;
    }

    /** retorna todas as tags que a carta nao tem e que podem ser compradas */
    public List<String> getTagsDisponiveis(){
        return new ArrayList<>(this.tagsCompraveis.stream().filter(tag -> !this.tags.contains(tag)).toList());
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

    public void setEfeitoEmArea(boolean _efeitoEmArea) {
        this.efeitoEmArea = _efeitoEmArea;
        if (_efeitoEmArea) {
            if (!tags.contains("Area")) {
                tags.add("Area");
            }
        } else {
            tags.remove("Area");
        }
    }

    public void setConsumir(boolean _consumir) {
        this.consumir = _consumir;
        if (_consumir) {
            if (!tags.contains("Consumir")) {
                tags.add("Consumir");
            }
        } else {
            tags.remove("Consumir");
        }
    }

    public void setManter(boolean _manter) {
        this.manter = _manter;
        if (_manter) {
            if (!tags.contains("Manter")) {
                tags.add("Manter");
            }
        } else {
            tags.remove("Manter");
        }
    }

    public void setInata(boolean _inata) {
        this.inata = _inata;
        if (_inata) {
            if (!tags.contains("Inata")) {
                tags.add("Inata");
            }
        } else {
            tags.remove("Inata");
        }
    }

    public void setSacrificio(int sacrificio) {
        this.sacrificio = sacrificio;
        if (sacrificio  > 0) {
            tags.add("Sacrificio");
        } else {
            tags.remove("Sacrificio");
        }
    }

    public void setRaridade(int raridade) {
        this.raridade = raridade;
    }

    public void setUsoCancelado(boolean usoCancelado) {
        this.usoCancelado = usoCancelado;
    }

    public void aplicarTag(String tag, boolean adicionar) {
        switch (tag) {
            case "Area" -> setEfeitoEmArea(adicionar);
            case "Consumir" -> setConsumir(adicionar);
            case "Manter" -> setManter(adicionar);
            case "Sacrificio" -> {
                if (!adicionar) {
                    setSacrificio(0);
                }
            }
            case "Inata" -> setInata(adicionar);
        }
    }

    // ---------------------------------------------
    
    public boolean podeGastar(Heroi heroi){
        return (heroi.getEnergia() >= this.custo);
    }

    /** imprime uma string ao utilizar a carta, se existir. */
    public void printaResenha(){
        if (!this.getResenha().equals("")){
                Textos.sleep(200);
                Textos.printaLinhaDevagar(this.getResenha());
                Textos.sleep(600);
                System.out.println();
            }
    }

    /** verifica se o heroi tem energia. Se sim, aplica o efeito da carta e subtrai o custo da energia. */
    public abstract void usar(Heroi heroi, Entidade alvo, Batalha batalha);

    /** apenas aplica o efeito da carta, sem consumir */
    public abstract void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha); 

    /** retorna a descriçao da carta ao ser exibida na mao */
    public abstract String descricao();
    
    public abstract Carta criaCopia();

    @Override
    public String toString(){
        return this.descricao();
    }

    /** string a ser printada ao ganhar a carta */
    public String recompensa(){
        return this.getNomeRaridade() + " (Custo: " + this.getCusto() + Cor.txtAmarelo(" energia") +") - " + this.getDescricao();
    }

    public List<String> getTagsCompraveis() {
        return tagsCompraveis;
    }

    public List<String> getTagsRemoviveis() {
        return tagsRemoviveis;
    }

    public boolean isInata() {
        return inata;
    }
}
