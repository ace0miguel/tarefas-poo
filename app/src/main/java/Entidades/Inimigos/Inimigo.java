package Entidades.Inimigos;

import EfeitosDeStatus.Efeito;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;
import Util.Cor;
import Util.RNGHandler;
import Util.Textos;
import static Util.Moldes.*;

/* inimigo base.
caso queira mudar algum dos danos dos ataques padroes(normal, com efeito) basta dar override em:
getDanoAtaque, getDanoAtaqueEfeito. */
public class Inimigo extends Entidade{

    protected int dano;
    protected int nextAcao;
    protected int tier = 0; // 0 : randola, 1 : elite, 2 : boss (ainda nao ta sendo usado pra nada, talvez nunca seja)  

    public Inimigo(String nome, int vida, int dano){
        super(nome, vida);
        this.dano = dano;
    }

    public Inimigo(Inimigo copiado){
        super(copiado);
        this.dano = copiado.dano;
    }

    public Inimigo criaCopia() {
        return new Inimigo(this);
    }

    // aplica seu dano base
    public void atacar(Heroi alvo){
        alvo.receberDano(getDanoAtaque(alvo));
    }

    // aplica metade do dano base + um efeito (por enquanto é sempre sangramento!)
    public void atacarEfeito(Heroi alvo, Batalha batalha, Efeito efeito){
        alvo.receberDano(getDanoAtaqueEfeito(alvo));
        efeito.adicionar(alvo, batalha);
    }

    // adiciona um efeito a si mesmo
    public void receberEfeito(Batalha batalha, Efeito efeito){
        Efeito e = efeito.criaCopia();
        e.setDur(e.getDur() + 1); // resolve o bug de 1 rodada ser comida instantaneamente nos efeitos dos inimigos
        e.setAlvo(this);
        batalha.adicionarEfeito(e);
    }

    /*  sorteia a proxima açao, por enquanto é so aleatorio msm (inclusive ele pode se matar com pacto sinistro)
    o batalha que lida com o que cada açao faz dentro da funçao turnoInimigo. lista de açoes ja programadas:
    0 - ataque (base)
    1 - ataque (com efeito)
    2 - aplicar efeito em si mesmo*/

    public void escolheAcao(){
        nextAcao = RNGHandler.getGen().nextInt(3);
    }

    // anuncia o ataque que sera realizado, efeitos aplicados etc
    public void anunciarAtaque(Entidade alvo){
        if (this.estaVivo()){
            System.out.print(Cor.amarelo + "> " + Cor.reset + this.getNome() + " ");
            Cor.setCinza();
            switch (nextAcao) {
                case 0 -> System.out.println("irá te atacar causando " + (getDanoAtaque(alvo)) + " pontos de dano." );
                case 1 -> System.out.println("irá te atacar causando " + (getDanoAtaqueEfeito(alvo)) + 
                " pontos de dano e aplicar" + Cor.txtVermelho(" sangramento") + ".");
                case 2 -> System.out.println("está prestes a realizar um PACTO SINISTRO!");
            }
            Textos.sleep(50);
        }
        Cor.txtReset();
    }

    //anuncia o dano causado e efeitos aplicados, etc
    public void ataqueRealizado(Entidade alvo){
        if (this.estaVivo()){
            System.out.print(Cor.reset + "> " + this.getNome() + Cor.txtAmarelo(" "));
            Cor.setVermelho();
            switch (nextAcao) {
                case 0 -> { System.out.println("Causou "+(alvo.getDanoEfetivo(getDanoAtaque(alvo)))+" pontos de dano!" ); Textos.sleep(300); }
                case 1 -> { System.out.println("Causou "+(alvo.getDanoEfetivo(getDanoAtaqueEfeito(alvo)))+" pontos de dano e aplicou Sangramento!" ); Textos.sleep(300); }
                case 2 -> { System.out.println("REALIZOU UM PACTO SINISTRO! Está mais forte, porém o preço foi pago..." ); Textos.sleep(300); }
            }
        }
        Cor.txtReset();
    }
    
    // aqui vc bota oq cada açao faz msm
    public void realizarAcao(Heroi alvo, Batalha batalha){
                switch (nextAcao){
                    case 0 -> this.atacar(alvo); // ataque base
                    case 1 -> this.atacarEfeito(alvo, batalha, sangramento); // ataque com efeio
                    case 2 -> { // pacto sinistro 
                        this.receberDano(2);
                        this.receberEfeito(batalha, pactoSinistro);
                    }
                }
    }

    // getters e setters ---------

    //dano causado no ataque base
    public int getDanoAtaque(Entidade alvo){
        return this.dano + this.getDanoExtra();
    }

    //causado no ataque com efeito
    public int getDanoAtaqueEfeito(Entidade alvo){
        return this.dano/2 + this.getDanoExtra();
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
