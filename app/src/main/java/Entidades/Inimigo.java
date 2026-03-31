package Entidades;

import EfeitosDeStatus.DanoConstante;
import EfeitosDeStatus.Efeito;
import Telas.Batalha;
import Util.RNGHandler;


public class Inimigo extends Entidade{

    private int dano;
    private int nextAcao;

    Efeito sangramento = new DanoConstante("Sangramento", "Causa 1 de dano por rodada ao alvo", 3, 1);

    public Inimigo(String nome, int vida, int dano){
        super(nome, vida);
        this.dano = dano;

    }

    public void atacar(Heroi alvo){
        alvo.receberDano(this.dano + this.getDanoExtra());
    }

    public void atacarEfeito(Heroi alvo, Batalha batalha){ // falta generalizar, no momento ele só aplica sangramento
        alvo.receberDano(this.dano - 2 + this.getDanoExtra());
        Efeito e = sangramento.criaCopia();
        e.setAlvo(alvo);
        batalha.adicionarEfeito(e);
    }

    public void escolheAcao(){ // no momento simplificado para 0 = ataque 1 = ataque com efeito(menos dano mas aplica um status)
        nextAcao = RNGHandler.getGen().nextInt(2);
    }
    
    @Override
    public String status(){
        return (getEscudo() != 0) 
        ?""+this.getNome()+"("+getVida() +"/"+this.getVidaMax()+" de vida) ("+this.getEscudo()+" de escudo)" 
        : ""+this.getNome()+" ("+this.getVida()+"/"+this.getVidaMax()+" de vida)";
    }

    public void anunciarAtaque(){
        System.out.print(" "+this.getNome()+" ");
        System.out.println((nextAcao == 0) 
        ? "irá te atacar causando "+this.dano+" pontos de dano" 
        : "irá te atacar causando 1 ponto de dano e te deixar sangrando"); 
    }

    public int getNextAcao() {
        return nextAcao;
    }
}
