package Cartas;

import EfeitosDeStatus.Efeito;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;
import Util.Cor;

/* cartas que te prejudicam ou nao fazem nada (no momento so ta programada pra nao fazer nada) (normalmente inimigos vao colocar ela no seu deck ou vao ser consequencia de algo.) */
public class CartaMaldicao extends Carta 
{
    private Efeito efeito;

    public CartaMaldicao(String nome, String descricao, int custo, Efeito efeito, boolean _selfCast){
        super(nome, descricao, custo);
        this.efeito = efeito;
        this.setSelfCast(_selfCast);
    }

    public CartaMaldicao(String nome, String descricao, int custo, boolean _selfCast){
        super(nome, descricao, custo);
        this.setSelfCast(_selfCast);
    }

    @Override
    public void usar (Heroi heroi, Entidade alvo, Batalha batalha){
        int energiaAtual = heroi.getEnergia();
        if(energiaAtual >= this.getCusto()){
            heroi.usarEnergia(this.getCusto());
            printaResenha();
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
        printaResenha();
    }
    
    public String descricao(){
        return ""+this.getNome()+" - "+this.getDescricao()+ " - [ " +this.efeito.getNomeColorido()+" ]" + Cor.txtAmareloClaro(" < custo: " + this.getCusto());
    }
}
