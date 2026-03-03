package Bonecos;
public class Personagem {
    private String nome;
    private int vida;
    private int escudo;
    private int energia;

    /* inicializa os atributos */
    public Personagem(String nome, int vida, int escudo, int energia){
        this.escudo = escudo;
        this.nome = nome;
        this.vida = vida;
        this.energia = energia;
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

    /* ganha escudo */
    public void ganharEscudo(int bonus){
        this.escudo +=
         bonus;
    }

    /* retorna 1 se esta vivo e 0 do contrario */
    public int estarVivo (){
        return (vida > 0) ? 1 : 0;
    }

    public String status(){
        return (escudo != 0) 
        ? "Héroi ("+vida+"/20 de vida) ("+escudo+" de escudo)" 
        : "Héroi ("+vida+"/20 de vida)";
    }
}
