package batalhaListeners.efeitos.instantaneos.status;

import batalhaListeners.efeitos.Efeito;
import batalhaListeners.efeitos.instantaneos.Instantaneo;
import entidades.Heroi;
import telas.eventos.combate.Batalha;

/** adiciona vida instantaneamente */
public class GanhaVida extends Instantaneo {

    private int valor;
    public GanhaVida(String nome, String desc, int valor){
        super(nome, desc);
        this.valor = valor;
    }

    public GanhaVida(GanhaVida copiado){
        super(copiado);
        this.valor = copiado.valor;
    }

    @Override
    public void onCreate(Batalha batalha, Heroi heroi) {
        this.getAlvo().addVida(valor);
    }

    @Override
    public Efeito criaCopia() {
        return new GanhaVida(this);
    }
    
}
