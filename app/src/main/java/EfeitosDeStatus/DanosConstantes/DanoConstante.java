package EfeitosDeStatus.DanosConstantes;
import Cartas.Carta;
import EfeitosDeStatus.Efeito;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Batalha;
import Util.Cor;

/* efeitos de dano constante, dano depende do efeito especifico */
public class DanoConstante extends Efeito {
    private int dano;
    public DanoConstante(String nome, String desc, int dur, int dano){
        super(nome, desc, dur);
        this.dano = dano;
    }

    public DanoConstante(DanoConstante copiado){
        super(copiado);
        this.dano = copiado.dano;
    }

    @Override
    public void aplicar(){
        this.getAlvo().receberDano(dano);
        
        if (this.getDur() > 1)
            this.getAlvo().setSangrando(true);

        Cor.printaVermelho(this.getAlvo().getNome() + " sofreu " + this.dano + " pontos de dano de " + this.getNome() + "!\n\n");
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
        this.getAlvo().setSangrando(true);
    }

    @Override
    public Efeito criaCopia() {
        return new DanoConstante(this);
    }

    @Override
    public String status() {
        return Cor.amarelo + this.getNome() + Cor.reset + " > " + this.getDur() ; 
        
    }
    
}
