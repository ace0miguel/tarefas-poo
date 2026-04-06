package Entidades;

import EfeitosDeStatus.Efeito;
import Telas.Eventos.Batalha;
import Util.Acao;
import Util.Cor;
import static Util.Moldes.pactoSinistro;
import static Util.Moldes.sangramento;
import Util.RNGHandler;
import Util.Textos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* inimigo base.
caso queira mudar algum dos danos dos ataques padroes(normal, com efeito) basta dar override em:
getDanoAtaque, getDanoAtaqueEfeito. */
public class Inimigo extends Entidade{

    protected int dano;
    protected Acao nextAcao;
    protected int tier = 0; // 0 : randola, 1 : elite, 2 : boss (ainda nao ta sendo usado pra nada, talvez nunca seja)  
    protected Acao[] acoesArray; // array de acoes :(
    protected List<Acao> acoes; // arraylist de acoes !!

    public  Inimigo(String nome, int vida, int dano, Acao... acoes){
        super(nome, vida);
        this.acoesArray = acoes;
        this.acoes = new ArrayList<>(Arrays.asList(acoesArray));
        this.dano = dano;
    }

    public Inimigo(Inimigo copiado){
        super(copiado);
        this.dano = copiado.dano;
        this.acoesArray = copiado.acoesArray;
        this.acoes = new ArrayList<>(Arrays.asList(acoesArray));
    }

    public Inimigo criaCopia() {
        return new Inimigo(this);
    }

    /** sorteia um numero de 0 à (quantidade de ações - 1)*/
    public void escolheAcao(){
        nextAcao = acoes.get(RNGHandler.getGen().nextInt(acoes.size()));
    }

    public void anunciarIntencao(Entidade alvo){
        if (this.estaVivo()){
            nextAcao.anunciar(this, alvo);
            Textos.sleep(50);
        }     
    }

    public void realizarAcao(Heroi alvo, Batalha batalha){
        if (this.estaVivo())
            nextAcao.executar(this, alvo, batalha);
    }

    public void resultadoAcao(Entidade alvo){
        if (this.estaVivo()){
            nextAcao.resultado(this, alvo);
            Textos.sleep(50);
            Cor.txtReset();
        }
    }
    
    // getters e setters ---------

    public int getDano() {
        return dano;
    }

    public Acao getNextAcao() {
        return nextAcao;
    }
    
    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }
}
