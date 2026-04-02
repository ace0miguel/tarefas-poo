package EfeitosDeStatus;
import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Batalha;

// altera o dano causado por ataques do alvo pelo valor passado (positivo aumenta, negativo reduz)
public class AumentaResistencia extends Efeito {
    private int valor;
    private int stacks = 1;

    public AumentaResistencia(String nome, String desc, int dur, int valor){
        super(nome, desc, dur);
        this.valor = valor;
        this.setInsta(true);
    }

    public AumentaResistencia(AumentaResistencia copiado){
        super(copiado);
        this.valor = copiado.valor;
        this.setInsta(true);
    }

    public int getStacks() {
        return stacks;
    }

    public void addStack(){
        this.stacks++;
    }

    @Override
    public void aplicar(){
        this.getAlvo().somaResistencia(this.valor * this.stacks);
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
    }

    @Override
    public Efeito criaCopia() {
        return new AumentaResistencia(this);
    }

    @Override
    public String status() {
        return this.getNome() + " (" + this.valor * this.stacks + ") > " + this.getDur(); 
    }
    
    @Override
    public void acabar() {
        this.getAlvo().somaResistencia(((this.valor * this.stacks) * -1));
    }
}