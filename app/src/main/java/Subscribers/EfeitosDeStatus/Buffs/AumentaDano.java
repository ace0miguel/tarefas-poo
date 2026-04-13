package Subscribers.EfeitosDeStatus.Buffs;
import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
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
    public boolean getResetDur() {
        return true;
    }

    @Override
    public void addStack(){
        this.stacks++;
        this.getAlvo().somaDanoExtra(this.valor);
    }

    @Override
    public void onRoundStart(Batalha batalha, Heroi heroi){
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
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
    public String status() {
        return this.getNome() + " [" + this.valor * this.stacks + "] > " + this.getDur(); 
    }
    
    @Override
    public void onRemove() {
        this.getAlvo().somaDanoExtra(valor * this.stacks * -1);
    }
}