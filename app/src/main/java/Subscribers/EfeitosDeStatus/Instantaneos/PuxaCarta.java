package Subscribers.EfeitosDeStatus.Instantaneos;

import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
import Subscribers.EfeitosDeStatus.Efeito;
import Telas.Eventos.Batalha;
import Util.InputHandler;
import Visual.Cor;

/** puxa da pilha de compra valor cartas e adiciona a mao do heroi */
public class PuxaCarta extends Efeito {

    protected int valor;
    public PuxaCarta(String nome, String desc, int valor){
        super(nome, desc, 0);
        this.valor = valor;
    }

    public PuxaCarta(PuxaCarta copiado){
        super(copiado);
        this.valor = copiado.valor;
    }

    @Override
    public void addStack(){
        this.stacks++;

        this.onCreate();
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
    public void onRemove() {
    }
    
}
