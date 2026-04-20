package cartas;
import batalhaListeners.efeitos.Efeito;
import entidades.Entidade;
import entidades.Heroi;
import telas.eventos.combate.Batalha;
import visual.Textos;

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
    public void usar (Batalha batalha){
         Textos.limpaTela();

        Heroi heroi = batalha.getHeroi();
        int energiaAtual = heroi.getEnergia();      
        if(energiaAtual >= this.getCusto()) {
            Entidade alvo = resolverAlvo(batalha);
            if (alvo == null) {
                return;
            }

            heroi.receberDanoDireto(this.sacrificio);

            if (!this.temResenha())
                Textos.limpaTela();
            
            aplicarEfeito(heroi, alvo, batalha);

            if (!this.getUsoCancelado()) {
                heroi.usarEnergia(this.getCusto());
            }
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {       
        if (aplicarEfeitos(heroi, alvo, batalha, efeitos)) {
            this.setUsoCancelado(true);
        }
        printaResenha();
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
