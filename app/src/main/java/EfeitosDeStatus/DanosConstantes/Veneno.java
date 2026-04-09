package EfeitosDeStatus.DanosConstantes;

import Cartas.Carta;
import EfeitosDeStatus.Efeito;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;
import Util.Cor;
import Util.Textos;

/** Causa dano igual a duraçao restante; ao colocar veneno denovo soma a duraçao;
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
        this.getAlvo().receberDanoDireto(this.getDur());
        
        if (this.getDur() > 1)
            this.getAlvo().setEnvenenado(true);

        System.out.println("> " +this.getAlvo().getNome() + Cor.cinza  + " sofreu " + this.getDur() + " pontos de dano de " + this.getNomeColorido() + "!"); Textos.sleep(300);
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
    }

    @Override
    public void onCreate() {
        this.getAlvo().setEnvenenado(true);
    }

    @Override
    public Efeito criaCopia() {
        return new Veneno(this);
    }

    @Override
    public String status() {
        return Cor.verdeEscuro + this.getNome() + " > " + this.getDur() + Cor.reset; 
    }
}
