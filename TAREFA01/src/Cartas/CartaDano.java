package Cartas;
import Bonecos.Inimigo;
public class CartaDano 
{
    private String nome;
    private int custo;
    private int dano;

    public CartaDano(String nome, int custo, int dano){
        this.nome = nome;
        this.custo = custo;
        this.dano = dano;
    }

    public void usar(Inimigo alvo){
        alvo.receberDano(this.dano);
    }
    
    public String descricao(){
        return "1 -  Usar Carta de Dano (custo:" + custo + ")";
    }
}
