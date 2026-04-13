package Subscribers.EfeitosDeStatus.Instantaneos;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import Cartas.Carta;
import Entidades.Heroi;
import Subscribers.EfeitosDeStatus.Efeito;
import Util.InputHandler;

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
        Heroi heroi = (Heroi) this.getAlvo();
        List<List<String>> matrizPaginas = InputHandler.montaPaginas(heroi.getPilhaCompra().getPilhaCartas());

        if (heroi.getPilhaCompra().getPilhaCartas().size() <= 0) {
            System.out.println("Não há cartas para escolher!");
            InputHandler.esperar();
            this.cancelarJogada();

            return;
        }

        
        Carta escolha = heroi.getPilhaCompra().getPilhaCartas().get(InputHandler.menu(matrizPaginas, new AtomicInteger(0), false));

        heroi.getMaoAtual().addCartaEsp(escolha);
        heroi.getPilhaCompra().getPilhaCartas().remove(escolha);
    }
}