package Cartas;
import Entidades.Entidade;
import Entidades.Heroi;
import Subscribers.EfeitosDeStatus.Efeito;
import Telas.Eventos.Batalha;
import Visual.Cor;

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
        if(energiaAtual >= this.getCusto()) {
            heroi.receberDanoDireto(this.sacrificio);
            
            aplicarEfeito(heroi, alvo, batalha);

            heroi.usarEnergia(this.getCusto());
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {

        Efeito efeitoAplicado = efeito.adicionar(alvo, batalha);

            if (efeitoAplicado.getCancelarJogada()) {
                this.setUsoCancelado(true);
                return;
            }

        printaResenha();
    }

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

        retorno += Cor.cinza + " (" + this.efeito.getNomeColorido() + ")"  +  Cor.txtAmareloClaro(" < custo: " + this.getCusto());
        return retorno;
    }

    @Override
    public Carta criaCopia() {
        return new CartaHabilidade(this);
    }
}
