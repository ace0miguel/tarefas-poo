package Cartas;
import EfeitosDeStatus.Efeito;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;
import Util.Cor;

/*
Cartas que aplicam efeitos; não causam dano direto.
*/
public class CartaHabilidade extends Carta // aplica um efeito em um alvo
{
    private Efeito efeito;

    public CartaHabilidade(String nome, String descricao, int custo, Efeito efeito, boolean _selfCast){
        super(nome, descricao, custo);
        this.efeito = efeito;
        this.setSelfCast(_selfCast);
    }

    public CartaHabilidade(String nome, String descricao, int custo, Efeito efeito, boolean _selfCast, int tipo){
        super(nome, descricao, custo);
        this.efeito = efeito;
        this.setSelfCast(_selfCast);
        this.tipo = tipo; 
    }

    public CartaHabilidade(CartaHabilidade copia) {
        super(copia);
        this.efeito = copia.efeito;
    }

    @Override
    public void usar (Heroi heroi, Entidade alvo, Batalha batalha){
        int energiaAtual = heroi.getEnergia();
        if(energiaAtual >= this.getCusto()){
            heroi.receberDanoDireto(this.sacrificio);
            efeito.adicionar(alvo, batalha);
            heroi.usarEnergia(this.getCusto());

            printaResenha();
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
        efeito.adicionar(alvo, batalha);
        printaResenha();
    }
    
    @Override
    public String descricao(){
        return ""+this.getNome()+" - "+this.getDescricao()+ " - [ " +this.efeito.getNomeColorido()+" ]" + Cor.txtAmareloClaro(" < custo: " + this.getCusto());
    }

    @Override
    public Carta criaCopia() {
        return new CartaHabilidade(this);
    }
}
