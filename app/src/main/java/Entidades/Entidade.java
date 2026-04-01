package Entidades;

import Util.Cor;

public abstract class Entidade {
    private String nome;
    private int vida;
    private int vidaMax;
    private int escudo = 0;
    private int danoExtra = 0;
    private boolean purificar = false;
    private boolean envenenado = false;

    public Entidade(String nome, int vida){
        this.vida = vida;
        this.nome = nome;
        this.vidaMax = vida;
    }

    //getters ------

    public String getNome(){
        return this.nome;
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

    public void setPurificar(boolean purificar) {
        this.purificar = purificar;
    }
    
    public void setEnvenenado(boolean envenenado) {
        this.envenenado = envenenado;
    }

    public void coresVida(){
        if (this.getVida() > this.getVidaMax()/2)
            Cor.txtReset();
        else if (this.getVida() > this.getVidaMax()/5)
            Cor.setAmarelo();
        else 
            Cor.setVermelho();

        if (this.envenenado == true)
            Cor.setVerde();
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
        setEnvenenado(false);
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

    /* retorna 1 se esta vivo e 0 do contrario */
    public boolean estaVivo (){
        return (vida > 0) ? true : false;
    }

    public void passaTurno(){
        resetarBonus();
    }


    public void setDanoExtra(int valor){
        danoExtra = valor;
    }

    public abstract String status();
}
