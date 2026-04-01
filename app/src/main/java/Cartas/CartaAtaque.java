package Cartas;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Batalha;

/* 
Cartas que causam dano direto e podem aplicar efeitos secundários.
 */
public class CartaAtaque extends Carta
{
    private int dano;

    
    public CartaAtaque(String nome, String descricao, int custo, int dano){
        super(nome, descricao, custo);
        this.dano = dano;
    }
    
    public CartaAtaque(String nome, String descricao, int custo, int dano, int tipo){
        super(nome, descricao, custo);
        this.dano = dano;
        this.tipo = tipo;
    }

    @Override
    public void usar(Heroi heroi, Entidade alvo, Batalha batalha){
        int energiaAtual = heroi.getEnergia();
        if(energiaAtual >= this.getCusto()){
            alvo.receberDano(this.dano);
            heroi.usarEnergia(this.getCusto());
            
            printaResenha();
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
        alvo.receberDano(this.dano);

        printaResenha();
    }
    
    
    public int getDano() {
        return dano;
    }

    public String descricao(){
        return "Usar "+this.getNome()+" - "+this.getDescricao()+" ("+this.getDano()+" pontos de dano) [custo: " + this.getCusto() + "]";
    }
}
