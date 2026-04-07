package EfeitosDeStatus.Instantaneos;

import Cartas.Carta;
import EfeitosDeStatus.Efeito;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;
import Util.Cor;
import Util.InputHandler;

/** adiciona a carta passada a mao do heroi */
public class PuxaCartaEsp extends PuxaCarta {
    private Carta carta;

    public PuxaCartaEsp(String nome, String desc, int valor, Carta carta){
        super(nome, desc, valor);
        this.carta = carta;
    }

    public PuxaCartaEsp(PuxaCartaEsp copiado){
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
            h.getMaoAtual().addCartaEsp(this.carta.criaCopia());
        } else {
            Cor.printaAmarelo("erro -> tentou dar cartas pra algo nao heroi");
            InputHandler.esperar();
        }
    }

    @Override
    public Efeito criaCopia() {
        return new PuxaCartaEsp(this);
    }

    @Override
    public String status() {
        return "";    
    }

    @Override
    public void acabar() {
    }
    
}
