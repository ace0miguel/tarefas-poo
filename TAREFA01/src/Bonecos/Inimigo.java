package Bonecos;
//import Bonecos.Heroi;
public class Inimigo extends Entidade{

    private int dano;

    public Inimigo(String nome, int vida, int vidaMax, int escudo, int dano){
        super(nome, vida, vidaMax, escudo);
        this.dano = dano;
    }

    public void atacar(Heroi alvo){
        alvo.receberDano(this.dano);
    }

    @Override
    public String status(){
        return (getEscudo() != 0) 
        ?" "+this.getNome()+"("+getVida() +"/"+this.getVidaMax()+" de vida) ("+this.getEscudo()+" de escudo)" 
        : ""+this.getNome()+" ("+this.getVida()+"/"+this.getVidaMax()+" de vida)";
    }
}
