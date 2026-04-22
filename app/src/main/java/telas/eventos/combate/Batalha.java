package telas.eventos.combate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import baralho.Mao;
import baralho.PilhaCompra;
import baralho.PilhaDescarte;
import batalhaListeners.batalhaListener;
import batalhaListeners.efeitos.Efeito;
import batalhaListeners.itens.passivos.ItemPassivo;
import batalhaListeners.poderes.Poder;
import cartas.Carta;
import cartas.Carta.raridades;
import entidades.Entidade;
import entidades.Heroi;
import entidades.Inimigo;
import telas.eventos.Evento;
import util.InputHandler;
import util.Recompensas;
import visual.Arte;
import visual.Cor;
import visual.Textos;

public class Batalha extends Evento {
    private int turno; // 0 -> heroi, 1 -> inimigos
    private boolean fimBatalha = false;

    private List<Inimigo> inimigos; // lista viva dos inimigos no combate atual
    private Inimigo[] moldesInimigos; // moldes imutaveis para recriar a batalha
    private List<Inimigo> novosInimigos = new ArrayList<>(); // lista de inimigos a serem adicionados na batalha quando possivel

    private int sleepFimRodada = 200; // tempo de sleep depois de printar as mensagens de fim de rodada
    private int recompensa = 0; // recompensa em dinheiro pela batalha, baseada no tier dos inimigos
    private int dificuldadeTotal = 0; // soma da dificuldade(tier) de cada inimigo
    private boolean primeiraRodada = true;

    private Mao mao = new Mao();
    private PilhaCompra pilhaCompra = new PilhaCompra();
    private PilhaDescarte pilhaDescarte = new PilhaDescarte();
    private PilhaDescarte pilhaConsumir = new PilhaDescarte(); // <- pra cartas que nao voltam pra sua mao durante o combate

    private Carta cartaEmUso; // guarda a carta sendo utilizada no momento

    // subscribers --------
    private ArrayList<batalhaListener> subscribers = new ArrayList<>();
    private ArrayList<batalhaListener> novosSubscribers = new ArrayList<>();
    private ArrayList<Efeito> listaEfeitos = new ArrayList<>(); 
    private ArrayList<Poder> listaPoderes = new ArrayList<>();
    // -------------

    public Batalha(Inimigo... _inimigos){
        this.inimigos = new ArrayList<>(Arrays.asList(copiarInimigos(_inimigos)));
        this.moldesInimigos = copiarInimigos(_inimigos);
        for (Inimigo inimigo : inimigos) {
            recompensa += inimigo.getRecompensa();
            dificuldadeTotal += inimigo.getTier();
        }
    }

    public List<Inimigo> getInimigos() {
        return inimigos;
    }

    public Heroi getHeroi(){
        return this.heroi;
    }   

    public ArrayList<Efeito> getListaEfeitos() {
        return listaEfeitos;
    }

    public ArrayList<Poder> getListaPoderes() {
        return listaPoderes;
    }

    public PilhaDescarte getPilhaDescarte() {
        return pilhaDescarte;
    }

    public PilhaCompra getPilhaCompra() {
        return pilhaCompra;
    }

    public Mao getMao() {
        return mao;
    }

    public Carta getCartaEmUso() {
        return cartaEmUso;
    }

    
    /** retorna al ista de inimigos vivos convertidas pra array */
    public Inimigo[] inimigosAtuaisArray() {
        return inimigos.stream().filter(i -> i.estaVivo()).toArray(Inimigo[]::new);
    }

    /** aplica dano mitigado e notifica o alvo com a vida perdida */
    public int causarDano(Entidade alvo, int dano, Entidade atacante){
        int vidaPerdida = alvo.receberDanoRetornandoVidaPerdida(dano);
        notificarDanoRecebido(alvo, atacante, vidaPerdida);
        return vidaPerdida;
    }

    /** aplica dano puro (direto na vida) e notifica o alvo com a vida pegitrdida */
    public int causarDanoDireto(Entidade alvo, int dano, Entidade atacante){
        int vidaPerdida = alvo.receberDanoDiretoRetornandoVidaPerdida(dano);
        notificarDanoRecebido(alvo, atacante, vidaPerdida);
        return vidaPerdida;
    }

    /** retorna os subscribers da entidade passada */
    public List<batalhaListener> getSubscribers(Entidade alvo) {
        return subscribers.stream()
                .filter(subscriber -> subscriber.getAlvo() == alvo)
                .collect(Collectors.toList());
    }

    /** adiciona um inimigo na lista de inimigos da batalha! */
    public void adicionarInimigo(Inimigo inimigo){
        if (inimigo != null) {
            novosInimigos.add(inimigo);
        }
    }

    /** adiciona um subscriber na lista de subscribers e notifica onCreate */
    public void adicionarSubscriber(batalhaListener novoSubscriber) { 
        for (batalhaListener subscriber : subscribers) {
            if (subscriber.addStack(this, novoSubscriber)){
                return;
            }
        }

        this.subscribers.add(novoSubscriber);
        novoSubscriber.onCreate(this, heroi);
        
        // coloca os subscribers que afetam o heroi por ultimo e depois ordenam pela prioridade.
        this.subscribers.sort(
            Comparator.comparing((batalhaListener subscriber) -> subscriber.getAlvo() == heroi)
                .thenComparing(batalhaListener::getPrioridade)
        );

        /** adiciona nas listas correspondentes, pra printar o estado da batalha corretamente */
        if (novoSubscriber instanceof Efeito e)
            this.listaEfeitos.add(e);
        else if (novoSubscriber instanceof Poder p)
            this.listaPoderes.add(p);
    }

    public void adicionarSubscribers(List<? extends batalhaListener> _subscribers){
        for (batalhaListener item : _subscribers) {
            adicionarSubscriber(item);
        }
    }

    /** adiciona um subscriber em uma lista separada, para ser colocado na batalha quando possivel. */
    public void adicionarFuturoSubscriber(batalhaListener novo) {
        novosSubscribers.add(novo);
    }

    /** recebe heroi, define as variáveis e chama o metodo principal. */
    @Override
    public void iniciar(Heroi heroi){ 
        this.heroi = heroi;

        inicializaItens();
        inicializaHeroi();

        // inicializa a pilha de compras (recebe o baralho e embaralha)
        pilhaCompra.addCartas(new ArrayList<>(heroi.getBaralho()));
        pilhaCompra.shuffleStack();
        
        turno = 0; // 0: turno do heroi 
        
        for (Inimigo inimigo : inimigos) 
            inimigo.escolheAcao();

        batalha();
    }

    private void batalha(){
        // loop principal: checa se o heroi ou ao menos um inimigo esta vivo
        while(heroi.estaVivo() == true && inimigos.stream().anyMatch(i -> i.estaVivo() == true && fimBatalha == false)){
            if (turno == 0 && !fimBatalha) turnoHeroi();
            else if (turno != 0 && !fimBatalha) turnoInimigos();
        }
        
        fimBatalha(); 
    }

    private void turnoHeroi(){
        if (primeiraRodada) {
            mao.inicioBatalha(pilhaCompra, pilhaDescarte);
            primeiraRodada = false;
        }
        else {
            mao.completaCinco(pilhaCompra, pilhaDescarte);
        }
        
        boolean primeiroLoop = true; // se true, printa a animaçao de batalha
        boolean escolhaInvalida = false; // se true, mostra texto

        // loop da escolha de ação
        while(true){ 
            Textos.limpaTela();

            if (notificaMorte() == 0) {
                return;
            }

            if (escolhaInvalida){
                System.out.println();
                System.out.println(Textos.escolhaInvalida(mao.getSize() + 3)); // considera o itens, pilhacompra e pilha descarte
                InputHandler.esperar();
                Textos.limpaTela();
                escolhaInvalida = false;  
            }

            // mostra a animaçao de batalha apenas caso seja o inicio da rodada e o jogador nao esteja usando deck teste
            printaBatalha(primeiroLoop);
            primeiroLoop = false;

            mostrarEscolhas();

            int escolha = InputHandler.lerInt() - 1;

            if (escolha == -1) // opção de passar o turno
                break; 

            else if (escolha == mao.getSize()) { // ver itens
                mostrarItensAtivos();
                continue;
            }

            else if (escolha == mao.getSize() + 1) {  // ver pilha de compras
                pilhaCompra.mostrar();
                continue;
            }

            else if (escolha == mao.getSize() + 2) { // ver pilha de descarte
                visual.Textos.limpaTela();
                System.out.println(Cor.amareloClaro + "Pilha de descarte" + Cor.reset);
                System.out.println();

                pilhaDescarte.mostrar();
                System.out.println();

                Cor.printaCinzaLn("- - - - - - = = = = = = - - - - - -");

                System.out.println();
                System.out.println(Cor.roxo + "Cartas consumidas:" + Cor.reset);
                System.out.println();

                pilhaConsumir.mostrar();
                InputHandler.esperar();
                continue;
            }

            else if ( 0 <= escolha && escolha < mao.getSize()) { // usar carta, validada depois
                // continua a execuçao normalmente
            }

            else { // opcao invalida
                escolhaInvalida = true;
                continue;
            }

            if (usaCarta(escolha) == -1){ // uso cancelado
                // remove instantaneos criados durante a tentativa cancelada
                limpaSubscribers();
                cartaEmUso = null;
                continue;
            }

            if (notificaMorte() == 0) {
                return;
            }

            // atualiza a lista de subscribers
            limpaSubscribers();
            addNovosSubscribers();

            // se conseguir usar todas as cartas da mao puxa mais 5 e ganha 2 de energia bonus, 
            // injeçao de dopamina assim q vc mantem um jogador preso
            if (mao.getSize() == 0){
                rodadaBonus();
            }

            // notifica possiveis meia vida
            for (Inimigo inimigo : inimigos) {
                inimigo.checkMeiaVida(inimigo, heroi, this);
            }
            heroi.checkMeiaVida(heroi, heroi, this);

            addNovosInimigos();
            mao.limpaRemover(this);
        }            

        mao.limpa(this);    

        passaTurno();
    }

    private void turnoInimigos(){
        Textos.limpaTela();
        Textos.printaBonito(Cor.txtCinza(Arte.bordaHud9), 2,2); Textos.sleep(sleepFimRodada);

        for (Inimigo inimigo : inimigos) {
            if (inimigo.estaVivo()){ // adicionei isso pq joguei uma partida aqui e tomei hit de um inimigo morto.
                inimigo.passaRodada(); // reseta os bonus (escudo por enquanto)
                inimigo.resultadoAcao(heroi); // printa oq ele ta fazendo (antes de fazer pq as vezes ele se mata)
                inimigo.realizarAcao(heroi, this); // faz oq ele ia fazer
                inimigo.escolheAcao(); // escolhe prox ação

                // checa possiveis meia vida
                inimigo.checkMeiaVida(inimigo, heroi, this);
                heroi.checkMeiaVida(heroi, heroi, this);
            }
        }

        System.out.println();
    
        passaTurno();
    }

    private void fimBatalha(){
        System.out.println();
        Cor.printaAmarelo("DUELO ENCERRADO!\n");
        System.out.println();

        Textos.sleep(1500);
        Textos.limpaTela();

        // tira as referencias de mao e pilha do heroi
        heroi.fimBatalhaReset();
        heroi.setMaoAtual(null);
        heroi.setPilhaCompra(null);
        heroi.setPilhaDescarte(null);
        
        for (batalhaListener sub : subscribers) {
            sub.onBattleEnd(this, heroi);
        }

        if(heroi.estaVivo()){
            vitoria();
        } else {
            derrota();
        }
    }

    /** troca de turno e se voltar pra vez do heroi chama o passaRodada  */
    public void passaTurno(){
        turno = (turno == 0) ? 1 : 0;

        if (notificaMorte() == 0) {
            return;
        }

        if (turno == 0) passaRodada();
        
        addNovosInimigos();
    }


    /** atualiza os estados entre as rodadas e exibe as mensagens. */
    private void passaRodada(){
        heroi.resetEfeitos();

        for (Inimigo inimigo : inimigos) {
            inimigo.resetEfeitos();
        }
        
        limpaSubscribers();

        boolean msgPrintada = false;
        
        // chama onRoundStart de todos os subscribers, e printa as mensagens de fim de rodada caso tenham.
        for (batalhaListener subscriber : subscribers) {
            Entidade alvo = subscriber.getAlvo();
            if (alvo != null && alvo.estaVivo()){
                String msgFimRodada = subscriber.getMsgFimRodada(this, heroi);

                if (!msgFimRodada.equals("")) {
                    Textos.printaBonito(msgFimRodada + "\n", 5,2);
                    msgPrintada = true;
                }

                subscriber.onRoundStart(this, heroi);

                if (!msgFimRodada.equals("")) {
                    Textos.sleep(sleepFimRodada);
                }
            }
        }

        // se alguma msgfimrodada nao for vazio pula uma linha
        if (msgPrintada) {
            System.out.println();
        }

        Textos.printaBonito(Cor.txtCinza(Arte.bordaHud9), 2,2);
        Textos.sleep(sleepFimRodada);
        InputHandler.esperar();

        limpaSubscribers();

        if (notificaMorte() == 0) {
            return;
        }

        // notifica possiveis meia vida
        for (Inimigo inimigo : inimigos) {
            inimigo.checkMeiaVida(inimigo, heroi, this);
        }
        heroi.checkMeiaVida(heroi, heroi, this);

        heroi.passaRodada(); // remove os bonus que acabam (escudo, etc) e reseta energia
    }

    /** adiciona os itens passivos na batalha */
    private void inicializaItens(){
        for (ItemPassivo item : heroi.getListaItensPassivos()) {
            item.setAlvo(heroi);
            adicionarSubscriber(item);
        }
    }

    /** inicializa o estado do heroi para a batalha */
    private void inicializaHeroi(){
        // passa a referencia da mao e das pilhas pro heroi
        heroi.setMaoAtual(mao); 
        heroi.setPilhaCompra(pilhaCompra);
        heroi.setPilhaDescarte(pilhaDescarte);

        // resetando os bonus do heroi q possam ter sobrado da batalha passada
        heroi.passaRodada();
        heroi.passaRodada();
        heroi.resetaBuffs();
        heroi.resetEfeitos();

        for (batalhaListener sub : subscribers) {
            sub.onBattleStart(this, heroi);
        }

        heroi.aplicaBonus();
    }

    /** retorna uma copia da lista de inimigos */
    private Inimigo[] copiarInimigos(Inimigo[] inimigosOriginais) {
        Inimigo[] copiados = new Inimigo[inimigosOriginais.length];
        for (int i = 0; i < inimigosOriginais.length; i++) {
            copiados[i] = inimigosOriginais[i].criaCopia();
        }
        return copiados;
    }

    /** retorna todos os inimigos e o heroi (não é copia) (nao ta sendo usado ainda mas criei pq pode ser util)*/
    @SuppressWarnings("unused")
    private List<Entidade> listaEntidades() {
        List<Entidade> entidades = new ArrayList<>();
        entidades.add(heroi);
        entidades.addAll(inimigos);
        return entidades;
    }

    /** adiciona os inimigos na lista novosInimigos a batalha, remove os inimigos com flag pra remover e reseta a lista */
    private void addNovosInimigos() {

        // garante q ele nao retorne estavivo ao ser removido sem morrer
        for (Inimigo inimigo : inimigos) { 
            if (inimigo.getRemoverDaBatalha()) {
                inimigo.setVida(0); 
            }
        }

        inimigos.removeIf(Inimigo::getRemoverDaBatalha);

        if (novosInimigos.isEmpty()) {
            return;
        }

        for (Inimigo novoInimigo : novosInimigos) {
            if (novoInimigo != null) {
                novoInimigo.escolheAcao();
                inimigos.add(novoInimigo);
            }
        }
        
        novosInimigos.clear();
    }

    /** adiciona os novos subscribers e apaga a lista */
    private void addNovosSubscribers() {
        if (novosSubscribers.isEmpty()) {
            return;
        }

        for (batalhaListener novoSubscriber : novosSubscribers) {
            if (novoSubscriber != null) {
                adicionarSubscriber(novoSubscriber);
            }
        }
        
        novosSubscribers.clear();
    }

    /** notifica onReceivedHit */
    private void notificarDanoRecebido(Entidade alvo, Entidade atacante, int vidaPerdida){
        if (vidaPerdida <= 0) {
            return;
        }

        for (batalhaListener subscriber : subscribers) {
            if (subscriber.getAlvo() == alvo) {
                subscriber.onReceivedHit(this, heroi, atacante, vidaPerdida);
            }
        }
    }

    /** notifica onRemove e remove os subscribers*/
    private void limpaSubscribers(){     
        List<batalhaListener> toRemove = new ArrayList<>();

        for (batalhaListener subscriber : subscribers ) {
            if (subscriber.getRemover()){
                toRemove.add(subscriber);
            }
        }

        for (batalhaListener subscriber : toRemove) {
            subscriber.onRemove(this, heroi);
        }

        subscribers.removeAll(toRemove);

        listaEfeitos.removeIf(efeito -> !subscribers.contains(efeito));
        listaPoderes.removeIf(poder -> !subscribers.contains(poder));
    }

    /** checa inimigos mortos. Se sim, notifica subscribers onDeath e remove o inimigo da lista.
     * depois adiciona novos subscribers pendentes.
     * @return tamanho da lista de inimigos (quantidade de inimigos vivos)
     */
    public int notificaMorte(){

        // ve se morreu todo mundo ou se so sobraram inimigos passivos e ja retorna
        boolean todosMortos = true;
        for (Inimigo inimigo : inimigos) {
            if (inimigo.estaVivo() && !inimigo.getPassivo()) todosMortos = false;
        }

        if (todosMortos) {
            if (inimigos.stream().anyMatch(Inimigo::estaVivo)) {
                Textos.limpaTela();
                Cor.printaAmarelo("Todos os inimigos agressivos foram mortos!");
                InputHandler.esperar();
            }
            fimBatalha = true;
            return 0;
        }

        for (Inimigo i : inimigos) {
            if (!i.estaVivo()){
                for (batalhaListener subscriber : subscribers) {
                    subscriber.onDeath(this, i);
                }
            }
        } 

        inimigos.removeIf(inimigo -> inimigo.estaVivo() == false);

        addNovosSubscribers();

        return inimigos.size();
    }

    /** exibe uma mensagem e da outra mao e 2 energias pro jogador */
    private void rodadaBonus(){
        Textos.sobeTela();

        Textos.printaLinhaDevagar(Cor.rosa + (Arte.bonus).repeat(15) + Cor.reset);

        heroi.ganhaEnergia(2);
        InputHandler.esperar();
        mao.addCinco(pilhaCompra, pilhaDescarte);
    }

    /** mostra as opções de escolha para o jogador durante o turno: encerrar, cartas, itens, e ver as pilhas */
    private void mostrarEscolhas() {
        // adiciona a descriçao de todas as cartas da mao atual em uma lista
        List<String> escolhas = new ArrayList<>(mao.mostrar().stream().map(Carta::descricao).toList());

        // pula uma linha depois da ultima carta pra separar as cartas das outras opçoes
        escolhas.set(escolhas.size() - 1, escolhas.get(escolhas.size() - 1) + "\n"); 

        // adiciona as outras opçoes de escolha na lista
        escolhas.add(Cor.azulClaro + "Itens ativos" + Cor.reset + " ( " + Cor.amareloClaro + heroi.getListaItensAtivos().size() + Cor.reset + " )");
        escolhas.add(Cor.verdeClaro + "Pilha de compra (embaralhada) " + Cor.cinza + "( " + Cor.amareloClaro + pilhaCompra.getSize() + Cor.cinza + " )" + Cor.reset);
        escolhas.add(Cor.vermelho + "Pilha de descarte " + Cor.cinza + "( " + Cor.amareloClaro + pilhaDescarte.getSize() + Cor.cinza + " )" + Cor.reset);

        //printa um menu de seleção com as opçoes (não retorna nada, o turnoHeroi que lida com a escolha)
        InputHandler.exibirMenuSelecao(escolhas, true, Cor.txtLaranja("- - - - - - = = = = = = - - - - - -"), "Encerrar turno", true);
        return;
    }

    private int usaCarta(int escolha){
        Carta cartaEscolhida = mao.escolheCarta(escolha); 
        cartaEmUso = cartaEscolhida;

        if (!cartaEscolhida.podeGastar(heroi)){
            System.out.println();
            System.out.println("Energia insuficiente");
            System.out.println();
            InputHandler.esperar();
            return -1;
        } 
        
        cartaEscolhida.usar(this);

        if (cartaEscolhida.getUsoCancelado()) {
            cartaEscolhida.setUsoCancelado(false);
            return -1;
        }

        Entidade alvoSelecionado = cartaEscolhida.getAlvoDaJogada();

        // se der BO no alvo definido printa um erro mas vira o heroi pra nao crasha o jogo
        if (alvoSelecionado == null) {
            System.out.println("erro na seleçao de alvo, heroi selecionado pra nao crashar o jogo!");
            InputHandler.esperar();
            alvoSelecionado = heroi;
        }

        // cartas com a flag consumir vao pra pilha secundaria e nao voltam pra pilha de compras (normalmente)
        if (cartaEscolhida.isConsumir())
            mao.removeCarta(escolha, pilhaConsumir);
        else 
            mao.removeCarta(escolha, pilhaDescarte);

        // notifica os efeitos com on hit
        for (batalhaListener subscriber : subscribers)
            subscriber.onHit(cartaEscolhida, heroi, alvoSelecionado, this);

        cartaEmUso = null;
        return 0;
    }

    public int mostrarItensAtivos(){
        int escolha = InputHandler.selecionar(heroi.getListaItensAtivos(), true, Cor.azulClaro + "Itens:" + Cor.reset, "Voltar");

        if (escolha == -1) 
            return -1;

        heroi.usarItemAtivo(escolha, this);

        return 1;
    }

    /** printa a parte principal da hud de batalha, com o titulo as bordas e os status das entidades. */
    private void printaBatalha (boolean primeiroLoop){
        if (primeiroLoop && !heroi.getTestMode()){
            Textos.batalha(this);
            primeiroLoop = false;
        } else {
            Textos.batalhaSemDelay(this);
        }
    }

    /** exibe a mensagem de vitória e cuida da recompensa da batalha */
    public void vitoria(){
        String arteVitoria = Textos.colorirPartes(Arte.venceu2, Cor.amareloClaro, Cor.laranja, 5);
        Textos.printaLinhaDevagar(arteVitoria);
        System.out.println();
        InputHandler.esperar();
        Recompensas.ganharDinheiro(this.recompensa, heroi);

        // recompensa baseada na dificuldade total da batalha
        switch (getNivelDificuldade()) {
            case 1 -> {
                Recompensas.ganharOpcoes(raridades.COMUM, 3, heroi);
            }
            case 2 -> {
                Recompensas.ganharOpcoes(raridades.COMUM, 3, heroi);
                Recompensas.ganharItemAtivo(heroi);
            }
            case 3 -> {
                Recompensas.ganharCartas(raridades.COMUM, 3, heroi);
                Recompensas.ganharOpcoes(raridades.INCOMUM, 3, heroi);
                Recompensas.ganharItemAtivo(heroi);   
            }
            case 4 -> {
                Recompensas.ganharCartas(raridades.INCOMUM, 3, heroi);
                Recompensas.ganharOpcoes(raridades.INCOMUM, 5, heroi);
                Recompensas.ganharItemAtivo(heroi); 
                Recompensas.ganharItemPassivo(heroi);  
            }
            case 5 -> {
                Recompensas.ganharCartas(raridades.RARA, 5, heroi);
                Recompensas.ganharOpcoes(raridades.RARA, 3, heroi);
                Recompensas.ganharItemAtivo(heroi);
                Recompensas.ganharItemPassivo(heroi);
            }
        }
    }

    /** exibe a mensagem de derrota e cuida da recompensa da batalha */
    public void derrota(){
        System.out.println();
        Textos.printaLinhaDevagar(Cor.txtCinza(Arte.sans2));
        System.out.println();
        System.out.println();
        Textos.printaLinhaDevagar(Cor.txtVermelho(Arte.VOCEMORREU));
        System.out.println();
        InputHandler.esperar("Pressione ENTER para aceitar a sua derrota!");
        System.exit(0);
    }

    public int getDificuldadeTotal() {
        return dificuldadeTotal;
    }

    public int getNivelDificuldade() {
        if (inimigos.stream().anyMatch(inimigo -> inimigo.getTier() == 5)) {
            return 5; // BOSS
        }
        else if (dificuldadeTotal < 3) {
            return 1; // trivial
        }
        else if (dificuldadeTotal < 6) {
            return 2; // normal
        }
        else if (dificuldadeTotal <= 7) {
            return 3; // desafiador
        }
        else {
            return 4; // elite
        }
    }

    @Override
    public String toString() {
        String retorno = Cor.txtVermelho("Batalha");
        
        switch (getNivelDificuldade()) {
            case 1 -> retorno += " <" + Cor.azul + "trivial" + Cor.reset + ">";
            case 2 -> retorno += " <" + Cor.verde + "normal" + Cor.reset + ">";
            case 3 -> retorno += " <" + Cor.amarelo + "desafiador" + Cor.reset + ">";
            case 4 -> retorno += " <" + Cor.vermelho + "elite" + Cor.reset + ">";
            case 5 -> retorno += " <" + Cor.roxo + "BOSS" + Cor.reset + ">";
        }

        retorno += Cor.txtCinza(" VERSUS:");

        for (Inimigo inimigo : moldesInimigos) {
            retorno += " [ " + inimigo.getNome() + " ]";
        }
        return retorno;
    }

    @Override
    public Batalha criaCopia() {
        return new Batalha(moldesInimigos); 
    }
}
