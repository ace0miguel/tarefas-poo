package Telas.Eventos;

import Entidades.Heroi;
import Util.Cor;
import Util.InputHandler;

/** cassino */
public class Tigrinho extends Evento{
    @Override
    public void iniciar(Heroi heroi) {
        this.heroi = heroi;
        // opções: apostar dinheiro, cartas ou vida.
        List<String> opcoes = new ArrayList<>(Arrays.asList("Apostar dinheiro", "Apostar cartas", "Apostar vida"));
        int escolha = InputHandler.selecionar(opcoes, Cor.txtAmareloClaro("O que você deseja apostar?"));
        switch (escolha) {
            case 0 -> {
                // Lógica para apostar dinheiro
            }
            case 1 -> {
                // Lógica para apostar cartas
            }
            case 2 -> {
                // Lógica para apostar vida
            }
        }
    }

    @Override
    public String toString() {
        String retorno = Cor.txtLaranja("Tigrinho");
        return retorno;
    }   
}
