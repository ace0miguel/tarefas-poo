package Subscribers.EfeitosDeStatus.DanosConstantes;

import java.util.List;

import Entidades.Entidade;
import Entidades.Heroi;
import Entidades.Inimigo;
import Subscribers.BatalhaSubscriber;
import Subscribers.EfeitosDeStatus.Efeito;
import Telas.Eventos.Batalha;
import Util.InputHandler;
import Visual.Arte;
import Visual.Cor;
import Visual.Textos;

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
    public void onRoundStart(Batalha batalha, Heroi heroi){
        this.getAlvo().receberDanoDireto(this.getDur());
        
        if (this.getDur() > 1)
            this.getAlvo().setEnvenenado(true);

        passaTurno();
    }

    @Override
    public void onDeath(Batalha batalha, Entidade alvo) {
        if (alvo != this.getAlvo())
            return;

        boolean espalhou = false;
        List<Inimigo> listaInimigos = batalha.getInimigos();
        for (Inimigo i : listaInimigos){
            if (i != alvo){
                adicionar(i, batalha);
                if (!espalhou){
                    Textos.printaBonito(Cor.txtVerdeEscuro(Arte.TOXICO), 1 ,1);
                    espalhou = true;
                }
            }
        }

    }
    
    @Override
    public boolean addStack(Batalha batalha, BatalhaSubscriber novo) {
        if (!acumulaEfeito(novo))
            return false;

        if (novo instanceof Veneno v) {
            this.setDur(this.getDur() + (v).getDur());
        } else {
            System.out.println("erro: tentou stackar veneno com algo que nao e veneno");
            InputHandler.esperar("pressione ENTER para admitir o erro e prometer que vai arrumar");
        }

        return true;
    }

    @Override
    public void onCreate(Batalha batalha, Heroi heroi) {
        this.getAlvo().setEnvenenado(true);
    }

    @Override
    public void onRemove(Batalha batalha, Heroi heroi) {
        this.getAlvo().setEnvenenado(false);
    }

    @Override
    public Efeito criaCopia() {
        return new Veneno(this);
    }

    @Override
    public String status() {
        return Cor.verdeEscuro + this.getNome() + " > " + this.getDur() + Cor.reset; 
    }

    @Override
    public String getMsgFimRodada(Batalha batalha, Heroi heroi){
        return "> " +this.getAlvo().getNome() + Cor.cinza  + " sofreu " + (this.getDur() + 1) + " pontos de dano de " + this.getNomeColorido() + "!";
    }
}
