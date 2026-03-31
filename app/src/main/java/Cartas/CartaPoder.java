package Cartas;
import Entidades.*;
import Telas.Batalha;

/*
Aplica melhoria permanente para todo o encontro de combate.
*/

// TEMPORARIAMENTE COPIEI O CARTAHABILIDADE COMO PLACEHOLDER

public class CartaPoder extends Carta
{
    private int escudo;

    public CartaPoder(String nome, int custo, int escudo){
        super(nome, custo);
        this.escudo = escudo;
    }

    @Override
    public void usar (Heroi heroi, Entidade inimigo, Batalha batalha){
        int energiaAtual = heroi.getEnergia();

        if(energiaAtual >= this.getCusto()){
            heroi.ganharEscudo(this.escudo);
            heroi.usarEnergia(this.getCusto());
        }
    }

    public String descricao(){
        return "Usar "+this.getNome()+" [custo: " + this.getCusto() + "]";
    }
}
