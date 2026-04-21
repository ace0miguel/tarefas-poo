package batalhaListeners.efeitos.instantaneos.baralho;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import batalhaListeners.efeitos.Efeito;
import batalhaListeners.efeitos.instantaneos.Instantaneo;
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
        Carta cartaEmUso = batalha.getCartaEmUso();

        List<Carta> cartasDescartaveis = new ArrayList<>(heroi.getMaoAtual().mostrar());

        if (cartaEmUso != null) {
            cartasDescartaveis.remove(cartaEmUso);
        }

        if (cartasDescartaveis.size() < this.quantidade) {
            System.out.println("Não há cartas suficientes para descartar!");
            InputHandler.esperar();
            this.cancelarJogada();
            return;
        }

        for (int i = 0; i < this.quantidade; i++) {
            visual.Textos.sleep(100);

            System.out.println("Escolha uma carta para descartar:");

            List<List<String>> matrizPaginas = InputHandler.montaPaginas(cartasDescartaveis);

            int escolha = InputHandler.menu(matrizPaginas, new AtomicInteger(0),"Escolha uma carta para descartar: ", false);

            if (escolha < 0 || escolha >= cartasDescartaveis.size()) {
                this.cancelarJogada();
                return;
            }

            Carta cartaEscolhida = cartasDescartaveis.remove(escolha);
            heroi.getMaoAtual().descartar(cartaEscolhida);
        }
    }
}