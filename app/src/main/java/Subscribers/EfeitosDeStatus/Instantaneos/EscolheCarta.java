package Subscribers.EfeitosDeStatus.Instantaneos;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import Cartas.Carta;
import Entidades.Heroi;
import Subscribers.EfeitosDeStatus.Efeito;
import Telas.Eventos.Batalha;
import Util.InputHandler;

/** Abre um menu de seleção baseado na lista de compras
 *  e permite escolher uma carta pra puxar imediatamente */
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
        }
    }
}