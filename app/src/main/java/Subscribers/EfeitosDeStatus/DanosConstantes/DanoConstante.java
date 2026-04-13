package Subscribers.EfeitosDeStatus.DanosConstantes;
import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
import Subscribers.EfeitosDeStatus.Efeito;
import Telas.Eventos.Batalha;
import Visual.Cor;
import Visual.Textos;

/** efeitos de dano constante, dano depende do efeito especifico
causam dano direto (ignoram resistencias) */
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
    public void onRoundStart(Batalha batalha, Heroi heroi){
        this.getAlvo().receberDanoDireto(dano);
 
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
    public String getMsgFimRodada(Batalha batalha, Heroi heroi){
         return "> " +this.getAlvo().getNome() + Cor.cinza  + " sofreu " + this.getDur() + " pontos de dano de " + this.getNomeColorido() + "!";
       }

    @Override
    public void onRemove() {
    }

    @Override
    public void onCreate(Batalha batalha, Heroi heroi) {
    }  
}
