package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import batalhaListeners.itens.Item;
import batalhaListeners.itens.ativos.ItemAtivo;
import batalhaListeners.itens.passivos.ItemPassivo;
import cartas.Carta;
import cartas.Carta.raridades;
import entidades.Heroi;
import static fabricas.FabricaCartas.cartasEncontraveis;
import static fabricas.FabricaItens.listaItensAtivos;
import static fabricas.FabricaItens.listaItensPassivos;
import visual.Cor;
import visual.Textos;

/** Aqui ficam todos os metodos pra dar recompensas ao jogador (cartas, itens, etc) */
public class Recompensas {
    private static final List<ItemPassivo> poolItensUnicos = new ArrayList<>();
    private static final List<Carta> poolTodasCartas = new ArrayList<>();

    static {
        resetarPoolCartas();
        resetarPoolItens();
    }

    /** concede um item passivo especifico caso ainda esteja disponivel no pool global */
    public static boolean ganharItemPassivoEsp(ItemPassivo item, Heroi heroi) {
        if (!poolItensUnicos.remove(item)) {
            return false;
        }

        heroi.addItemPassivo(item);
        
        imprimeGanhoDeItem(item);
        return true;
    }

    /** concede um item passivo aleatorio unico, se houverem disponiveis na pool global */
    public static boolean ganharItemPassivo(Heroi heroi) {
        if (poolItensUnicos.isEmpty()) {
            return false;
        }

        int sorteado = RNGHandler.getGen().nextInt(poolItensUnicos.size());

        ItemPassivo escolhido = poolItensUnicos.get(sorteado);
        poolItensUnicos.remove(escolhido);

        heroi.addItemPassivo(escolhido);

        imprimeGanhoDeItem(escolhido);
        return true;
    }

    /** concede um item ativo aleatorio unico */
    public static boolean ganharItemAtivo(Heroi heroi) {
        int sorteado = RNGHandler.getGen().nextInt(listaItensAtivos.size());

        ItemAtivo escolhido = listaItensAtivos.get(sorteado);

        heroi.addItemAtivo(escolhido);

        imprimeGanhoDeItem(escolhido);
        return true;
    }

    /** concede um item ativo especifico */
    public static boolean ganharItemAtivoEsp(ItemAtivo item, Heroi heroi) {
        heroi.addItemAtivo(item);    
        imprimeGanhoDeItem(item);
        return true;
    }

    /** concede varias cartas ao heroi e imprime uma mensagem bonitinha mostrando as cartas ganhas
     * @param raridade a raridade minima das cartas a serem concedidas
     * @param quantidade a quantidade de cartas a serem concedidas
     * @param heroi o heroi que recebera as cartas
     * @param pool a lista de cartas a ser considerada para a escolha
     */
    public static void ganharCartas(raridades raridade, int quantidade, Heroi heroi, List<Carta> pool){
        System.out.println(Cor.txtAmareloClaro("Você ganhou:")); Textos.sleep(500);
        System.out.println();

        List<Carta> recompensa = Recompensas.cartasAleatorias(raridade, quantidade, pool, false);
        
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
    public static void ganharCartas(raridades raridade, int quantidade, Heroi heroi){
        ganharCartas(raridade, quantidade, heroi, poolTodasCartas);
    }

    /** concede uma carta ao heroi e imprime uma mensagem bonitinha mostrando a carta ganha
     * @param raridade a raridade minima das carta
     * @param heroi o heroi que recebera a carta
     * @param pool a lista de cartas a ser considerada para a escolha
     */
    public static void ganharCarta(raridades raridade, Heroi heroi, List<Carta> pool){
        System.out.println(Cor.txtAmareloClaro("Você ganhou:")); Textos.sleep(500);
        System.out.println();

        Carta recompensa = Recompensas.cartaAleatoria(raridade, pool);
        
        Textos.printaBonito(recompensa.recompensa(), 5, 2);

        InputHandler.esperar();
        
        guardaOuEquipa(recompensa, heroi);
    }

    /** concede uma carta ao heroi e imprime uma mensagem bonitinha mostrando a carta ganha
     * @param raridade a raridade minima das carta
     * @param heroi o heroi que recebera a carta
     */
    public static void ganharCarta(raridades raridade, Heroi heroi){
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

        InputHandler.esperar();

        guardaOuEquipa(carta, heroi);
    }

    /** concede uma maldicao especifica ao heroi e imprime uma mensagem bonitinha mostrando a maldicao ganha 
     * @param carta a maldicao a ser concedida
     * @param heroi o heroi que recebera a maldicao
    */
    public static void ganharMaldicao(Carta carta, Heroi heroi){
        System.out.println(Cor.txtRoxo("Você recebeu a maldição: [ " + carta.getNomeColorido() + Cor.roxo + " ] !" + Cor.reset)); Textos.sleep(500);
        System.out.println();
   
        Textos.printaBonito(carta.recompensa(), 5, 2);

        InputHandler.esperar();

        heroi.addCarta(carta);
    }

    /** concede uma escolha de cartas ao heroi, com uma raridade minima
     * @param raridade a raridade minima das cartas a serem escolhidas
     * @param quantidade a quantidade de opcoes
     */
    public static void ganharOpcoes(raridades raridade, int quantidade, Heroi heroi){
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

        if (valor > 0)
            imprimeGanhoVida(valor);
        else
            imprimePerdaVida(-valor);
    }

    /** reseta o pool global de itens unicos da run */
    public static void resetarPoolItens() {
        resetarPoolItens(null);
    }

    /** reseta o pool global de itens unicos, removendo itens passivos que o heroi ja possui */
    public static void resetarPoolItens(Heroi heroi) {
        poolItensUnicos.clear();
        poolItensUnicos.addAll(listaItensPassivos);

        if (heroi == null || heroi.getListaItensPassivos().isEmpty()) {
            return;
        }

        Set<String> nomesItensDoHeroi = new HashSet<>();
        for (ItemPassivo item : heroi.getListaItensPassivos()) {
            nomesItensDoHeroi.add(item.getNome());
        }

        poolItensUnicos.removeIf(item -> nomesItensDoHeroi.contains(item.getNome()));
    }
    

    /** reseta o pool global de cartas */
    public static void resetarPoolCartas() {
        poolTodasCartas.clear();
        poolTodasCartas.addAll(cartasEncontraveis);
    }

    public static int getQuantidadeItensDisponiveis() {
        return poolItensUnicos.size();
    }

    public static void ganhaTagAleatoria(Carta carta) {
        carta = aplicaTagAleatoria(carta);

        System.out.println(Cor.txtVerde("A carta " + carta.getNome() + " ganhou a tag " + 
        Cor.txtRosa(carta.getTags().get(carta.getTags().size() - 1)) + Cor.reset + "!"));
        
        InputHandler.esperar();
    }

        /** retorna uma carta aleatoria
     * @param raridade a raridade minima da carta a ser retornada (1 - comum, 2 - incomum, 3 - rara, 4 - especial)
     * @param pool a lista de cartas a ser considerada para a escolha
     */
    public static Carta cartaAleatoria(raridades raridade, List<Carta> pool){
        List<Carta> listaRecompensas = new ArrayList<>();
        for(Carta c : pool)
        {
            if (c.getRaridade() >= raridade.getValor()) 
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
     * @param naoRepetir se true, não adiciona cartas repetidas à lista de recompensas
     */
    public static List<Carta> cartasAleatorias(raridades raridade, int quantidade, List<Carta> pool, boolean naoRepetir){
        List<Carta> listaRecompensas = new ArrayList<>();
        for(Carta c : pool)
        {
            if (c.getRaridade() >= raridade.getValor()) 
            {
                for(int i = 0; i < 5 - c.getRaridade(); i++)
                {
                    if (naoRepetir)
                        for (Carta k : listaRecompensas)
                            if (k.getNome().equals(c.getNome()))
                                continue;   
                    
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
    public static Carta escolheCarta(raridades raridade, int quantidade){
        List<Carta> cartas = cartasAleatorias(raridade, quantidade, poolTodasCartas, true);
        Carta[] cartasArray = new Carta[cartas.size()];
        for (int i = 0; i < cartas.size(); i++) 
        {
            cartasArray[i] = cartas.get(i);
        }
        return menuCartas(cartasArray);
    }

    // metodos privados ---------------------------------

    public static Carta aplicaTagAleatoria(Carta _carta) {
        Carta carta = _carta.criaCopia();
        if (carta.getTags().size() == Carta.tipoTags.size())
            return carta;

        String tagSorteada = RNGHandler.sorteiaDeLista(Carta.tipoTags);

        while (true) {
            if (!carta.aplicarTag(tagSorteada, true)){
                continue;
            }
            break;
        }

        return carta;
    }

    private static void guardaOuEquipa(Carta carta, Heroi heroi) {
        List<String> opcoes = new ArrayList<>();
        opcoes.add("Adicionar ao baralho");
        opcoes.add("Guardar no inventário");

        int escolha = InputHandler.selecionar(opcoes, Cor.txtAmareloClaro("O que deseja fazer com " + carta.getNomeColorido() + "?"));

        if (escolha == 0) {
            heroi.addCarta(carta);
            System.out.println("Carta adicionada ao baralho!");
        } else {
            heroi.addCartaInventario(carta);
            System.out.println("Carta guardada no inventário! Visite o deckBuilder se desejar equipá-la.");
        }

        Textos.sleep(500);

        InputHandler.esperar();
    }

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

    private static void imprimeGanhoVida(int valor) {
        Textos.limpaTela();
        System.out.println("Voce ganhou " + Cor.txtVerde(String.valueOf(valor)) + " pontos de vida!");
        InputHandler.esperar();
    }

    public static void imprimePerdaVida(int valor) {
        Textos.limpaTela();
        System.out.println("Voce perdeu " + Cor.txtVermelho(String.valueOf(valor)) + " pontos de vida!");
        InputHandler.esperar();
    }

    private static void imprimeGanhoDeItem(Item item) {
        Textos.printaBonito("Voce ganhou: [ " + (item.getNomeColorido()) + 
        " ] ( " + Cor.txtAmareloClaro(item.getDescricao()) + " )" + Cor.reset, 4, 2);
        InputHandler.esperar();
    }

}
