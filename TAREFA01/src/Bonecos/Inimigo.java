package Bonecos;
//import Bonecos.Heroi;
public class Inimigo {
    private String nome;
    private int vida;
    private int escudo;
    private int dano;

    public Inimigo(String nome, int vida, int escudo, int dano){
        this.escudo = escudo;
        this.nome = nome;
        this.vida = vida;
        this.dano = dano;
    }

    public String getNome(){
        return this.nome;
    }

    public void receberDano(int dano){
        this.vida -= dano; 
    }

    public void ganharEscudo(int bonus){
        this.escudo += bonus;
    }

    public void atacar(Heroi alvo){
        alvo.receberDano(this.dano);
    }

    public int estaVivo (){
        return (vida > 0) ? 1 : 0;
    }
    
    public String status(){
        return (escudo != 0) 
        ? "Inimigo ("+vida+"/10 de vida) ("+escudo+" de escudo)" 
        : "Inimigo ("+vida+"/10 de vida)";
    }
}
