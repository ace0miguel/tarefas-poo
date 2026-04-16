package batalhaListeners.efeitos.instantaneos;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import batalhaListeners.efeitos.Efeito;
import cartas.Carta;
import entidades.Heroi;
import telas.eventos.combate.Batalha;
import util.InputHandler;

/** Abre um menu de seleção baseado na lista de compras
 *  e permite escolher uma carta pra puxar imediatamente
 *  e dps reembaralha a pilha de compras (pq o jogador viu a ordem) */
public class EscolheCarta extends Instantaneo {
    protected Carta carta;

    public EscolheCarta(String nome, String desc) {
        super(nome, desc);
    }

    public EscolheCarta(EscolheCarta copiado) {
        super(copiado);
    }

    @Override
    public Efeito criaCopia() {
        return new EscolheCarta(this);
    }

    @Override
    public void onCreate(Batalha batalha, Heroi heroi) {
        if (this.getAlvo() instanceof Heroi h) {
            if (h.getPilhaCompra().getPilhaCartas().size() <= 0) {
                System.out.println("Não há cartas para escolher!");
                InputHandler.esperar();
                this.cancelarJogada();

                return;
            }

            List<List<String>> matrizPaginas = InputHandler.montaPaginas(h.getPilhaCompra().getPilhaCartas());

            Carta escolha = h.getPilhaCompra().getPilhaCartas().get(InputHandler.menu(matrizPaginas, new AtomicInteger(0), false));

            h.getMaoAtual().addCartaEsp(escolha);
            h.getPilhaCompra().getPilhaCartas().remove(escolha);
            
            Collections.shuffle(h.getPilhaCompra().getPilhaCartas());
        }
    }
}