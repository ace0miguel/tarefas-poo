package EfeitosDeStatus.Instantaneos;

import EfeitosDeStatus.Efeito;

/** adiciona escudo ao alvo */
public class Escudo extends Efeito {

    private int valor;
    public Escudo(String nome, String desc, int valor){
        super(nome, desc, 0);
        this.valor = valor;
    }

    public Escudo(Escudo copiado){
        super(copiado);
        this.valor = copiado.valor;
    }

    @Override
    public void addStack(){
        this.onCreate();
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
}
