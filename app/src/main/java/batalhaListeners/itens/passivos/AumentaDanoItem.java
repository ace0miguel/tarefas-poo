package batalhaListeners.itens.passivos;

import entidades.Heroi;
import telas.eventos.combate.Batalha;

public class AumentaDanoItem extends ItemPassivo {
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
    public ItemPassivo criaCopia() {
        return new AumentaDanoItem(this);
    }

}
