package Cartas;
import Bonecos.Heroi;
public class CartaEscudo 
{
    private String nome;
    private int custo;
    private int escudo;

    public CartaEscudo(String nome, int custo, int escudo){
        this.nome = nome;
        this.custo = custo;
        this.escudo = escudo;
    }

    public String getNome(){
        return this.nome;
    }

    public void usar (Heroi heroi){
        int energiaAtual = heroi.getEnergia();
        if(energiaAtual >= this.custo){
            heroi.ganharEscudo(this.escudo);
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
        return "2 -  Usar Carta de escudo (custo:" + custo + ")";
    }
}
