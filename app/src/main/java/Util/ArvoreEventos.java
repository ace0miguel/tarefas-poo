package Util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import Telas.Eventos.Batalha;
import Telas.Eventos.Evento;
import Telas.Eventos.Fogueira;
import static Util.Moldes.barbossa;
import static Util.Moldes.drake;
import static Util.Moldes.endrick;
import static Util.Moldes.loudCoringa;
import static Util.Moldes.paulAtreides;
import static Util.Moldes.sabrinaCarpenter;
import static Util.Moldes.tripleT;

/** cria uma arvore onde cada nó representa um evento
 * @param n quantidade de filhos por nó
 * @param p profundidade (quantidade total de eventos: p - 1, começa da profundidade 0)
 */
public class ArvoreEventos {
    int n; // quantidade de filhos por nó
    int p; // profundidade (QUANTIDADE TOTAL DE BATALHAS: P - 1 PQ COMEÇA DO PROFUNDIDADE 0)

    public ArvoreEventos(int n, int p){
        this.n = n;
        this.p = p;
    }

    
    public long getTotalNos(){
        if (n == 1) return p+1;

        long numerador = ((long) Math.pow(n, p + 1) - 1);
        long denominador = n - 1;

        return numerador / denominador;
    }

    /** define como os eventos serao distribuidos por cada nó */
    public Evento escolherEvento(int profundidadeAtual) { // da pra da uma personalizadinha aqui dps so fiz o basico   
        if (profundidadeAtual == 0)
            { // a primeira vai ser facinha pq eu sou bonzinho
            return new Batalha(loudCoringa.criaCopia(), barbossa.criaCopia(), endrick.criaCopia());
        } 
        else if (profundidadeAtual % 2 == 0) 
            { // a cada uma luta mais ou menos vai ter um evento aleatorio (por enquanto só fogueira)
            return new Fogueira();
        }
        else if (profundidadeAtual == p) 
            { // se chegou no maximo vai toma a batalha mais dificil quero nem saber VAI LUTA COM TODO MUNDO
            return new Batalha(barbossa.criaCopia(), loudCoringa.criaCopia(), endrick.criaCopia(), drake.criaCopia(), paulAtreides.criaCopia(), sabrinaCarpenter.criaCopia(), tripleT.criaCopia());
        } 

        int sorteio = RNGHandler.valorAleatorio(100);

        // fiz umas variaçoes genericas de batalha ai
        if (sorteio <= 33) {
            return new Batalha(drake.criaCopia(), tripleT.criaCopia(), endrick.criaCopia());
        } else if (sorteio <= 66) {
            return new Batalha(paulAtreides.criaCopia(), sabrinaCarpenter.criaCopia());
        } else {
            return new Batalha( loudCoringa.criaCopia(), barbossa.criaCopia(), endrick.criaCopia()); 
        }
    }

    /** cria uma arvore baseada nos parametros da instancia atual dessa classe e retorna a raiz.
    ele cria cada no com um evento, eu pensei em criar depois uma funçao pra percorrer e transformar
    esses eventos no q eles forem, tipo batalha ou outros ai q venham a ser criados. */
    public DefaultMutableTreeNode criarArvore(int profundidade) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(escolherEvento(profundidade));
        
        // caso base
        if (profundidade == p) return node;

        // cria n nos
        for (int i = 0; i < n; i ++){
            DefaultMutableTreeNode novonode = criarArvore(profundidade+1);
            node.add(novonode);
        }

        return node;
    }

    // função pra vc nao precisar chamar como criarArvore(0) quando for utilizar
    public DefaultMutableTreeNode criarArvore() {
        return criarArvore(0);
    }

    /** retorna os filhos do no q vc passar */
    public List<DefaultMutableTreeNode> getFilhos (DefaultMutableTreeNode node) {
        List<DefaultMutableTreeNode> listaFilhos = new ArrayList<>();

        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode filho = (DefaultMutableTreeNode) node.getChildAt(i);
            listaFilhos.add(filho);
        }

        return listaFilhos;
    }

    /** retorna os eventos nos filhos do no q vc passar */
    public List<Evento> getEventos (DefaultMutableTreeNode node) {
        List<Evento> listaEventos = new ArrayList<>();

        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode filho = (DefaultMutableTreeNode) node.getChildAt(i);
            Evento evento = (Evento) filho.getUserObject();        
            listaEventos.add(evento);
        }

        return listaEventos;
    }
}
