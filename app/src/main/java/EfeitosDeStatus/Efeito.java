package EfeitosDeStatus;

import Cartas.Carta;
import Entidades.Entidade;

public abstract class Efeito {
    private String nome;
    private String desc;
    private int dur;
    private Entidade alvo;

    public Efeito(String nome, String desc, int dur) {
        this.nome = nome;
        this.desc = desc;
        this.dur = dur;
    }

    public Efeito(Efeito copiado){
        this.nome = copiado.getNome();
        this.desc = copiado.getDesc();
        this.dur = copiado.getDur();
    }

    public abstract void aplicar();

    public abstract void onHit(Carta carta);

    public abstract Efeito criaCopia();
    
    // getters ------------
    public String getNome() {
        return this.nome;
    }
    
    public String getDesc() {
        return this.desc;
    }

    public int getDur() {
        return this.dur;
    }

    public Entidade getAlvo() {
        return this.alvo;
    }

    // setters ------------

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public void setDur(int dur) {
        this.dur = dur;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setAlvo(Entidade alvo) {
        this.alvo = alvo;
    }
    
    // ----------------

    public boolean passaTurno() { // retorna true se ainda nao tiver acabado a duraçao
        if (this.dur > 0){ 
            this.dur--;
            return true;
        }
        return false;
    }
}
