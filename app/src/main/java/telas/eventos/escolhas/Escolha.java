package telas.eventos.escolhas;

import java.util.List;

import entidades.Heroi;
import telas.eventos.Evento;
import util.InputHandler;

/** evento curto, que apresenta uma escolha ao jogador (ganhar recompensa, talvez perder algo, etc) */
public abstract class Escolha extends Evento{
    protected String descricao;
    protected List<String> opcoes;
    private int escolha;

    @Override
    public void iniciar(Heroi heroi) {
       this.heroi = heroi;  

       System.out.println(descricao);

       escolha = InputHandler.selecionar(opcoes, descricao);

       realizarEscolha(escolha);
    }

    public abstract void realizarEscolha(int escolha);
}
