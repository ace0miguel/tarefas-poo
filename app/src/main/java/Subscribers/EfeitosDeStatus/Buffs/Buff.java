package Subscribers.EfeitosDeStatus.Buffs;

import Subscribers.EfeitosDeStatus.Efeito;

public abstract class Buff extends Efeito{
    protected int valor;

    public Buff(String nome, String desc, int dur, int valor){
        super(nome, desc, dur);
        this.valor = valor;
    }

    public Buff(Buff copiado){
        super(copiado);
        this.valor = copiado.valor;
    }

}
