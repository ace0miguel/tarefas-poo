package Telas.Eventos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Entidades.Heroi;
import Util.Arte;
import Util.Cor;
import Util.InputHandler;

public class Fogueira extends Evento{
    List<String> opcoes = new ArrayList<>(Arrays.asList("Descansar", "Ganhar cartas(nao ta pronto ainda vc nao vai ganhar nada)"));

    @Override
    public void iniciar(Heroi heroi) {
        this.heroi = heroi;

        int escolha = InputHandler.selecionar(opcoes,Arte.fogueira);
        switch (escolha) {
            case 0:
                heroi.ganhaVida(heroi.getVidaMax()/3);
                break;
            case 1:
                System.out.println(Cor.txtAmareloClaro("se tivesse pronto vc ia ganha uma carta aqui"));
                InputHandler.esperar();
                // falta fazer o pacote de cartas, pro heroi ganhar aqui
                break;
        }
    }

    public String toString() {
        String retorno = Cor.txtVerde("Fogueira");
        return retorno;
    }
}
