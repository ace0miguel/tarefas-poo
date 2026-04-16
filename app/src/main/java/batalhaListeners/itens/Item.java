package batalhaListeners.itens;
import batalhaListeners.batalhaListener;
import entidades.Entidade;
import entidades.Heroi;

public class Item implements batalhaListener {
    protected String nome;
    protected String descricao;
    protected Heroi alvo;

    public Item(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Item(Item copia) {
        this.nome = copia.nome;
        this.descricao = copia.descricao;
        this.alvo = copia.alvo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Item criaCopia() {
        return new Item(this);
    }

    public void setAlvo(Heroi alvo) {
        this.alvo = alvo;
    }

    @Override
    public Entidade getAlvo() {
        return this.alvo;
    }

}
