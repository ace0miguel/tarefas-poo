package EfeitosDeStatus;

import Cartas.Carta;
import EfeitosDeStatus.DanosConstantes.DanoConstante;
import EfeitosDeStatus.DanosConstantes.Sangramento;
import EfeitosDeStatus.DanosConstantes.Veneno;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Batalha;
import Util.Cor;

public abstract class Efeito {
    private String nome;
    private String desc;
    private int dur;
    private Entidade alvo;

    protected boolean insta = false;
    protected boolean onHit = true;

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

    public void aplicar(){ // era abstract nao e mais pq eu tentei fazer um negocio mas resolvi deixa assim msm se precisar pode bota abstract dnv q nao vai da BO nenhum
    }

    public abstract void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha);

    public abstract Efeito criaCopia();

    public abstract String status();
    
    // getters ------------
    public String getNome() {
        return this.nome;
    }

    public String getUpperNome() { 
        return this.nome.toUpperCase();
    }
    
    public String getNomeColorido() { // define aqui a cor pra cada efeito ai se quiser
        if (this.getClass() == DanoConstante.class) return Cor.txtAmarelo(this.nome);

        else if (this instanceof Veneno) return Cor.verde + this.nome + Cor.reset;

        else if (this instanceof Sangramento) return Cor.vermelho + this.nome + Cor.reset;

        else if (this instanceof AumentaDano) return Cor.txtRosa(this.nome);

        else if (this instanceof Purificar || this instanceof Escudo) return Cor.azul + this.nome + Cor.reset;
        
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

    public boolean getInsta() {
        return this.insta;
    }
    
    public boolean getOnHit() {
        return this.onHit;
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

    public void setInsta(boolean insta) {
        this.insta = insta;
    }

    public void updateOnHit() { // pra efeitos que causam algo on hit uma vez so manter, se nao dar override na classe especifica
        this.onHit = false;
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
