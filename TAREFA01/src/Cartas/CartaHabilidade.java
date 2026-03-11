package Cartas;
import Bonecos.*;

/*
Cartas que aplicam efeitos; não causam dano direto.
*/
public class CartaHabilidade extends Carta
{
    private int escudo;

    public CartaHabilidade(String nome, int custo, int escudo){
        super(nome, custo);
        this.escudo = escudo;
    }

    @Override
    public void usar (Heroi heroi, Inimigo inimigo){
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
