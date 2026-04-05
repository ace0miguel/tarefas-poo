package Cartas;

import EfeitosDeStatus.Efeito;
import Entidades.Entidade;
import Entidades.Heroi;
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
        
        setDescricao(!this.getSelfCast() 
        ? ("Aplica [ " + this.efeito.getNomeColorido() + " ]")
        : ("Recebe [ " + this.efeito.getNomeColorido() + " ]")
        );
    }

    @Override
    public void usar(Heroi heroi, Entidade alvo, Batalha batalha){
        int energiaAtual = heroi.getEnergia();
        if(energiaAtual >= this.getCusto()){
            alvo.receberDano(this.getDano() + heroi.getDanoExtra());
            printaResenha();
            heroi.usarEnergia(this.getCusto());

            Entidade trueAlvo = (this.getSelfCast() ? heroi : alvo); // se for selfcast o efeito vai pra si mesmo, se nao vai pro inimigo!
            efeito.adicionar(trueAlvo, batalha);
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
        alvo.receberDano(this.getDano() + heroi.getDanoExtra());
        printaResenha();
        Efeito e = efeito.criaCopia();
        e.setAlvo(this.getSelfCast() ? heroi : alvo); // selfcast: ataca o inimigo e aplica um efeito em si mesmo
        batalha.adicionarEfeito(e);
    }

    @Override
    public Carta criaCopia() {
        return new CartaAtaqueComEfeito(this);
    }
}
