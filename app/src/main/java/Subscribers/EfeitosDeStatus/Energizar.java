package Subscribers.EfeitosDeStatus;

import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;
import Util.InputHandler;
import Visual.Cor;
import Visual.Textos;

/** ganha o valor em energia quando a duraçao acabar */
public class Energizar extends Efeito {
    private int valor;
    private int valorBase;

    public Energizar(String nome, String desc, int dur, int valor){
        super(nome, desc, dur);
        this.valor = valor;
        this.valorBase = valor;
    }

    public Energizar(Energizar copiado){
        super(copiado);
        this.valor = copiado.valor;
        this.valorBase = valor;
    }

    @Override
    public void addStack(){
        this.stacks++;
        this.valor += this.valorBase;
    }

    @Override
    public String getMsgFimRodada(Batalha batalha, Heroi heroi) {
        return ("> " +this.getAlvo().getNome() + Cor.cinza + " recebeu " + Cor.amarelo + this.valor + " pontos de energia!");
    }

    @Override
    public void onRoundStart(Batalha batalha , Heroi heroi ) {   
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
    }

    @Override
    public void onCreate(Batalha batalha, Heroi heroi) {
        this.getAlvo().setEnergizado(true);
    }

    @Override
    public Efeito criaCopia() {
        return new Energizar(this);
    }

    @Override
    public String status() {
        return Cor.amarelo + this.getNome() + " (" + this.valor + ") > " + this.getDur() + Cor.reset;   
    }

    @Override
    public void onRemove(Batalha batalha, Heroi heroi) {
        if (!this.getAlvo().getPurificar()){
            if (this.getAlvo() instanceof Heroi h){
                h.setEnergiaBonus(this.valor);
                h.setEnergizado(false);
            }
            else {
                System.out.println("vc tentou dar energia para um inimigo ??");
                InputHandler.esperar();
            }
        }
    }
}
