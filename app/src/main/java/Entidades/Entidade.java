package Entidades;

import static java.lang.Math.max;

import Util.Cor;
import Util.Textos;

public abstract class Entidade {
    private String nome;
    private int vida;
    private int vidaMax;
    private int escudo = 0;
    private int danoExtra = 0;
    private int resistencia = 0; // todo dano recebido subtrai isso aqui
    private boolean purificar = false;

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

    public int getDanoEfetivo(int dano){
        return max((dano - this.resistencia), 0);
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

    public void receberDano(int dano){
        int danoEfetivo = max((dano - this.resistencia), 0); // pra evitar o tal do -1 dano

        // se tiver por ex 1 de escudo e voce tomar 1000 de dano vc nao toma dano so perde o escudo.       
        if (this.escudo >= danoEfetivo){
            this.escudo -= danoEfetivo;
        } else {
            danoEfetivo -= this.escudo;
            this.escudo = 0;
            this.vida-= danoEfetivo;
        }
    }

    public void receberDanoDireto(int dano){ // danos que ignoram resistencias e escudo
        this.vida -= dano;
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

    public String status(){
        return (getEscudo() != 0) 
        ? ""+this.getNomeColorido()+ Cor.reset + " | " + Textos.desenharBarraVida(this.getVida(), this.getVidaMax()) + " "+Cor.azul+" ("+this.getEscudo()+" de escudo)" + Cor.reset + " |" + Cor.reset
        : ""+this.getNomeColorido()+ Cor.reset + " | " + Textos.desenharBarraVida(this.getVida(), this.getVidaMax()) + Cor.reset + " |" + Cor.reset ;
    }
}
