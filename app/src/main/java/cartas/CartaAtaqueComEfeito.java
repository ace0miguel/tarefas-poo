package cartas;

import batalhaListeners.efeitos.Efeito;
import entidades.Entidade;
import entidades.Heroi;
import entidades.Inimigo;
import telas.eventos.combate.Batalha;
import visual.Cor;

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
            acertos = 0;
            
            heroi.receberDanoDireto(this.sacrificio);
            
            aplicarEfeito(heroi, alvo, batalha);
    
            heroi.usarEnergia(this.getCusto());
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
        if (!efeitoEmArea){
                batalha.causarDano(alvo, this.getDano() + ((this.getDano() * heroi.getDanoExtra()) / 100), heroi);
            }    
            else {
                for (Inimigo inimigo : batalha.getInimigos() ) {
                    batalha.causarDano(inimigo, this.getDano() + ((this.getDano() * heroi.getDanoExtra()) / 100), heroi);
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

        acertos++;

        printaResenha();
    }

    @Override
    public String descricao(){
        StringBuilder retorno = iniciarDescricao();

        appendTagsDescricao(retorno);

        if (!this.getDescricao().equals("")) {
            retorno.append(" - ").append(this.getDescricao()).append(" -");
        }

        retorno.append(Cor.vermelho).append(" (").append(this.getDano()).append(") ").append("DANO");
        appendSacrificio(retorno);
        appendCustoDescricao(retorno);

        return fecharColchete(retorno);
    }

    @Override
    public Carta criaCopia() {
        return new CartaAtaqueComEfeito(this);
    }
}
