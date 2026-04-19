package batalhaListeners.itens.ativos;

import java.util.ArrayList;
import java.util.List;

import cartas.Carta;
import telas.eventos.combate.Batalha;
import util.InputHandler;

/** Abre um menu de seleção baseado na lista de compras (embaralhadagit)
 *  e permite escolher uma carta pra puxar imediatamente */
public class EscolheCartaItem extends ItemAtivo {
    public EscolheCartaItem(String nome, String descricao, int custo) {
        super(nome, descricao, custo, true);
    }

    public EscolheCartaItem(EscolheCartaItem copiado) {
        super(copiado);
    }

    @Override
    public EscolheCartaItem criaCopia() {
        return new EscolheCartaItem(this);
    }

    @Override
    public int usar(Batalha batalha) {
        if (batalha.getPilhaCompra().getPilha().isEmpty()) {
            System.out.println("A pilha de compras esta vazia!");
            InputHandler.esperar();
            return -1;
        }

        List<Carta> listaCartas = new ArrayList<>(batalha.getPilhaCompra().getCartasEmbaralhadas());

        int escolha = InputHandler.selecionar(listaCartas, true, visual.Cor.azulClaro + "Escolha uma carta:");

        if (escolha == -1) {
            return -1;
        }

        Carta cartaEscolhida = listaCartas.get(escolha);

        batalha.getMao().addCartaEsp(batalha.getPilhaCompra().puxaCartaEsp(cartaEscolhida));
        
        return 0;
    }
}