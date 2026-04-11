package EfeitosDeStatus;

import Cartas.Carta;
import EfeitosDeStatus.Buffs.AumentaDano;
import EfeitosDeStatus.DanosConstantes.DanoConstante;
import EfeitosDeStatus.DanosConstantes.Sangramento;
import EfeitosDeStatus.DanosConstantes.Veneno;
import EfeitosDeStatus.Instantaneos.Escudo;
import EfeitosDeStatus.Instantaneos.GanhaEnergia;
import EfeitosDeStatus.Instantaneos.Purificar;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;
import Util.Cor;

public abstract class Efeito {
    private String nome;
    private String desc;
    private int dur;
    private Entidade alvo;
    protected int stacks = 1;
    private boolean cancelarJogada = false;

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
        this.cancelarJogada = false;
    }
    
    // ------------ getters
    public String getNome() {
        return this.nome;
    }

    public String getUpperNome() { 
        return this.nome.toUpperCase();
    }
    
    /** retorna o nome do efeito colorido baseado na subclasse */
    public String getNomeColorido() { // define aqui a cor pra cada efeito ai se quiser, acho que seria mais orientado a objetos ficar dando override filhos mas to com pregs 
        if (this.getClass() == DanoConstante.class) return Cor.txtAmarelo(this.nome);

        else if (this instanceof Veneno) return Cor.verde + this.nome + Cor.reset;

        else if (this instanceof Sangramento) return Cor.vermelho + this.nome + Cor.reset;

        else if (this instanceof AumentaDano) return Cor.txtRosa(this.nome);

        else if (this instanceof Purificar || this instanceof Escudo) return Cor.azul + this.nome + Cor.reset;
        
        else if (this instanceof GanhaEnergia) return Cor.txtAmarelo(this.nome);
        
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

    public boolean getOnHit() {
        return this.onHit;
    }

    public int getStacks() {
        return stacks;
    }

    public boolean getCancelarJogada() {
        return cancelarJogada;
    }

    public int getPrioridade() { // se quiser q um efeito va antes os outros reduz, os danos constantes tao 0.
        return 1;
    }

    /** flag pra efeitos que resetam a duraçao ao inves de somar quando stackam */
    public boolean getResetDur() {
        return false;
    }

    // ------------ setters

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

    public void setOnHit(boolean onHit) {
        this.onHit = onHit;
    }

    public void setStacks(int stacks) {
        this.stacks = stacks;
    }

    protected void cancelarJogada() {
        this.cancelarJogada = true;
    }

    // ---------------------

    public boolean passaTurno() { // retorna true se ainda nao tiver acabado a duraçao
        if (this.dur > 0){ 
            this.dur--;
            return true;
        }
        return false;
    }

    public void addStack(){
        this.stacks++;
    }

    /** adiciona uma cópia do efeito à batalha */
    public Efeito adicionar(Entidade alvo, Batalha batalha){ 
        Efeito e = this.criaCopia();
        e.setAlvo(alvo); 
        batalha.adicionarEfeito(e);
        return e;
    }

    // ----------- abstratos

    /** chamado no inicio de cada rodada */
    public void aplicar(){};

    /** chamado sempre que uma carta é usada */
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha){};

    /** chamado sempre que o efeito vai ser removido da batalha */
    public void acabar(){};

    /** chamado quando o efeito é adicionado a batalha */
    public void onCreate(){};

    /** cria copia */
    public Efeito criaCopia(){ return this;};

    /** a string que vai ficar na linha de efeitos embaixo do nome da entidade q eles tao afetando */
    public String status(){ return this.getNomeColorido() + " (" + this.stacks + ")"; };
}
