package batalhaListeners.efeitos.instantaneos.status;

import batalhaListeners.efeitos.Efeito;
import batalhaListeners.efeitos.instantaneos.Instantaneo;
import entidades.Heroi;
import telas.eventos.combate.Batalha;
import visual.Cor;

/** perde vida instataneamente */
public class RecebeDanoPuro extends Instantaneo {
    private int valor;
    public RecebeDanoPuro(String nome, String desc, int valor){
        super(nome, desc);
        this.valor = valor;
    }

    public RecebeDanoPuro(RecebeDanoPuro copiado){
        super(copiado);
        this.valor = copiado.valor;
    }

    @Override
    public void onCreate(Batalha batalha, Heroi heroi) {
        batalha.causarDanoDireto(this.getAlvo(), valor, null);
    }

    @Override
    public Efeito criaCopia() {
        return new RecebeDanoPuro(this);
    }
    
    @Override
    public String getMsgFimRodada(Batalha batalha, Heroi heroi) {
        return this.getAlvo().getNomeColorido() + " Recebeu [ " + Cor.vermelho + valor + Cor.reset  + " ] dano puro.";
    }
}
