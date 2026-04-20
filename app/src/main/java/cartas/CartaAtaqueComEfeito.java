package cartas;

import batalhaListeners.efeitos.Efeito;
import entidades.Entidade;
import entidades.Heroi;
import entidades.Inimigo;
import telas.eventos.combate.Batalha;
import visual.Cor;
import visual.Textos;

/** cartas que causam dano direto e aplicam um ou mais efeitos adicionais */
public class CartaAtaqueComEfeito extends CartaAtaque {
    private Efeito efeitos[];

    public CartaAtaqueComEfeito(String nome, String descricao, int custo, int dano, boolean selfCast, Efeito... efeitos){
        super(nome, descricao, custo, dano);
        this.efeitos = efeitos;
        this.setSelfCast(selfCast);
        
        // descrição automatica se tiver só 1 efeito
        if (efeitos.length == 1) {
            setDescricao(!this.getSelfCast() 
            ? ("Aplica [ " + this.efeitos[0].getNomeColorido() + " ]")
            : ("Recebe [ " + this.efeitos[0].getNomeColorido() + " ]")
            );
        }
    }

    public CartaAtaqueComEfeito(CartaAtaqueComEfeito copia) {
        super(copia);
        this.efeitos = copia.efeitos;      

        // descrição automatica se tiver só 1 efeito
        if (efeitos.length == 1) {
            setDescricao(!this.getSelfCast() 
            ? ("Aplica [ " + this.efeitos[0].getNomeColorido() + " ]")
            : ("Recebe [ " + this.efeitos[0].getNomeColorido() + " ]")
            );
        }
    }

    @Override
    public void usar(Batalha batalha){

        Heroi heroi = batalha.getHeroi();
        int energiaAtual = heroi.getEnergia();
        if(energiaAtual >= this.getCusto()){
            acertos = 0;
            
            // resolve a questao do selfcast
            Entidade alvo = resolverAlvo(batalha);

            if (alvo == null) {
                return;
            }
            
            heroi.receberDanoDireto(this.sacrificio);

            Textos.limpaTela();
            
            aplicarEfeito(heroi, alvo, batalha);
    
            heroi.usarEnergia(this.getCusto());
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
        printaResenha();

        if (!efeitoEmArea){
                batalha.causarDano(alvo, this.getDano() + ((this.getDano() * heroi.getDanoExtra()) / 100), heroi);
            }    
        else {
            for (Inimigo inimigo : batalha.getInimigos() ) {
                batalha.causarDano(inimigo, this.getDano() + ((this.getDano() * heroi.getDanoExtra()) / 100), heroi);
            }
        }

        if (aplicarEfeitos(heroi, alvo, batalha, efeitos)) {
            this.setUsoCancelado(true);
            return;
        }

        acertos++;
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

        return finalizarDescricao(retorno);
    }

    @Override
    public CartaAtaqueComEfeito criaCopia() {
        return new CartaAtaqueComEfeito(this);
    }
}
