package Entidades;
public class Heroi extends Entidade {
    private int energia;
    private int energiaMax;

    /* inicializa os atributos */
    public Heroi(String nome, int vida, int vidaMax, int escudo, int energiaMax){
        super(nome, vida, vidaMax, escudo);
        this.energiaMax = energiaMax;
    }

    public int getEnergia(){
        return this.energia;
    }

    public String statusEnergia(){
        return "Energia ("+this.energia+"/"+this.energiaMax+")";
    }

    public void usarEnergia(int custo){
        this.energia -= custo;
    }

    public void resetarEnergia(){
        energia = energiaMax;
    }

    @Override
    public String status(){
        return (getEscudo() != 0) 
        ? " "+this.getNome()+"("+getVida()+"/"+this.getVidaMax()+" de vida) ("+this.getEscudo()+" de escudo)" 
        : ""+this.getNome()+" ("+this.getVida()+"/"+this.getVidaMax()+" de vida)";
    }
}
