package Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Cartas.Carta;
import static Util.Moldes.listaCartasMoldes;

/** Aqui ficam todos os metodos pra dar recompensas ao jogador (cartas, dinheiro, etc) */
public class Recompensas {

    // parei pra analisa nenhuma dessas funçao deve ta certa tem q da uma refazida 

    /**  retorna uma carta aleatoria
     * @param raridade a raridade minima da carta a ser retornada (1 - comum, 2 - incomum, 3 - rara, 4 - especial)
     */
    public Carta cartaAleatoria(int raridade){
        List<Carta> listaRecompensas = new ArrayList<>();
        for(Carta c : listaCartasMoldes)
            {
            for(int i = 0; i < 5 - c.getRaridade(); i++)
                {
                if (c.getRaridade() >= raridade)
                    listaRecompensas.add(c);
            }
        }

        Collections.shuffle(listaRecompensas);
        return listaRecompensas.get(0);
    }

    /** retorna cartas aleatorias
     * @param raridade a raridade minima de uma das cartas a serem retornadas (1 - comum, 2 - incomum, 3 - rara, 4 - especial)
     * @param quantidade a quantidade de cartas a ser retornada
     */
    public List<Carta> cartasAleatorias(int raridade, int quantidade){
        List<Carta> listaRecompensas = new ArrayList<>();
        boolean cartaMinima = false;

        List<Carta> listaCartas = listaCartasMoldes;
        Collections.shuffle(listaCartas);
        
        for(Carta c : listaCartas)
            {
            for(int i = 0; i < 5 - c.getRaridade(); i++)
                {
               if (c.getRaridade() >= raridade || !cartaMinima){
                    listaRecompensas.add(c);
                    cartaMinima = true;
                }
            }
        }

        return listaRecompensas.subList(0, quantidade);
    }
}
