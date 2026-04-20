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
 * @param tipoComparacao operador (se o efeito vai ser com cartas cujo custo for igual, maior ou menor que o custoEnergia)
 */
public class EfeitoPorCusto extends ItemPassivo {
    private Efeito efeito;
    private int custoEnergia;
    
    public enum TipoComparacao {
        IGUAL,
        MAIOR,
        MENOR
    }

    private TipoComparacao tipoComparacao;

    public EfeitoPorCusto(String nome, String descricao, Efeito efeito, int custoEnergia, TipoComparacao tipoComparacao, int custo) {
        super(nome, descricao, custo);
        this.efeito = efeito;
        this.custoEnergia = custoEnergia;
        this.tipoComparacao = tipoComparacao;
    }

    public EfeitoPorCusto(EfeitoPorCusto copia) {
        super(copia);
        this.efeito = copia.efeito;
        this.custoEnergia = copia.custoEnergia;
        this.tipoComparacao = copia.tipoComparacao;
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
        if ((!(carta instanceof CartaAtaque))) {
            return;
        }

        switch (tipoComparacao) {
            case IGUAL:
                if (carta.getCusto() != this.custoEnergia) {
                    return;
                }
                break;
            case MAIOR:
                if (carta.getCusto() <= this.custoEnergia) {
                    return;
                }
                break;
            case MENOR:
                if (carta.getCusto() >= this.custoEnergia) {
                    return;
                }
                break;
        }

        for (int i = 0; i < ((CartaAtaque) carta).getAcertos(); i++) {
            efeito.setAdicionar(alvo, batalha);
        }
    }

    /** roda por ultimo no onHit para sinerginzar com acertos extras */
    @Override
    public int getPrioridade() {
        return PRIORIDADE_BAIXISSIMA;
    }
}
