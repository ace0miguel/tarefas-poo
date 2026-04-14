package Subscribers.EfeitosDeStatus.Instantaneos;

import Cartas.Carta;
import Entidades.Heroi;
import Subscribers.EfeitosDeStatus.Efeito;
import Telas.Eventos.Batalha;
import Util.InputHandler;
import Visual.Cor;

/** adiciona a carta passada a mao do heroi */
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
       if (this.getAlvo() instanceof Heroi h) {
            h.getMaoAtual().addCartaEsp(this.carta.criaCopia());
        } else {
            Cor.printaAmarelo("erro -> tentou dar cartas pra algo nao heroi");
            InputHandler.esperar();
        }
    }

    @Override
    public Efeito criaCopia() {
        return new PuxaCartaEsp(this);
    }
    
}
