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

        if (((Heroi) this.getAlvo()).getPilhaCompra().getPilhaCartas().size() <= 0) {
            System.out.println("Não há cartas para escolher!");
            InputHandler.esperar();

            ((Heroi) this.getAlvo()).getPilhaDescarte().recuperaUltima(((Heroi) this.getAlvo()).getMaoAtual());

            if (((Heroi) this.getAlvo()).getMaoAtual().getSize() > 0) {
                ((Heroi) this.getAlvo()).ganhaEnergia(((Heroi) this.getAlvo()).getMaoAtual().getUltima().getCusto());
            }

            return;
        }

        
        Carta escolha = ((Heroi) this.getAlvo()).getPilhaCompra().getPilhaCartas().get(InputHandler.menu(matrizPaginas, new AtomicInteger(0), false));

        ((Heroi) this.getAlvo()).getMaoAtual().addCartaEsp(escolha);
        ((Heroi) this.getAlvo()).getPilhaCompra().getPilhaCartas().remove(escolha);
    }
}