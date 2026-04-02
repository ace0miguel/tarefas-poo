package Entidades;

import Util.Cor;
import Util.Textos;

public abstract class Entidade {
    private String nome;
    private int vida;
    private int vidaMax;
    private int escudo = 0;
    private int danoExtra = 0;
    private boolean purificar = false;

    private boolean envenenado = false;
    private boolean sangrando = false;

    public Entidade(String nome, int vida){
        this.vida = vida;
        this.nome = nome;
        this.vidaMax = vida;
    }

    //getters ------

    public String getNome(){
        return this.nome + Cor.reset;
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

    public boolean estaVivo (){
        return vida > 0;
    }

    //setters -------

    public void setPurificar(boolean purificar) {
        this.purificar = purificar;
    }
    
    public void setEnvenenado(boolean envenenado) {
        this.envenenado = envenenado;
    }

    public void setSangrando(boolean sangrando) {
        this.sangrando = sangrando;
    }

    // ----------   

    public void resetEfeitos(){
        this.envenenado = false;
        this.sangrando = false;
    }

    public String corStatus() {
        if (this.sangrando) {
            return Cor.vermelho; 
        }
        else if (this.envenenado) {
            return Cor.verde;
        }
        return Cor.reset;
    }

    public void receberDano(int dano){
        if (this.escudo >= dano){
            this.escudo -= dano;
        }
        else{
            dano -= this.escudo;
            this.escudo = 0;
            this.vida-= dano;
        }
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
        this.danoExtra = 0;
    }

    public void passaTurno(){
        resetarBonus();
    }


    public void setDanoExtra(int valor){
        danoExtra = valor;
    }

    public String status(){
        return (getEscudo() != 0) 
        ? ""+this.getNomeColorido()+ Cor.reset + " | " + Textos.desenharBarraVida(this.getVida(), this.getVidaMax()) + " "+Cor.azul+" ("+this.getEscudo()+" de escudo)" + Cor.reset + " |" + Cor.reset
        : ""+this.getNomeColorido()+ Cor.reset + " | " + Textos.desenharBarraVida(this.getVida(), this.getVidaMax()) + Cor.reset + " |" + Cor.reset ;
    }
}
