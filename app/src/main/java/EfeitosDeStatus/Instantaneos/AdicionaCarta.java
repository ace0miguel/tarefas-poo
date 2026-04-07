package EfeitosDeStatus.Instantaneos;

import Cartas.Carta;
import EfeitosDeStatus.Efeito;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;
import Util.Cor;
import Util.InputHandler;

/** adiciona a carta passada na pilha de compras do heroi */
public class AdicionaCarta extends Efeito {
    protected Carta carta;

    public AdicionaCarta(String nome, String desc, int dur, Carta carta){
        super(nome, desc, dur);
        this.carta = carta;
    }

    public AdicionaCarta(AdicionaCarta copiado){
        super(copiado);
        this.carta = copiado.carta;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    @Override
    public void addStack(){
        this.stacks++;

        this.onCreate();
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
            h.getPilhaCompra().addCartaPilha(this.carta.criaCopia());
        } else {
            Cor.printaAmarelo("erro -> tentou dar cartas pra algo nao heroi");
            InputHandler.esperar();
        }
    }

    @Override
    public Efeito criaCopia() {
        return new AdicionaCarta(this);
    }

    @Override
    public String status() {
        return "";    
    }

    @Override
    public void acabar() {
    }
    
}
