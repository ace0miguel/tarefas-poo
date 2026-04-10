package Telas.Eventos;

import Entidades.Heroi;
import Util.Cor;

public class Evento { // evento generico
    protected Heroi heroi;

    public void iniciar(Heroi heroi){
        this.heroi = heroi;
    }

    public String toString() {
        String retorno = Cor.txtVermelho("evento") + " ( > ";
        return retorno;
    }

    public Evento criaCopia() {
        return new Evento();
    }
}
