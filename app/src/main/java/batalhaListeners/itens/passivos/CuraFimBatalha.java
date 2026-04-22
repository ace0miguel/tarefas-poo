package batalhaListeners.itens.passivos;

import entidades.Heroi;
import telas.eventos.combate.Batalha;
import visual.Textos;

/** cura o heroi no final da batalha
 * @param cura a quantidade de vida que o heroi vai recuperar no final da batalha
 */
public class CuraFimBatalha extends ItemPassivo {
    int cura;

    public CuraFimBatalha(String nome, String descricao, int cura, int custo) {
        super(nome, descricao, custo);
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
    public CuraFimBatalha criaCopia() {
        return new CuraFimBatalha(this);
    }
 
    @Override
    public void onBattleEnd(Batalha batalha, Heroi heroi) {
        heroi.addVida(cura);
        Textos.limpaTela();
    }

}
