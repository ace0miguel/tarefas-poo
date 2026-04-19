package batalhaListeners.itens.ativos;

import telas.eventos.combate.Batalha;

public class PocaoEnergia extends ItemAtivo {
    private int valor;

    public PocaoEnergia(String nome, String descricao, int custo, int valor) {
        super(nome, descricao, custo, true);
        this.valor = valor;
    }

    public PocaoEnergia(PocaoEnergia copiado) {
        super(copiado);
        this.valor = copiado.valor;
    }

    @Override
    public PocaoEnergia criaCopia() {
        return new PocaoEnergia(this);
    }

    @Override
    public int usar(Batalha batalha) {
        batalha.getHeroi().ganhaEnergia(valor);

        return 0;
    }
}
