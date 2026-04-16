package batalhaListeners.efeitos.danosConstantes;
import batalhaListeners.batalhaListener;
import batalhaListeners.efeitos.Efeito;
import entidades.Heroi;
import telas.eventos.combate.Batalha;
import visual.Cor;

/** efeitos de dano constante, dano depende do efeito especifico
causam dano direto (ignoram resistencias) 
stacks somam a duraçao e multiplicam o dano*/
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
    public boolean addStack(Batalha batalha, batalhaListener novo) {
        if (!acumulaEfeito(novo))
            return false;

        this.stacks++;
             
        this.setDur(this.getDur() + ((Efeito) novo).getDur());

        return true;
    }

    @Override
    public int getPrioridade() {
        return 0;
    }
    
    @Override
    public void onRoundStart(Batalha batalha, Heroi heroi){
        batalha.causarDanoDireto(this.getAlvo(), dano*stacks, null);
        this.passaTurno();
    }

    @Override
    public Efeito criaCopia() {
        return new DanoConstante(this);
    }

    @Override
    public String status() {
        return Cor.amarelo + this.getNome() + Cor.reset + " > " + Cor.amarelo + this.getDur() ; 
    }

    @Override
    public String getMsgFimRodada(Batalha batalha, Heroi heroi){
        return "> " +this.getAlvo().getNome() + Cor.cinza  + " sofreu " + (this.dano * this.stacks) + " pontos de dano de " + this.getNomeColorido() + "!";
    }
}
