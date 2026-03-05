package Cartas;
import Bonecos.Heroi;
public class CartaEscudo extends Carta
{
    private int escudo;

    public CartaEscudo(String nome, int custo, int escudo){
        super(nome, custo);
        this.escudo = escudo;
    }

    public void usar (Heroi heroi){
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
