package Subscribers.EfeitosDeStatus.Instantaneos;

import Entidades.Heroi;
import Subscribers.EfeitosDeStatus.Efeito;
import Telas.Eventos.Batalha;

/** adiciona escudo ao alvo */
public class Escudo extends Instantaneo {
    private int valor;
    
    public Escudo(String nome, String desc, int valor){
        super(nome, desc);
        this.valor = valor;
    }

    public Escudo(Escudo copiado){
        super(copiado);
        this.valor = copiado.valor;
    }

    @Override
    public void onCreate(Batalha batalha, Heroi heroi) {
        this.getAlvo().ganharEscudo(valor);
    }

    @Override
    public Efeito criaCopia() {
        return new Escudo(this);
    }
}
