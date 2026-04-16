package telas.eventos;

import entidades.Heroi;
import util.Recompensas;
import visual.Arte;
import visual.Cor;
import visual.Textos;

/** ganha uma boa recompensa. no momento um pacote de cartas incomum, mas quando tiver item vai ser item. */
public class Tesouro extends Evento {
    @Override
    public void iniciar(Heroi heroi) {
        Textos.limpaTela();

        String arteTitulo = Textos.colorirPartes(Arte.tesouro, Cor.marromClaro, Cor.marromClaro, 6);

        Textos.printaBonito(arteTitulo, 2 ,2);

        System.out.println();
        
        Textos.printaBonito("Você encontrou um tesouro!", 10, 2);

        System.out.println();

        Recompensas.ganharCartas(2, 5, heroi);

        Recompensas.ganharItemAleatorio(heroi);
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
