package EfeitosDeStatus.Instantaneos;

import Cartas.Carta;
import EfeitosDeStatus.Efeito;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;

public class Escudo extends Efeito {

    private int valor;
    public Escudo(String nome, String desc, int dur, int valor){
        super(nome, desc, dur);
        this.valor = valor;
    }

    public Escudo(Escudo copiado){
        super(copiado);
        this.valor = copiado.valor;
    }

    @Override
    public void addStack(){
        this.stacks++;
        this.getAlvo().ganharEscudo(valor);
    }
    
    @Override
    public void aplicar() {
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
    }

    @Override
    public void onCreate() {
        this.getAlvo().ganharEscudo(valor);
        this.setDur(0);
    }

    @Override
    public Efeito criaCopia() {
        return new Escudo(this);
    }

    @Override
    public String status() {
        return "";    
    }

    @Override
    public void acabar() {
    }
}
