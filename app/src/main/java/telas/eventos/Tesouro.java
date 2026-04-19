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

        util.InputHandler.esperar(Cor.txtAmareloClaro("Pressione ENTER para abrir o tesouro..."));

        System.out.println();

        abrirTesouro(heroi);
    }
    
    public void abrirTesouro(Heroi heroi){
        
        // switch (sorteado) {
        //     case 0 -> Textos.printaBonito("Você encontrou um tesouro raro!", 10, 2);
        //     case 1 -> Textos.printaBonito("Você encontrou um tesouro épico!", 10, 2);
        //     case 2 -> Textos.printaBonito("Você encontrou um tesouro lendário!", 10, 2);
        //     default -> Textos.printaBonito("Você encontrou um tesouro comum!", 10, 2);
        // }

        Recompensas.ganharCartas(2, 5, heroi);
        Recompensas.ganharItemPassivo(heroi);
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
