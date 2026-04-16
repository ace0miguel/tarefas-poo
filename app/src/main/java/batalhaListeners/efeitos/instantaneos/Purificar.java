package batalhaListeners.efeitos.instantaneos;
import batalhaListeners.efeitos.Efeito;
import entidades.Heroi;
import telas.eventos.combate.Batalha;

/** remove todos os efeitos (incluindo positivos) */
public class Purificar extends Instantaneo {
    public Purificar(String nome, String desc){
        super(nome, desc);
    }

    public Purificar(Purificar copiado){
        super(copiado);
    }


    @Override
    public int getPrioridade() {
        return 9;
    }

    @Override
    public void onCreate(Batalha batalha, Heroi heroi) {
        this.getAlvo().setPurificar(true);
        this.getAlvo().setPurificado(true);
    }

    @Override
    public Efeito criaCopia() {
        return new Purificar(this);
    }

    @Override
    public void onRemove(Batalha batalha, Heroi heroi) {
        this.getAlvo().setPurificar(false);
    }
}