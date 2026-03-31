package EfeitosDeStatus;
import Cartas.Carta;

// amplia algum dos status do alvo em algum valor
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
        this.getAlvo().aumentaDano(valor);
    }

    @Override
    public void onHit(Carta carta) {
    }

    @Override
    public Efeito criaCopia() {
        return new AumentaDano(this);
    }
    
}