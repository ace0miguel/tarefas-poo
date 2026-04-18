package batalhaListeners.poderes;

import entidades.Heroi;
import telas.eventos.combate.Batalha;
import visual.Cor;

/** no começo da rodada puxa quantidade cartas extras e perde sacrificio pontos de vida. */
public class CartaAdicional extends Poder {
    int quantidade;

    public CartaAdicional(String nome, String desc, int quantidade) {
        super(nome, desc);
        this.quantidade = quantidade;
    }

    public CartaAdicional(CartaAdicional copiado){
        super(copiado);
        this.quantidade = copiado.quantidade;
    }

    @Override
    public void onRoundStart(Batalha batalha, Heroi heroi){
        heroi.addCartasBonus(this.quantidade*this.getStacks());
        heroi.receberDanoDireto(this.sacrificio*this.getStacks());
    }

    @Override
    public Poder criaCopia() {
        return new CartaAdicional(this);
    }

    @Override
    public String status() {
        return "No início de cada turno, retire " + Cor.txtAmarelo(String.valueOf(this.quantidade)) + (this.quantidade != 1 ? " cartas adicionais." : " carta adicional.");
    }
}
