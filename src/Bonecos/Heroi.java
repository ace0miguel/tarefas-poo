package Bonecos;
public class Heroi {
    private String nome;
    private int vida;
    private int vidaMax;
    private int escudo;
    private int energia;
    private int energiaMax;

    /* inicializa os atributos */
    public Heroi(String nome, int vida, int escudo, int energia){
        this.escudo = escudo;
        this.nome = nome;
        this.vida = vida;
        this.vidaMax = vida;
        this.energia = energia;
        this.energiaMax = energia;
    }

    /* recebe dano */
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

    public int getEnergia(){
        return this.energia;
    }

    public String statusEnergia(){
        return "Energia ("+this.energia+"/"+this.energiaMax+")";
    }

    public String getNome(){
        return this.nome;
    }

    /* ganha escudo */
    public void ganharEscudo(int bonus){
        this.escudo +=bonus;
    }

    public void resetarEscudo(){
        this.escudo = 0;
    }

    public void usarEnergia(int custo){
        this.energia -= custo;
    }

    /* retorna 1 se esta vivo e 0 do contrario */
    public int estaVivo (){
        return (vida > 0) ? 1 : 0;
    }

    public void resetarEnergia(){
        energia = energiaMax;
    }

    // printa a vida atual e escudo se houver
    public String status(){
        return (escudo != 0) 
        ? ""+this.nome+" ("+vida+"/"+vidaMax+" de vida) ("+escudo+" de escudo)" 
        : ""+this.nome+" ("+vida+"/"+vidaMax+" de vida)";
    }
}
