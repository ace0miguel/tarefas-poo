package cartas;
import batalhaListeners.efeitos.Efeito;
import entidades.Entidade;
import entidades.Heroi;
import telas.eventos.combate.Batalha;

/*
Cartas que aplicam efeito(s); não causam dano direto.
*/
public class CartaHabilidade extends Carta // aplica um efeito em um alvo
{
    private Efeito efeitos[];

    public CartaHabilidade(String nome, String descricao, int custo, boolean _selfCast, Efeito... efeitos){
        super(nome, descricao, custo);
        this.efeitos = efeitos;
        this.setSelfCast(_selfCast);
    }

    public CartaHabilidade(CartaHabilidade copia) {
        super(copia);
        this.efeitos = copia.efeitos;
    }

    @Override
    public void usar (Heroi heroi, Entidade alvo, Batalha batalha){
        int energiaAtual = heroi.getEnergia();      
        if(energiaAtual >= this.getCusto()) {
            Entidade alvoReal = resolverAlvo(heroi, alvo, batalha);
            if (alvoReal == null) {
                return;
            }

            heroi.receberDanoDireto(this.sacrificio);
            
            aplicarEfeito(heroi, alvoReal, batalha);

            heroi.usarEnergia(this.getCusto());
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
        printaResenha();

        if (aplicarEfeitos(heroi, alvo, batalha, efeitos)) {
            this.setUsoCancelado(true);
        }
    }

    @Override
    public String descricao(){
        StringBuilder retorno = iniciarDescricao();

        appendTagsDescricao(retorno);
        appendDescricao(retorno);
        appendSacrificio(retorno);
        appendCustoDescricao(retorno);

        return finalizarDescricao(retorno);
    }

    @Override
    public Carta criaCopia() {
        return new CartaHabilidade(this);
    }
}
