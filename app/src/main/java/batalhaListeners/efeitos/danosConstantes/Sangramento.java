package batalhaListeners.efeitos.danosConstantes;

import batalhaListeners.batalhaListener;
import batalhaListeners.efeitos.Efeito;
import entidades.Heroi;
import telas.eventos.combate.Batalha;
import util.InputHandler;
import visual.Arte;
import visual.Cor;
import visual.Textos;

/**  ao aplicar sangramento em um inimigo que ja tem o efeito, reseta a duraçao e aumenta o dano.
dano causado : dano base * numero de stacks
Ao atingir 5 acumulos de sangramento, causa todo o dano restante e remove o efeito */

public class Sangramento extends DanoConstante{

    public Sangramento(String nome, String desc, int dur, int dano){
        super(nome, desc, dur, dano);
    }

    public Sangramento(DanoConstante copiado){
        super(copiado);
    }

    @Override
    public boolean addStack(Batalha batalha, batalhaListener novo){
        if (!acumulaEfeito(novo))
            return false;

        this.stacks++;
        this.setDur(((Sangramento) novo).getDur());

        if (this.stacks >= 5){
            int danoAcumulado = this.getDur() * this.stacks * this.getDano();

            Textos.printaLinhaDevagar("\n" + Arte.SANGUE + Cor.amarelo + this.getAlvo().getNome() + " recebeu " + danoAcumulado + " pontos de dano!" + Cor.reset);

            InputHandler.esperar();

            batalha.causarDanoDireto(this.getAlvo(), danoAcumulado, null);
            this.getAlvo().setSangrando(false);
            this.setDur(0);
            this.stacks = 0;
        }

        return true;
    }

    @Override
    public void onRoundStart(Batalha batalha, Heroi heroi){
        int danoEfetivo = this.stacks * this.getDano();

        batalha.causarDanoDireto(this.getAlvo(), danoEfetivo, null);
        
        if (this.getDur() > 1)
            this.getAlvo().setSangrando(true);

        passaTurno();
    }

    @Override
    public void onCreate(Batalha batalha, Heroi heroi) {
        this.getAlvo().setSangrando(true);
    }

    @Override
    public void onRemove(Batalha batalha, Heroi heroi) {
        this.getAlvo().setSangrando(false);
    }

    @Override
    public Efeito criaCopia() {
        return new Sangramento(this);
    }

    @Override
    public String status() {
        return (this.stacks < 4) 
        ? Cor.vermelho + this.getNome() + "!".repeat(Math.max(this.stacks - 1, 0)) + Cor.reset + " > " + Cor.vermelho + this.getDur() + Cor.reset
        : Cor.vermelho + this.getUpperNome() + "!".repeat(Math.max(this.stacks - 1, 0)) + Cor.reset + " > " + Cor.vermelho + this.getDur() + Cor.reset; 
    }

    @Override
    public String getMsgFimRodada(Batalha batalha, Heroi heroi){
        return "> " +this.getAlvo().getNome() + Cor.cinza  + " sofreu " + this.getDano() * this.stacks + " pontos de dano de " + this.getNomeColorido() + "!";
    }

}
