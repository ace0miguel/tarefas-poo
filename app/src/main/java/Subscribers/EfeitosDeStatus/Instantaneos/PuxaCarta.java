package Subscribers.EfeitosDeStatus.Instantaneos;

import Entidades.Heroi;
import Subscribers.EfeitosDeStatus.Efeito;
import Telas.Eventos.Batalha;
import Util.InputHandler;
import Visual.Cor;

/** puxa da pilha de compra valor cartas e adiciona a mao do heroi */
public class PuxaCarta extends Instantaneo {

    protected int valor;
    public PuxaCarta(String nome, String desc, int valor){
        super(nome, desc);
        this.valor = valor;
    }

    public PuxaCarta(PuxaCarta copiado){
        super(copiado);
        this.valor = copiado.valor;
    }

    @Override
    public void onCreate(Batalha batalha, Heroi heroi) {
        if (this.getAlvo() instanceof Heroi h) {
            h.getMaoAtual().addCartas(h.getPilhaCompra(), h.getPilhaDescarte(), this.valor);
        } else {
            Cor.printaAmarelo("erro -> tentou dar cartas pra algo nao heroi");
            InputHandler.esperar();
        }
    }

    @Override
    public Efeito criaCopia() {
        return new PuxaCarta(this);
    }
    
}
