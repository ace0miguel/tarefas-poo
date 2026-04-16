package Subscribers.EfeitosDeStatus;

import Entidades.Entidade;
import Entidades.Heroi;
import Subscribers.BatalhaSubscriber;
import Subscribers.EfeitosDeStatus.Buffs.AumentaDano;
import Subscribers.EfeitosDeStatus.DanosConstantes.DanoConstante;
import Subscribers.EfeitosDeStatus.DanosConstantes.Sangramento;
import Subscribers.EfeitosDeStatus.DanosConstantes.Veneno;
import Subscribers.EfeitosDeStatus.Instantaneos.Escudo;
import Subscribers.EfeitosDeStatus.Instantaneos.GanhaEnergia;
import Subscribers.EfeitosDeStatus.Instantaneos.Purificar;
import Telas.Eventos.Batalha;
import Visual.Cor;

public abstract class Efeito implements BatalhaSubscriber{
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

    @Override
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

    @Override
    public int getPrioridade() {
        return 1;
    }

    @Override
    public boolean getRemover(){
        return (this.dur <= 0 || this.alvo.getPurificar());
    }

    /** retorna true se o efeito passado é igual a instancia do efeito comparando */
    public boolean acumulaEfeito(BatalhaSubscriber novo){
        if (!(novo instanceof Efeito))
            return false;

        else if (novo instanceof Efeito e)
            if (e.getAlvo() != this.alvo || e.getClass() != this.getClass() || !e.getNome().equals(this.nome))
                return false;

        return true;
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

    @Override
    /** por padrao, onRoundStart apenas reduz a duraçao */
    public void onRoundStart(Batalha batalha, Heroi heroi){
        passaTurno();
    }

    @Override
    public boolean addStack(Batalha batalha, BatalhaSubscriber novo){
        return false;
    }

    /** adiciona uma cópia do efeito à batalha. Retorna a cópia do efeito para validaçao da jogada*/
    public Efeito adicionar(Entidade alvo, Batalha batalha){ 
        Efeito e = this.criaCopia();
        e.setAlvo(alvo); 
        batalha.adicionarSubscriber(e);
        return e;
    }

    /** coloca na lista de espera pra adicionar subscriber */
    public void setAdicionar(Entidade alvo, Batalha batalha) {
        Efeito e = this.criaCopia();
        e.setAlvo(alvo); 
        batalha.adicionarFuturoSubscriber(e);
    }

    /** cria copia */
    public Efeito criaCopia(){ return this;};

    /** a string que vai ficar na linha de efeitos embaixo do nome da entidade q eles tao afetando */
    public String status(){ return this.getNomeColorido() + " (" + this.stacks + ")"; };
}
