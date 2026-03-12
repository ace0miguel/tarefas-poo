package EfeitosDeStatus;

import Entidades.Entidade;

public abstract class Efeito {
    private String nome;
    private String desc;
    private int dur;

    public Efeito(String nome, String desc, int dur) {
        this.nome = nome;
        this.desc = desc;
        this.dur = dur;
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

    public void passaTurno() {
        if (this.dur > 0) 
            this.dur--;
        else if (this.dur < 0) this.dur = 0; // else pra evitar possiveis bugs
    }
}
