package Subscribers.Itens;

import Entidades.Heroi;
import Telas.Eventos.Batalha;

public class AumentaResistItem extends Item {
    private int valor;

    public AumentaResistItem(String nome, String desc, int valor) {
        super(nome, desc);
        this.valor = valor;
    }

    public AumentaResistItem(AumentaResistItem copiado) {
        super(copiado);
        this.valor = copiado.valor;
    }

    @Override
    public void onBattleStart(Batalha batalha, Heroi heroi) {
        heroi.somaResistencia(valor);
    }

    @Override
    public void onBattleEnd(Batalha batalha, Heroi heroi) {
        heroi.somaResistencia(-valor);
    }

    @Override
    public Item criaCopia() {
        return new AumentaResistItem(this);
    }

}
