package Entidades;
import java.util.ArrayList;
import java.util.List;

import Cartas.Carta;
import Deck.Mao;
import Deck.PilhaCompra;
import Deck.PilhaDescarte;
import Util.Cor;

public class Heroi extends Entidade {
    private int energia;
    private int energiaMax;
    private int energiaBonus; // soma na energia no começo da rodada e reseta.
    private List<Carta> baralho = new ArrayList<>();

    // pra poder acessar a mao e as pilhas pelo heroi
    private Mao maoAtual;
    private PilhaCompra pilhaCompra;
    private PilhaDescarte pilhaDescarte;

    /* inicializa os atributos */
    public Heroi(String nome, int vida, int energiaMax){
        super(nome, vida);
        this.energiaMax = energiaMax;
        this.energia = energiaMax;
    }

    // getters -----

    public int getEnergia(){
        return this.energia;
    }

    public int getEnergiaMax() {
        return energiaMax;
    }

    public int getEnergiaBonus() {
        return energiaBonus;
    }

    public Mao getMaoAtual() {
        return maoAtual;
    }

    public PilhaCompra getPilhaCompra() {
        return pilhaCompra;
    }
    
    public PilhaDescarte getPilhaDescarte() {
        return pilhaDescarte;
    }

    public List<Carta> getBaralho() {
        return baralho;
    }

    // setters -----

    public void setDeck(List<Carta> deck) {
        this.baralho = deck;
    }

    public void setBaralho(List<Carta> baralho) {
        this.baralho = baralho;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void setEnergiaBonus(int energiaBonus) {
        this.energiaBonus = energiaBonus;
    }

    public void setEnergiaMax(int energiaMax) {
        this.energiaMax = energiaMax;
    }

    public void setMaoAtual(Mao maoAtual) {
        this.maoAtual = maoAtual;
    }

    public void setPilhaCompra(PilhaCompra pilhaCompra) {
        this.pilhaCompra = pilhaCompra;
    }

    public void setPilhaDescarte(PilhaDescarte pilhaDescarte) {
        this.pilhaDescarte = pilhaDescarte;
    }
    
    // ----

    public void usarEnergia(int custo){
        this.energia -= custo;
    }

    public void resetarEnergia(){
        this.energia = energiaMax;
    }

    public void ganhaEnergia(int valor){
        this.energia += valor;
    }

    

    public String statusEnergia(){
        if (this.energia > 5) // da pra passar de 5 por meio de cartas!
            Cor.setRosa();
        else if (this.energia > 3)
            Cor.setVerde();
        else if (this.energia > 1)
            Cor.setAmarelo();
        else if (this.energia == 1) 
            Cor.setVermelho();
        else 
            Cor.setCinza();

        return "Energia ("+this.energia+"/"+this.energiaMax+")" + Cor.reset;
    }

    public void addCarta(Carta c){
        baralho.add(c);
    }

    public void removeCarta(Carta c){
        baralho.remove(c);
    }

    @Override
    public void passaRodada(){
        resetarBonus();
        resetarEnergia();
        ganhaEnergia(this.energiaBonus);
        this.energiaBonus = 0;
    }
}
