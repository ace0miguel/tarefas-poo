package Bonecos;
public class Inimigo {
    private String nome;
    private int vida;
    private int escudo;

    public Inimigo(String nome, int vida, int escudo){
        this.escudo = escudo;
        this.nome = nome;
        this.vida = vida;
    }

    public void receberDano(int dano){
        this.vida -= dano; 
    }

    public void ganharEscudo(int bonus){
        this.escudo += bonus;
    }

    public int estarVivo (){
        return (vida > 0) ? 1 : 0;
    }
    
    public String status(){
        return (escudo != 0) 
        ? "Inimigo ("+vida+"/20 de vida) ("+escudo+" de escudo)" 
        : "Inimigo ("+vida+"/20 de vida)";
    }
}
