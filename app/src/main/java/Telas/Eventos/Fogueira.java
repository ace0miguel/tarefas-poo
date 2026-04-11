package Telas.Eventos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Entidades.Heroi;
import Util.Arte;
import Util.Cor;
import Util.InputHandler;
import Util.Recompensas;
import Util.Textos;

/** evento aleatório, o jogador pode escolher entre recuperar vida ou ganhar uma recompensa */
public class Fogueira extends Evento{
    List<String> opcoes = new ArrayList<>(Arrays.asList("descansar.", "abrir um pacotinho de cartas q vc achou."));

    @Override
    public void iniciar(Heroi heroi) {
        this.heroi = heroi;

        Textos.sobeTela();
        int escolha = InputHandler.selecionar(opcoes, true, (Arte.fogueiraColorida) + Cor.txtAmareloClaro("\n\nVocê encontrou uma fogueira! O que deseja fazer? ") + Textos.menuStatus(heroi), "ir embora.");
        switch (escolha) {
            case 0 ->
            {
                heroi.ganhaVida(heroi.getVidaMax()/3);
                break;
            }
            case 1 ->
            {
                Recompensas.ganharCartas(1,3,heroi);
                break;
            }
        }
    }

    @Override
    public String toString() {
        String retorno = Cor.txtVerde("Fogueira.");
        return retorno;
    }

    @Override
    public Fogueira criaCopia() {
        return new Fogueira();
    }

    
}
