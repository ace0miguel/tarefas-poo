package Cartas;
import Entidades.Entidade;
import Entidades.Heroi;
import Entidades.Inimigo;
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

    public CartaAtaque(CartaAtaque copia) {
        super(copia.getNome(), copia.getDescricao(), copia.getCusto());
        this.dano = copia.dano;
        this.tipo = copia.tipo; 
        this.setResenha(copia.getResenha());
    }

    @Override
    public void usar(Heroi heroi, Entidade alvo, Batalha batalha){
        if (!efeitoEmArea) {
            int energiaAtual = heroi.getEnergia();
            if(energiaAtual >= this.getCusto()){
                alvo.receberDano(this.dano + heroi.getDanoExtra());
                heroi.usarEnergia(this.getCusto());
                
                printaResenha();
            }
        }
        else {
            int energiaAtual = heroi.getEnergia();
            if(energiaAtual >= this.getCusto()){
                for (Inimigo inimigo : batalha.getInimigos() ) {
                    inimigo.receberDano(this.dano + heroi.getDanoExtra());
                }
                heroi.usarEnergia(this.getCusto());
                
                printaResenha();
            }
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
        if (!efeitoEmArea) {
            alvo.receberDano(this.dano + heroi.getDanoExtra());    
            printaResenha();     
        }
        else {
            for (Inimigo inimigo : batalha.getInimigos() ) {
                inimigo.receberDano(this.dano + heroi.getDanoExtra());
            }           
            printaResenha();         
        }
    }
    
    
    public int getDano() {
        return dano;
    }

    @Override
    public String descricao(){
        return (!this.getDescricao().equals("")) 
        ? ""+this.getNome()+ " - " +this.getDescricao() + Cor.cinza +  " - ("  +this.getDano()+ ") " + "DANO" +  Cor.txtAmareloClaro(" < custo: " + this.getCusto())
        : ""+this.getNome() + Cor.cinza +  " - ("  +this.getDano()+ ") " + "DANO" +  Cor.txtAmareloClaro(" < custo: " + this.getCusto());
    }

    @Override
    public Carta criaCopia() {
        return new CartaAtaque(this);
    }
}
