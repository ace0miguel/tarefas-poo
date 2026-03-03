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

    public int receberDano(int dano){
        vida -= dano;
        return vida;
    }

    public int ganharEscudo(int bonus){
        escudo+=bonus;
        return escudo;
    }

    public int estarVivo (){
        if (vida == 0) return 0;
        else return 1;
    }
}
