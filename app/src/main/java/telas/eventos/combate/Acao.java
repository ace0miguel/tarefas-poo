package telas.eventos.combate;

import batalhaListeners.efeitos.Efeito;
import cartas.Carta;
import entidades.Heroi;
import entidades.Inimigo;
import visual.Cor;
import visual.Textos;

/** classe abstrata que representa uma ação que pode ser realizada por um inimigo */
public abstract class Acao {
    protected Efeito efeito;
    protected Carta carta;
    protected boolean agressiva = true; // se false, é uma ação de suporte (aplica efeito em aliado, etc)

    /** realiza a açao */
    public void executar(Inimigo executor, Heroi heroi, Batalha batalha){};
    
    /** exibe no turno do heroi */
    public void anunciar( Inimigo executor, Heroi heroi){};
    
    /** exibe no turno do inimigo */
    public int resultado(Inimigo executor, Heroi heroi){ return 0;};

    /** retorna o valor passado ou 0 se for menor q 0 */
    public int validarDano(int dano){
        return Math.max(dano,0);
    }

    /** retorna true se a açao causa dano direto ao jogador */
    public boolean getAgressiva() {
        return agressiva;
    }
    
    /** Causa o dano base */
    public static class Atacar extends Acao {
        @Override
        public void executar(Inimigo executor, Heroi heroi, Batalha batalha){
            batalha.causarDano(heroi, executor.getDanoEfetivo(), executor);
        }

        @Override
        public void anunciar(Inimigo executor, Heroi heroi){
            Textos.printaBonito(Cor.amarelo + "> " + Cor.reset + executor.getNome() + Cor.cinza + " irá te atacar causando " + (executor.getDanoEfetivo()) + " pontos de dano.", 3, 2);
        }

        @Override
        public int resultado(Inimigo executor, Heroi heroi) {
            Textos.printaBonito(Cor.reset + "> " + executor.getNome() + Cor.txtAmarelo(" ") + Cor.vermelho + "Causou " + heroi.getDanoReduzido(executor.getDanoEfetivo()) + " pontos de dano!", 5, 2);
            return heroi.getDanoReduzido(executor.getDanoEfetivo());
        }
    }

    /** Causa metade do dano base mas aplica um efeito */
    public static class AtacarEfeito extends Acao {
        public AtacarEfeito(Efeito efeito){
            this.efeito = efeito;
        }

        @Override
        public void executar(Inimigo executor, Heroi heroi, Batalha batalha){
            batalha.causarDano(heroi, executor.getFracaoDanoEfetivo(2), executor);
            efeito.adicionar(heroi, batalha);
        };

        @Override
        public void anunciar(Inimigo executor, Heroi heroi) {
            Textos.printaBonito(Cor.amarelo + "> " + Cor.reset + executor.getNome() + Cor.cinza + " irá te atacar causando " + (executor.getFracaoDanoEfetivo(2)) + " pontos de dano e aplicar " + efeito.getNomeColorido()  + ".", 3, 2);
        }

        @Override
        public int resultado(Inimigo executor, Heroi heroi) {
            Textos.printaBonito(Cor.reset + "> " + executor.getNome() + Cor.txtAmarelo(" ") + Cor.vermelho + "Causou " + heroi.getDanoReduzido(executor.getFracaoDanoEfetivo(2)) + " pontos de dano e aplicou " + efeito.getNomeColorido() + "!", 5, 2);
            return heroi.getDanoReduzido(executor.getFracaoDanoEfetivo(2));
        }
    }

    /** Causa o dano base + dano extra baseado na vida perdida do heroi */
    public static class AtacarVidaPerdida extends Acao {
        // um quinto da vida perdida do heroi é convertido em dano adicional
        public int danoVidaPerdida(Heroi heroi) {
            int vidaPerdida = heroi.getVidaMax() - heroi.getVida();
            return vidaPerdida / 5;
        }

        @Override
        public void executar(Inimigo executor, Heroi heroi, Batalha batalha) {
            batalha.causarDano(heroi, executor.getDanoEfetivo() + danoVidaPerdida(heroi), executor);
        }

        @Override
        public void anunciar(Inimigo executor, Heroi heroi) {
            Textos.printaBonito(Cor.amarelo + "> " + Cor.reset + executor.getNome() + Cor.cinza +  " irá te atacar causando " + (executor.getDanoEfetivo()) + " + (" + Cor.vermelho + danoVidaPerdida(heroi) + Cor.cinza  + ") pontos de dano.", 3, 2);
        }

        @Override
        public int resultado(Inimigo executor, Heroi heroi) {
            Textos.printaBonito(Cor.reset + "> " + executor.getNome() + Cor.txtAmarelo(" ") + Cor.vermelho + "Causou " + heroi.getDanoReduzido(executor.getDanoEfetivo() + danoVidaPerdida(heroi)) + " pontos de dano!", 5, 2);
            return heroi.getDanoReduzido(executor.getDanoEfetivo() + danoVidaPerdida(heroi));   
        }
    }

    /** Aplica um efeito a si mesmo */
    public static class ReceberEfeito extends Acao {
        public ReceberEfeito(Efeito efeito){
            this.efeito = efeito;
            this.agressiva = false;
        }

        @Override
        public void executar(Inimigo executor, Heroi heroi, Batalha batalha) {
            efeito.adicionar(executor, batalha);
        };

        @Override
        public void anunciar(Inimigo executor, Heroi heroi) {
            Textos.printaBonito(Cor.txtAmarelo("> ") + executor.getNome() + Cor.cinza + " irá utilizar " + efeito.getNomeColorido() + ".", 3, 2);
        }

        @Override
        public int resultado(Inimigo executor, Heroi heroi) {
            Textos.printaBonito(Cor.reset + "> " + executor.getNome() + " utilizou " + efeito.getNomeColorido() + "!", 5, 2); 
            return 0;
        }
    }

    /** Adiciona uma carta a pilha de compras do heroi (tem que ser o heroi) */
    public static class AdicionarCarta extends Acao {
        public AdicionarCarta(Carta carta) {
            this.carta = carta.criaCopia();
            this.agressiva = false;
        }

        @Override
        public void executar(Inimigo executor, Heroi heroi, Batalha batalha) {
            heroi.getPilhaCompra().addCartaPilha(carta);
            heroi.getPilhaCompra().shuffleStack();
        }

        @Override
        public void anunciar(Inimigo executor, Heroi heroi) {
            Textos.printaBonito(Cor.amarelo + "> " + Cor.reset + executor.getNome() + Cor.cinza + " irá colocar uma " + Cor.txtVermelho("carta ") + Cor.cinza + "na sua pilha de compras.", 3, 2);
        }

        @Override
        public int resultado(Inimigo executor, Heroi heroi) {
            Textos.printaBonito(Cor.reset + "> " + executor.getNome() + Cor.txtAmarelo(" ") + "Colocou " + Cor.txtVermelho("algo ") + "na sua pilha de compras...", 5, 2);
            return 0;
        }
    }

    /** aplica um efeito de status no aliado mais forte (procura pelo tier, se tiver mais de um igual vai no primeiro.
     * se nao tiver mais ninguem vivo, vai aplicar em si mesmo.
    ) */
    public static class AplicarEfeitoAliadoMaisForte extends Acao {
        Inimigo novoAlvo;
        public AplicarEfeitoAliadoMaisForte(Efeito efeito){
            this.efeito = efeito;
            this.agressiva = false;
        }

        @Override
        public void executar(Inimigo executor, Heroi heroi, Batalha batalha) {
            novoAlvo = executor;
            if (batalha.getInimigos().stream().anyMatch(i -> i.estaVivo()))
            {
                if (!batalha.getInimigos().isEmpty())
                    novoAlvo = batalha.getInimigos().get(0);
                
                for (Inimigo inimigo : batalha.getInimigos())
                {
                    if (inimigo.estaVivo() && inimigo.getTier() > novoAlvo.getTier())
                    {
                        novoAlvo = inimigo;
                    }
                }
            }

            efeito.adicionar(novoAlvo, batalha);
        };

        @Override
        public void anunciar(Inimigo executor, Heroi heroi) {
            Textos.printaBonito(Cor.txtAmarelo("> ") + executor.getNome() + Cor.cinza + " irá aplicar o efeito " + efeito.getNomeColorido() + Cor.cinza + " em um " + Cor.txtAmarelo("aliado") + ".", 3, 2);
        }

        @Override
        public int resultado(Inimigo executor, Heroi heroi) {
            Textos.printaBonito(Cor.reset + "> " + executor.getNome() + " aplicou " + efeito.getNomeColorido() + " em um aliado!", 5, 2); 
            return 0;
        }
    }

    /** se divide em (fator) inimigos, com a vida atual. Se livra de todos os efeitos aplicados. */
    public static class multiplicar extends Acao {
        private int fator;

        public multiplicar(int fator){
            this.fator = fator;
            this.agressiva = false;
        }

        @Override
        public void executar(Inimigo executor, Heroi heroi, Batalha batalha) {
            if (fator <= 0) {
                return;
            }

            int vidaAtual = executor.getVida();
            for (int i = 0; i < fator; i++){
                Inimigo clone = executor.criaCopia();
                clone.setVida(vidaAtual);
                clone.setVidaMax(vidaAtual);
                batalha.adicionarInimigo(clone);
            }

            executor.setRemoverDaBatalha(true);
        }

        @Override
        public void anunciar(Inimigo executor, Heroi heroi) {
            Textos.printaBonito(Cor.txtAmarelo("> ") + executor.getNome() + Cor.cinza + " irá se multiplicar em " + fator + "!", 3, 2);
        }

        @Override
        public int resultado(Inimigo executor, Heroi heroi) {
            Textos.printaBonito(Cor.reset + "> " + executor.getNome() + Cor.txtAmarelo(" se multiplicou em ") + fator + "!", 5, 2); 
            return 0;
        }
    }   
}
