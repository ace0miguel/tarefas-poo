package Subscribers.Itens;

import Entidades.Heroi;

public class CartasInicioBatalha extends Item {
    int quantidade;

    public CartasInicioBatalha(String nome, String descricao, int quantidade, Heroi heroi) {
        super(nome, descricao, heroi);
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
     public void onBattleStart(Telas.Eventos.Batalha batalha, Entidades.Heroi heroi) {
        heroi.setCartasBonus(heroi.getCartasBonus() + (this.quantidade));
    }
}
