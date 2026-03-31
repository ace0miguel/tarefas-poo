package Entidades;

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
        return "Energia ("+this.energia+"/"+this.energiaMax+")";
    }

    @Override
    public String status(){
        return (getEscudo() != 0) 
        ? ""+this.getNome()+"("+getVida()+"/"+this.getVidaMax()+" de vida) ("+this.getEscudo()+" de escudo)" 
        : ""+this.getNome()+" ("+this.getVida()+"/"+this.getVidaMax()+" de vida)";
    }
}
