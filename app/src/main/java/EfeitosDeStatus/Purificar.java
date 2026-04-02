package EfeitosDeStatus;
import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Batalha;

// limpa todos os efeitos (incluindo positivos)
public class Purificar extends Efeito {
    public Purificar(String nome, String desc, int dur){
        super(nome, desc, dur);
        this.setInsta(true);
    }

    public Purificar(Purificar copiado){
        super(copiado);
        this.setInsta(true);
    }

    @Override
    public void aplicar(){
        this.getAlvo().setPurificar(true);
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
    }

    @Override
    public Efeito criaCopia() {
        return new Purificar(this);
    }

    @Override
    public String status() {
        return "";      
    }
    
}