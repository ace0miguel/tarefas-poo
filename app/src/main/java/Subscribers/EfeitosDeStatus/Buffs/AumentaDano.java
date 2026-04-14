package Subscribers.EfeitosDeStatus.Buffs;
import Entidades.Heroi;
import Subscribers.BatalhaSubscriber;
import Subscribers.EfeitosDeStatus.Efeito;
import Telas.Eventos.Batalha;

/** altera o dano causado por ataques do alvo pelo valor passado (positivo aumenta, negativo reduz) */
public class AumentaDano extends Buff {


    public AumentaDano(String nome, String desc, int dur, int valor){
        super(nome, desc, dur, valor);
    }

    public AumentaDano(AumentaDano copiado){
        super(copiado);
    }

    @Override
    public boolean addStack(Batalha batalha, BatalhaSubscriber novo){
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