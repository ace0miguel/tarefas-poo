package Subscribers.Itens;

import Entidades.Heroi;
import Telas.Eventos.Batalha;

public class AumentaDanoItem extends Item {
    private int valor;

    public AumentaDanoItem(String nome, String desc, int valor) {
        super(nome, desc);
        this.valor = valor;
    }

    public AumentaDanoItem(AumentaDanoItem copiado) {
        super(copiado);
        this.valor = copiado.valor;
    }
    
    @Override
    public void onBattleStart(Batalha batalha, Heroi heroi) {
        heroi.somaDanoExtra(valor);
    }
    
    @Override
    public void onBattleEnd(Batalha batalha, Heroi heroi) {
        heroi.somaDanoExtra(-valor);
    }

    @Override
    public Item criaCopia() {
        return new AumentaDanoItem(this);
    }

}
