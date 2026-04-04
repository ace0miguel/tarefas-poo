package Entidades;

import EfeitosDeStatus.Efeito;
import Telas.Eventos.Batalha;
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

    public Inimigo(Inimigo copiado){
        super(copiado);
        this.dano = copiado.dano;
    }

    // aplica seu dano base
    public void atacar(Heroi alvo){
        alvo.receberDano(this.dano + this.getDanoExtra());
    }

    // aplica metade do dano base + um efeito
    public void atacarEfeito(Heroi alvo, Batalha batalha, Efeito efeito){
        alvo.receberDano(this.dano / 2 + this.getDanoExtra());
        efeito.adicionar(alvo, batalha);
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

    public void anunciarAtaque(){
        if (this.estaVivo()){
            System.out.print(Cor.amarelo + "> " + Cor.reset + this.getNome() + " ");
            Cor.setCinza();
            switch (nextAcao) {
                case 0 -> System.out.println("irá te atacar causando "+(this.dano + this.getDanoExtra()) + " pontos de dano." );
                case 1 -> System.out.println("irá te atacar causando "+(this.dano / 2 + this.getDanoExtra())+" pontos de dano e te deixar sangrando.");
                case 2 -> System.out.println("está prestes a realizar um PACTO SINISTRO!");
            }
            Textos.sleep(50);
        }
        Cor.txtReset();
    }

    public void ataqueRealizado(Entidade heroi){
        if (this.estaVivo()){
            System.out.print(Cor.reset + "> " + this.getNome() + Cor.txtAmarelo(" "));
            Cor.setVermelho();
            switch (nextAcao) {
                case 0 -> { System.out.println("Causou "+(heroi.getDanoEfetivo(this.dano + this.getDanoExtra()))+" pontos de dano!" ); Textos.sleep(300); }
                case 1 -> { System.out.println("Causou "+(heroi.getDanoEfetivo(this.dano / 2 + this.getDanoExtra()))+" pontos de dano e te deixou sangrando!" ); Textos.sleep(300); }
                case 2 -> { System.out.println("REALIZOU UM PACTO SINISTRO! Está mais forte, porém o preço foi pago..." ); Textos.sleep(300); }
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

    public Inimigo criaCopia() {
        return new Inimigo(this);
    }
}
