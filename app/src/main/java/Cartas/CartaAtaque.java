package Cartas;
import Entidades.Entidade;
import Entidades.Heroi;
import Entidades.Inimigo;
import Telas.Eventos.Batalha;
import Visual.Cor;

/* 
Cartas que causam dano direto e não aplicam efeitos secundários.
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
        super(copia);
        this.dano = copia.dano;
    }

    @Override
    public void usar(Heroi heroi, Entidade alvo, Batalha batalha){
        int energiaAtual = heroi.getEnergia();
        if(energiaAtual >= this.getCusto()){
            heroi.receberDanoDireto(this.sacrificio);

            aplicarEfeito(heroi, alvo, batalha);

            heroi.usarEnergia(this.getCusto());
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
        if (!efeitoEmArea) {
            alvo.receberDano(this.dano + ((this.dano * heroi.getDanoExtra()) / 100));             
        }
        else {
            for (Inimigo inimigo : batalha.getInimigos() ) {
                inimigo.receberDano(this.dano + ((this.dano * heroi.getDanoExtra()) / 100));
            }                   
        }
        printaResenha(); 
    }
    
    
    public int getDano() {
        return dano;
    }

    @Override
    public String descricao(){
        String retorno = this.getNome();

        if (!tags.isEmpty()) {
            retorno += " - [" + String.join(", ",  tags) + "]";
        }

        if (!this.getDescricao().equals("")) {
            retorno += " - " + this.getDescricao();
        }

        if (this.getSacrificio() != 0){
            retorno += " - " + Cor.txtVermelho("[Sacrifício: " + this.getSacrificio() + "]");
        }

        retorno += Cor.cinza + " (" + this.getDano() + ") " + "DANO" +  Cor.txtAmareloClaro(" < custo: " + this.getCusto());
        return retorno;
    }

    @Override
    public Carta criaCopia() {
        return new CartaAtaque(this);
    }
}
