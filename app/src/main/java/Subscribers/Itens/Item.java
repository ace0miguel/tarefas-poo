package Subscribers.Itens;
import Entidades.Entidade;
import Entidades.Heroi;
import Subscribers.BatalhaSubscriber;

public class Item implements BatalhaSubscriber {
    protected String nome;
    protected String descricao;
    protected Heroi heroi;

    public Item(String nome, String descricao, Heroi heroi) {
        this.nome = nome;
        this.descricao = descricao;
        this.heroi = heroi;
    }

    public Item(Item copia) {
        this.nome = copia.nome;
        this.descricao = copia.descricao;
        this.heroi = copia.heroi;
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

    @Override
    public Entidade getAlvo() {
        return this.heroi;
    }
}
