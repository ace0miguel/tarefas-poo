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
        setDescricao("Aplica [ " + this.efeito.getNomeColorido() + " ]");
    }

    @Override
    public void usar(Heroi heroi, Entidade alvo, Batalha batalha){
        int energiaAtual = heroi.getEnergia();
        if(energiaAtual >= this.getCusto()){
            alvo.receberDano(this.getDano());
            printaResenha();
            heroi.usarEnergia(this.getCusto());

            Efeito e = efeito.criaCopia();
            e.setAlvo(this.getSelfCast() ? heroi : alvo); // selfcast: ataca o inimigo e aplica um efeito em si mesmo
            batalha.adicionarEfeito(e);
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
        alvo.receberDano(this.getDano());
        printaResenha();
        Efeito e = efeito.criaCopia();
        e.setAlvo(this.getSelfCast() ? heroi : alvo); // selfcast: ataca o inimigo e aplica um efeito em si mesmo
        batalha.adicionarEfeito(e);
    }
}
