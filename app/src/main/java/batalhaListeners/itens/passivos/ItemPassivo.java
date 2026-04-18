package batalhaListeners.itens.passivos;
import batalhaListeners.batalhaListener;
import batalhaListeners.itens.Item;
import entidades.Entidade;
import entidades.Heroi;

/** itens passivos sao unicos (so tem uma copia por run), e normalmente aplicam um bonus permanente. */
public class ItemPassivo extends Item {
    protected Heroi alvo;

    public ItemPassivo(String nome, String descricao) {
        super(nome, descricao);
    }

    public ItemPassivo(ItemPassivo copia) {
        super(copia);
        this.alvo = copia.alvo;
    }

    @Override
    public ItemPassivo criaCopia() {
        return new ItemPassivo(this);
    }

    public void setAlvo(Heroi alvo) {
        this.alvo = alvo;
    }

    @Override
    public Entidade getAlvo() {
        return this.alvo;
    }

}
