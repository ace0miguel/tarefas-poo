package Entidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Telas.Eventos.Batalha;
import Util.Acao;
import Util.Cor;
import Util.RNGHandler;
import Util.Textos;

/* inimigo base. */
public class Inimigo extends Entidade{

    protected int dano; // dano base
    protected Acao nextAcao;
    /** nível de dificuldade do inimigo, influencia a recompensa por vencer a batalha. */
    protected int tier = 0;
    protected Acao[] acoesArray; // array de acoes :(
    protected List<Acao> acoes; // arraylist de acoes! :)

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
        this.tier = copiado.tier;
    }

    public Inimigo criaCopia() {
        return new Inimigo(this);
    }

    /** sorteia um numero de 0 à (quantidade de ações - 1)*/
    public void escolheAcao(){
        nextAcao = acoes.get(RNGHandler.getGen().nextInt(acoes.size()));
    }

    /** imprime a intenção do inimigo (próxima ação a ser realizada) */
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

    /** imprime o resultado da ação do inimigo (dano causado, efeitos aplicados, etc.) */
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

    /** retorna a recompensa por matar o inimigo. valor: 3^tier */
    public int getRecompensa() {
        return (int) Math.pow(3,tier);
    }

    public void setTier(int tier) {
        this.tier = tier;
    }
}
