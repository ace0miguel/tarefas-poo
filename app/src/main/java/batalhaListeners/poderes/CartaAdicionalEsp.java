package batalhaListeners.poderes;

import cartas.Carta;
import entidades.Heroi;
import telas.eventos.combate.Batalha;
import visual.Cor;

/** no começo da rodada puxa quantidade cartas extras de uma carta especifica
 * @param quantidade quantidade de cartas puxadas por stack
 * @param carta a carta que vai ser dada
 */
public class CartaAdicionalEsp extends Poder {
    int quantidade;
    Carta carta;

    public CartaAdicionalEsp(String nome, String desc, int quantidade, Carta carta) {
        super(nome, desc);
        this.quantidade = quantidade;
        this.carta = carta;
    }

    public CartaAdicionalEsp(CartaAdicionalEsp copiado){
        super(copiado);
        this.quantidade = copiado.quantidade;
        this.carta = copiado.carta;
    }

    @Override
    public void onRoundStart(Batalha batalha, Heroi heroi){
        for (int i = 0; i < this.quantidade*this.getStacks(); i++) {
            heroi.getMaoAtual().addCartaEsp(this.carta.criaCopia());
        }
    }

    @Override
    public Poder criaCopia() {
        return new CartaAdicionalEsp(this);
    }

    @Override
    public String status() {
        return "";
    }
}
