package Subscribers.EfeitosDeStatus.Latentes;

import Entidades.Entidade;
import Entidades.Heroi;
import Subscribers.BatalhaSubscriber;
import Subscribers.EfeitosDeStatus.Efeito;
import Telas.Eventos.Batalha;
import Visual.Cor;

/** guarda o dano recebido pela entidade alvo durante sua duração, e no final aplica uma porcentagem desse
 * dano em dano direto.
 */
public class EcoaDano extends Efeito {
    private int porcento;
    private int danoAcumulado = 0;

    public EcoaDano(String nome, String desc, int dur, int porcento){
        super(nome, desc, dur);
        this.porcento = porcento;
    }

    public EcoaDano(EcoaDano copiado){
        super(copiado);
        this.porcento = copiado.porcento;
    }

    private int getTaxa(){
        return (this.porcento * this.stacks) / 100;
    }

    @Override
    public boolean addStack(Batalha batalha, BatalhaSubscriber novo){
        if (!acumulaEfeito(novo))
            return false;

        this.stacks++;
        this.setDur(((Efeito)novo).getDur());
        return true;
    }

    @Override
    public Efeito criaCopia() {
        return new EcoaDano(this);
    }

    @Override
    public void onReceivedHit(Batalha batalha, Heroi heroi, Entidade atacante, int danoRecebido) {
        this.danoAcumulado += danoRecebido;
    }

    @Override
    public void onRemove(Batalha batalha, Heroi heroi) {
        int danoFinal = (int) (this.danoAcumulado * this.getTaxa());
        this.getAlvo().receberDanoDireto(danoFinal);
    }

    @Override
    public String getMsgFimRodada(Batalha batalha, Heroi heroi) {
        return (this.getDur() == 1) 
        ? Cor.txtAmareloClaro(this.getAlvo().getNome()) + " recebeu " + Cor.txtVermelho(String.valueOf(danoAcumulado * this.getTaxa())) + " pontos de dano acumulados!" 
        : "";
    }
}
