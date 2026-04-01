package EfeitosDeStatus;

import Util.Cor;

/* Causa dano igual a duraçao restante;
ao colocar veneno denovo soma a duraçao;
caso um inimigo morra envenenado o veneno espalha para todos os outros com a mesma duraçao de quando ele morreu */
public class Veneno extends DanoConstante{
    public Veneno(String nome, String desc, int dur, int dano){ // a variavel dano nao faz nada ok nao se preocupar
        super(nome, desc, dur, dano);
    }

    public Veneno(Veneno copiado){
        super(copiado);
    }

    @Override
    public void aplicar(){
        this.getAlvo().receberDano(this.getDur());
        this.getAlvo().setEnvenenado(true);
    }

    @Override
    public Efeito criaCopia() {
        return new Veneno(this);
    }

    @Override
    public String status() {
        return Cor.verde + " [" + this.getNome() + " - (" +  this.getDur() + " Pontos de dano/Rodadas)]" + Cor.reset; 
    }
}
