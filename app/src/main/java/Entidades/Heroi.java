package Entidades;
import java.util.ArrayList;
import java.util.List;

import Cartas.Carta;
import Util.Cor;
import Util.Textos;

public class Heroi extends Entidade {
    private int energia;
    private int energiaMax;
    private List<Carta> deck = new ArrayList<>();

    /* inicializa os atributos */
    public Heroi(String nome, int vida, int energiaMax){
        super(nome, vida);
        this.energiaMax = energiaMax;
        this.energia = energiaMax;
    }

    public int getEnergia(){
        return this.energia;
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

    public List<Carta> getDeck() {
        return deck;
    }

    public void setDeck(List<Carta> deck) {
        this.deck = deck;
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

    @Override
    public void passaRodada(){
        resetarBonus();
        resetarEnergia();
    }
}
