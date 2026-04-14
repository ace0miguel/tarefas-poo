package Entidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Telas.Eventos.Batalha;
import Util.Acao;
import Util.InputHandler;
import Util.RNGHandler;
import Visual.Cor;
import Visual.Textos;

/* inimigo base. */
public class Inimigo extends Entidade{

    protected int dano; // dano base
    protected Acao nextAcao;
    /** nível de dificuldade do inimigo, influencia a recompensa por vencer a batalha. */
    protected int tier = 0;
    protected Acao[] acoesArray; // array de acoes :(
    protected List<Acao> acoes; // arraylist de acoes! :)

    private Acao acaoMeiaVida; // ação que o inimigo realiza quando chega na metade da vida pela primeira vez

    protected boolean removerDaBatalha = false; // flag para remover um inimigo da batalha ativar efeitos ao morrer

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
        this.acaoMeiaVida = copiado.acaoMeiaVida;
        this.meiaVida = copiado.meiaVida; // garante que nao ative dnv o meia vida
    }

    public Inimigo criaCopia() {
        return new Inimigo(this);
    }

    /** sorteia um numero de 0 à (quantidade de ações - 1)*/
    public void escolheAcao(){
        nextAcao = acoes.get(RNGHandler.getGen().nextInt(acoes.size()));
    }

    /** imprime a intenção do inimigo (próxima ação a ser realizada) */
    public void anunciarIntencao(Heroi alvo){
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
    public void resultadoAcao(Heroi alvo){
        if (this.estaVivo()){
            nextAcao.resultado(this, alvo);
        Textos.sleep(200);
            Cor.reset();
        }
    }

    @Override
    public void onMeiaVida(Entidade executor, Heroi heroi, Batalha batalha) {
        if (this.acaoMeiaVida != null) {
            if (executor instanceof Inimigo i) {
                this.acaoMeiaVida.executar(i, heroi, batalha);
                System.out.println();
                Textos.printaBonito(Cor.txtAmareloClaro(this.getNome() + " chegou na metade da vida!" + "\n"), 5,2 );
                this.acaoMeiaVida.resultado(i, heroi);
                InputHandler.esperar();
            }    
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

    public int getDanoEfetivo(){
        return this.dano + ((this.dano * this.getDanoExtra()) / 100);
    }

    public int getFracaoDanoEfetivo(int divisor){
        int danoBase = this.dano / divisor;
        return danoBase + ((danoBase * this.getDanoExtra()) / 100);
    }

    /** retorna a recompensa por matar o inimigo. valor atual: 3^tier */
    public int getRecompensa() {
        return (int) Math.pow(3,tier);
    }

    public Acao getAcaoMeiaVida() {
        return this.acaoMeiaVida;
    }
    public boolean getRemoverDaBatalha() {
        return removerDaBatalha;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public void setAcaoMeiaVida(Acao acaoMeiaVida) {
        this.acaoMeiaVida = acaoMeiaVida;
    }

    public void setRemoverDaBatalha(boolean removerDaBatalha) {
        this.removerDaBatalha = removerDaBatalha;
    }
}
