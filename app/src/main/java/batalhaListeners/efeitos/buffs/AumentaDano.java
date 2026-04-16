package batalhaListeners.efeitos.buffs;
import batalhaListeners.batalhaListener;
import batalhaListeners.efeitos.Efeito;
import entidades.Heroi;
import telas.eventos.combate.Batalha;

/** altera o dano causado por ataques do alvo pelo valor passado (positivo aumenta, negativo reduz) */
public class AumentaDano extends Buff {


    public AumentaDano(String nome, String desc, int dur, int valor){
        super(nome, desc, dur, valor);
    }

    public AumentaDano(AumentaDano copiado){
        super(copiado);
    }

    @Override
    public boolean addStack(Batalha batalha, batalhaListener novo){
        if (!acumulaEfeito(novo))
            return false;

        this.stacks++;
        this.getAlvo().somaDanoExtra(this.valor);

        return true;
    }

    @Override
    public void onCreate(Batalha batalha, Heroi heroi) {
        this.getAlvo().somaDanoExtra(valor);
    }

    @Override
    public Efeito criaCopia() {
        return new AumentaDano(this);
    }
    
    @Override
    public void onRemove(Batalha batalha, Heroi heroi) {
        this.getAlvo().somaDanoExtra(valor * this.stacks * -1);
    }
}