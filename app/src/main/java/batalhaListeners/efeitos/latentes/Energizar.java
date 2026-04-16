package batalhaListeners.efeitos.latentes;

import batalhaListeners.batalhaListener;
import batalhaListeners.efeitos.Efeito;
import entidades.Heroi;
import telas.eventos.combate.Batalha;
import util.InputHandler;
import visual.Cor;

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
    public boolean addStack(Batalha batalha, batalhaListener novo){
        if (!acumulaEfeito(novo))
            return false;

        this.stacks++;
        this.valor += valorBase;
        return true;
    }

    @Override
    public String getMsgFimRodada(Batalha batalha, Heroi heroi) {
        return ("> " +this.getAlvo().getNome() + Cor.cinza + " recebeu " + Cor.amarelo + this.valor + " pontos de energia!");
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
                h.setEnergiaBonus(h.getEnergiaBonus() + this.valor);
                h.setEnergizado(false);
            }
            else {
                System.out.println("vc tentou dar energia para um inimigo ??");
                InputHandler.esperar();
            }
        }
    }
}
