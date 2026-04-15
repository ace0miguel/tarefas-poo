package Subscribers.EfeitosDeStatus.Instantaneos;

import Cartas.Carta;
import Entidades.Heroi;
import Subscribers.EfeitosDeStatus.Efeito;
import Telas.Eventos.Batalha;
import Util.InputHandler;
import Visual.Cor;

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
