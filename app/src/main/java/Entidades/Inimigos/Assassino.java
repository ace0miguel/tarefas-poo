package Entidades.Inimigos;

import Entidades.Entidade;
import Util.Cor;
import Util.RNGHandler;
import Util.Textos;

/* inimigo com menos vida(cria com menos vida pelo amor de deus) 
dano extra baseado em vida perdida no ataque base
perde so 1/3 do dano no ataque com efeito*/
public class Assassino extends Inimigo{

    public Assassino(String nome, int vida, int dano){
        super(nome,vida,dano);
    }

    public Assassino(Assassino copiado){
        super(copiado);
    }

    @Override
    public Assassino criaCopia() {
        return new Assassino(this);
    }

    @Override
    public int getDanoAtaque(Entidade alvo) {
        return this.dano + this.getDanoExtra() + danoVidaPerdida(alvo.getVida(), alvo.getVidaMax());
    }

    @Override
    public int getDanoAtaqueEfeito(Entidade alvo) {
        return this.dano - this.dano/3 + this.getDanoExtra();
    }

    //balancear isso aqui depois!!!
    public int danoVidaPerdida(int vida, int vidaMax) {
        int vidaPerdida = vidaMax - vida;
        return vidaPerdida / 5;
    } 

    @Override
    public void escolheAcao() {
        nextAcao = RNGHandler.getGen().nextInt(2);
    }

    @Override
    public void anunciarAtaque(Entidade alvo){
        if (this.estaVivo()){
            System.out.print(Cor.amarelo + "> " + Cor.reset + this.getNome() + " ");
            Cor.setCinza();
            switch (nextAcao) {
                case 0 -> System.out.println("irá te atacar causando " + 
                (this.dano + this.getDanoExtra()) + " + (" + Cor.vermelho +(danoVidaPerdida(alvo.getVida(), alvo.getVidaMax()))
                 + Cor.cinza + ")" + " pontos de dano." );
                
                case 1 -> System.out.println("irá te atacar causando "+ getDanoAtaqueEfeito(alvo) + 
                " pontos de dano e aplicar" + Cor.txtVermelho(" Sangramento") + ".");
            }
            Textos.sleep(50);
        }
        Cor.txtReset();
    }

    @Override
    public void ataqueRealizado(Entidade alvo){
        if (this.estaVivo()){
            System.out.print(Cor.reset + "> " + this.getNome() + Cor.txtAmarelo(" "));
            Cor.setVermelho();
            switch (nextAcao) {
                case 0 -> { System.out.println("Causou "+(alvo.getDanoEfetivo(getDanoAtaque(alvo)))+" pontos de dano!" ); Textos.sleep(300); }
                case 1 -> { System.out.println("Causou "+(alvo.getDanoEfetivo(getDanoAtaqueEfeito(alvo)))+" pontos de dano e aplicou Sangramento!" ); Textos.sleep(300); }
            }
        }
        Cor.txtReset();
    }
    

}
