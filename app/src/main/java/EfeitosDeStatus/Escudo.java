package EfeitosDeStatus;

import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Batalha;

public class Escudo extends Efeito {

    private int valor;
    public Escudo(String nome, String desc, int dur, int valor){
        super(nome, desc, dur);
        this.valor = valor;
        this.insta = true;
    }

    public Escudo(Escudo copiado){
        super(copiado);
        this.valor = copiado.valor;
        this.insta = true;
    }

    @Override
    public void aplicar() {
        this.getAlvo().ganharEscudo(valor);
        this.setDur(0);
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
    }

    @Override
    public Efeito criaCopia() {
        return new Escudo(this);
    }

    @Override
    public String status() {
        return " [" + this.getNome() + this.valor + " pontos de escudo]";    
    }

    @Override
    public void acabar() {
    }
}
