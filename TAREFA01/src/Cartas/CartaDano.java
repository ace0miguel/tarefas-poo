package Cartas;
import Bonecos.Inimigo;
import Bonecos.Heroi;
public class CartaDano extends Carta
{
    private int dano;

    public CartaDano(String nome, int custo, int dano){
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
