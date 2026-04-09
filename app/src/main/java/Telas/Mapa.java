package Telas;
import javax.swing.tree.DefaultMutableTreeNode;

import Entidades.Heroi;
import Telas.Eventos.Evento;
import Util.Arte;
import Util.ArvoreEventos;
import Util.Cor;
import Util.InputHandler;
import Util.RNGHandler;
import Util.Textos;

public class Mapa {
    private Heroi heroi;

    public Mapa(Heroi _heroi){
        this.heroi = _heroi;
    }

    ArvoreEventos arvoreEventos = new ArvoreEventos(3, 7); // QUANTIDADE TOTAL DE EVENTOS: P - 1 (COMEÇA DO PROFUNDIDADE 0)
    DefaultMutableTreeNode nodeInicial = arvoreEventos.criarArvore(); // cria arvore e recebe a raiz
    DefaultMutableTreeNode nodeAtual = nodeInicial; // eu to chamando de node pq noAtual ia ficar muito feio
    
    // retorna um menu de seleçao com os filhos da posiçao passada. a opçao exit te manda pro deckbuilder.
    public int escolherCaminho(DefaultMutableTreeNode posicaoAtual){ 
        return InputHandler.selecionar(arvoreEventos.getEventos(posicaoAtual),true, Arte.mapa + "\n" + Cor.txtAzul(Arte.bordaHud4) + Cor.txtAmarelo("\nEscolha voltar para personalizar seu deck"));
    }

    public int escolherCaminho(){ // se nao passar nada retorna baseado no nó atual
        return escolherCaminho(nodeAtual);
    }
    

    // atualiza o nodeAtual para o filho de indice n do nó passado como argumento
    public void irPara(DefaultMutableTreeNode posicaoAtual,int n){ 
        nodeAtual = (DefaultMutableTreeNode) posicaoAtual.getChildAt(n);
    }

    public void irPara(int n){ // se nao passar nada passa baseado no nó atual
        if (RNGHandler.check(15)){ // tem uma chance de 15% tomar jumpscare do golden freddy
            System.out.println(Cor.txtAmarelo(Arte.GOLDENFREDDY));
            System.out.println(Cor.txtVermelho("RECEBA O JUMPSCARE!"));
            InputHandler.esperar();
        }
        irPara(nodeAtual, n);
    }

    // converte o objeto em evento dentro do no atual e retorna 
    public Evento getEvento(DefaultMutableTreeNode atual) {
        return (Evento) atual.getUserObject();
    }

    public Evento getEvento() {
        return getEvento(nodeAtual);
    }
    

    /* converte o TreeNode(getChildAt retorna TreeNode) em DefaultMutableTreeNode,
     pega o objeto dentro dele e dps converte em evento e retorna.*/
    public Evento getProximoEvento(DefaultMutableTreeNode atual, int escolha) {
        return (Evento) ((DefaultMutableTreeNode) atual.getChildAt(escolha)).getUserObject();
    }

    public Evento getProximoEvento(int escolha) {
        return getProximoEvento(nodeAtual, escolha);
    }

    public void explorar() { // explorar e um nome meio sem graça se tiver uma ideia melhor atualizar aq 

        boolean primeiroLoop = true; // se quiser pular a primeira luta por motivos de teste so deixar false aqui (lembra de arruma dps)

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

            if (nodeAtual.isLeaf()) { // nao tem mais evento depois, sai do mapa ( mas avisa antes )
                System.out.println(Cor.txtAmarelo("Parabéns!"));
                InputHandler.esperar();
                return;
            } 

            int escolha = escolherCaminho();

            // o voltar te manda pro deckbuilder ( da pra deixar mais bonito depois e adicionar uma opçao deckbuilder mas enfim )
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
