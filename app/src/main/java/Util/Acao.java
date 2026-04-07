package Util;

import Cartas.Carta;
import EfeitosDeStatus.Efeito;
import Entidades.Entidade;
import Entidades.Heroi;
import Entidades.Inimigo;
import Telas.Eventos.Batalha;

public abstract class Acao {
    protected Efeito efeito;
    protected Carta carta;
    
    /** realiza a açao */
    public void executar(Inimigo executor, Entidade alvo, Batalha batalha){};
    
    /** exibe no turno do heroi */
    public void anunciar( Inimigo executor, Entidade alvo){};
    
    /** exibe no turno do inimigo */
    public void resultado(Inimigo executor, Entidade alvo){};

    /** retorna o valor passado ou 0 se for menor q 0 */
    public int validarDano(int dano){
        return Math.max(dano,0);
    }
    
    /** Causa o dano base */
    public static class Atacar extends Acao {
        @Override
        public void executar(Inimigo executor, Entidade alvo, Batalha batalha){
            alvo.receberDano(executor.getDano() + executor.getDanoExtra());
        }

        @Override
        public void anunciar(Inimigo executor, Entidade alvo){
            System.out.println(Cor.amarelo + "> " + Cor.reset + executor.getNome() + Cor.cinza + " irá te atacar causando " + (executor.getDano() + executor.getDanoExtra()) + " pontos de dano.");
        }

        @Override
        public void resultado(Inimigo executor, Entidade alvo) {
            System.out.println(Cor.reset + "> " + executor.getNome() + Cor.txtAmarelo(" ") + Cor.vermelho + "Causou " + validarDano((executor.getDano() + executor.getDanoExtra()) - alvo.getResistencia()) + " pontos de dano!");
        }
    }

    /** Causa metade do dano base mas aplica um efeito */
    public static class AtacarEfeito extends Acao {
        public AtacarEfeito(Efeito efeito){
            this.efeito = efeito;
        }

        @Override
        public void executar(Inimigo executor, Entidade alvo, Batalha batalha){
            alvo.receberDano(executor.getDano()/2 + executor.getDanoExtra());
            efeito.adicionar(alvo, batalha);
        };

        @Override
        public void anunciar(Inimigo executor, Entidade alvo) {
            System.out.println(Cor.amarelo + "> " + Cor.reset + executor.getNome() + Cor.cinza + " irá te atacar causando " + (executor.getDano()/2 + executor.getDanoExtra()) + " pontos de dano e aplicar " + efeito.getNomeColorido()  + ".");
        }

        @Override
        public void resultado(Inimigo executor, Entidade alvo) {
            System.out.println(Cor.reset + "> " + executor.getNome() + Cor.txtAmarelo(" ") + Cor.vermelho + "Causou " + validarDano((executor.getDano()/2 + executor.getDanoExtra()) - alvo.getResistencia()) + " pontos de dano e aplicou " + efeito.getNomeColorido() + "!");
        }
    }

    /** Causa o dano base + dano extra baseado na vida perdida do alvo */
    public static class AtacarVidaPerdida extends Acao {
        public int danoVidaPerdida(Entidade alvo) {
            int vidaPerdida = alvo.getVidaMax() - alvo.getVida();
            return vidaPerdida / 5;
        }

        @Override
        public void executar(Inimigo executor, Entidade alvo, Batalha batalha) {
            alvo.receberDano(executor.getDano() + executor.getDanoExtra() + danoVidaPerdida(alvo));
        }

        @Override
        public void anunciar(Inimigo executor, Entidade alvo) {
            System.out.println(Cor.amarelo + "> " + Cor.reset + executor.getNome() + Cor.cinza +  " irá te atacar causando " + (executor.getDano() + executor.getDanoExtra()) + " + (" + Cor.vermelho + danoVidaPerdida(alvo) + Cor.cinza  + ") pontos de dano.");
        }

        @Override
        public void resultado(Inimigo executor, Entidade alvo) {
            System.out.println(Cor.reset + "> " + executor.getNome() + Cor.txtAmarelo(" ") + Cor.vermelho + "Causou " + validarDano((executor.getDano() + executor.getDanoExtra() + danoVidaPerdida(alvo)) - alvo.getResistencia()) + " pontos de dano!");
        }
    }

    /** Aplica um efeito a si mesmo */
    public static class ReceberEfeito extends Acao {
        public ReceberEfeito(Efeito efeito){
            this.efeito = efeito;
        }

        @Override
        public void executar(Inimigo executor, Entidade alvo, Batalha batalha) {
            efeito.adicionar(executor, batalha);
        };

        @Override
        public void anunciar(Inimigo executor, Entidade alvo) {
            System.out.println(Cor.txtAmarelo("> ") + executor.getNome() + Cor.cinza + " irá utilizar " + efeito.getNomeColorido() + ".");
        }

        @Override
        public void resultado(Inimigo executor, Entidade alvo) {
            System.out.println(Cor.reset + "> " + executor.getNome() + " utilizou " + efeito.getNomeColorido() + "!"); 
        }
    }

    /** Adiciona uma carta a pilha de compras do alvo (tem que ser o heroi) */
    public static class AdicionarCarta extends Acao {
        public AdicionarCarta(Carta carta) {
            this.carta = carta.criaCopia();
        }

        @Override
        public void executar(Inimigo executor, Entidade alvo, Batalha batalha) {
            ((Heroi) alvo).getPilhaCompra().addCartaPilha(carta);
            ((Heroi) alvo).getPilhaCompra().shuffle();
        }

        @Override
        public void anunciar(Inimigo executor, Entidade alvo) {
            System.out.println(Cor.amarelo + "> " + Cor.reset + executor.getNome() + Cor.cinza + " irá colocar uma " + Cor.txtVermelho("carta ") + Cor.cinza + "na sua pilha de compras.");
        }

        @Override
        public void resultado(Inimigo executor, Entidade alvo) {
            System.out.println(Cor.reset + "> " + executor.getNome() + Cor.txtAmarelo(" ") + "Colocou " + Cor.txtVermelho("algo ") + "na sua pilha de compras...");
        }
    }
}   
