package batalhaListeners.itens;

import batalhaListeners.batalhaListener;
import batalhaListeners.itens.passivos.ItemPassivo;

public class Item implements batalhaListener {
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

    public String getNomeColorido() {
        return (this instanceof ItemPassivo) ? visual.Cor.txtRosa(nome) : visual.Cor.txtAzulClaro(nome);
    }

    public String getDescricao() {
        return descricao;
    }

    public Item criaCopia() {
        return new Item(this);
    }
}
