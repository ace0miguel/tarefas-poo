package Entidades;
import java.util.ArrayList;
import java.util.List;

import Cartas.Carta;
import Cartas.CartaMaldicao;
import Deck.Mao;
import Deck.PilhaCompra;
import Deck.PilhaDescarte;
import Util.InputHandler;
import Visual.Cor;
import Visual.Textos;

public class Heroi extends Entidade {
    private int energia;
    private int energiaMax;
    private int energiaBonus = 0; // energia extra a ganhar no começo da rodada
    private int cartasBonus = 0; // cartas extras a ganhar no começo da rodada

    private int dinheiro = 0; 

    private List<Carta> baralho = new ArrayList<>(); // todas as cartas q o jogador tem e ta usando
    private List<Carta> inventarioCartas = new ArrayList<>(); // todas as cartas q o jogador tem mas nao ta usando

    // guarda a mao e as pilhas por referencia
    private Mao maoAtual;
    private PilhaCompra pilhaCompra;
    private PilhaDescarte pilhaDescarte;

    private boolean testMode = false; // true caso o jogador escolha o deck teste

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

    public List<Carta> getInventarioCartas() {
        return inventarioCartas;
    }

    public int getDinheiro() {
        return dinheiro;
    }

    public boolean getTestMode() {
        return testMode;
    }

    public int getCartasBonus() {
        return cartasBonus;
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

    public void setCartasBonus(int cartasBonus) {
        this.cartasBonus = cartasBonus;
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

    public void setInventarioCartas(List<Carta> inventarioCartas) {
        this.inventarioCartas = inventarioCartas;
    }

    public void setDinheiro(int dinheiro) {
        this.dinheiro = dinheiro;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public void fimBatalhaReset(){
        this.cartasBonus = 0;
        this.energiaBonus = 0;
    }
    // ----

    public void ganhaDinheiro(int valor) {
        Textos.limpaTela();
        System.out.println("Voce recebeu " + Cor.txtAmarelo(String.valueOf(valor)) + " dinheiros!");
        InputHandler.esperar();
        this.dinheiro += valor;
    }

    public void gastaDinheiro(int valor) {
        Textos.limpaTela();
        System.out.println("Voce gastou " + Cor.txtVermelho(String.valueOf(valor)) + " dinheiros!");
        InputHandler.esperar();
        this.dinheiro -= valor;
    }

    public void ganhaVida(int valor){
        this.vida += valor;
        if (this.vida > this.vidaMax)
            this.vida = this.vidaMax;

        System.out.println("Voce ganhou " + Cor.txtVerde(String.valueOf(valor)) + " pontos de vida!");
        InputHandler.esperar();
    }

    public void usarEnergia(int custo){
        this.energia -= custo;
    }

    public void resetarEnergia(){
        this.energia = energiaMax;
    }

    public void ganhaEnergia(int valor){
        this.energia += valor;
    }

    /** Imprime a energia atual do heroi, com cores diferentes a depender da
     * quantidade atual.
     */
    public String statusEnergia(){
        if (this.energia > this.energiaMax){ // da pra passar do max com cartas
            Cor.setRosa();
            return "ENERGIA!!! ("+this.energia+"/"+this.energiaMax+")" + Cor.reset;
        }
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

    // adicionam e removem cartas do baralho
    public void addCarta(Carta c){
        baralho.add(c.criaCopia());
    }

    public void addCartas(Carta c, int quantidade){
        for (int i = 0; i < quantidade; i++){
            addCarta(c);
        }
    }

    public void removeCarta(Carta c){
        baralho.remove(c);
    }

    // adicionam e removem cartas do inventario
    public void addCartaInventario(Carta c){
        inventarioCartas.add(c.criaCopia());
    }

    public void removeCartaInventario(Carta c){
        inventarioCartas.remove(c);
    }

    /** Troca uma carta entre o baralho e o inventário
     * @param c : carta a ser trocada
     */
    public void trocaCarta(Carta c){
        if (c instanceof CartaMaldicao){
            Textos.limpaTela();
            System.out.println("maldiçoes nao podem ser removidas do baralho!");
            InputHandler.esperar();
            return;
        }
        if (baralho.contains(c)){
            removeCarta(c);
            inventarioCartas.add(c);
            return;
        }
        if (inventarioCartas.contains(c)){
            removeCartaInventario(c);
            baralho.add(c);
            return;
        }
        System.out.println("Essa carta nao existe ou voce nao tem.");
        InputHandler.esperar();
    }

    /** aplica os bonus de inicio de rodadae reseta */
    public void aplicaBonus(){
        ganhaEnergia(this.energiaBonus);
        maoAtual.setCartasBonus(maoAtual.getCartasBonus() + this.cartasBonus);

        energiaBonus = 0;
        cartasBonus = 0;    
    }

    /** reseta o estado do heroi e soma a energia bonus */
    @Override
    public void passaRodada(){
        resetarBonus();
        resetarEnergia();
        aplicaBonus();
    }
}
