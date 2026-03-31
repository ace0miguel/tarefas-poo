package Cartas;
import Entidades.*;
import EfeitosDeStatus.*;
import Telas.Batalha;

/*
Cartas que aplicam efeitos; não causam dano direto.
*/
public class CartaHabilidade extends Carta // aplica um efeito em um alvo
{
    private Efeito efeito;

    public CartaHabilidade(String nome, int custo, Efeito efeito, boolean _selfCast){
        super(nome, custo);
        this.efeito = efeito;
        this.setSelfCast(_selfCast);
    }

    public CartaHabilidade(String nome, int custo, int tipo, Efeito efeito, boolean _selfCast){
        super(nome, custo, tipo);
        this.efeito = efeito;
        this.setSelfCast(_selfCast);
    }

    @Override
    public void usar (Heroi heroi, Entidade alvo, Batalha batalha){
        int energiaAtual = heroi.getEnergia();

        if(energiaAtual >= this.getCusto()){
            Efeito e = efeito.criaCopia();
            e.setAlvo(alvo);
            batalha.adicionarEfeito(e);
        }
    }

    public String descricao(){
        return "Usar "+this.getNome()+" ("+this.efeito.getNome()+") [custo: " + this.getCusto() + "]";
    }
}
