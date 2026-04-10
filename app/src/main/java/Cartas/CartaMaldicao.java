    package Cartas;

    import EfeitosDeStatus.Efeito;
    import Entidades.Entidade;
    import Entidades.Heroi;
    import Telas.Eventos.Batalha;
    import Util.Cor;

    /* cartas que te prejudicam(te aplicam um efeito) ou nao fazem nada 
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
            String retorno = "" + this.getNome() + " - " + this.getDescricao();
            
            if (!tags.isEmpty()) {
            retorno += " - [" + String.join(", ",  tags) + "]";
            }
            // tirei a parte de mostrar o efeito, acho q ajuda a deixar a vibe da carta meio sinistra
            // if (this.efeito != null) {
            //     texto += " - [ " + this.efeito.getNomeColorido() + " ]";
            // }
            
            retorno += Cor.txtAmareloClaro(" < custo: " + this.getCusto());
            
            return retorno;
        }

        @Override
        public Carta criaCopia() {
            return new CartaMaldicao(this);
        }
    }
