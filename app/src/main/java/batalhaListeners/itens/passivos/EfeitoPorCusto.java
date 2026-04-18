package batalhaListeners.itens.passivos;

import batalhaListeners.efeitos.Efeito;
import cartas.Carta;
import cartas.CartaAtaque;
import entidades.Entidade;
import entidades.Heroi;
import telas.eventos.combate.Batalha;

/** adiciona um efeito ao utilizar cartas ataque de um custo especifico
 * @param efeito o efeito a ser aplicadodo
 * @param custo o custo das cartas que vao ativar o efeito
 */
public class EfeitoPorCusto extends ItemPassivo {
    private Efeito efeito;
    private int custo;

    public EfeitoPorCusto(String nome, String descricao, Efeito efeito, int custo) {
        super(nome, descricao);
        this.efeito = efeito;
        this.custo = custo;
    }

    public EfeitoPorCusto(EfeitoPorCusto copia) {
        super(copia);
        this.efeito = copia.efeito;
        this.custo = copia.custo;
    }

    @Override
    public ItemPassivo criaCopia() {
        return new EfeitoPorCusto(this);
    }

    public Efeito getEfeito() {
        return efeito;
    }

    public int getCusto() {
        return custo;
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
        if (carta.getCusto() == this.custo && carta instanceof cartas.CartaAtaque) {
            for (int i = 0; i < ((CartaAtaque) carta).getAcertos(); i++) {
                efeito.setAdicionar(alvo, batalha);
            }
        }
    }

    /** roda por ultimo no onHit para sinerginzar com acertos extras */
    @Override
    public int getPrioridade() {
        return PRIORIDADE_BAIXISSIMA;
    }
}
