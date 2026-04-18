package telas.eventos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import static fabricas.FabricaInimigos.amoeba;
import static fabricas.FabricaInimigos.barbossa;
import static fabricas.FabricaInimigos.drake;
import static fabricas.FabricaInimigos.endrick;
import static fabricas.FabricaInimigos.kungFuPanda;
import static fabricas.FabricaInimigos.loudCoringa;
import static fabricas.FabricaInimigos.loudSacy;
import static fabricas.FabricaInimigos.paulAtreides;
import static fabricas.FabricaInimigos.paulAtreidesSupremo;
import static fabricas.FabricaInimigos.sabrinaCarpenter;

import telas.eventos.combate.Batalha;
import util.InputHandler;

/** cria uma arvore onde cada nó representa um evento
 * @param n quantidade de filhos por nó
 * @param p profundidade (quantidade total de eventos: p - 1, começa da profundidade 0)
 */
public class ArvoreEventos {
    int n; // quantidade de filhos por nó
    int p; // profundidade (QUANTIDADE TOTAL DE BATALHAS: P - 1 PQ COMEÇA DO PROFUNDIDADE 0)

    List<Evento> eventosCuradores = new ArrayList<>(); // lista de eventos que podem curar o jogador
    List<Evento> eventosNeutros = new ArrayList<>(); // lista de eventos que podem ou nao ajudar o jogador
    List<Evento> todosEventos = new ArrayList<>(); // lista de todos os eventos, usada caso precise sortear um evento completamente aleatorio sem se importar com a dificuldade

    List<Evento> batalhasTriviais = new ArrayList<>();
    List<Evento> batalhasMedias = new ArrayList<>(); 
    List<Evento> batalhasDesafiadoras = new ArrayList<>();
    List<Evento> batalhasElite = new ArrayList<>();

    // moldes de eventos aleatorios
    Loja loja = new Loja();
    Fogueira fogueira = new Fogueira();
    Tigrinho tigrinho = new Tigrinho();
    Tesouro tesouro = new Tesouro();

    // moldes de batalhas

    // triviais ( dificuldade < 3 )
    Batalha amoebas = new Batalha(amoeba.criaCopia(), amoeba.criaCopia());
    Batalha barbossaSolo = new Batalha(barbossa.criaCopia());
    Batalha drakendrick = new Batalha(endrick.criaCopia(), drake.criaCopia());
    Batalha pandaSolo = new Batalha(kungFuPanda.criaCopia());

    // medias ( dificuldade entre 4 e 5 )
    Batalha media1 = new Batalha(sabrinaCarpenter.criaCopia());
    Batalha media2 = new Batalha(loudCoringa.criaCopia(), barbossa.criaCopia());
    Batalha media3 = new Batalha(amoeba.criaCopia(), amoeba.criaCopia(), loudCoringa.criaCopia());
    Batalha loud = new Batalha(loudCoringa.criaCopia(), loudSacy.criaCopia());

    // desafiadoras ( dificuldade entre 6 e 7 )
    Batalha desafiadora1 = new Batalha(loudCoringa.criaCopia(), loudSacy.criaCopia(), barbossa.criaCopia());
    Batalha desafiadora2 = new Batalha(sabrinaCarpenter.criaCopia(), drake.criaCopia(), loudCoringa.criaCopia());

    // elite ( maior que 7 )
    Batalha sabrinaAtreides = new Batalha(drake.criaCopia(), paulAtreides.criaCopia(), sabrinaCarpenter.criaCopia());

    // boss ( contem inimigo de tier 5 )
    Batalha lisanAlGaib = new Batalha(paulAtreidesSupremo.criaCopia(), drake.criaCopia(), amoeba.criaCopia()); 

    public ArvoreEventos(int n, int p){
        this.n = n;
        this.p = p;

        // adicionar as batalhas triviais --
        batalhasTriviais.add(amoebas);
        batalhasTriviais.add(barbossaSolo);
        batalhasTriviais.add(drakendrick);
        batalhasTriviais.add(pandaSolo);

        // adicionar as batalhas médias --
        batalhasMedias.add(media1);
        batalhasMedias.add(media2);
        batalhasMedias.add(media3);
        batalhasMedias.add(loud);

        // adicionar as batalhas desafiadoras --
        batalhasDesafiadoras.add(desafiadora1);
        batalhasDesafiadoras.add(desafiadora2);

        // adicionar as batalhas elite --
        batalhasElite.add(sabrinaAtreides);

        // lista com todos os eventos
        todosEventos.add(loja.criaCopia());
        todosEventos.add(fogueira.criaCopia());
        todosEventos.add(tigrinho.criaCopia());
        todosEventos.add(tesouro.criaCopia());
        
        // adicionar os eventos que podem cair aleatoriamente aq --
        eventosCuradores.add(loja.criaCopia());
        eventosCuradores.add(fogueira.criaCopia());
        eventosCuradores.add(tigrinho.criaCopia());

        // eventos neutros
        eventosNeutros.add(tigrinho.criaCopia());
    }

    public int getProfundidadeMax() {
        return p;
    }

    public int getNosPorFilho() {
        return n;
    }

    /** fefine como os eventos serao distribuidos para um conjunto de nós irmãos */
    public List<Evento> escolherEvento(int profundidadeAtual) {
        List<Evento> opcoes = new ArrayList<>();
        List<Evento> sorteados = new ArrayList<>();

        // andares predefinidios -----------------------------

        // primeiro andar (batalha facil)
        if (profundidadeAtual == 0) { 
            // adiciona aqui opcoes.add evento que vc quer testar
            opcoes.add(loja.criaCopia());
            sorteados.addAll(getEventosAleatorios(batalhasTriviais));
        }    
        // ultimo andar (boss)
        else if (profundidadeAtual == p) { 
            sorteados.add(lisanAlGaib.criaCopia());
        }
        // recompensa do meio do mapa (andar pacifico)
        else if (profundidadeAtual == p/2) { 
            opcoes.add(tesouro.criaCopia());
        }
        
        // andar logo antes do boss (cura ou loja)
        else if (profundidadeAtual == p - 1) { 
            sorteados.addAll(getEventosAleatorios(eventosCuradores));
        }

        // andares aleatorios -----------------------------------------

        // primeiros 4 andares
        else if (profundidadeAtual < 3 ) {
            sorteados.addAll(getEventosAleatorios(batalhasTriviais));
        }

        // até chegar na metade do mapa
        else if (profundidadeAtual < p/2) {
            sorteados.addAll(getEventosAleatorios(batalhasTriviais, batalhasMedias, eventosNeutros));
        }

        // até 3 quartos do mapa
        else if (profundidadeAtual < 3*p/4) {
            sorteados.addAll(getEventosAleatorios(batalhasMedias, batalhasDesafiadoras, eventosNeutros));
        }

        // ate o penultimo andar (curinha pre boss)
        else if (profundidadeAtual < p - 1) {
            sorteados.addAll(getEventosAleatorios(batalhasDesafiadoras, batalhasElite, eventosNeutros));
        } 

        // se por algum motivo tiver algum erro de logica e nao caia em nenhum, vai cair aqui (qualquer evento)
        else {
            sorteados.addAll(getEventosAleatorios(todosEventos));
        }

        // se tiver menos eventos que n gera so a quantidade de eventos disponiveis
        int quantidadeOpcoes = n;
        if (sorteados.size() < n) {
            quantidadeOpcoes = sorteados.size();
        }

        // adiciona os sorteados as opçoes, repetindo caso a quantidade de sorteados seja menor que n
        if (!sorteados.isEmpty()) {
            for (int i = 0; i < quantidadeOpcoes; i++) {
                opcoes.add(sorteados.get(i % sorteados.size()).criaCopia());
            }
        }

        // fallback caso opcoes acabe vindo vazio
        if (opcoes.isEmpty()) {
            InputHandler.esperar("Algum erro ocorreu na escolha de eventos. gerando eventos aleatórios");
            opcoes.addAll(getEventosAleatorios(todosEventos));
        }
        return opcoes;
    }

    /** retorna os filhos do no recebido */
    public List<DefaultMutableTreeNode> getFilhos (DefaultMutableTreeNode node) {
        List<DefaultMutableTreeNode> listaFilhos = new ArrayList<>();

        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode filho = (DefaultMutableTreeNode) node.getChildAt(i);
            listaFilhos.add(filho);
        }

        return listaFilhos;
    }

    /** retorna os eventos nos filhos do no recebido */
        public List<Evento> getEventos (DefaultMutableTreeNode node) {
            List<Evento> listaEventos = new ArrayList<>();

            for (int i = 0; i < node.getChildCount(); i++) {
                DefaultMutableTreeNode filho = (DefaultMutableTreeNode) node.getChildAt(i);
                Evento evento = (Evento) filho.getUserObject();        
                listaEventos.add(evento);
            }

            return listaEventos;
        }

    /** gera apenas os filhos de um nó especifico, baseado nas regras do escolherEvento
     * se tiver menos opçoes disponiveis que n, gera o maximo de opçoes disponiveis
     */
    public void expandirNo(DefaultMutableTreeNode node) {
        // checagens de segurança
        if (node == null) return;
        if (node.getLevel() >= p) return;
        if (node.getChildCount() > 0) return;

        List<Evento> eventosFilhos = escolherEvento(node.getLevel() + 1);

        int quantidadeOpcoes = n;
        if (eventosFilhos.size() < n) {
            quantidadeOpcoes = eventosFilhos.size();
        }

        for (int i = 0; i < quantidadeOpcoes; i++) {
            node.add(new DefaultMutableTreeNode(eventosFilhos.get(i)));
        }
    }

    /** cria uma arvore completa baseada nos parametros da instancia atual dessa classe e retorna a raiz.
    distribui os eventos para os filhos baseados na funcao escolherEvento */
    public DefaultMutableTreeNode criarArvore(int profundidade, Evento evento) {       
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(evento);
        
        // caso base
        if (profundidade == p) return node;

        // gera a lista de eventos dos filhos do no atual
        List<Evento> eventosFilhos = escolherEvento(profundidade + 1);

        int quantidadeOpcoes = n;
        if (eventosFilhos.size() < n) {
            quantidadeOpcoes = eventosFilhos.size();
        }
        
        // cria os filhos distribuindo os eventos gerados
        for (int i = 0; i < quantidadeOpcoes; i++){
            DefaultMutableTreeNode novonode = criarArvore(profundidade + 1, eventosFilhos.get(i));
            node.add(novonode);
        }

        return node;
    }

    /** cria uma arvore completa baseada nos parametros da instancia atual dessa classe e retorna a raiz.
    distribui os eventos para os filhos baseados na funcao escolherEvento */
    public DefaultMutableTreeNode criarArvore() {
        return criarArvore(0, escolherEvento(0).get(0)); // gera o evento da raiz e chama a função recursiva
    }

    
    /** recebe listas de eventos e retorna uma lista só, com todos os itens, já embaralhada */
    @SafeVarargs
    public final List<Evento> getEventosAleatorios(List<Evento>... listasEventos) {
        List<Evento> eventos = new ArrayList<>();

        for (List<Evento> lista : listasEventos) {
            eventos.addAll(lista);
        }

        Collections.shuffle(eventos);
        return eventos;
    }

    /** recebe eventos e retorna uma lista com todos e ja embaralhada */
    public List<Evento> getEventosAleatorios(Evento... listaEventos) {
        List<Evento> eventos = new ArrayList<>();

        Collections.addAll(eventos, listaEventos);

        Collections.shuffle(eventos);
        return eventos;
    }
}

