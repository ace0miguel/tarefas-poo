package Poderes;

import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Batalha;
import Util.Cor;

/* aplica um efeito no heroi, duraçao infinita, nao volta pra pilha descarte ao ser usado. Normalmente stacka */
public abstract class Poder {

    private String nome;
    private String desc;
    private int stacks = 1;

    public Poder(String nome, String desc) {
        this.nome = nome;
        this.desc = desc;
    }

    public Poder(Poder copiado){
        this.nome = copiado.getNome();
        this.desc = copiado.getDesc();
    }

    public abstract void aplicar();

    public abstract void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha);

    public abstract Poder criaCopia();

    public abstract String status();
    
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
}
