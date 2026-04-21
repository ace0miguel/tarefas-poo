package batalhaListeners.itens.passivos;

import entidades.Heroi;
import telas.eventos.combate.Batalha;

/** Item passivo que faz com que o herói mantenha seu escudo restante entre as rodadas. */
public class ManterEscudo extends ItemPassivo {

    public ManterEscudo(String nome, String desc, int custo ) {
        super(nome, desc, custo);
    }

    public ManterEscudo(ManterEscudo copiado) {
        super(copiado);
    }

    @Override
    public void onBattleStart(Batalha batalha, Heroi heroi) {
        heroi.setManterEscudo(true);
    }

    @Override
    public void onBattleEnd(Batalha batalha, Heroi heroi) {
        heroi.setManterEscudo(false);
    }

    @Override
    public ManterEscudo criaCopia() {
        return new ManterEscudo(this);
    }
}
