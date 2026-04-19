package batalhaListeners.itens.ativos;

import java.util.ArrayList;
import java.util.List;

import cartas.Carta;
import telas.eventos.combate.Batalha;
import util.InputHandler;

/** Escolhe uma carta da pilha de descarte */
public class RecuperaCarta extends ItemAtivo {
    public RecuperaCarta(String nome, String descricao, int custo) {
        super(nome, descricao, custo, true);
    }

    public RecuperaCarta(RecuperaCarta copiado) {
        super(copiado);
    }

    @Override
    public RecuperaCarta criaCopia() {
        return new RecuperaCarta(this);
    }

    @Override
    public int usar(Batalha batalha) {
        if (batalha.getPilhaDescarte().getPilha().isEmpty()) {
            System.out.println("A pilha de descarte esta vazia!");
            InputHandler.esperar();
            return -1;
        }

        List<Carta> listaCartas = new ArrayList<>() ;
        listaCartas.addAll(batalha.getPilhaDescarte().getPilha());

        int escolha = InputHandler.selecionar(listaCartas, true, visual.Cor.azulClaro + "Escolha uma carta:");

        if (escolha == -1) {
            return -1;
        }

        Carta cartaEscolhida = listaCartas.get(escolha);

        batalha.getPilhaDescarte().recupera(cartaEscolhida, batalha.getMao());
        
        return 0;
    }
}