package Cartas;

import EfeitosDeStatus.Efeito;
import Entidades.*;
import Telas.Batalha;

public class CartaAtaqueComEfeito extends CartaAtaque {
    private Efeito efeito;

    public CartaAtaqueComEfeito(String nome, int custo, int dano, Efeito efeito){
        super(nome, custo, dano);
        this.efeito = efeito;
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
    
        }
    }

}
