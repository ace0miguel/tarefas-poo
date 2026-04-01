package Cartas;

import EfeitosDeStatus.Efeito;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Batalha;

public class CartaAtaqueComEfeito extends CartaAtaque {
    private Efeito efeito;

    public CartaAtaqueComEfeito(String nome, String descricao, int custo, int dano, Efeito efeito){
        super(nome, descricao, custo, dano);
        this.efeito = efeito;
        setDescricao("Aplica " + efeito.getNome());
    }

    public CartaAtaqueComEfeito(String nome, String descricao, int custo, int dano, Efeito efeito, int tipo){
        super(nome, descricao, custo, dano);
        this.efeito = efeito;
        this.tipo = tipo;
        setDescricao("Aplica " + this.efeito.getNome());
    }

    @Override
    public void usar(Heroi heroi, Entidade alvo, Batalha batalha){
        int energiaAtual = heroi.getEnergia();
        if(energiaAtual >= this.getCusto()){
            alvo.receberDano(this.getDano());
            heroi.usarEnergia(this.getCusto());

            Efeito e = efeito.criaCopia();
            e.setAlvo(alvo);
            batalha.adicionarEfeito(e);
    
            printaResenha();
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
        alvo.receberDano(this.getDano());
        Efeito e = efeito.criaCopia();
        e.setAlvo(alvo);
        batalha.adicionarEfeito(e);

        printaResenha();
    }
}
