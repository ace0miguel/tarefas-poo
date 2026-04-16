package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import batalhaListeners.itens.Item;
import cartas.Carta;
import entidades.Heroi;
import visual.Cor;
import visual.Textos;

import static fabricas.FabricaCartas.listaCartasMoldes;
import static fabricas.FabricaItens.listaItensMoldes;

/** Aqui ficam todos os metodos pra dar recompensas ao jogador (cartas, itens, etc) */
public class Recompensas {
    private static final List<Item> poolItensUnicos = new ArrayList<>();
    private static final List<Carta> poolTodasCartas = new ArrayList<>();

    static {
        resetarPoolCartas();
        resetarPoolItens();
    }

    /** concede um item especifico caso ainda esteja disponivel no pool global */
    public static boolean ganharItemEsp(Item item, Heroi heroi) {
        if (!poolItensUnicos.remove(item)) {
            return false;
        }

        heroi.addItem(item);
        
        imprimeGanhoDeItem(item);
        return true;
    }

    /** concede um item aleatorio unico, se houverem disponiveis na pool global */
    public static boolean ganharItemAleatorio(Heroi heroi) {
        if (poolItensUnicos.isEmpty()) {
            return false;
        }

        int sorteado = RNGHandler.getGen().nextInt(poolItensUnicos.size());

        Item escolhido = poolItensUnicos.get(sorteado);
        poolItensUnicos.remove(escolhido);

        heroi.addItem(escolhido);

        imprimeGanhoDeItem(escolhido);
        return true;
    }

    /** concede varias cartas ao heroi e imprime uma mensagem bonitinha mostrando as cartas ganhas
     * @param raridade a raridade minima das cartas a serem concedidas
     * @param quantidade a quantidade de cartas a serem concedidas
     * @param heroi o heroi que recebera as cartas
     * @param pool a lista de cartas a ser considerada para a escolha
     */
    public static void ganharCartas(int raridade, int quantidade, Heroi heroi, List<Carta> pool){
        System.out.println(Cor.txtAmareloClaro("Você ganhou:")); Textos.sleep(500);
        System.out.println();

        List<Carta> recompensa = Recompensas.cartasAleatorias(raridade, quantidade, pool);
        
        for (Carta carta : recompensa) {
            Textos.printaBonito(carta.recompensa(), 5, 2);
            heroi.addCartaInventario(carta);
            Textos.sleep(500);
        }

        System.out.println();
        System.out.println("Novas cartas adicionadas ao inventário! Visite o deckBuilder para equipá-las.");
        InputHandler.esperar();
    }

    /** concede varias cartas ao heroi e imprime uma mensagem bonitinha mostrando as cartas ganhas
     * @param raridade a raridade minima das cartas a serem concedidas
     * @param quantidade a quantidade de cartas a serem concedidas
     * @param heroi o heroi que recebera as cartas
     */
    public static void ganharCartas(int raridade, int quantidade, Heroi heroi){
        ganharCartas(raridade, quantidade, heroi, poolTodasCartas);
    }

    /** concede uma carta ao heroi e imprime uma mensagem bonitinha mostrando a carta ganha
     * @param raridade a raridade minima das carta
     * @param heroi o heroi que recebera a carta
     * @param pool a lista de cartas a ser considerada para a escolha
     */
    public static void ganharCarta(int raridade, Heroi heroi, List<Carta> pool){
        System.out.println(Cor.txtAmareloClaro("Você ganhou:")); Textos.sleep(500);
        System.out.println();

        Carta recompensa = Recompensas.cartaAleatoria(raridade, pool);
        
        Textos.printaBonito(recompensa.recompensa(), 5, 2);
        heroi.addCartaInventario(recompensa);
        Textos.sleep(500);

        System.out.println();
        System.out.println("Nova carta adicionada ao inventário! Visite o deckBuilder para equipá-la.");
        InputHandler.esperar();
    }

    /** concede uma carta ao heroi e imprime uma mensagem bonitinha mostrando a carta ganha
     * @param raridade a raridade minima das carta
     * @param heroi o heroi que recebera a carta
     */
    public static void ganharCarta(int raridade, Heroi heroi){
        ganharCarta(raridade, heroi, poolTodasCartas);
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

    public static void ganharDinheiro(int valor, Heroi heroi) {
        imprimeGanhoDinheiro(valor);
        heroi.addDinheiro(valor);
    }

    public static void gastarDinheiro(int valor, Heroi heroi) {
        imprimeGastoDinheiro(valor);
        heroi.removeDinheiro(valor);
    }

    public static void ganharVida(int valor, Heroi heroi){
        heroi.addVida(valor);
        imprimeGanhoVida(valor);
    }

    /** reseta o pool global de itens unicos da run */
    public static void resetarPoolItens() {
        poolItensUnicos.clear();
        poolItensUnicos.addAll(listaItensMoldes);
    }
    

    /** reseta o pool global de cartas */
    public static void resetarPoolCartas() {
        poolTodasCartas.clear();
        poolTodasCartas.addAll(listaCartasMoldes);
    }

    public static int getQuantidadeItensDisponiveis() {
        return poolItensUnicos.size();
    }


    // metodos privados ---------------------------------

    private static void imprimeGanhoDinheiro(int valor) {
        Textos.limpaTela();
        System.out.println("Voce recebeu " + Cor.txtAmarelo(String.valueOf(valor)) + " dinheiros!");
        InputHandler.esperar();
    }

    private static void imprimeGastoDinheiro(int valor) {
        Textos.limpaTela();
        System.out.println("Voce gastou " + Cor.txtVermelho(String.valueOf(valor)) + " dinheiros!");
        InputHandler.esperar();
    }

    private static void  imprimeGanhoVida(int valor) {
        Textos.limpaTela();
        System.out.println("Voce ganhou " + Cor.txtVerde(String.valueOf(valor)) + " pontos de vida!");
        InputHandler.esperar();
    }

    /** retorna uma carta aleatoria
     * @param raridade a raridade minima da carta a ser retornada (1 - comum, 2 - incomum, 3 - rara, 4 - especial)
     * @param pool a lista de cartas a ser considerada para a escolha
     */
    private static Carta cartaAleatoria(int raridade, List<Carta> pool){
        List<Carta> listaRecompensas = new ArrayList<>();
        for(Carta c : pool)
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
     * @param pool a lista de cartas a ser considerada para a escolha
     */
    private static List<Carta> cartasAleatorias(int raridade, int quantidade, List<Carta> pool){
        List<Carta> listaRecompensas = new ArrayList<>();
        for(Carta c : pool)
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
    private static Carta menuCartas(Carta... cartas){
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
    private static Carta escolheCarta(int raridade, int quantidade){
        List<Carta> cartas = cartasAleatorias(raridade, quantidade, poolTodasCartas);
        Carta[] cartasArray = new Carta[cartas.size()];
        for (int i = 0; i < cartas.size(); i++) 
        {
            cartasArray[i] = cartas.get(i);
        }
        return menuCartas(cartasArray);
    }

    private static void imprimeGanhoDeItem(Item item) {
        Textos.printaBonito("Voce ganhou: " + Cor.txtRosa(item.getNome()) + 
        " < " + Cor.txtAmareloClaro(item.getDescricao()) + " >" + Cor.reset, 4, 2);
        InputHandler.esperar();
    }

}
