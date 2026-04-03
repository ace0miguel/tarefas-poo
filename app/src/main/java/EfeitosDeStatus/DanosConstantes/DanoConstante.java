package EfeitosDeStatus.DanosConstantes;
import Cartas.Carta;
import EfeitosDeStatus.Efeito;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;
import Util.Cor;
import Util.Textos;

/* efeitos de dano constante, dano depende do efeito especifico
ignoram resistencias(inclusive escudo) */
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

    public int getDano() {
        return dano;
    }

    @Override
    public int getPrioridade() {
        return 0;
    }
    
    @Override
    public void aplicar(){
        this.getAlvo().receberDanoDireto(dano);
 
        System.out.println("> " +this.getAlvo().getNome() + Cor.cinza + " sofreu " + this.dano + " pontos de dano de " + this.getNomeColorido() + "!"); Textos.sleep(300);
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
    }

    @Override
    public Efeito criaCopia() {
        return new DanoConstante(this);
    }

    @Override
    public String status() {
        return Cor.amarelo + this.getNome() + Cor.reset + " > " + this.getDur() ; 
    }

    @Override
    public void acabar() {
    }
    
}
