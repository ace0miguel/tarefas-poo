package Entidades;

import EfeitosDeStatus.Efeito;
import Telas.Batalha;
import Util.Cor;
import Util.RNGHandler;
import Util.Textos;


public class Inimigo extends Entidade{

    private int dano;
    private int nextAcao;
    private int tier = 0; // 0 : randola, 1 : elite, 2 : boss

    public Inimigo(String nome, int vida, int dano){
        super(nome, vida);
        this.dano = dano;
    }

    // aplica seu dano base
    public void atacar(Heroi alvo){
        alvo.receberDano(this.dano + this.getDanoExtra());
    }

    // aplica metade do dano base + um efeito
    public void atacarEfeito(Heroi alvo, Batalha batalha, Efeito efeito){
        alvo.receberDano(this.dano / 2 + this.getDanoExtra());
        Efeito e = efeito.criaCopia();
        e.setAlvo(alvo);
        batalha.adicionarEfeito(e);
    }

    // adiciona um efeito a si mesmo
    public void receberEfeito(Batalha batalha, Efeito efeito){
        Efeito e = efeito.criaCopia();
        e.setDur(e.getDur() + 1); // resolve o bug de 1 rodada ser comida instantaneamente nos efeitos dos inimigos
        e.setAlvo(this);
        batalha.adicionarEfeito(e);
    }

    public void escolheAcao(){
        nextAcao = RNGHandler.getGen().nextInt(3);
    }
    
    @Override
    public String status(){
        return (getEscudo() != 0) 
        ?""+this.getNome()+ " | " + Textos.desenharBarraVida(this.getVida(), this.getVidaMax())+" "+Cor.azul+" ("+this.getEscudo()+" de escudo)" + Cor.reset 
        : ""+this.getNome()+ " | " + Textos.desenharBarraVida(this.getVida(), this.getVidaMax()) + Cor.reset;

    }

    public void anunciarAtaque(){
        Cor.setCinza();
        if (this.estaVivo()){
            System.out.print("> " + this.getNome() + " ");
            switch (nextAcao) {
                case 0 -> System.out.println("irá te atacar causando "+(this.dano + this.getDanoExtra())+" pontos de dano" );
                case 1 -> System.out.println("irá te atacar causando "+(this.dano / 2 + this.getDanoExtra())+" e te deixar ferido");
                case 2 -> System.out.println("está prestes a realizar um PACTO SINISTRO");
            }
        }
        Cor.txtReset();
    }

    public int getNextAcao() {
        return nextAcao;
    }
    
    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }
}
