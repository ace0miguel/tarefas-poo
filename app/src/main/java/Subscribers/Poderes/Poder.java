package Subscribers.Poderes;

import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
import Subscribers.BatalhaSubscriber;
import Telas.Eventos.Batalha;
import Visual.Cor;

/* aplica um efeito no heroi, duraçao infinita, nao volta pra pilha descarte ao ser usado. Normalmente stacka */
public abstract class Poder implements BatalhaSubscriber{

    private String nome;
    private String desc;
    private int stacks = 1;
    // poderes que custam vida pra serem usados
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

    public Poder criaCopia(){ return null; };
    
    public String status(){ return null; };
    
    // getters ------------
    public String getNome() {
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

    public void stackar(){
        this.stacks++;
    }

    public void setSacrificio(int sacrificio) {
        this.sacrificio = sacrificio;
    }
}
