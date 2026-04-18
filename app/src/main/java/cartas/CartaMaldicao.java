package cartas;

import batalhaListeners.efeitos.Efeito;
import entidades.Entidade;
import entidades.Heroi;
import telas.eventos.combate.Batalha;
import visual.Cor;

    /** cartas que te prejudicam (te aplicam um efeito negativo) ou nao fazem nada 
    (normalmente inimigos vao colocar ela no seu deck ou vao ser consequencia de algo.) */
    public class CartaMaldicao extends Carta 
    {
        private Efeito efeito;

        public CartaMaldicao(String nome, String descricao, int custo, Efeito efeito, boolean _selfCast){
            super(nome, descricao, custo);
            this.setSelfCast(_selfCast);
            this.efeito = efeito;
        }

        public CartaMaldicao(String nome, String descricao, int custo, boolean _selfCast){
            super(nome, descricao, custo);
            this.setSelfCast(_selfCast);
        }

        public CartaMaldicao(CartaMaldicao copia) {
            super(copia);
            this.efeito = copia.efeito;
        }

        @Override
        public void usar (Heroi heroi, Entidade alvo, Batalha batalha){
            int energiaAtual = heroi.getEnergia();
            if(energiaAtual >= this.getCusto()){
                heroi.usarEnergia(this.getCusto());
                if (this.efeito != null){
                    efeito.adicionar(alvo,batalha);
                }
                printaResenha();
            }
        }

        @Override
        public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
            if (this.efeito != null){
                    efeito.adicionar(alvo,batalha);
                }
            printaResenha();
        }
        
        @Override
        public String descricao(){
            StringBuilder retorno = iniciarDescricao();
            retorno.append(" - ").append(this.getDescricao());

            appendTagsDescricao(retorno);
            appendCustoDescricao(retorno);

            return finalizarDescricao(retorno);
        }

        @Override
        public Carta criaCopia() {
            return new CartaMaldicao(this);
        }
    }
