package Cartas;
import Entidades.*;

/* 
Cartas que causam dano direto e podem aplicar efeitos secundários.
 */
public class CartaAtaque extends Carta
{
    private int dano;
    
    public CartaAtaque(String nome, int custo, int dano){
        super(nome, custo);
        this.dano = dano;
    }

    @Override
    public void usar(Heroi heroi, Inimigo alvo){
        int energiaAtual = heroi.getEnergia();
        if(energiaAtual >= this.getCusto()){
            alvo.receberDano(this.dano);
            heroi.usarEnergia(this.getCusto());
        }
    }
    
    public int getDano() {
        return dano;
    }

    public String descricao(){
        return "Usar "+this.getNome()+" ("+this.getDano()+" pontos de dano) [custo: " + this.getCusto() + "]";
    }
}
