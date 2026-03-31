package EfeitosDeStatus;
import Cartas.Carta;

/* efeitos de dano constante, dano depende do efeito especifico */
public class DanoConstante extends Efeito {
    private int dano;
    public DanoConstante(String nome, String desc, int dur, int dano){
        super(nome, desc, dur);
        this.dano = dano;
    }

    public DanoConstante(DanoConstante copiado){
        super(copiado);
        this.dano = copiado.dano;
    }

    @Override
    public void aplicar(){
        this.getAlvo().receberDano(dano);
    }

    @Override
    public void onHit(Carta carta) {
    }

    @Override
    public Efeito criaCopia() {
        return new DanoConstante(this);
    }

    @Override
    public String status() {
        return " [" + this.getNome() + " - " + this.dano + " pontos de dano" + " - (" + this.getDur() + " Rodadas)]"; 
        
    }
    
}
