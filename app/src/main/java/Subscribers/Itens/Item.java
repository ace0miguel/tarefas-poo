package Subscribers.Itens;
import Subscribers.BatalhaSubscriber;

public class Item implements BatalhaSubscriber {
    protected String nome;
    protected String descricao;

    public Item(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Item(Item copia) {
        this.nome = copia.nome;
        this.descricao = copia.descricao;
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
}
