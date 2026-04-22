package batalhaListeners.itens.ativos;

import batalhaListeners.itens.Item;
import telas.eventos.combate.Batalha;

/** fazem uma açao instantaneamente durante uma batalha, e ao contrario dos passivos nao sao unicos */
public class ItemAtivo extends Item{
    protected boolean selfCast; // se true, o item é usado no heroi

    public ItemAtivo(String nome, String descricao, int custo, boolean selfCast) {
        super(nome, descricao, custo);
        this.selfCast = selfCast;
    }

    public ItemAtivo(ItemAtivo copia) {
        super(copia);
        this.selfCast = copia.selfCast;
    }

    public boolean isSelfCast() {
        return selfCast;
    }

    public void setSelfCast(boolean selfCast) {
        this.selfCast = selfCast;
    }

    /** aplica o efeito do item.
     * @return -1 cancela o uso e nao remove da lista de inventario
     */
    public int usar(Batalha batalha) {
        // logica para usar o item ativo
        return 0;
    }

    @Override
    public ItemAtivo criaCopia() {
        return new ItemAtivo(this);
    }

    @Override
    public String toString() {
        return this.nome + ": " + this.descricao;
    }
}
