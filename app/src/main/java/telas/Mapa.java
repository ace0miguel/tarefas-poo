package telas;
import javax.swing.tree.DefaultMutableTreeNode;

import entidades.Heroi;
import telas.eventos.ArvoreEventos;
import telas.eventos.Evento;
import util.InputHandler;
import util.RNGHandler;
import visual.Arte;
import visual.Cor;
import visual.Textos;

public class Mapa {
    private Heroi heroi;
    private boolean primeiroLoop = true;

    public Mapa(Heroi _heroi){
        this.heroi = _heroi;
    }

    ArvoreEventos arvoreEventos = new ArvoreEventos(3, 11); // QUANTIDADE TOTAL DE ILHAS/ANDARES: P + 1 (COMEÇA DO PROFUNDIDADE 0)

    // gera a raiz da arvore
    DefaultMutableTreeNode nodeInicial = new DefaultMutableTreeNode(arvoreEventos.escolherEvento(0).get(0));
    DefaultMutableTreeNode nodeAtual = nodeInicial; // eu to chamando de node pq noAtual ia ficar muito feio

    public ArvoreEventos getArvoreEventos() {
        return arvoreEventos;
    }

    public DefaultMutableTreeNode getNodeInicial() {
        return nodeInicial;
    }

    public DefaultMutableTreeNode getNodeAtual() {
        return nodeAtual;
    }

    public boolean getPrimeiroLoop() {
        return primeiroLoop;
    }

    // vai usado pelo gerenciador de mapa, o qual nao ta funcionando ainda 
    public void restaurarEstado(ArvoreEventos arvoreEventos, DefaultMutableTreeNode nodeInicial, DefaultMutableTreeNode nodeAtual, boolean primeiroLoop) {
        this.arvoreEventos = arvoreEventos;
        this.nodeInicial = nodeInicial;
        this.nodeAtual = nodeAtual;
        this.primeiroLoop = primeiroLoop;
    }
    
    /** gera os filhos da posiçao passada e printa um menu de seleçao com seus eventos. (usar quando gerando os filhos dinamicamente) */
    public int criarCaminhos(DefaultMutableTreeNode posicaoAtual){ 
        arvoreEventos.expandirNo(posicaoAtual);
        return InputHandler.selecionar(arvoreEventos.getEventos(posicaoAtual),true, Arte.mapa + "\n" + Cor.txtAzul(Arte.bordaHud4) + 
        "\n" + Textos.menuStatus(heroi) + Cor.reset +
        "|| Próxima ilha: [" + Cor.txtAmarelo(String.valueOf((posicaoAtual.getLevel() + 1))) + "]" , "deckBuilder.");
    }

    /** gera os filhos da posiçao atual e printa um menu de seleçao com os eventos (usar quando gerando os filhos dinamicamente) */
    public int criarCaminhos(){
        return criarCaminhos(nodeAtual);
    }
    
    /** retorna os filhos da posiçao passada e printa um menu de seleçao com seus evento s (usar para arvores completas)*/
    public int escolherCaminho(DefaultMutableTreeNode posicaoAtual){ 
        return InputHandler.selecionar(arvoreEventos.getEventos(posicaoAtual),true, Arte.mapa + "\n" + Cor.txtAzul(Arte.bordaHud4) + 
        "\n" + Textos.menuStatus(heroi) + Cor.reset +
        "|| Próxima ilha: [" + Cor.txtAmarelo(String.valueOf((posicaoAtual.getLevel() + 1))) + "]" , "deckBuilder.");
    }

     /** gera os filhos da posiçao e printa um menu de seleçao com os eventos (usar para arvores completas)*/
    public int escolherCaminho(){
        return escolherCaminho(nodeAtual);
    }

    /** atualiza o nodeAtual para o filho de indice n do nó passado como argumento */
    public void irPara(DefaultMutableTreeNode posicaoAtual,int n){ 
    // tem uma chance de 15% tomar jumpscare do golden freddy
        if (RNGHandler.check(10)){ 
            System.out.println(Cor.txtAmarelo(Arte.GOLDENFREDDY));
            System.out.println(Cor.txtVermelho("RECEBA O JUMPSCARE!"));
            InputHandler.esperar();
        }
        nodeAtual = (DefaultMutableTreeNode) posicaoAtual.getChildAt(n);
    }

    /** atualiza o nodeAtual para o filho de indice n do nó atual*/
    public void irPara(int n){ 
        irPara(nodeAtual, n);
    }

    /** retorna o evento dentro do nó passado como argumento*/
    public Evento getEvento(DefaultMutableTreeNode atual) {
        return (Evento) atual.getUserObject();
    }

    /** retorna o evento dentro do nó atual*/
    public Evento getEvento() {
        return getEvento(nodeAtual);
    }
    

    /** converte o TreeNode(getChildAt retorna TreeNode) em DefaultMutableTreeNode,
     pega o objeto dentro dele e dps converte em evento e retorna.*/
    public Evento getProximoEvento(DefaultMutableTreeNode atual, int escolha) {
        return (Evento) ((DefaultMutableTreeNode) atual.getChildAt(escolha)).getUserObject();
    }

    public Evento getProximoEvento(int escolha) {
        return getProximoEvento(nodeAtual, escolha);
    }

    /** inicia o mapa, mandando o jogador pro primeiro nó */
    public void explorar() {
        while (true) { 
            Textos.limpaTela();

            if (primeiroLoop){
                DeckBuilder.mostrarDecksPadrao(heroi);
                
                Textos.printaLinhaDevagar(Arte.mapa);
                Textos.printaLinhaDevagar(Cor.txtAzul(Arte.bordaHud4));
                System.out.println();
                InputHandler.esperar(Cor.cinza + "Pressione ENTER para ir para " + Cor.reset + getEvento());
                getEvento().iniciar(heroi);
                primeiroLoop = false;
                continue;
            }
            
            if (nodeAtual.getLevel() == arvoreEventos.getProfundidadeMax()) { // nao tem mais evento depois, sai do mapa ( mas avisa antes )
                System.out.println(Cor.txtAmarelo("Parabéns!"));
                InputHandler.esperar();
                return;
            }

            int escolha = criarCaminhos();

            // o voltar te manda pro deckbuilder
            if (escolha == -1) {
                DeckBuilder.iniciar(heroi);
                continue;
            }

            InputHandler.esperar(Cor.cinza + "Pressione ENTER para ir para " + Cor.reset + getProximoEvento(escolha));

            irPara(escolha);

            getEvento().iniciar(heroi);
        }
    }

}
