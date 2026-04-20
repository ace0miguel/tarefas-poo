package batalhaListeners.itens;

import batalhaListeners.batalhaListener;
import batalhaListeners.itens.ativos.ItemAtivo;
import batalhaListeners.itens.passivos.ItemPassivo;
import visual.Cor;

public class Item implements batalhaListener {
    protected String nome;
    protected String descricao;
    protected int custo = 0; // custo para comprar o item na loja, era melhor chamar preço pra ficar organizado mas agr ja tem coisa dms pra muda

    public Item(String nome, String descricao, int custo) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
    }

    public Item(Item copia) {
        this.nome = copia.nome;
        this.descricao = copia.descricao;
        this.custo = copia.custo;
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

    public void setCusto(int custo) {
        this.custo = custo;
    }
    
    public int getCusto() {
        return custo;
    }

    public String descricaoLoja() {
        return "[ " + this.getNomeColorido() + " (Custo: " + this.custo + "): [ " + (this instanceof ItemAtivo ?  Cor.txtAzulClaro("Ativo") : Cor.txtRosa("Passivo")) + " ] - " + this.descricao + " ]";
    }

}
