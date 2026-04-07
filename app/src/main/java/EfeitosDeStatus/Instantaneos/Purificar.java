package EfeitosDeStatus.Instantaneos;
import Cartas.Carta;
import EfeitosDeStatus.Efeito;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;

// limpa todos os efeitos (incluindo positivos)
public class Purificar extends Efeito {
    public Purificar(String nome, String desc){
        super(nome, desc, 0);
    }

    public Purificar(Purificar copiado){
        super(copiado);
    }

    @Override
    public void addStack(){
        this.stacks++;
        this.getAlvo().setPurificar(true);
        this.setDur(0);
    }

    @Override
    public void aplicar(){
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
    }

    @Override
    public void onCreate() {
        this.getAlvo().setPurificar(true);
        this.getAlvo().setPurificado(true);
        this.setDur(0);
    }

    @Override
    public Efeito criaCopia() {
        return new Purificar(this);
    }

    @Override
    public String status() {
        return "";      
    }
    
    @Override
    public void acabar() {
        this.getAlvo().setPurificar(false);
    }
}