package Subscribers.EfeitosDeStatus.DanosConstantes;

import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
import Subscribers.EfeitosDeStatus.Efeito;
import Telas.Eventos.Batalha;
import Util.InputHandler;
import Visual.Arte;
import Visual.Cor;
import Visual.Textos;

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
    public boolean getResetDur() {
        return true;
    }

    @Override
    public void addStack(){
        this.stacks++;
        
        if (this.stacks >= 5){
            int danoAcumulado = this.getDur() * this.stacks * this.getDano();

            Textos.printaLinhaDevagar("\n" + Arte.SANGUE + Cor.amarelo + this.getAlvo().getNome() + " recebeu " + danoAcumulado + " pontos de dano!" + Cor.reset);

            InputHandler.esperar();

            this.getAlvo().receberDanoDireto(danoAcumulado);
            this.getAlvo().setSangrando(false);
            this.setDur(0);
            this.stacks = 0;
        }
    }

    @Override
    public void onRoundStart(){
        int danoEfetivo = this.stacks * this.getDano();

        this.getAlvo().receberDanoDireto(danoEfetivo);
        
        if (this.getDur() > 1)
            this.getAlvo().setSangrando(true);

    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {   
    }

    @Override
    public void onCreate() {
        this.getAlvo().setSangrando(true);
    }

    @Override
    public Efeito criaCopia() {
        return new Sangramento(this);
    }

    @Override
    public String status() {
        return (this.stacks < 4) 
        ? Cor.vermelho + this.getNome() + "!".repeat(Math.max(this.stacks - 1, 0)) + " > " + this.getDur() + Cor.reset
        : Cor.vermelho + this.getUpperNome() + "!".repeat(Math.max(this.stacks - 1, 0)) + " > " + this.getDur() + Cor.reset; 
    }

}
