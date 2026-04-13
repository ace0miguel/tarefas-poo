package Cartas;

import Entidades.Entidade;
import Entidades.Heroi;
import Entidades.Inimigo;
import Subscribers.EfeitosDeStatus.Efeito;
import Telas.Eventos.Batalha;
import Visual.Cor;

/** cartas que causam dano direto e aplicam efeitos */
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
        super(nome, descricao, custo, dano, tipo);
        this.efeito = efeito;
        this.setSelfCast(selfCast);

        setDescricao(!this.getSelfCast() 
        ? ("Aplica [ " + this.efeito.getNomeColorido() + " ]")
        : ("Recebe [ " + this.efeito.getNomeColorido() + " ]")
        );
    }

    public CartaAtaqueComEfeito(CartaAtaqueComEfeito copia) {
        super(copia);
        this.efeito = copia.efeito;      

        setDescricao(!this.getSelfCast() 
        ? ("Aplica [ " + this.efeito.getNomeColorido() + " ]")
        : ("Recebe [ " + this.efeito.getNomeColorido() + " ]")
        );
    }

    @Override
    public void usar(Heroi heroi, Entidade alvo, Batalha batalha){
        int energiaAtual = heroi.getEnergia();
        if(energiaAtual >= this.getCusto()){
            heroi.receberDanoDireto(this.sacrificio);
            
            aplicarEfeito(heroi, alvo, batalha);
    
            heroi.usarEnergia(this.getCusto());
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
        if (!efeitoEmArea){
                alvo.receberDano(this.getDano() + ((this.getDano() * heroi.getDanoExtra()) / 100));
            }    
            else {
                for (Inimigo inimigo : batalha.getInimigos() ) {
                    inimigo.receberDano(this.getDano() + ((this.getDano() * heroi.getDanoExtra()) / 100));
                }
            }
        if (getSelfCast()){
            efeito.adicionar(heroi, batalha);
        } else if (!efeitoEmArea){
            efeito.adicionar(alvo, batalha);
        } else {
            for (Inimigo inimigo : batalha.getInimigos() ) {
                efeito.adicionar(inimigo, batalha);
            }
        }

        printaResenha();
    }

    @Override
    public String descricao(){
        String retorno = this.getNome();

        if (!tags.isEmpty()) {
            retorno += " - [" + String.join(", ",  tags) + "]";
        }

        if (!this.getDescricao().equals("")) {
            retorno += " - " + this.getDescricao();
        }

        if (this.getSacrificio() != 0){
            retorno += " - " + Cor.txtVermelho("[Sacrifício: " + this.getSacrificio() + "]");
        }

        retorno += Cor.cinza + " (" + this.getDano() + ") " + "DANO" +  Cor.txtAmareloClaro(" < custo: " + this.getCusto());
        return retorno;
    }

    @Override
    public Carta criaCopia() {
        return new CartaAtaqueComEfeito(this);
    }
}
