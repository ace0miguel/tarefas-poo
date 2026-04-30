package telas.eventos;

import java.util.List;

import entidades.Heroi;
import util.InputHandler;
import visual.Cor;
import visual.Textos;



/** evento curto, que apresenta uma escolha ao jogador (ganhar recompensa, talvez perder algo, etc) */
public abstract class Escolha extends Evento{
    protected String descricao;
    protected List<String> opcoes;
    private int escolha;

    public Escolha(String descricao, List<String> opcoes) {
        this.descricao = descricao;
        this.opcoes = opcoes;
    }

    @Override
    public void iniciar(Heroi heroi) {
        this.heroi = heroi;  
        Textos.limpaTela();
        
        escolha = InputHandler.selecionar(opcoes, Textos.wrapText(descricao, 110));

        realizarEscolha(escolha);
    }

    public String getDescricao() {
        return descricao;
    }

    public int getEscolha() {
        return escolha;
    }

    public List<String> getOpcoes() {
        return opcoes;
    }
    
    public abstract void realizarEscolha(int escolha);

    @Override
    public String toString() {
        String retorno = Cor.txtCinza("[ ? ]");
        return retorno;
    }

    @Override
    public Escolha criaCopia() {
        return this;
    }
}
