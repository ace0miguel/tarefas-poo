package batalhaListeners.efeitos.buffs;
import batalhaListeners.batalhaListener;
import batalhaListeners.efeitos.Efeito;
import entidades.Heroi;
import telas.eventos.combate.Batalha;

/** altera a resistencia ( se quiser diminuir so passar um valor negativo ) */
public class AumentaResistencia extends Buff {

    public AumentaResistencia(String nome, String desc, int dur, int valor){
        super(nome, desc, dur, valor);
    }

    public AumentaResistencia(AumentaResistencia copiado){
        super(copiado);
    }

    @Override
    public boolean addStack(Batalha batalha, batalhaListener novo){
        if (!acumulaEfeito(novo))
            return false;

        this.stacks++;
        this.getAlvo().somaResistencia(this.valor);

        return true;
    }

    @Override
    public void onCreate(Batalha batalha, Heroi heroi) {
        this.getAlvo().somaResistencia(valor);
    }

    @Override
    public Efeito criaCopia() {
        return new AumentaResistencia(this);
    }

    @Override
    public void onRemove(Batalha batalha, Heroi heroi) {
        this.getAlvo().somaResistencia(this.valor * this.stacks * -1);
    }
}