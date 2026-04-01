package Poderes;

import Cartas.Carta;
import Entidades.Entidade;
import Entidades.Heroi;
import Telas.Batalha;
import Util.Textos;

// se a carta for um tiro usa [stacks] vezes a mais.
// a fazer: generalizar pra funcionar pra qualquer tipo (nao deve dar mt trabalho)
public class MaosLeves extends Poder {
    public MaosLeves(String nome, String desc) {
        super(nome, desc);
    }

    public MaosLeves(Poder copiado){
        super(copiado);
    }

    @Override
    public void aplicar() {
    }

    @Override
    public void onHit(Carta carta, Heroi heroi, Entidade alvo, Batalha batalha) {
        if (carta.getTipo() == 1)  {
            for(int i = 0; i < this.getStacks(); i++){         
                System.out.println("MAIS UM!" );
                System.out.println();
                Textos.sleep(200);
                carta.aplicarEfeito(heroi, alvo, batalha);
            } 
        }
    }

    @Override
    public Poder criaCopia() {
        return new MaosLeves(this);
    }

    @Override
    public String status() {
        return "Sempre que utilizar um tiro, atire novamente.";
    }
}
