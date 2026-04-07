package Cartas;

import EfeitosDeStatus.Efeito;
import Entidades.Entidade;
import Entidades.Heroi;
import Entidades.Inimigo;
import Telas.Eventos.Batalha;

public class CartaAtaqueComEfeito extends CartaAtaque {
    private Efeito efeito;

    public CartaAtaqueComEfeito(String nome, String descricao, int custo, int dano, Efeito efeito, boolean selfCast){
        super(nome, descricao, custo, dano);
        this.efeito = efeito;
        this.setSelfCast(selfCast);
        setDescricao(!this.getSelfCast() 
        ? ("Aplica [ " + this.efeito.getNomeColorido() + " ]")
        : ("Recebe [ " + this.efeito.getNomeColorido() + " ]")
        );
    }

    public CartaAtaqueComEfeito(String nome, String descricao, int custo, int dano, Efeito efeito, boolean selfCast, int tipo){
        super(nome, descricao, custo, dano);
        this.efeito = efeito;
        this.tipo = tipo;
        this.setSelfCast(selfCast);
        setDescricao(!this.getSelfCast() 
        ? ("Aplica [ " + this.efeito.getNomeColorido() + " ]")
        : ("Recebe [ " + this.efeito.getNomeColorido() + " ]")
        );
    }

    public CartaAtaqueComEfeito(CartaAtaqueComEfeito copia) {
        super(copia.getNome(), copia.getDescricao(), copia.getCusto(), copia.getDano());
        this.efeito = copia.efeito; 
        this.setSelfCast(copia.getSelfCast());
        this.tipo = copia.tipo;
        this.setResenha(copia.getResenha());
        this.consumir = copia.getConsumir();
        this.setEfeitoEmArea(copia.getEfeitoEmArea());
        
        setDescricao(!this.getSelfCast() 
        ? ("Aplica [ " + this.efeito.getNomeColorido() + " ]")
        : ("Recebe [ " + this.efeito.getNomeColorido() + " ]")
        );
    }

    @Override
    public void usar(Heroi heroi, Entidade alvo, Batalha batalha){
        int energiaAtual = heroi.getEnergia();
        if(energiaAtual >= this.getCusto()){
            if (!efeitoEmArea){
                alvo.receberDano(this.getDano() + heroi.getDanoExtra());
            }    
            else {
                for (Inimigo inimigo : batalha.getInimigos() ) {
                    inimigo.receberDano(this.getDano() + heroi.getDanoExtra());
                }
            }
            printaResenha();
            heroi.usarEnergia(this.getCusto());
            Entidade trueAlvo = (this.getSelfCast() ? heroi : alvo); // se for selfcast o efeito vai pra si mesmo, se nao vai pro inimigo!
            
            if (trueAlvo == heroi){
                efeito.adicionar(trueAlvo, batalha);
            } else if (!efeitoEmArea){
                efeito.adicionar(trueAlvo, batalha);
            } else {
                for (Inimigo inimigo : batalha.getInimigos() ) {
                    efeito.adicionar(inimigo, batalha);
                }
            }
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
        if (!efeitoEmArea){
                alvo.receberDano(this.getDano() + heroi.getDanoExtra());
            }    
            else {
                for (Inimigo inimigo : batalha.getInimigos() ) {
                    inimigo.receberDano(this.getDano() + heroi.getDanoExtra());
                }
            }
        printaResenha();

        if (getSelfCast()){
            efeito.adicionar(heroi, batalha);
        } else if (!efeitoEmArea){
            efeito.adicionar(alvo, batalha);
        } else {
            for (Inimigo inimigo : batalha.getInimigos() ) {
                efeito.adicionar(inimigo, batalha);
            }
        }
    }

    @Override
    public Carta criaCopia() {
        return new CartaAtaqueComEfeito(this);
    }
}
