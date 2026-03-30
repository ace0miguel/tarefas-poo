package EfeitosDeStatus;
import Entidades.Entidade;

/* efeitos de dano constante, dano depende do efeito especifico */
public class DanoConstante extends Efeito {
    private int dano;
    public DanoConstante(String nome, String desc, int dur, int dano){
        super(nome, desc, dur);
        this.dano = dano;
    }

    public void aplicar(Entidade alvo){
        alvo.receberDano(dano);
    }
}
