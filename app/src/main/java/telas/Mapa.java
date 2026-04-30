package telas;
import javax.swing.tree.DefaultMutableTreeNode;

import entidades.Heroi;
import telas.eventos.ArvoreEventos;
import telas.eventos.ArvoreEventos.tipoNode;
import telas.eventos.Evento;
import util.InputHandler;
import util.RNGHandler;
import util.Recompensas;
import visual.Arte;
import visual.Cor;
import visual.Textos;

public class Mapa {
    String bordaHudFinal;
    private Heroi heroi;

    public Mapa(Heroi _heroi){
        this.heroi = _heroi;
    }

    ArvoreEventos arvoreEventos = new ArvoreEventos(3, 12); // QUANTIDADE TOTAL DE ILHAS/ANDARES: P + 1 (COMEÇA DO PROFUNDIDADE 0)

    // gera a raiz da arvore
    DefaultMutableTreeNode nodeInicial = new DefaultMutableTreeNode(arvoreEventos.escolherEvento(0).get(0));
    DefaultMutableTreeNode nodeAtual = nodeInicial; // eu to chamando de node pq noAtual ia ficar muito feio
    
    /** gera os filhos da posiçao passada e printa um menu de seleçao com seus eventos. (usar quando gerando os filhos dinamicamente) */
    public int criarCaminhos(DefaultMutableTreeNode posicaoAtual){ 
        arvoreEventos.expandirNo(posicaoAtual);
        return InputHandler.selecionar(arvoreEventos.getTipoNodes(posicaoAtual),true, Arte.mapa + "\n" + bordaHudFinal + 
        "\n" + Textos.menuStatus(heroi) + Cor.reset +
        "|| Próxima ilha: [" + Cor.txtAmarelo(String.valueOf((posicaoAtual.getLevel() + 1))) + "]" , "deckBuilder.");
    }

    /** gera os filhos da posiçao atual e printa um menu de seleçao com os eventos (usar quando gerando os filhos dinamicamente) */
    public int criarCaminhos(){
        return criarCaminhos(nodeAtual);
    }
    
    /** retorna os filhos da posiçao passada e printa um menu de seleçao com seus eventos (usar para arvores completas)*/
    public int escolherCaminho(DefaultMutableTreeNode posicaoAtual){ 
        return InputHandler.selecionar(arvoreEventos.getTipoNodes(posicaoAtual),true, Arte.mapa + "\n" + bordaHudFinal + 
        "\n" + Textos.menuStatus(heroi) + Cor.reset +
        "|| Próxima ilha: [" + Cor.txtAmarelo(String.valueOf((posicaoAtual.getLevel() + 1))) + "]" , "deckBuilder.");
    }

     /** gera os filhos da posiçao e printa um menu de seleçao com os eventos (usar para arvores completas)*/
    public int escolherCaminho(){
        return escolherCaminho(nodeAtual);
    }

    /** atualiza o nodeAtual para o filho de indice n do nó passado como argumento */
    public void irPara(DefaultMutableTreeNode posicaoAtual,int n){ 
    // tem uma chance de 5% tomar jumpscare do golden freddy
        if (RNGHandler.check(5)){ 
            Textos.limpaTela();
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

    /** cria evento do no atual baseado no tipo */
    public void iniciar() {
        tipoNode tipo = (tipoNode) nodeAtual.getUserObject();

        Evento evento = tipo.getEvento().criaCopia();

        InputHandler.esperar(Cor.cinza + "Pressione ENTER para ir para " + Cor.reset + evento);
        
        evento.iniciar(heroi);
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
        boolean primeiroLoop = true; // se quiser pular a primeira luta por motivos de teste so deixar false aqui (lembra de arruma dps)
        bordaHudFinal = Textos.colorirPartes(Arte.bordaHud4, Cor.azulClaro, Cor.azul, 1);

        while (true) { 
            Textos.limpaTela();

            if (!heroi.estaVivo()){
                Textos.printaBonito(Arte.VOCEMORREU, 21, 2);
                System.exit(0);
            }


            if (primeiroLoop){
                DeckBuilder.mostrarDecksPadrao(heroi);
                Recompensas.resetarPoolItens(heroi);
                
                Textos.printaLinhaDevagar(Arte.mapa);
                Textos.printaLinhaDevagar(bordaHudFinal);
                System.out.println();

                iniciar();

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

            irPara(escolha);

            iniciar();
        }
    }

}
