package Subscribers;

import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;

public interface BatalhaSubscriber {

    int PRIORIDADE_DANO_CONSTANTE = 0;
    int PRIORIDADE_PADRAO = 1;
    int PRIORIDADE_BAIXISSIMA = 100;

    /** chamado quando um batalha começa */
    default public void onBattleStart(Batalha batalha, Heroi heroi){};

    /** chamado quando uma batalha termina */
    default public void onBattleEnd(Batalha batalha, Heroi heroi){};

    /** chamado quando a instancia é adicionada a batalha */
    default public void onCreate(Batalha batalha, Heroi heroi){};

    /** chamado sempre que a instancia vai ser removida da batalha */
    default public void onRemove(Batalha batalha, Heroi heroi){};

    /** chamado no inicio de cada rodada */
    default public void onRoundStart(Batalha batalha, Heroi heroi){};

    /** chamado sempre que uma carta é usada */
    default public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha){};

    /** chamado quando o alvo é atacado */
    default public void onReceivedHit(Batalha batalha, Heroi heroi, Entidade atacante, int danoRecebido){};

    /** chamado quando QUALQUER INIMIGO morre */
    default public void onDeath(Batalha batalha, Entidade alvo){};

    /** adiciona um acumulo. retorna true se o acumulo novo foi absorvido */
    default public boolean addStack(Batalha batalha, BatalhaSubscriber novo){
        return false;
    }

    /** string pra ser exibida no fim da rodada */
    default public String getMsgFimRodada(Batalha batalha, Heroi heroi){
        return "";
    };

    /** Menor prioridade age primeiro. padrão: 1 */
    default public int getPrioridade(){
        return PRIORIDADE_PADRAO;
    }   

    /** se true, será removido na proxima limpeza */
    default public boolean getRemover(){
        return false;
    }

    /** retorna o alvo */
    default public Entidade getAlvo(){
        return null;
    }
}
