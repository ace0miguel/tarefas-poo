package Poderes;

import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Eventos.Batalha;
import Util.Arte;
import Util.Textos;

// se a carta for um tiro usa [stacks] vezes a mais.
// a fazer: generalizar pra funcionar pra qualquer tipo (nao deve dar mt trabalho)
public class MaosLeves extends Poder {

    int tipo;

    public MaosLeves(String nome, String desc, int tipo) {
        super(nome, desc);
        this.tipo = tipo;
    }

    public MaosLeves(Poder copiado, int tipo){
        super(copiado);
        this.tipo = tipo;
    }

    @Override
    public void aplicar() {
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
        if (carta.getTipo() == this.tipo)  {
            for(int i = 0; i < this.getStacks(); i++){         
                Textos.printaLinhaDevagar(Arte.MAISUM);
                System.out.println();
                carta.aplicarEfeito(heroi, alvo, batalha);
            } 
        }
    }

    @Override
    public Poder criaCopia() {
        return new MaosLeves(this, this.tipo);
    }

    @Override
    public String status() {
        return "Sempre que utilizar uma carta do tipo " + this.tipo + ", aplica o efeito dela mais " + this.getStacks() + " vezes.";
    }
}
