package Subscribers.EfeitosDeStatus.Instantaneos;

import Subscribers.EfeitosDeStatus.Efeito;

public class Instantaneo extends Efeito {
    public Instantaneo(String nome, String desc) {
        super(nome, desc, 0);
    }

    public Instantaneo(Instantaneo copiado){
        super(copiado);
        this.setDur(0);
    }
}
