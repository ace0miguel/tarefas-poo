package batalhaListeners.efeitos.instantaneos;

import batalhaListeners.efeitos.Efeito;
import cartas.Carta;
import entidades.Heroi;
import telas.eventos.combate.Batalha;
import util.InputHandler;
import visual.Cor;

/** adiciona a carta passada na pilha de compras do heroi */
public class AdicionaCarta extends Instantaneo {
    protected Carta carta;

    public AdicionaCarta(String nome, String desc, Carta carta){
        super(nome, desc);
        this.carta = carta;
    }

    public AdicionaCarta(AdicionaCarta copiado){
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
        if (this.getAlvo() instanceof Heroi h) {
            h.getPilhaCompra().addCartaPilha(this.carta.criaCopia());
        } else {
            Cor.printaAmarelo("erro -> tentou dar cartas pra algo nao heroi");
            InputHandler.esperar();
        }
    }

    @Override
    public Efeito criaCopia() {
        return new AdicionaCarta(this);
    }
}
