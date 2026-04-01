package EfeitosDeStatus;

import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Batalha;
import Util.Cor;
import Util.Textos;

/*  ao aplicar sangramento em um inimigo que ja tem o efeito, reseta a duraçao e aumenta o dano.
Ao atingir 5 acumulos de sangramento, causa todo o dano restante e remove o efeito*/

public class Sangramento extends DanoConstante{
    private int stacks = 1;

    public Sangramento(String nome, String desc, int dur, int dano){
        super(nome, desc, dur, dano);
    }

    public Sangramento(DanoConstante copiado){
        super(copiado);
    }

    public int getStacks() {
        return stacks;
    }

    public void addStack(){
        this.stacks++;
        
        if (this.stacks >= 5){
            int danoAcumulado = this.getDur() * this.stacks;

            Cor.printaVermelho("O SANGRAMENTO EXPLODIU CAUSANDO >" + danoAcumulado + "< PONTOS DE DANO!!\n" );
            Textos.sleep(3000);

            this.getAlvo().receberDano(danoAcumulado);
            this.getAlvo().setSangrando(false);
            this.setDur(0);
            this.stacks = 0;
        }
    }

    @Override
    public void aplicar(){
        this.getAlvo().receberDano(this.stacks);
        
        if (this.getDur() > 1)
            this.getAlvo().setSangrando(true);
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
        this.getAlvo().setSangrando(true);
    }

    @Override
    public Efeito criaCopia() {
        return new Sangramento(this);
    }

    @Override
    public String status() {
        return (this.stacks < 4) 
        ? Cor.vermelho + this.getNome() + "!".repeat(this.stacks - 1) + " > " + this.getDur() + Cor.reset
        : Cor.vermelho + this.getNome().toUpperCase() + "!".repeat(this.stacks - 1) + " > " + this.getDur() + Cor.reset;
    }

}
