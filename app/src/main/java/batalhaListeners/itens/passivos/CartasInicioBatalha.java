package batalhaListeners.itens.passivos;

public class CartasInicioBatalha extends ItemPassivo {
    int quantidade;

    public CartasInicioBatalha(String nome, String descricao, int quantidade, int custo) {
        super(nome, descricao, custo);
        this.quantidade = quantidade;
    }

    public CartasInicioBatalha(CartasInicioBatalha copiado){
        super(copiado);
        this.quantidade = copiado.quantidade;
    }

    @Override
    public CartasInicioBatalha criaCopia() {
        return new CartasInicioBatalha(this);
    }

     @Override
     public void onBattleStart(telas.eventos.combate.Batalha batalha, entidades.Heroi heroi) {
        heroi.addCartasBonus(this.quantidade);
    }
}
