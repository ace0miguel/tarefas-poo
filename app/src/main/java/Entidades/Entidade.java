package Entidades;

import java.util.ArrayList;

import EfeitosDeStatus.Efeito;

public abstract class Entidade {
    private String nome;
    private int vida;
    private int vidaMax;
    private int escudo = 0;
    private ArrayList<Efeito> efeitosAplicados;

    public Entidade(String nome, int vida){
        this.vida = vida;
        this.nome = nome;
        this.vidaMax = vida;
        this.efeitosAplicados = new ArrayList<>();
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

    public void passaTurno(){
        for(int i = efeitosAplicados.size() - 1; i >= 0; i--){
            Efeito efeito = efeitosAplicados.get(i);
            if (efeito.getDur() > 0){
                efeito.aplicar(this);
                System.out.println();
                System.out.println(efeito.getDesc());
                System.out.println();
                System.out.println("Efeito aplicado por mais "+ efeito.getDur()+ " turnos");
                System.out.println();
            } else removeEfeito(efeito);
        }
        
    }

    public ArrayList<Efeito> getEfeitosAplicados() {
        return efeitosAplicados;
    }
    
    public void addEfeito(Efeito e){
        this.efeitosAplicados.add(e);
    }

    public void removeEfeito(Efeito e){
        this.efeitosAplicados.remove(e);
    }

    public boolean temEfeito(){
        if (efeitosAplicados.size() == 0) return false; 
        else return true;
    }

    public String descEfeito(Efeito e){
        return e.getDesc();
    }

    public abstract String status();

}
