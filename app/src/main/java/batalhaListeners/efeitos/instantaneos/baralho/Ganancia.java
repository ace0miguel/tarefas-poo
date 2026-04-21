package batalhaListeners.efeitos.instantaneos.baralho;

import java.util.ArrayList;
import java.util.List;

import batalhaListeners.efeitos.instantaneos.Instantaneo;
import cartas.Carta;
import entidades.Heroi;
import telas.eventos.combate.Batalha;

/** descarta todas as cartas da mao e puxa uma quantidade igual de cartas*/
public class Ganancia extends Instantaneo {
    public Ganancia(String nome, String desc) {
        super(nome, desc);
    }

    public Ganancia(Ganancia copiado) {
        super(copiado);
    }

    @Override
    public Ganancia criaCopia() {
        return new Ganancia(this);
    }
    
    @Override
    public void onCreate(Batalha batalha, Heroi heroi) {

        List<Carta> cartas = new ArrayList<>(heroi.getMaoAtual().mostrar());
        int quantidade = cartas.size();

        if (quantidade == 0) {
            System.out.println("Não há cartas para descartar!");
            util.InputHandler.esperar();
            return;
        }

        for (int i = 0; i < quantidade; i++) {
            heroi.getMaoAtual().descartar(cartas.get(i));
        }

        for (int i = 0; i < quantidade; i++) {
            heroi.getMaoAtual().addCarta(batalha.getPilhaCompra(), batalha.getPilhaDescarte());
        }

    }
}