package EfeitosDeStatus;

import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;
import Util.Cor;
import Util.InputHandler;
import Util.Textos;

/* heroi ganha o valor em energia quando a duraçao acabar */
public class Energizar extends Efeito {
    private int valor;
    private int valorBase;

    public Energizar(String nome, String desc, int dur, int valor){
        super(nome, desc, dur);
        this.valor = valor;
        this.valorBase = valor;
    }

    public Energizar(Energizar copiado){
        super(copiado);
        this.valor = copiado.valor;
        this.valorBase = valor;
    }

    @Override
    public void addStack(){
        this.stacks++;
        this.valor += this.valorBase;
    }

    @Override
    public void aplicar() {
        this.getAlvo().setEnergizado(true);
        if (this.getDur() == 1) 
            System.out.println("> " +this.getAlvo().getNome() + Cor.cinza + " recebeu " + Cor.amarelo + this.valor + " pontos de energia!"); Textos.sleep(300);
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
    }

    @Override
    public void onCreate() {
        this.getAlvo().setEnergizado(true);
    }

    @Override
    public Efeito criaCopia() {
        return new Energizar(this);
    }

    @Override
    public String status() {
        return Cor.amarelo + this.getNome() + " (" + this.valor + ") > " + this.getDur() + Cor.reset;   
    }

    @Override
    public void acabar() {
        if (!this.getAlvo().getPurificar()){
            if (this.getAlvo() instanceof Heroi h){
                h.setEnergiaBonus(this.valor);
                h.setEnergizado(false);
            }
            else {
                System.out.println("vc tentou dar energia para um inimigo ??");
                InputHandler.esperar();
            }
        }
    }
}
