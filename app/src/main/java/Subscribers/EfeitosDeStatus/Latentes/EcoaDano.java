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

    private double getTaxa(){
        return calcularRetornoDecrescente(porcento, stacks) / 100.0;
    }

    private int danoFinal(){
        return (int) (this.danoAcumulado * this.getTaxa());
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
        return (this.getDur() <= 1) 
        ? "> " + (this.getAlvo().getNome()) + Cor.cinza + " recebeu " + Cor.txtVermelho(String.valueOf(this.danoFinal())) + Cor.cinza+ " pontos de dano acumulados!" 
        : "> " + (this.getAlvo().getNome()) + Cor.cinza + " está com dano acumulado: " + Cor.txtRoxo(String.valueOf(this.danoFinal()));
    }

    @Override
    public String status() {
        String retorno =  Cor.txtRoxo(this.getNome());

        if (stacks > 1)
            retorno += "x" + Cor.amareloClaro + stacks + Cor.reset;

        retorno += " (" + Cor.txtRoxo(String.valueOf(this.danoFinal())) + ") > " + Cor.roxo + this.getDur() + Cor.reset;

        return retorno;
    }
}
