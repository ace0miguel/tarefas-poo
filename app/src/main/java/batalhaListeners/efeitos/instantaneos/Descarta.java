package batalhaListeners.efeitos.instantaneos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import batalhaListeners.efeitos.Efeito;
import cartas.Carta;
import entidades.Heroi;
import telas.eventos.combate.Batalha;
import util.InputHandler;

/** Abre um menu com as cartas da mao e permite descartar uma quantidade */
public class Descarta extends Instantaneo {
    protected int quantidade;

    public Descarta(String nome, String desc, int quantidade) {
        super(nome, desc);
        this.quantidade = quantidade;
    }

    public Descarta(Descarta copiado) {
        super(copiado);
        this.quantidade = copiado.quantidade;
    }

    @Override
    public Efeito criaCopia() {
        return new Descarta(this);
    }
    

    @Override
    public void onCreate(Batalha batalha, Heroi heroi) {
        if (this.getAlvo() instanceof Heroi h) {
            Carta cartaEmUso = batalha.getCartaEmUso();

            // ve se tem cartas suficientes pra descartar ( - 1 pq a carta q ta aplicando o efeito de descartar nao conta )
            if (h.getMaoAtual().getSize() - 1 < this.quantidade) {
                System.out.println("Não há cartas suficientes para descartar!");
                InputHandler.esperar();
                this.cancelarJogada();

                return;
            }

            // cartas ja removidas numa iteraçao do loop
            List<Carta> cartasRepetidas = new ArrayList<>();

            for (int i = 0; i < this.quantidade; i++) {
                List<Carta> cartas = new ArrayList<>(h.getMaoAtual().mostrar());

                // remove a carta usada e as ja escolhidas para nao mutar a mao nem repetir descarte
                cartas.remove(cartaEmUso);
                cartas.removeAll(cartasRepetidas);

                visual.Textos.sleep(100);

                System.out.println("Escolha uma carta para descartar:");

                List<List<String>> matrizPaginas = InputHandler.montaPaginas(cartas);

                int escolha = InputHandler.menu(matrizPaginas, new AtomicInteger(0),"Escolha uma carta para descartar: ", false);

                Carta cartaEscolhida = cartas.get(escolha);
                cartasRepetidas.add(cartaEscolhida);
                h.getMaoAtual().setRemover(cartaEscolhida);
            }
        }
    }
}