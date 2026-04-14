package Deck;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Cartas.Carta;
import Util.InputHandler;
import Visual.Cor;
import Visual.Textos;

/* Início do turno : puxa 5 carta;
 tudo que voce nao usar vai pra pilha de descarte;
acabou a pilha de compra: reembaralha a pilha de descarte */
public class Mao {
    // uma boa mudança seria adicionar um construtor com a pilha de compras e a pilha de descarte, ai nao teria q ficar passando toda vez que chama
    private int quantMax = 5;
    private ArrayList<Carta> cartas = new ArrayList<>(); 

    Scanner ler = InputHandler.getLeitor();

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

    public void inicioBatalha(PilhaCompra pilhaCompra, PilhaDescarte pilhaDescarte){
        List<Carta> obrigatorias = new ArrayList<>();
        for (Carta carta : pilhaCompra.getPilhaCartas()) {
            if (carta.getInata()) {
                obrigatorias.add(carta);
            }
        }

        // remove as obrigatorias da pilha de compra
        pilhaCompra.getPilhaCartas().removeAll(obrigatorias);

        // adiciona as obrigatorias a mao
        this.cartas.addAll(obrigatorias);

        int cartasRestantes = quantMax - obrigatorias.size();

        // se tiver menos que o tamanho padrao da mao, completa com cartas aleatórias
        if (cartasRestantes > 0) {
            this.addCartas(pilhaCompra, pilhaDescarte, cartasRestantes);
        }
    }

    public int getQuantMax() {
        return quantMax;
    }

    /** exibe uma lista de opçoes com as cartas da mão, valida a escolha e retorna o indice da carta escolhida */
    public int mostrar(){ 
        System.out.println("Mão atual:");
        int ultimoNumero = 0;

        if (cartas.size() <= 0) return -1;

        for (int i = 0; i < cartas.size(); i++){
            Carta cartaAtual = cartas.get(i);
            Textos.printaBonito("["+i+"] > "+cartaAtual.descricao()+"\n", 1, 2); Textos.sleep(3);
            ultimoNumero = i;
        }
        ultimoNumero++;
        Cor.printaCinza("["+ultimoNumero+"] - Encerrar turno\n");
        
        int opcao = -1;

        try {
            opcao = ler.nextInt();
            if (ler.hasNextLine()) {
                ler.nextLine();
            };
        } catch (Exception e) {
            if (ler.hasNextLine()) {
                ler.nextLine();
            };
        }
        return opcao;
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
}
