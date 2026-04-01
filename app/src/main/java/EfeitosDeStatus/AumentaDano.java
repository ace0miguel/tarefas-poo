package EfeitosDeStatus;
import Cartas.Carta;

// altera o dano causado por ataques do alvo pelo valor passado (positivo aumenta, negativo reduz)
public class AumentaDano extends Efeito {
    private int valor;
    public AumentaDano(String nome, String desc, int dur, int valor){
        super(nome, desc, dur);
        this.valor = valor;
    }

    public AumentaDano(AumentaDano copiado){
        super(copiado);
        this.valor = copiado.valor;
    }

    @Override
    public void aplicar(){
        this.getAlvo().setDanoExtra(this.getAlvo().getDanoExtra() + this.valor);
    }

    @Override
    public void onHit(Carta carta) {
    }

    @Override
    public Efeito criaCopia() {
        return new AumentaDano(this);
    }

    @Override
    public String status() {
        return " [" + this.getNome() + " - " + this.valor + " dano extra" + " - (" + this.getDur() + " Rodadas)]"; 
        
    }
    
}