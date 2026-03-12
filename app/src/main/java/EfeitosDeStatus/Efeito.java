package EfeitosDeStatus;

import Entidades.Entidade;

public abstract class Efeito {
    private String nome;
    private String desc;
    private int dur;
    private boolean selfApply;

    public Efeito(String nome, String desc, int dur, boolean selfApply) {
        this.nome = nome;
        this.desc = desc;
        this.dur = dur;
        this.selfApply = selfApply;
    }

    // importante: lembrar de chamar passa turno sempre no final do aplicar
    public abstract void aplicar(Entidade alvo);
    
    public String getNome() {
        return this.nome;
    }
    
    public String getDesc() {
        return this.desc;
    }

    public int getDur() {
        return this.dur;
    }

    public boolean getSelfApply() {
        return this.selfApply;
    }

    public void passaTurno() {
        if (this.dur > 0) 
            this.dur--;
        else if (this.dur < 0) this.dur = 0; // else pra evitar possiveis bugs
    }
}
