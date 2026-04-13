package Subscribers.EfeitosDeStatus.Instantaneos;

import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
import Subscribers.EfeitosDeStatus.Efeito;
import Telas.Eventos.Batalha;
import Util.InputHandler;
import Visual.Cor;

/** adiciona energia instantaneamente */
public class GanhaEnergia extends Efeito {

    private int valor;
    public GanhaEnergia(String nome, String desc, int valor){
        super(nome, desc, 0);
        this.valor = valor;
    }

    public GanhaEnergia(GanhaEnergia copiado){
        super(copiado);
        this.valor = copiado.valor;
    }

    @Override
    public void addStack(){
        this.stacks++;

        if (this.getAlvo() instanceof Heroi h) {
            h.ganhaEnergia(valor);
        } else {
            Cor.printaAmarelo("erro -> tentou dar energia pra algo nao heroi");
            InputHandler.esperar();
        }

    }

    @Override
    public void onRoundStart() {
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
    }

    @Override
    public void onCreate() {
        if (this.getAlvo() instanceof Heroi h) {
            h.ganhaEnergia(valor);
        } else {
            Cor.printaAmarelo("erro -> tentou dar energia pra algo nao heroi");
            InputHandler.esperar();
        }
    }

    @Override
    public Efeito criaCopia() {
        return new GanhaEnergia(this);
    }

    @Override
    public String status() {
        return "";    
    }

    @Override
    public void onRemove() {
    }
    
}
