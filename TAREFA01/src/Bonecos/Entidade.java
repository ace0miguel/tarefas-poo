package Bonecos;

public abstract class Entidade {
    private String nome;
    private int vida;
    private int vidaMax;
    private int escudo;

    public Entidade(String nome, int vida, int vidaMax, int escudo){
        this.vida = vida;
        this.escudo = escudo;
        this.nome = nome;
        this.vidaMax = vidaMax;
    }

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

    public void ganharEscudo(int bonus){
        this.escudo += bonus;
    }

    public void resetarEscudo(){
        this.escudo = 0;
    }

    /* retorna 1 se esta vivo e 0 do contrario */
    public boolean estaVivo (){
        return (vida > 0) ? true : false;
    }

    public abstract String status();

}
