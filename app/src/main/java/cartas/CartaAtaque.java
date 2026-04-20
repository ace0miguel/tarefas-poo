package cartas;
import entidades.Entidade;
import entidades.Heroi;
import entidades.Inimigo;
import telas.eventos.combate.Batalha;
import visual.Cor;

/* 
Cartas que causam dano direto e não aplicam efeitos secundários.
 */
public class CartaAtaque extends Carta
{
    private int dano;
    protected int acertos = 0;

    public CartaAtaque(String nome, String descricao, int custo, int dano){
        super(nome, descricao, custo);
        this.dano = dano;
    }

    public CartaAtaque(CartaAtaque copia) {
        super(copia);
        this.dano = copia.dano;
    }

    @Override
    public void usar(Heroi heroi, Entidade alvo, Batalha batalha){
        int energiaAtual = heroi.getEnergia();
        if(energiaAtual >= this.getCusto()){
            acertos = 0;

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

        if (!efeitoEmArea) {
            batalha.causarDano(alvo, this.dano + ((this.dano * heroi.getDanoExtra()) / 100), heroi);             
        }
        else {
            for (Inimigo inimigo : batalha.getInimigos() ) {
                batalha.causarDano(inimigo, this.dano + ((this.dano * heroi.getDanoExtra()) / 100), heroi);
            }                   
        }

        acertos++;
    }
    
    
    public int getDano() {
        return dano;
    }

    public int getAcertos() {
        return acertos;
    }

    @Override
    public String descricao(){
        StringBuilder retorno = iniciarDescricao();

        appendTagsDescricao(retorno);

        retorno.append(" - ").append(Cor.vermelho).append("(").append(this.getDano()).append(") ").append("DANO");
        appendCustoDescricao(retorno);
        appendSacrificio(retorno);

        return finalizarDescricao(retorno);
    }

    @Override
    public Carta criaCopia() {
        return new CartaAtaque(this);
    }
}
