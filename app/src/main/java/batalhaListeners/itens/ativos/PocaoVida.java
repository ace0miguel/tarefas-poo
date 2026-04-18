package batalhaListeners.itens.ativos;

import telas.eventos.combate.Batalha;

public class PocaoVida extends ItemAtivo {
    private int valor;

    public PocaoVida(String nome, String desc, int valor) {
        super(nome, desc, true);
        this.valor = valor;
    }

    public PocaoVida(PocaoVida copiado) {
        super(copiado);
        this.valor = copiado.valor;
    }

    @Override
    public void usar(Batalha batalha) {
        if (isSelfCast()) {
            batalha.getHeroi().addVida(valor);
        }       
    }
}
