package Subscribers.EfeitosDeStatus.Instantaneos;
import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
import Subscribers.EfeitosDeStatus.Efeito;
import Telas.Eventos.Batalha;

/** remove todos os efeitos (incluindo positivos) */
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
    public void onRoundStart(Batalha batalha, Heroi heroi){
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
    }

    @Override
    public void onCreate(Batalha batalha, Heroi heroi) {
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
    public void onRemove() {
        this.getAlvo().setPurificar(false);
    }
}