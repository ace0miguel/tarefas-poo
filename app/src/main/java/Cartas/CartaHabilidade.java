package Cartas;
import Entidades.Heroi;
import Entidades.Inimigo;
import Telas.Batalha;

/*
Cartas que aplicam efeitos; não causam dano direto.
*/
public class CartaHabilidade extends Carta // no momento ela só da escudo, falta generalizar
{
    private int escudo;

    public CartaHabilidade(String nome, int custo, int escudo){
        super(nome, custo);
        this.escudo = escudo;
    }

    @Override
    public void usar (Heroi heroi, Inimigo inimigo, Batalha batalha){
        int energiaAtual = heroi.getEnergia();

        if(energiaAtual >= this.getCusto()){
            heroi.ganharEscudo(this.escudo);
            heroi.usarEnergia(this.getCusto());
        }
    }

    public String descricao(){
        return "Usar "+this.getNome()+" ("+this.escudo+" pontos de escudo) [custo: " + this.getCusto() + "]";
    }
}
