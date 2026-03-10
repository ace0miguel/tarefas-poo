package Cartas;
import Bonecos.*;

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
    
    public String descricao(){
        return "Usar "+this.getNome()+" [custo: " + this.getCusto() + "]";
    }
}
