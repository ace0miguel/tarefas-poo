package batalhaListeners.efeitos.instantaneos;

import batalhaListeners.efeitos.Efeito;
import entidades.Heroi;
import telas.eventos.combate.Batalha;
import util.InputHandler;
import visual.Cor;

/** adiciona energia instantaneamente */
public class GanhaEnergia extends Instantaneo {

    private int valor;
    public GanhaEnergia(String nome, String desc, int valor){
        super(nome, desc);
        this.valor = valor;
    }

    public GanhaEnergia(GanhaEnergia copiado){
        super(copiado);
        this.valor = copiado.valor;
    }

    @Override
    public void onCreate(Batalha batalha, Heroi heroi) {
        if (this.getAlvo() instanceof Heroi h) {
            h.ganhaEnergia(valor);
        } else {
            Cor.printaAmarelo("erro -> tentou dar energia pra algo nao heroi");
            InputHandler.esperar();
        }
    }

    @Override
    public Efeito criaCopia() {
        return new GanhaEnergia(this);
    }
    
}
