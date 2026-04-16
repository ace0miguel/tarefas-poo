package batalhaListeners.poderes;

import cartas.Carta;
import entidades.Entidade;
import entidades.Heroi;
import telas.eventos.combate.Batalha;
import visual.Arte;
import visual.Textos;

// se a carta for um tiro usa [stacks] vezes a mais.
// a fazer: generalizar pra funcionar pra qualquer tipo (nao deve dar mt trabalho)
public class MaosLeves extends Poder {

    int tipo;

    public MaosLeves(String nome, String desc, int tipo) {
        super(nome, desc);
        this.tipo = tipo;
    }

    public MaosLeves(MaosLeves copiado){
        super(copiado);
        this.tipo = copiado.tipo;
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
        return new MaosLeves(this);
    }

    @Override
    public String status() {
        return "Sempre que utilizar uma carta do tipo " + this.tipo + ", aplica o efeito dela mais " + this.getStacks() + " vezes.";
    }
    
    @Override
    public int getPrioridade() {
        return 0;
    }
}
