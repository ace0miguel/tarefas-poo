package entidades;

import static java.lang.Math.max;

import telas.eventos.combate.Batalha;
import visual.Cor;
import visual.Textos;

public abstract class Entidade {
    protected String nome;
    protected int vida;
    protected int vidaMax;
    private int escudo = 0;

    // buffs (serao utilizados em porcentagem, mas aqui guarda o valor inteiro pra facilitar)
    private int danoExtra = 0; // aumenta em todo dano causado diretamente
    private int resistencia = 0; // reduz de todo dano recebido diretamente
    
    private boolean purificar = false; // se ativa, todos os efeitos de status sao removidos
    protected boolean meiaVida = false; // flag que ativa quando a vida da entidade fica abaixo de 50% pela primeira vez

    // booleans pra defini a cor do nome
    private boolean purificado = false;
    private boolean envenenado = false;
    private boolean sangrando = false;
    private boolean energizado = false;

    public Entidade(String nome, int vida){
        this.vida = vida;
        this.nome = nome;
        this.vidaMax = vida;
    }

    public Entidade(Entidade copiada){
        this.vida = copiada.getVida();
        this.nome = copiada.getNome();
        this.vidaMax = copiada.getVida();
    }

    //getters ------

    public String getNome(){
        return this.nome;
    }

    public String getNomeColorido(){
        return corStatus() + this.nome + Cor.reset;
    }

    public int getVida(){
        return this.vida;
    }

    public int getVidaMax(){
        return this.vidaMax;
    }

    public int getEscudo(){
        return this.escudo;
    }

    public int getDanoExtra() {
        return danoExtra;
    }
    
    public boolean getPurificar(){
        return this.purificar;
    }

    public boolean getEnvenenado(){
        return this.envenenado;
    }

    public boolean getSangrando(){
        return this.sangrando;
    }

    public boolean getPurificado(){
        return this.purificado;
    }
    
    public boolean getEnergizado(){
        return this.energizado;
    }

    public int getResistencia() {
        return resistencia;
    }

    public boolean estaVivo (){
        return vida > 0;
    }

    /** recebe um valor de dano, e retorna o dano reduzido pela resistencia da entidade no qual esta sendo aplicado */
    public int getDanoEfetivo(int dano){
        return max(dano - ((dano * this.resistencia) / 100), 0);
    }

    //setters -------

    public void setEscudo(int escudo) {
        this.escudo = escudo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setVidaMax(int vidaMax) {
        this.vidaMax = vidaMax;
    }
    
    public void setPurificar(boolean purificar) {
        this.purificar = purificar;
    }
    
    public void setEnvenenado(boolean envenenado) {
        this.envenenado = envenenado;
    }

    public void setSangrando(boolean sangrando) {
        this.sangrando = sangrando;
    }

    public void setPurificado(boolean purificado) {
        this.purificado = purificado;
    }

    public void setEnergizado(boolean energizado) {
        this.energizado = energizado;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public void somaResistencia(int valor) {
        this.resistencia += valor;
    }

    public void somaDanoExtra(int valor) {
        this.danoExtra += valor;
    }

    /** garante que onMeiaVida() seja chamado apenas uma vez */
    public void checkMeiaVida(Entidade executor, Heroi heroi, Batalha batalha){
        if (!this.meiaVida && this.vida <= this.vidaMax / 2 && this.vida > 0){
            this.meiaVida = true;
            onMeiaVida(executor, heroi, batalha);
        }
    }

    // ----------   

    public void resetEfeitos(){
        this.purificado = false;
        this.envenenado = false;
        this.sangrando = false;
        this.energizado = false;
    }

    public String corStatus() { // se quiser adicionar efeitos q mudam de cor colocar aqui!
        if (this.purificado) {
            return Cor.azulClaro;
        }
        else if (this.energizado) {
            return Cor.amarelo;
        }
        else if (this.sangrando) {
            return Cor.vermelho; 
        }
        else if (this.envenenado) {
            return Cor.verdeEscuro;
        }

        return Cor.reset;
    }

    /** aplica um valor de dano considerando resistencia e escudo */
    public void receberDano(int dano){
        receberDanoRetornandoVidaPerdida(dano);
    }

    /** aplica dano considerando resistencia e escudo e retorna a vida perdida de fato */
    public int receberDanoRetornandoVidaPerdida(int dano){
        int vidaAntes = this.vida;
        int danoEfetivo = max(dano - ((dano * this.resistencia) / 100), 0);
  
        if (this.escudo >= danoEfetivo){
            this.escudo -= danoEfetivo;
        } else {
            danoEfetivo -= this.escudo;
            this.escudo = 0;
            this.vida-= danoEfetivo;
        }

        return max(vidaAntes - max(this.vida, 0), 0);
    }

    /** retorna o dano recebido levando em consideraçao escudo e resistencia*/
    public int getDanoReduzido(int dano){
        return max(getDanoEfetivo(dano) - this.escudo, 0);
    }

    /** aplica um valor de dano que ignora resistencia e escudo */
    public void receberDanoDireto(int dano){ 
        receberDanoDiretoRetornandoVidaPerdida(dano);
    }

    /** aplica dano direto e retorna a vida perdida de fato */
    public int receberDanoDiretoRetornandoVidaPerdida(int dano){
        int vidaAntes = this.vida;
        this.vida -= dano;

        return max(vidaAntes - max(this.vida, 0), 0);
    }

    public void passaRodada(){
        resetarBonus();
    }
    
    public void ganharEscudo(int bonus){
        this.escudo += bonus;
    }

    public void resetarEscudo(){
        this.escudo = 0;
    }

    public void resetarBonus(){ 
        this.escudo = 0;
        this.purificar = false;
    }

    public void passaTurno(){
        resetarBonus();
    }

    public void setDanoExtra(int valor){
        danoExtra = valor;
    }

    // todos os buffs e bom adicionar aqui, tipo dano extra, resistencia ou outros. pra passar no começo de cada batalha
    public void resetaBuffs(){
        this.danoExtra = 0;
        this.resistencia = 0;
    }

    /** metodo chamado quando a entidade fica com metade da vida pela primeira vez */
    public void onMeiaVida(Entidade executor, Heroi heroi, Batalha batalha){}

    public String status(){
        return (getEscudo() != 0) 
        ? ""+this.getNomeColorido()+ Cor.reset + " " + Textos.desenharBarraVida(this.getVida(), this.getVidaMax()) + " "+Cor.azul+" ("+this.getEscudo()+" de escudo)" + Cor.reset + "" + Cor.reset
        : ""+this.getNomeColorido()+ Cor.reset + " " + Textos.desenharBarraVida(this.getVida(), this.getVidaMax()) + Cor.reset + "" + Cor.reset ;
    }
}
