package Entidades;
import Util.Cor;
import Util.Textos;

public class Heroi extends Entidade {
    private int energia;
    private int energiaMax;

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

    public String statusEnergia(){
        if (this.energia > 3)
            Cor.setVerde();
        else if (this.energia > 1)
            Cor.setAmarelo();
        else 
            Cor.setVermelho();

        return "Energia ("+this.energia+"/"+this.energiaMax+")" + Cor.reset;
    }

    @Override
    public void passaRodada(){
        resetarBonus();
        resetarEnergia();
    }

    @Override
    public String status(){
        return (getEscudo() != 0) 
        ? ""+this.getNome()+ Cor.reset + " | " + Textos.desenharBarraVida(this.getVida(), this.getVidaMax()) + " "+Cor.azul+" ("+this.getEscudo()+" de escudo)" + Cor.reset 
        : ""+this.getNome()+ Cor.reset + " | " + Textos.desenharBarraVida(this.getVida(), this.getVidaMax()) + Cor.reset;
    }
}
