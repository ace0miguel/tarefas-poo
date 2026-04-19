package baralho;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cartas.Carta;
import cartas.CartaAtaque;
import cartas.CartaHabilidade;
import cartas.CartaMaldicao;
import cartas.CartaPoder;
import util.InputHandler;
import visual.Cor;
import visual.Textos;

/* Início do turno : puxa 5 carta;
 tudo que voce nao usar vai pra pilha de descarte;
acabou a pilha de compra: reembaralha a pilha de descarte */
public class Mao {
    // uma boa mudança seria adicionar um construtor com a pilha de compras e a pilha de descarte, ai nao teria q ficar passando toda vez que chama
    private int quantMax = 5;
    private List<Carta> cartas = new ArrayList<>(); 
    private int cartasBonus = 0; // cartas extras a serem adicionadas no inicio do turno, depois de puxar as 5 cartas normais

    Scanner ler = InputHandler.getLeitor();

    public int getCartasBonus() {
        return cartasBonus;
    }

    public void setCartasBonus(int cartasBonus) {
        this.cartasBonus = cartasBonus;
    }

    public void addCarta(PilhaCompra pilhaCompra, PilhaDescarte pilhaDescarte){
        cartas.add(pilhaCompra.puxaCarta(pilhaDescarte));
    }
    
    public void addCartas(PilhaCompra pilhaCompra, PilhaDescarte pilhaDescarte, int quantidade){
        for (int i = 0; i < quantidade; i++) // adiciona n cartas a mao
            this.addCarta(pilhaCompra, pilhaDescarte); 
    }

    /** adiciona uma carta específica à mão */
    public void addCartaEsp(Carta carta){
        this.cartas.add(carta);
    }

    public void addCinco(PilhaCompra pilhaCompra, PilhaDescarte pilhaDescarte){
        for (int i = 0; i < 5; i++) // adiciona 5 cartas à mão
            this.addCarta(pilhaCompra, pilhaDescarte);
    }

    /** completa ate dar 5 cartas na mao e dps adiciona as cartas bonus. chamado todo inicio de rodada. */
    public void completaCinco(PilhaCompra pilhaCompra, PilhaDescarte pilhaDescarte){
        if (cartas.size() < quantMax){
            int faltantes = quantMax - cartas.size();
            for (int i = 0; i < faltantes; i++)
                this.addCarta(pilhaCompra, pilhaDescarte);
        }

        this.addCartas(pilhaCompra, pilhaDescarte, cartasBonus);
        cartasBonus = 0;
    }

    /** adiciona as cartas obrigatoras na mao inicial, e se forem menos que quantMax completa com cartas aleatórias */
    public void inicioBatalha(PilhaCompra pilhaCompra, PilhaDescarte pilhaDescarte){
        List<Carta> obrigatorias = new ArrayList<>();
        
        for (Carta carta : pilhaCompra.getPilha()) {
            if (carta.getInata()) {
                obrigatorias.add(carta);
            }
        }

        // remove as obrigatorias da pilha de compra
        pilhaCompra.getPilha().removeAll(obrigatorias);

        // adiciona as obrigatorias a mao
        this.cartas.addAll(obrigatorias);

        int cartasRestantes = quantMax - obrigatorias.size();

        // se tiver menos que o tamanho padrao da mao, completa com cartas aleatórias
        if (cartasRestantes > 0) {
            this.addCartas(pilhaCompra, pilhaDescarte, cartasRestantes);
        }

        this.addCartas(pilhaCompra, pilhaDescarte, cartasBonus);
        cartasBonus = 0;
    }

    public int getQuantMax() {
        return quantMax;
    }

    /** exibe uma lista de opçoes com as cartas da mão, valida a escolha e retorna o indice da carta escolhida */
    public List<Carta> mostrar(){ 
        return this.cartas;
    }

    /** remove a carta de indice opçao da mao e manda pra pilha de descarte
     *  (chamar essa funçao aqui so quando a carta realmente for USADA)
     */
    public void removeCarta(int opcao, PilhaDescarte pilhaDescarte){
        pilhaDescarte.descarta(cartas.get(opcao));
        cartas.remove(opcao);   
    }

    /** remove todas as cartas da mao e manda pra pilha de descarte
     * (não remove com tag manter)
     * chamar essa funçao no fim do turno
     */
    public void limpa(PilhaDescarte pilhaDescarte){
        List<Carta> cartasParaRemover = cartas.stream().
        filter(carta -> !carta.getManter()).toList();

        for (Carta carta : cartasParaRemover) {
            pilhaDescarte.descarta(carta);
        }

        cartas.removeAll(cartasParaRemover);
    }

    /** retorna a carta no indice opcao */
    public Carta escolheCarta(int opcao){
        Carta carta = cartas.get(opcao);
        return carta;
    }

    public int getSize(){
        return cartas.size();
    }

    public Carta getUltima(){
        return cartas.getLast();
    }

    public List<CartaAtaque> getCartasAtaque(){
        return cartas.stream().filter(carta -> carta instanceof CartaAtaque).map(carta -> (CartaAtaque) carta).toList();
    }

    public List<CartaHabilidade> getCartasHabilidade(){
        return cartas.stream().filter(carta -> carta instanceof CartaHabilidade).map(carta -> (CartaHabilidade) carta).toList();
    }

    public List<CartaPoder> getCartasPoder(){
        return cartas.stream().filter(carta -> carta instanceof CartaPoder).map(carta -> (CartaPoder) carta).toList();
    }

    public List<CartaMaldicao> getCartasMaldicao(){
        return cartas.stream().filter(carta -> carta instanceof CartaMaldicao).map(carta -> (CartaMaldicao) carta).toList();
    }
}
