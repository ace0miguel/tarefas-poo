package EfeitosDeStatus;

/* Causa dano igual a duraçao restante, (feito) 
ao colocar veneno denovo soma a duraçao
caso um inimigo morra envenenado o veneno espalha para todos os outros com a mesma duraçao de quando ele morreu (falta fazer) */
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
    }

    @Override
    public Efeito criaCopia() {
        return new Veneno(this);
    }

    @Override
    public String status() {
        return " [" + this.getNome() + " - (" +  this.getDur() + " Pontos de dano/Rodadas)]"; 
    }
}
