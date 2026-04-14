package Telas.Eventos;

import Entidades.Heroi;
import Util.Recompensas;
import Visual.Arte;
import Visual.Cor;
import Visual.Textos;

/** ganha uma boa recompensa. no momento um pacote de cartas incomum, mas quando tiver item vai ser item. */
public class Tesouro extends Evento {
    @Override
    public void iniciar(Heroi heroi) {
        Textos.limpaTela();

        Textos.printaBonito(Cor.txtAmarelo(Arte.tesouro), 2 ,2);

        System.out.println();
        
        Textos.printaBonito("Você encontrou um tesouro!", 10, 2);

        System.out.println();

        Recompensas.ganharCartas(2, 5, heroi);
    }
    
    @Override
    public String toString() {
        String retorno = Cor.txtAmarelo("Tesouro.");
        return retorno;
    }

    @Override
    public Tesouro criaCopia() {
        return new Tesouro();
    }
}
