package telas.eventos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import fabricas.FabricaEscolhas;
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
import util.RNGHandler;
import visual.Cor;

/** cria uma arvore onde cada nó representa um evento
 * @param n quantidade de filhos por nó
 * @param p profundidade (quantidade total de eventos: p - 1, começa da profundidade 0)
 */
public class ArvoreEventos {
    int n; // quantidade de filhos por nó
    int p; // profundidade (QUANTIDADE TOTAL DE BATALHAS: P - 1 PQ COMEÇA DO PROFUNDIDADE 0)

    public final static List<Evento> eventosCuradores = new ArrayList<>(); // lista de eventos que podem curar o jogador
    public final static List<Evento> eventosNeutros = new ArrayList<>(); // lista de eventos que podem ou nao ajudar o jogador
    public final static List<Evento> lojas = new ArrayList<>(); // lista de eventos de loja (so tem um mas precisa pro enum)
    public final static List<Evento> recompensas = new ArrayList<>(); // lista de eventos de recompensa (so tem o tesouro)

    public final static List<Evento> batalhasTriviais = new ArrayList<>();
    public final static List<Evento> batalhasMedias = new ArrayList<>(); 
    public final static List<Evento> batalhasDesafiadoras = new ArrayList<>();
    public final static List<Evento> batalhasElite = new ArrayList<>(); // tipo de batalha mais dificil no meio do mapa
    public final static List<Evento> batalhasFinais = new ArrayList<>(); // batalhas de fim de andar(ato, mundo, sla como vai chamar ainda)

    /** tipos de nós */
    public enum tipoNode {
        // BATALHAS !
        BATALHA_TRIVIAL(batalhasTriviais, Cor.azul + "Batalha trivial"),
        BATALHA_MEDIA(batalhasMedias, Cor.verde + "Batalha média"),
        BATALHA_DESAFIADORA(batalhasDesafiadoras, Cor.amarelo + "Batalha desafiadora"),
        BATALHA_ELITE(batalhasElite, Cor.vermelho + "Batalha elite"),
        BATALHA_FINAL(batalhasFinais, Cor.roxo + "Batalha final"),
        
        // outros tipos de nós
        EVENTO_NEUTRO(eventosNeutros, Cor.cinza + "[ ? ]"),
        CURA(eventosCuradores, Cor.verde + "Fogueira"),
        LOJA(lojas, Cor.ciano + "Loja"),
        RECOMPENSA(recompensas, Cor.amareloClaro + "Tesouro.");

        private final List<Evento> eventos;
        private final String descricao;

        tipoNode(List<Evento> eventos, String descricao) {
            this.eventos = eventos;
            this.descricao = descricao;
        }   

        public List<Evento> getEventos(int quantidade) {
            List<Evento> tempEventos = new ArrayList<>();
            tempEventos.addAll(eventos);
            Collections.shuffle(tempEventos);
            return tempEventos.subList(0, Math.min(quantidade, tempEventos.size()));
        }

        public Evento getEvento(){
            return getEventos(1).get(0); 
        }

        public int getQuantidadeEventos() {
            return eventos.size();
        }

        @Override
        public String toString() {
            return this.descricao + Cor.reset;
        }
    }

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
        batalhasTriviais.clear();
        batalhasTriviais.add(amoebas);
        batalhasTriviais.add(barbossaSolo);
        batalhasTriviais.add(drakendrick);
        batalhasTriviais.add(pandaSolo);

        // adicionar as batalhas médias --
        batalhasMedias.clear();
        batalhasMedias.add(media1);
        batalhasMedias.add(media2);
        batalhasMedias.add(media3);
        batalhasMedias.add(loud);

        // adicionar as batalhas desafiadoras --
        batalhasDesafiadoras.clear();
        batalhasDesafiadoras.add(desafiadora1);
        batalhasDesafiadoras.add(desafiadora2);

        // adicionar as batalhas elite --
        batalhasElite.clear();
        batalhasElite.add(sabrinaAtreides);

        // adicionar as batalhas finais --
        batalhasFinais.clear();
        batalhasFinais.add(lisanAlGaib);
        
        // adicionar os eventos que podem cair aleatoriamente aq --
        eventosCuradores.clear();
        eventosCuradores.add(fogueira.criaCopia());

        // eventos neutros
        eventosNeutros.clear();
        eventosNeutros.add(tigrinho.criaCopia());
        eventosNeutros.add(loja.criaCopia());
        eventosNeutros.addAll(batalhasMedias);
        eventosNeutros.addAll(FabricaEscolhas.escolhasDisponiveis);

        // lojas
        lojas.clear();
        lojas.add(loja.criaCopia());

        // recompensas
        recompensas.clear();
        recompensas.add(tesouro.criaCopia());
    }

    public int getProfundidadeMax() {
        return p;
    }

    public int getNosPorFilho() {
        return n;
    }

    /** fefine como os eventos serao distribuidos para um conjunto de nós irmãos */
    public List<tipoNode> escolherEvento(int profundidadeAtual) {
        List<tipoNode> opcoes = new ArrayList<>();

        // andares predefinidios -----------------------------

        // primeiro andar (batalha facil)
        if (profundidadeAtual == 0) { 
            // adiciona aqui opcoes.add tipoNode que vc quer testar pra ir primeir
            // opcoes.add(tipoNode.EVENTO_NEUTRO);
            opcoes.add(tipoNode.BATALHA_TRIVIAL);
        }    
        // ultimo andar (boss)
        else if (profundidadeAtual == p) { 
            opcoes.add(tipoNode.BATALHA_FINAL);
        }
        // recompensa do meio do mapa (andar pacifico)
        else if (profundidadeAtual == p/2) { 
            opcoes.add(tipoNode.RECOMPENSA);
        }
        
        // andar logo antes do boss (cura ou loja)
        else if (profundidadeAtual == p - 1) { 
            opcoes.add(tipoNode.CURA);
        }

        // andares aleatorios -----------------------------------------

        // primeira e segunda lutas
        else if (profundidadeAtual < 2 ) {
            opcoes.add(tipoNode.BATALHA_TRIVIAL);
        }

        // até chegar na metade do mapa
        else if (profundidadeAtual < p/2) {
            opcoes.addAll(Arrays.asList(tipoNode.BATALHA_TRIVIAL, tipoNode.BATALHA_MEDIA,
                tipoNode.EVENTO_NEUTRO, tipoNode.LOJA));
        }

        // até 3 quartos do mapa
        else if (profundidadeAtual < 3*p/4) {
            opcoes.addAll(Arrays.asList(tipoNode.BATALHA_MEDIA, tipoNode.BATALHA_DESAFIADORA, 
                tipoNode.BATALHA_ELITE, tipoNode.EVENTO_NEUTRO, tipoNode.LOJA, tipoNode.CURA));
        }

        // ate o penultimo andar (curinha pre boss)
        else if (profundidadeAtual < p - 1) {
            opcoes.addAll(Arrays.asList(tipoNode.BATALHA_DESAFIADORA, tipoNode.BATALHA_ELITE, 
                tipoNode.EVENTO_NEUTRO, tipoNode.LOJA, tipoNode.CURA));
        } 

        // se por algum motivo tiver algum erro de logica e nao caia em nenhum, vai cair nisso aqui
        else {
            opcoes.add(tipoNode.LOJA);
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

    /** retorna os tipoNodes nos filhos do no recebido */
        public List<tipoNode> getTipoNodes (DefaultMutableTreeNode node) {
            List<tipoNode> listaTipoNodes = new ArrayList<>();

            for (int i = 0; i < node.getChildCount(); i++) {
                DefaultMutableTreeNode filho = (DefaultMutableTreeNode) node.getChildAt(i);
                tipoNode tipoNode = (tipoNode) filho.getUserObject();
                listaTipoNodes.add(tipoNode);
            }

            return listaTipoNodes;
        }

    /** gera apenas os filhos de um nó especifico, baseado nas regras do escolherEvento
     * se tiver menos opçoes disponiveis que n, gera o maximo de opçoes disponiveis
     * (funçao feita com ajuda de ia)
     */
    public void expandirNo(DefaultMutableTreeNode node) {
        // checagens de segurança
        if (node == null) return;
        if (node.getLevel() >= p) return;
        if (node.getChildCount() > 0) return;

        List<tipoNode> tiposPossiveis = escolherEvento(node.getLevel() + 1);
        if (tiposPossiveis.isEmpty()) return;

        List<tipoNode> tiposElegiveis = new ArrayList<>();
        List<Integer> eventosRestantesPorTipo = new ArrayList<>();
        int totalEventosDisponiveis = 0;

        for (tipoNode tipo : tiposPossiveis) {
            int quantidadeTipo = tipo.getQuantidadeEventos();
            if (quantidadeTipo <= 0) continue;

            tiposElegiveis.add(tipo);
            eventosRestantesPorTipo.add(quantidadeTipo);
            totalEventosDisponiveis += quantidadeTipo;
        }

        if (totalEventosDisponiveis <= 0) return;

        int quantidadeOpcoes = Math.min(n, totalEventosDisponiveis);

        for (int i = 0; i < quantidadeOpcoes; i++) {
            if (tiposElegiveis.isEmpty()) break;

            int indiceSorteado = RNGHandler.getGen().nextInt(tiposElegiveis.size());
            tipoNode tipoSorteado = tiposElegiveis.get(indiceSorteado);
            node.add(new DefaultMutableTreeNode(tipoSorteado));

            int restante = eventosRestantesPorTipo.get(indiceSorteado) - 1;
            if (restante <= 0) {
                tiposElegiveis.remove(indiceSorteado);
                eventosRestantesPorTipo.remove(indiceSorteado);
            } else {
                eventosRestantesPorTipo.set(indiceSorteado, restante);
            }
        }
    }

    /** cria uma arvore completa baseada nos parametros da instancia atual dessa classe e retorna a raiz.
    distribui os eventos para os filhos baseados na funcao escolherEvento */
    public DefaultMutableTreeNode criarArvore(int profundidade, tipoNode evento) {       
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(evento);
        
        // caso base
        if (profundidade == p) return node;

        // gera a lista de eventos dos filhos do no atual
        List<tipoNode> eventosFilhos = escolherEvento(profundidade + 1);
      
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

