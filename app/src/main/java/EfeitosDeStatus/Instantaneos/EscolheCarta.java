package EfeitosDeStatus.Instantaneos;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import Cartas.Carta;
import EfeitosDeStatus.Efeito;
import Util.InputHandler;
import Entidades.Heroi;

/** Abre um menu de seleção baseado na lista de compras
 *  e permite escolher uma carta pra puxar imediatamente */
public class EscolheCarta extends Efeito {
    protected Carta carta;

    public EscolheCarta(String nome, String desc) {
        super(nome, desc, 0);
    }

    public EscolheCarta(EscolheCarta copiado) {
        super(copiado);
    }

    @Override
    public void addStack() {
        this.onCreate();
    }

    @Override
    public void onCreate() {
        List<List<String>> matrizPaginas = InputHandler.montaPaginas(((Heroi) this.getAlvo()).getPilhaCompra().getPilhaCartas());

        Carta escolha = ((Heroi) this.getAlvo()).getPilhaCompra().getPilhaCartas().get(InputHandler.menu(matrizPaginas, new AtomicInteger(0), false));

        ((Heroi) this.getAlvo()).getMaoAtual().addCartaEsp(escolha);
        ((Heroi) this.getAlvo()).getPilhaCompra().getPilhaCartas().remove(escolha);
    }
}