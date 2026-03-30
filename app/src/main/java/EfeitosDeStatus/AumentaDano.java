package EfeitosDeStatus;
import Entidades.Entidade;

// amplia algum dos status do alvo em algum valor
public class AumentaDano extends Efeito {
    private int valor;
    public AumentaDano(String nome, String desc, int dur, boolean selfApply, int valor){
        super(nome, desc, dur, selfApply);
        this.valor = valor;
    }

    public void aplicar(Entidade alvo){
        alvo.aumentaDano(valor);
        passaTurno();
    }
}