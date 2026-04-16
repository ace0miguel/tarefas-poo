package Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Cartas.Carta;
import Entidades.Heroi;
import Subscribers.Itens.Item;
import static Util.Moldes.listaCartasMoldes;
import static Util.Moldes.listaItensMoldes;
import Visual.Cor;
import Visual.Textos;

/** Aqui ficam todos os metodos pra dar recompensas ao jogador (cartas, itens, etc) */
public class Recompensas {
    private static final List<Item> poolItensUnicos = new ArrayList<>();

    /** reseta o pool global de itens unicos da run */
    public static void resetarPoolItens() {
        poolItensUnicos.clear();
        poolItensUnicos.addAll(listaItensMoldes);
    }

    public static int getQuantidadeItensDisponiveis() {
        return poolItensUnicos.size();
    }

    /** concede um item especifico caso ainda esteja disponivel no pool global */
    public static boolean ganharItemEsp(Item item, Heroi heroi) {
        if (!poolItensUnicos.remove(item)) {
            return false;
        }

        heroi.ganhaItem(item);
        return true;
    }

    /** concede um item aleatorio unico */
    public static boolean ganharItemAleatorio(Heroi heroi) {
        if (poolItensUnicos.isEmpty()) {
            return false;
        }

        int idx = RNGHandler.getGen().nextInt(poolItensUnicos.size());
        int last = poolItensUnicos.size() - 1;

        Item escolhido = poolItensUnicos.get(idx);
        poolItensUnicos.set(idx, poolItensUnicos.get(last));
        poolItensUnicos.remove(last);

        heroi.ganhaItem(escolhido);
        return true;
    }

    /** retorna uma carta aleatoria
     * @param raridade a raridade minima da carta a ser retornada (1 - comum, 2 - incomum, 3 - rara, 4 - especial)
     */
    public static Carta cartaAleatoria(int raridade){
        List<Carta> listaRecompensas = new ArrayList<>();
        for(Carta c : listaCartasMoldes)
        {
            if (c.getRaridade() >= raridade) 
            {           
                for(int i = 0; i < 5 - c.getRaridade(); i++)
                {
                    listaRecompensas.add(c);
                }
            }
        }

        Collections.shuffle(listaRecompensas);
        return listaRecompensas.get(0);
    }

    /** retorna cartas aleatorias
     * @param raridade a raridade minima das cartas a serem retornadas (1 - comum, 2 - incomum, 3 - rara, 4 - especial)
     * @param quantidade a quantidade de cartas a ser retornada
     */
    public static List<Carta> cartasAleatorias(int raridade, int quantidade){
        List<Carta> listaRecompensas = new ArrayList<>();
        for(Carta c : listaCartasMoldes)
        {
            if (c.getRaridade() >= raridade) 
            {
                for(int i = 0; i < 5 - c.getRaridade(); i++)
                {
                    listaRecompensas.add(c);
                }
            }
        }

        Collections.shuffle(listaRecompensas);
        return listaRecompensas.subList(0, Math.min(quantidade, listaRecompensas.size()));
    }

    /** exibe um menu de seleçao com as cartas passadas e retorna a carta escolhida */
    public static Carta menuCartas(Carta... cartas){
        List<String> opcoes = new ArrayList<>();
        for (Carta c : cartas) 
        {
            opcoes.add(c.toString());
        }
        int escolha = InputHandler.selecionar(opcoes, Cor.txtAmareloClaro("Escolha uma carta:"));
        return cartas[escolha];
    }

    /** cria um menu de seleção para o usuario escolher uma carta
     * @param raridade a raridade minima das cartas a serem exibidas
     * @param quantidade a quantidade de cartas a serem exibidas
     */
    public static Carta escolheCarta(int raridade, int quantidade){
        List<Carta> cartas = cartasAleatorias(raridade, quantidade);
        Carta[] cartasArray = new Carta[cartas.size()];
        for (int i = 0; i < cartas.size(); i++) 
        {
            cartasArray[i] = cartas.get(i);
        }
        return menuCartas(cartasArray);
    }

    /** concede varias cartas ao heroi e imprime uma mensagem bonitinha mostrando as cartas ganhas
     * @param raridade a raridade minima das cartas a serem concedidas
     * @param quantidade a quantidade de cartas a serem concedidas
     * @param heroi o heroi que recebera as cartas
     */
    public static void ganharCartas(int raridade, int quantidade, Heroi heroi){
        System.out.println(Cor.txtAmareloClaro("Você ganhou:")); Textos.sleep(500);
        System.out.println();

        List<Carta> recompensa = Recompensas.cartasAleatorias(raridade, quantidade);
        
        for (Carta carta : recompensa) {
            Textos.printaBonito(carta.recompensa(), 5, 2);
            heroi.addCartaInventario(carta);
            Textos.sleep(500);
        }

        System.out.println();
        System.out.println("Novas cartas adicionadas ao inventário! Visite o deckBuilder para equipá-las.");
        InputHandler.esperar();
}

    /** concede uma carta ao heroi e imprime uma mensagem bonitinha mostrando a carta ganha
     * @param raridade a raridade minima das carta
     * @param heroi o heroi que recebera a carta
     */
    public static void ganharCarta(int raridade, Heroi heroi){
        System.out.println(Cor.txtAmareloClaro("Você ganhou:")); Textos.sleep(500);
        System.out.println();

        Carta recompensa = Recompensas.cartaAleatoria(raridade);
        
        Textos.printaBonito(recompensa.recompensa(), 5, 2);
        heroi.addCartaInventario(recompensa);
        Textos.sleep(500);

        System.out.println();
        System.out.println("Nova carta adicionada ao inventário! Visite o deckBuilder para equipá-la.");
        InputHandler.esperar();
    }

    /** concede uma carta especifica ao heroi e imprime uma mensagem bonitinha mostrando a carta ganha 
     * @param carta a carta a ser concedida
     * @param heroi o heroi que recebera a carta
    */
    public static void ganharCartaEsp(Carta carta, Heroi heroi){
        System.out.println(Cor.txtAmareloClaro("Você ganhou:")); Textos.sleep(500);
        System.out.println();
   
        Textos.printaBonito(carta.recompensa(), 5, 2);
        heroi.addCartaInventario(carta);
        Textos.sleep(500);

        System.out.println();
        System.out.println("Nova carta adicionada ao inventário! Visite o deckBuilder para equipá-la.");
        InputHandler.esperar();
    }

    /** concede uma escolha de cartas ao heroi, com uma raridade minima
     * @param raridade a raridade minima das cartas a serem escolhidas
     * @param quantidade a quantidade de opcoes
     */
    public static void ganharOpcoes(int raridade, int quantidade, Heroi heroi){
        Carta cartaEscolhida = escolheCarta(raridade, quantidade);
        ganharCartaEsp(cartaEscolhida, heroi);
    }
}
