package Cartas;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;
import Util.Cor;

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
            alvo.receberDano(this.dano + heroi.getDanoExtra());
            heroi.usarEnergia(this.getCusto());
            
            printaResenha();
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
        alvo.receberDano(this.dano + heroi.getDanoExtra());
        printaResenha();
    }
    
    
    public int getDano() {
        return dano;
    }

    public String descricao(){
        return (!this.getDescricao().equals("")) 
        ? ""+this.getNome()+ " - " +this.getDescricao() + Cor.cinza +  " - ("  +this.getDano()+ ") " + "DANO" +  Cor.txtAmareloClaro(" < custo: " + this.getCusto())
        : ""+this.getNome() + Cor.cinza +  " - ("  +this.getDano()+ ") " + "DANO" +  Cor.txtAmareloClaro(" < custo: " + this.getCusto());
    }
}
