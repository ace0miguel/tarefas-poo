package Entidades;

public abstract class Entidade {
    private String nome;
    private int vida;
    private int vidaMax;
    private int escudo = 0;
    private int danoExtra = 0;
    private boolean purificar = false;

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

    public void setPurificar(boolean purificar) {
        this.purificar = purificar;
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
