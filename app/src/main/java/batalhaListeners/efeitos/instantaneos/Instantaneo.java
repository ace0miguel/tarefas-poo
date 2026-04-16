package batalhaListeners.efeitos.instantaneos;

import batalhaListeners.batalhaListener;
import batalhaListeners.efeitos.Efeito;
import entidades.Heroi;
import telas.eventos.combate.Batalha;

public class Instantaneo extends Efeito {
    public Instantaneo(String nome, String desc) {
        super(nome, desc, 0);
    }

    public Instantaneo(Instantaneo copiado){
        super(copiado);
        this.setDur(0);
    }

    @Override
    /** se nao acumula, ele nao faz nada. se acumula, reaplica o onCreate e retorna true */
    public boolean addStack(Batalha batalha, batalhaListener novo){
        if (!acumulaEfeito(novo)){
            return false;
        }

        if (this.getAlvo() instanceof Heroi h )
            novo.onCreate(batalha, h);
        
        return true;
    }
}
