package Cartas;

import EfeitosDeStatus.Efeito;
import Entidades.Heroi;
import Entidades.Inimigo;

public class CartaAtaqueComEfeito extends CartaAtaque {
    private Efeito efeito;

    public CartaAtaqueComEfeito(String nome, int custo, int dano, Efeito efeito){
        super(nome, custo, dano);
        this.efeito = efeito;
    }

    @Override
    public void usar(Heroi heroi, Inimigo alvo){
        int energiaAtual = heroi.getEnergia();
        if(energiaAtual >= this.getCusto()){
            alvo.receberDano(this.getDano());
            heroi.usarEnergia(this.getCusto());

            if (this.efeito.getSelfApply() == true){ 
            heroi.addEfeito(efeito);
            } else alvo.addEfeito(efeito);
            
        }
    }

}
