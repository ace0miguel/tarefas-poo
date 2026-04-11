package Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import Telas.Eventos.Batalha;
import Telas.Eventos.Evento;
import Telas.Eventos.Fogueira;
import Telas.Eventos.Loja;
import Telas.Eventos.Tigrinho;
import static Util.Moldes.barbossa;
import static Util.Moldes.drake;
import static Util.Moldes.endrick;
import static Util.Moldes.loudCoringa;
import static Util.Moldes.loudSacy;
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
    List<Evento> eventosAleatorios = new ArrayList<>(); // lista de eventos aleatorios
    List<Batalha> batalhasAleatorias = new ArrayList<>(); // lista de batalhas aleatorias (pode ser usada pra escolher as batalhas dos andares ímpares)

    // moldes de eventos aleatorios
    Loja loja = new Loja();
    Fogueira fogueira = new Fogueira();
    Tigrinho tigrinho = new Tigrinho();

    // moldes de batalhas
    Batalha tripleTeAmigos = new Batalha(drake.criaCopia(), tripleT.criaCopia(), endrick.criaCopia());
    Batalha sabrinaAtreides = new Batalha(loudSacy.criaCopia(), paulAtreides.criaCopia(), sabrinaCarpenter.criaCopia());
    Batalha coringaBarbossaEndrick = new Batalha(loudCoringa.criaCopia(), barbossa.criaCopia(), endrick.criaCopia());
    Batalha barbossaSabrinaCoringa = new Batalha(barbossa.criaCopia(), sabrinaCarpenter.criaCopia(), drake.criaCopia());

    public ArvoreEventos(int n, int p){
        this.n = n;
        this.p = p;

        // adicionar os eventos que podem cair aleatoriamente aq --
        eventosAleatorios.add(loja.criaCopia());
        eventosAleatorios.add(fogueira.criaCopia());
        eventosAleatorios.add(tigrinho.criaCopia());

        // adicionar as batalhas aleatórias --
        batalhasAleatorias.add(tripleTeAmigos);
        batalhasAleatorias.add(sabrinaAtreides);
        batalhasAleatorias.add(coringaBarbossaEndrick);
        batalhasAleatorias.add(barbossaSabrinaCoringa);
    }

    public long getTotalNos(){
        if (n == 1) return p+1;

        long numerador = ((long) Math.pow(n, p + 1) - 1);
        long denominador = n - 1;

        return numerador / denominador;
    }


    /** Define como os eventos serao distribuidos para um conjunto de nós irmãos */
    public List<Evento> escolherEvento(int profundidadeAtual) {
        List<Evento> opcoes = new ArrayList<>();

        // primeiro evento: luta facil
        if (profundidadeAtual == 0) {
            for (int i = 0; i < n; i++) {
                // se quiser testar um evento especifico e so substituir aq e comentar a batalha
                // opcoes.add(new Tigrinho());
                opcoes.add(new Batalha(loudCoringa.criaCopia(), barbossa.criaCopia(), drake.criaCopia()));
            }
        } 
        
        // ultimo evento: boss (todos os inimigos!)
        else if (profundidadeAtual == p) {
            for (int i = 0; i < n; i++) {
                opcoes.add(new Batalha(
                barbossa.criaCopia(), loudCoringa.criaCopia(), 
                endrick.criaCopia(), drake.criaCopia(), paulAtreides.criaCopia(), 
                sabrinaCarpenter.criaCopia(), tripleT.criaCopia(), loudSacy.criaCopia()));
            }
        }

        // profundidades pares: embaralha a lista de eventos aleatórios e entrega os n primeiros.
        else if (profundidadeAtual % 2 == 0) {
            List<Evento> eventos = new ArrayList<>();
            
            for (Evento e : eventosAleatorios) {
                eventos.add(e.criaCopia());
            }

            Collections.shuffle(eventos);

            // se tiver mais nós filhos do que eventos aleatorios, vai repetir os eventos aleatorios.
            for (int i = 0; i < n; i++) {
                opcoes.add(eventos.get(i % eventos.size()).criaCopia());
            }
        }

        // profundidades ímpares: embaralha a lista de batalhas aleatórias e entrega as n primeiras
        else {
            List<Batalha> batalhas = new ArrayList<>();

            for (Batalha b : batalhasAleatorias) {
                batalhas.add(b.criaCopia());
            }
            
            Collections.shuffle(batalhas);

            // se tiver mais nós filhos do que batalhas aleatorias, vai repetir as batalhas.
            for (int i = 0; i < n; i++) {
                opcoes.add(batalhas.get(i % batalhas.size()).criaCopia());
            }
        }
        return opcoes;
    }

    /** cria uma arvore baseada nos parametros da instancia atual dessa classe e retorna a raiz.
    distribui os eventos para os filhos baseados na funcao escolherEvento */
    public DefaultMutableTreeNode criarArvore(int profundidade, Evento evento) {       
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(evento);
        
        // caso base
        if (profundidade == p) return node;

        // gera a lista de eventos dos filhos do no atual
        List<Evento> eventosFilhos = escolherEvento(profundidade + 1);

        // cria os filhos distribuindo os eventos gerados
        for (int i = 0; i < n; i++){
            DefaultMutableTreeNode novonode = criarArvore(profundidade + 1, eventosFilhos.get(i));
            node.add(novonode);
        }

        return node;
    }

    /** cria uma arvore baseada nos parametros da instancia atual dessa classe e retorna a raiz.
    distribui os eventos para os filhos baseados na funcao escolherEvento */
    public DefaultMutableTreeNode criarArvore() {
        return criarArvore(0, escolherEvento(0).get(0)); // gera o evento da raiz e chama a função recursiva
    }

    // criararvore antigo
    // public DefaultMutableTreeNode criarArvore(int profundidade) {
    //     DefaultMutableTreeNode node = new DefaultMutableTreeNode(escolherEvento(profundidade));
        
    //     // caso base
    //     if (profundidade == p) return node;

    //     // cria n nos
    //     for (int i = 0; i < n; i ++){
    //         DefaultMutableTreeNode novonode = criarArvore(profundidade+1);
    //         node.add(novonode);
    //     }

    //     return node;
    // }


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
