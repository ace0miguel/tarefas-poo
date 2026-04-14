package Subscribers.Poderes;

import Entidades.Entidade;
import Subscribers.BatalhaSubscriber;
import Telas.Eventos.Batalha;
import Visual.Cor;

/* aplica um efeito no heroi, duraçao infinita, nao volta pra pilha descarte ao ser usado. Normalmente stacka */
public abstract class Poder implements BatalhaSubscriber{

    private String nome;
    private String desc;
    private int stacks = 1;

    // vida ao ser subtraida ao usar o poder (cada subclasse usa de uma maneira)
    protected int sacrificio = 0;

    public Poder(String nome, String desc) {
        this.nome = nome;
        this.desc = desc;
    }

    public Poder(Poder copiado){
        this.nome = copiado.getNome();
        this.desc = copiado.getDesc();
        this.sacrificio = copiado.sacrificio;
    }

    public abstract Poder criaCopia();
    
    public abstract String status();
    
    // getters ------------
    public String getNome() {
        return this.nome;
    }

    public String getNomeColorido() {
        return Cor.txtRoxo(this.nome);
    }
    
    public String getDesc() {
        return this.desc;
    }

    public int getStacks() {
        return stacks;
    }

    public int getSacrificio() {
        return sacrificio;
    }

    // setters ------------

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setStacks(int stacks) {
        this.stacks = stacks;
    }

    /** adiciona um acumulo */
    public void stackar(){
        this.stacks++;
    }

    public void setSacrificio(int sacrificio) {
        this.sacrificio = sacrificio;
    }

    /** retorna true se o poder passado é igual a instancia do poder comparando */
    public boolean acumulaPoder(BatalhaSubscriber novo){
        if (!(novo instanceof Poder))
            return false;

        else if (novo instanceof Poder p)
            if (p.getClass() != this.getClass() || !p.getNome().equals(this.nome))
                return false;

        return true;
    }

    @Override
    public boolean addStack(Batalha batalha, BatalhaSubscriber novo) {
        if (!acumulaPoder(novo)){
            return false;
        }

        stackar();
        return true;
    }

    
}
