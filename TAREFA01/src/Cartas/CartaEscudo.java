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

    public void usar(Heroi alvo){
        alvo.ganharEscudo(escudo);
    }
    
    public String descricao(){
        return "2 -  Usar Carta de escudo (custo:" + custo + ")";
    }
}
