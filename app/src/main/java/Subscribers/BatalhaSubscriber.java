package Subscribers;

import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
import Subscribers.EfeitosDeStatus.Efeito;
import Telas.Eventos.Batalha;

public interface BatalhaSubscriber {
    /** chamado quando o efeito é adicionado a batalha */
    default public void onCreate(Batalha batalha, Heroi heroi){};

    /** chamado sempre que o efeito vai ser removido da batalha */
    default public void onRemove(Batalha batalha, Heroi heroi){};

    /** chamado no inicio de cada rodada */
    default public void onRoundStart(Batalha batalha, Heroi heroi){};

    /** chamado sempre que uma carta é usada */
    default public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha){};

    /** chamado quando o heroi é atacado */
    default public void onReceivedHit(Batalha batalha, Heroi heroi, Entidade atacante, int danoRecebido){};

    /** chamado quando o alvo morre */
    default public void onDeath(Batalha batalha, Entidade alvo){};

    /** adiciona um acumulo. retorna true se não for o primeiro */
    default public boolean addStack(Batalha batalha, BatalhaSubscriber novo){
        return false;
    }

    /** string pra ser exibida no fim da rodada */
    default public String getMsgFimRodada(Batalha batalha, Heroi heroi){
        return "";
    };

    /** padrao: 1. Menor prioridade age primeiro */
    default public int getPrioridade(){
        return 1;
    }   

    default public boolean getRemover(){
        return false;
    }
}
