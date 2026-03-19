package Entidades;

import java.util.ArrayList;
import EfeitosDeStatus.Efeito;
import Cartas.CartaAtaqueComEfeito;

//import Bonecos.Heroi;
public class Inimigo extends Entidade{

    private int dano;
    private ArrayList<Efeito> sangramentoAcumulado;

    public Inimigo(String nome, int vida, int dano){
        super(nome, vida);
        this.dano = dano;
        this.sangramentoAcumulado = new ArrayList<Efeito>(); // isso aqui é uma lista dos danos de bleeding q o inimigo ainda tem pra tomar a cada turno, pq ele pode ter mais de 1 bleeding

    }

//fazer add do efeito(add sangramento) e o sangrar (receber dANO e verificar duracao)

    public void atacar(Heroi alvo){
        alvo.receberDano(this.dano);
    }

    public void getSangramento(Efeito sangramento){

    }

    public void sangrar()

    @Override
    public String status(){
        return (getEscudo() != 0) 
        ?" "+this.getNome()+"("+getVida() +"/"+this.getVidaMax()+" de vida) ("+this.getEscudo()+" de escudo)" 
        : ""+this.getNome()+" ("+this.getVida()+"/"+this.getVidaMax()+" de vida)";
    }
}
