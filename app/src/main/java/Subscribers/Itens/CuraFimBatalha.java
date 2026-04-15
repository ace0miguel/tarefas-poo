package Subscribers.Itens;

import Entidades.Heroi;
import Telas.Eventos.Batalha;
import Visual.Textos;

public class CuraFimBatalha extends Item {
    int cura;

    public CuraFimBatalha(String nome, String descricao, int cura, Heroi heroi) {
        super(nome, descricao, heroi);
        this.cura = cura;
    }

    public CuraFimBatalha(CuraFimBatalha copiado){
        super(copiado);
        this.cura = copiado.cura;
    }

    public int getCura() {
        return cura;
    }

    @Override
    public Item criaCopia() {
        return new CuraFimBatalha(this);
    }
 
    @Override
    public void onBattleEnd(Batalha batalha, Heroi heroi) {
        heroi.ganhaVida(cura);
        Textos.limpaTela();
    }

}
