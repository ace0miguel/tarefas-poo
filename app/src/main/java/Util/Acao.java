package Util;

import Cartas.Carta;
import EfeitosDeStatus.Efeito;
import Entidades.Heroi;
import Entidades.Inimigo;
import Telas.Eventos.Batalha;
import Visual.Cor;

/** classe abstrata que representa uma ação que pode ser realizada por um inimigo */
public abstract class Acao {
    protected Efeito efeito;
    protected Carta carta;
    
    /** realiza a açao */
    public void executar(Inimigo executor, Heroi heroi, Batalha batalha){};
    
    /** exibe no turno do heroi */
    public void anunciar( Inimigo executor, Heroi heroi){};
    
    /** exibe no turno do inimigo */
    public void resultado(Inimigo executor, Heroi heroi){};

    /** retorna o valor passado ou 0 se for menor q 0 */
    public int validarDano(int dano){
        return Math.max(dano,0);
    }
    
    /** Causa o dano base */
    public static class Atacar extends Acao {
        @Override
        public void executar(Inimigo executor, Heroi heroi, Batalha batalha){
            heroi.receberDano(executor.getDanoEfetivo());
        }

        @Override
        public void anunciar(Inimigo executor, Heroi heroi){
            System.out.println(Cor.amarelo + "> " + Cor.reset + executor.getNome() + Cor.cinza + " irá te atacar causando " + (executor.getDanoEfetivo()) + " pontos de dano.");
        }

        @Override
        public void resultado(Inimigo executor, Heroi heroi) {
            System.out.println(Cor.reset + "> " + executor.getNome() + Cor.txtAmarelo(" ") + Cor.vermelho + "Causou " + heroi.getDanoRecebido(executor.getDanoEfetivo()) + " pontos de dano!");
        }
    }

    /** Causa metade do dano base mas aplica um efeito */
    public static class AtacarEfeito extends Acao {
        public AtacarEfeito(Efeito efeito){
            this.efeito = efeito;
        }

        @Override
        public void executar(Inimigo executor, Heroi heroi, Batalha batalha){
            heroi.receberDano(executor.getFracaoDanoEfetivo(2));
            efeito.adicionar(heroi, batalha);
        };

        @Override
        public void anunciar(Inimigo executor, Heroi heroi) {
            System.out.println(Cor.amarelo + "> " + Cor.reset + executor.getNome() + Cor.cinza + " irá te atacar causando " + (executor.getFracaoDanoEfetivo(2)) + " pontos de dano e aplicar " + efeito.getNomeColorido()  + ".");
        }

        @Override
        public void resultado(Inimigo executor, Heroi heroi) {
            System.out.println(Cor.reset + "> " + executor.getNome() + Cor.txtAmarelo(" ") + Cor.vermelho + "Causou " + heroi.getDanoRecebido(executor.getFracaoDanoEfetivo(2)) + " pontos de dano e aplicou " + efeito.getNomeColorido() + "!");
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
            heroi.receberDano(executor.getDanoEfetivo() + danoVidaPerdida(heroi));
        }

        @Override
        public void anunciar(Inimigo executor, Heroi heroi) {
            System.out.println(Cor.amarelo + "> " + Cor.reset + executor.getNome() + Cor.cinza +  " irá te atacar causando " + (executor.getDanoEfetivo()) + " + (" + Cor.vermelho + danoVidaPerdida(heroi) + Cor.cinza  + ") pontos de dano.");
        }

        @Override
        public void resultado(Inimigo executor, Heroi heroi) {
            System.out.println(Cor.reset + "> " + executor.getNome() + Cor.txtAmarelo(" ") + Cor.vermelho + "Causou " + heroi.getDanoRecebido(executor.getDanoEfetivo() + danoVidaPerdida(heroi)) + " pontos de dano!");
        }
    }

    /** Aplica um efeito a si mesmo */
    public static class ReceberEfeito extends Acao {
        public ReceberEfeito(Efeito efeito){
            this.efeito = efeito;
        }

        @Override
        public void executar(Inimigo executor, Heroi heroi, Batalha batalha) {
            efeito.adicionar(executor, batalha);
        };

        @Override
        public void anunciar(Inimigo executor, Heroi heroi) {
            System.out.println(Cor.txtAmarelo("> ") + executor.getNome() + Cor.cinza + " irá utilizar " + efeito.getNomeColorido() + ".");
        }

        @Override
        public void resultado(Inimigo executor, Heroi heroi) {
            System.out.println(Cor.reset + "> " + executor.getNome() + " utilizou " + efeito.getNomeColorido() + "!"); 
        }
    }

    /** Adiciona uma carta a pilha de compras do heroi (tem que ser o heroi) */
    public static class AdicionarCarta extends Acao {
        public AdicionarCarta(Carta carta) {
            this.carta = carta.criaCopia();
        }

        @Override
        public void executar(Inimigo executor, Heroi heroi, Batalha batalha) {
            heroi.getPilhaCompra().addCartaPilha(carta);
            heroi.getPilhaCompra().shuffle();
        }

        @Override
        public void anunciar(Inimigo executor, Heroi heroi) {
            System.out.println(Cor.amarelo + "> " + Cor.reset + executor.getNome() + Cor.cinza + " irá colocar uma " + Cor.txtVermelho("carta ") + Cor.cinza + "na sua pilha de compras.");
        }

        @Override
        public void resultado(Inimigo executor, Heroi heroi) {
            System.out.println(Cor.reset + "> " + executor.getNome() + Cor.txtAmarelo(" ") + "Colocou " + Cor.txtVermelho("algo ") + "na sua pilha de compras...");
        }
    }

    /** aplica um efeito de status no aliado mais forte (procura pelo tier, se tiver mais de um igual vai no primeiro.
     * se nao tiver mais ninguem vivo, vai aplicar em si mesmo.
    ) */
    public static class AplicarEfeitoAliadoMaisForte extends Acao {
        Inimigo novoAlvo;
        public AplicarEfeitoAliadoMaisForte(Efeito efeito){
            this.efeito = efeito;
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
            System.out.println(Cor.txtAmarelo("> ") + executor.getNome() + Cor.cinza + " irá aplicar o efeito " + efeito.getNomeColorido() + Cor.cinza + " em um " + Cor.txtAmarelo("aliado") + ".");
        }

        @Override
        public void resultado(Inimigo executor, Heroi heroi) {
            System.out.println(Cor.reset + "> " + executor.getNome() + " aplicou " + efeito.getNomeColorido() + " em um aliado!"); 
        }
    }

    /** se divide em (fator) inimigos, com a vida atual */
    public static class multiplicar extends Acao {
        private int fator;

        public multiplicar(int fator){
            this.fator = fator;
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
            System.out.println(Cor.txtAmarelo("> ") + executor.getNome() + Cor.cinza + " irá se multiplicar em " + fator + "!");
        }

        @Override
        public void resultado(Inimigo executor, Heroi heroi) {
            System.out.println(Cor.reset + "> " + executor.getNome() + Cor.txtAmarelo(" se multiplicou em ") + fator + "!"); 
        }
    }   
}
