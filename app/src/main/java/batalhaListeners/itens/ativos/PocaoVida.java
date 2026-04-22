package batalhaListeners.itens.ativos;

import telas.eventos.combate.Batalha;
/** ganha vida instananeamente */
public class PocaoVida extends ItemAtivo {
    private int valor;

    public PocaoVida(String nome, String descricao, int custo, int valor) {
        super(nome, descricao, custo, true);
        this.valor = valor;
    }

    public PocaoVida(PocaoVida copiado) {
        super(copiado);
        this.valor = copiado.valor;
    }

    @Override
    public PocaoVida criaCopia() {
        return new PocaoVida(this);
    }

    @Override
    public int usar(Batalha batalha) {
        batalha.getHeroi().addVida(valor); 
        return 0;    
    }
}
