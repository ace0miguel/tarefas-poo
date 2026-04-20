package batalhaListeners.itens.passivos;

import batalhaListeners.efeitos.Efeito;
import cartas.Carta;
import cartas.CartaAtaque;
import entidades.Entidade;
import entidades.Heroi;
import telas.eventos.combate.Batalha;

/** adiciona um efeito ao utilizar cartas ataque de um custoEnergia especifico
 * @param efeito o efeito a ser aplicadodo
 * @param custoEnergia o custo em energia das cartas que vao ativar o efeito
 */
public class EfeitoPorCusto extends ItemPassivo {
    private Efeito efeito;
    private int custoEnergia;

    public EfeitoPorCusto(String nome, String descricao, Efeito efeito, int custoEnergia, int custo) {
        super(nome, descricao, custo);
        this.efeito = efeito;
        this.custoEnergia = custoEnergia;
    }

    public EfeitoPorCusto(EfeitoPorCusto copia) {
        super(copia);
        this.efeito = copia.efeito;
        this.custoEnergia = copia.custoEnergia;
    }

    @Override
    public EfeitoPorCusto criaCopia() {
        return new EfeitoPorCusto(this);
    }

    public Efeito getEfeito() {
        return efeito;
    }

    public int getCustoEnergia() {
        return custoEnergia;
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
        if (carta.getCusto() == this.custoEnergia && carta instanceof cartas.CartaAtaque) {
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
