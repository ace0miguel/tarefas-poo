package Util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import Entidades.Heroi;
import Entidades.Inimigo;
import Telas.Eventos.Batalha;
import Telas.Eventos.Evento;

public class ArvoreEventos {
    int n; // quantidade de filhos por nó
    int p; // profundidade

    // instancias padrao pra teste por enquanto. talvez seja bom fazer essa classe receber uma lista de inimigos pra ficar mais procedural sla
    Inimigo inimigo = new Inimigo("Capitão Hector Barbossa", 30, 4);
    Inimigo inimigo2 = new Inimigo("LOUD Coringa", 15, 3);    
    Inimigo inimigo3 = new Inimigo("Endrick", 12, 4);
    Inimigo inimigo4 = new Inimigo("PAUL MUAD'DIB ATREIDES, LISAN AL GAIB", 40, 8); // ESSE AQUI E FORTE VIU
    Inimigo inimigo5 = new Inimigo("SABRINA CARPENTER", 35, 6); // ELA E FORTE TB
    Inimigo inimigo6 = new Inimigo("Drake", 10, 2);

    public ArvoreEventos(int n, int p, Heroi heroi){
        this.n = n;
        this.p = p;
    }

    public long getTotalNos(){
        if (n == 1) return p+1;

        long numerador = ((long) Math.pow(n, p + 1) - 1);
        long denominador = n - 1;

        return numerador / denominador;
    }


    public Evento escolherEvento(int profundidadeAtual, Heroi heroi) { // da pra da uma personalizadinha aqui dps so fiz o basico
        
        if (profundidadeAtual == p) { // se chegou no maximo vai toma a batalha mais dificil quero nem saber VAI LUTA COM TODO MUNDO
            return new Batalha(inimigo.criaCopia(), inimigo2.criaCopia(), inimigo3.criaCopia(), inimigo4.criaCopia(), inimigo5.criaCopia(), inimigo6.criaCopia());
        } else if (profundidadeAtual == 0){
            return new Batalha(inimigo2.criaCopia(), inimigo3.criaCopia(), inimigo6.criaCopia()); // mas a primeira vai ser facinha pq eu sou bonzinho
        }

        int sorteio = RNGHandler.valorAleatorio(100);

        // fiz umas variaçoes genericas de batalha ai
        if (sorteio <= 33) {
            return new Batalha(inimigo4.criaCopia(), inimigo5.criaCopia(), inimigo3.criaCopia());
        } else if (sorteio <= 66) {
            return new Batalha(inimigo.criaCopia(), inimigo5.criaCopia(), inimigo3.criaCopia());
        } else {
            return new Batalha(inimigo.criaCopia(), inimigo2.criaCopia(), inimigo4.criaCopia()); 
        }
    }

    /* cria uma arvore baseada nos parametros da instancia atual dessa classe e retorna a raiz.
    ele cria cada no com um evento, eu pensei em criar depois uma funçao pra percorrer e transformar
    esses eventos no q eles forem, tipo batalha ou outros ai q venham a ser criados. */
    public DefaultMutableTreeNode criarArvore(int profundidade, Heroi heroi) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(escolherEvento(profundidade, heroi));
        
        // caso base
        if (profundidade == p) return node;

        // cria n nos
        for (int i = 0; i < n; i ++){
            DefaultMutableTreeNode novonode = criarArvore(profundidade+1, heroi);
            node.add(novonode);
        }

        return node;
    }

    // funçao pra vc nao precisar chamar como criarArvore(0) quando for utilizar
    public DefaultMutableTreeNode criarArvore(Heroi heroi) {
        return criarArvore(0, heroi);
    }

    // retorna os filhos do no q vc passar
    public List<DefaultMutableTreeNode> getFilhos (DefaultMutableTreeNode node) {
        List<DefaultMutableTreeNode> listaFilhos = new ArrayList<>();

        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode filho = (DefaultMutableTreeNode) node.getChildAt(i);
            listaFilhos.add(filho);
        }

        return listaFilhos;
    }

    // retorna os eventos nos filhos do no q vc passar
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
