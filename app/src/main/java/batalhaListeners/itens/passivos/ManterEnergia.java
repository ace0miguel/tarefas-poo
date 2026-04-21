package batalhaListeners.itens.passivos;

import entidades.Heroi;
import telas.eventos.combate.Batalha;

/** Item passivo que faz com que o herói mantenha sua energia restante entre as rodadas. */
public class ManterEnergia extends ItemPassivo {

    public ManterEnergia(String nome, String desc, int custo ) {
        super(nome, desc, custo);
    }

    public ManterEnergia(ManterEnergia copiado) {
        super(copiado);
    }

    @Override
    public void onBattleStart(Batalha batalha, Heroi heroi) {
        heroi.setManterEnergia(true);
    }

    @Override
    public void onBattleEnd(Batalha batalha, Heroi heroi) {
        heroi.setManterEnergia(false);
    }

    @Override
    public ManterEnergia criaCopia() {
        return new ManterEnergia(this);
    }
}
