package EfeitosDeStatus.Buffs;
import Cartas.Carta;
import EfeitosDeStatus.Efeito;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;

// altera a resistencia ( se quiser diminuir so passar um valor negativo e tals )
public class AumentaResistencia extends Buff {

    public AumentaResistencia(String nome, String desc, int dur, int valor){
        super(nome, desc, dur, valor);
    }

    public AumentaResistencia(AumentaResistencia copiado){
        super(copiado);
    }
    
    @Override
    public boolean getResetDur() {
        return true;
    }

    @Override
    public void addStack(){
        this.stacks++;
        this.getAlvo().somaResistencia(this.valor);
    }

    @Override
    public void aplicar(){
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
    }

    @Override
    public void onCreate() {
        this.getAlvo().somaResistencia(valor);
    }

    @Override
    public Efeito criaCopia() {
        return new AumentaResistencia(this);
    }

    @Override
    public String status() {
        return this.getNome() + " [" + this.valor * this.stacks + "] > " + this.getDur(); 
    }
    
    @Override
    public void acabar() {
        this.getAlvo().somaResistencia(this.valor * this.stacks * -1);
    }
}