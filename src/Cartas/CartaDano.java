package Cartas;
import Bonecos.Inimigo;
import Bonecos.Heroi;
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

    public String getNome(){
        return this.nome;
    }

    public void usar(Inimigo alvo, Heroi heroi){
        int energiaAtual = heroi.getEnergia();
        if(energiaAtual >= this.custo){
            alvo.receberDano(this.dano);
            heroi.usarEnergia(this.custo);
        }
    }

    public boolean podeGastar(Heroi heroi){
        int energiaAtual = heroi.getEnergia();
        if(energiaAtual < this.custo){
            return false;
        }
        else return true;
    }
    
    public String descricao(){
        return "1 -  Usar Tiro (custo: " + custo + ")";
    }
}
