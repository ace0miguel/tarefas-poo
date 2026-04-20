package batalhaListeners.efeitos.instantaneos.baralho;

import batalhaListeners.efeitos.Efeito;
import batalhaListeners.efeitos.instantaneos.Instantaneo;
import entidades.Heroi;
import telas.eventos.combate.Batalha;
import util.InputHandler;
import visual.Cor;

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
        heroi.getMaoAtual().addCartas(heroi.getPilhaCompra(), heroi.getPilhaDescarte(), this.valor);
    }

    @Override
    public Efeito criaCopia() {
        return new PuxaCarta(this);
    }
    
}
