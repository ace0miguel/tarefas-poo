package EfeitosDeStatus;

import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Batalha;
import Util.Cor;
import Util.InputHandler;

public class GanhaEnergia extends Efeito {

    private int valor;
    public GanhaEnergia(String nome, String desc, int dur, int valor){
        super(nome, desc, dur);
        this.valor = valor;
        this.insta = true;
    }

    public GanhaEnergia(GanhaEnergia copiado){
        super(copiado);
        this.valor = copiado.valor;
        this.insta = true;
    }

    @Override
    public void aplicar() {
        if (this.getAlvo() instanceof Heroi heroi) {
        heroi.ganhaEnergia(valor);
        } else {
        Cor.printaAmarelo("erro -> tentou dar energia pra algo nao heroi");
        InputHandler.esperar();
        }
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
    }

    @Override
    public Efeito criaCopia() {
        return new GanhaEnergia(this);
    }

    @Override
    public String status() {
        return " [" + this.getNome() + this.valor + " pontos de energia (pode ultrapassar o limite!) ]"; 
        
    }
}
