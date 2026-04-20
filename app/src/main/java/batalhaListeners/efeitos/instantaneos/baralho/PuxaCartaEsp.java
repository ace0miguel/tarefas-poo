package batalhaListeners.efeitos.instantaneos.baralho;

import batalhaListeners.efeitos.Efeito;
import cartas.Carta;
import entidades.Heroi;
import telas.eventos.combate.Batalha;
import util.InputHandler;
import visual.Cor;

/** adiciona a carta passada a mao do heroi 
 * @param carta a carta a ser adicionada a mao do heroi
 * @param valor quantidade de copias da carta a ser adicionada a mao do heroi
*/
public class PuxaCartaEsp extends PuxaCarta {
    private Carta carta;

    public PuxaCartaEsp(String nome, String desc, int valor, Carta carta){
        super(nome, desc, valor);
        this.carta = carta;
    }

    public PuxaCartaEsp(PuxaCartaEsp copiado){
        super(copiado);
        this.carta = copiado.carta;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    @Override
    public void onCreate(Batalha batalha, Heroi heroi) {
        for (int i = 0; i < this.valor; i++) {
            heroi.getMaoAtual().addCartaEsp(this.carta.criaCopia());
        }
    }

    @Override
    public Efeito criaCopia() {
        return new PuxaCartaEsp(this);
    }
    
}
