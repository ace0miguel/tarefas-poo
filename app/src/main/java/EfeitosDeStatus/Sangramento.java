package EfeitosDeStatus;

/*  ao aplicar sangramento em um inimigo que ja tem o efeito, reseta a duraçao e aumenta o dano.
Ao atingir 5 acumulos de sangramento, causa todo o dano restante e remove o efeito*/

public class Sangramento extends DanoConstante{
    public Sangramento(String nome, String desc, int dur, int dano){
        super(nome, desc, dur, dano);
    }

    public Sangramento(DanoConstante copiado){
        super(copiado);
    }


}
