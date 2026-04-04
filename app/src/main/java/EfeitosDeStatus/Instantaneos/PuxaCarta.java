package EfeitosDeStatus.Instantaneos;

import Cartas.Carta;
import EfeitosDeStatus.Efeito;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;
import Util.Cor;
import Util.InputHandler;

public class PuxaCarta extends Efeito {

    private int valor;
    public PuxaCarta(String nome, String desc, int dur, int valor){
        super(nome, desc, dur);
        this.valor = valor;
    }

    public PuxaCarta(PuxaCarta copiado){
        super(copiado);
        this.valor = copiado.valor;
    }

    @Override
    public void addStack(){
        this.stacks++;

        if (this.getAlvo() instanceof Heroi h) {
            h.getMaoAtual().addCartas(h.getPilhaCompra(), h.getPilhaDescarte(), this.valor);
        } else {
            Cor.printaAmarelo("erro -> tentou dar cartas pra algo nao heroi");
            InputHandler.esperar();
        }
    }

    @Override
    public void aplicar() {
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
    }

    @Override
    public void onCreate() {
        if (this.getAlvo() instanceof Heroi h) {
            h.getMaoAtual().addCartas(h.getPilhaCompra(), h.getPilhaDescarte(), this.valor);
        } else {
            Cor.printaAmarelo("erro -> tentou dar cartas pra algo nao heroi");
            InputHandler.esperar();
        }
    }

    @Override
    public Efeito criaCopia() {
        return new PuxaCarta(this);
    }

    @Override
    public String status() {
        return "";    
    }

    @Override
    public void acabar() {
    }
    
}
