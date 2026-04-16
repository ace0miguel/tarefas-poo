package batalhaListeners.itens;

public class CartasInicioBatalha extends Item {
    int quantidade;

    public CartasInicioBatalha(String nome, String descricao, int quantidade) {
        super(nome, descricao);
        this.quantidade = quantidade;
    }

    public CartasInicioBatalha(CartasInicioBatalha copiado){
        super(copiado);
        this.quantidade = copiado.quantidade;
    }

    @Override
    public Item criaCopia() {
        return new CartasInicioBatalha(this);
    }

     @Override
     public void onBattleStart(telas.eventos.combate.Batalha batalha, entidades.Heroi heroi) {
        heroi.setCartasBonus(heroi.getCartasBonus() + (this.quantidade));
    }
}
