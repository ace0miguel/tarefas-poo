package util;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import visual.Cor;
import visual.Textos;

/** classe responsável por lidar com as entradas do usuário */
public class InputHandler {
    private static final Scanner leitor = new Scanner(System.in);

    private static void prepararRedesenhoMenu(boolean naoLimpaTela, boolean[] ancoraMenuDefinida) {
        if (!naoLimpaTela) {
            Textos.limpaTela();
            return;
        }

        if (!ancoraMenuDefinida[0]) {
            // Salva o ponto inicial do menu para redesenhar sem deixar lixo no terminal.
            System.out.print("\u001B[s");
            System.out.flush();
            ancoraMenuDefinida[0] = true;
            return;
        }

        // Volta para a ancora e limpa ate o fim da tela.
        System.out.print("\u001B[u\u001B[J\u001B[s");
        System.out.flush();
    }

    private static <T> void imprimirMenuSelecao(List<T> lista, boolean exit, String mensagemInicial, String exitMsg, int tempoSleep) {
        if (!mensagemInicial.equals("")) {
            Textos.printaBonito(mensagemInicial, 1, 1);
            System.out.println();
        }

        if (exit) {
            System.out.println(Cor.cinza + 0 + Cor.txtAmarelo(" > ") +
                Cor.txtCinza((exitMsg.equals("") ? "Voltar." : exitMsg)));
                
            Textos.sleep(tempoSleep);
            System.out.println();
        }

        // se tiver exit, ele vai ser o 0 entao tem q printar a partir do 1
        int corretor = exit ? 1 : 0;

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(Cor.txtReset(String.valueOf(i + corretor)) + Cor.txtAmarelo(" > ") + lista.get(i));
            Textos.sleep(tempoSleep);
        }

        System.out.println(); // linha vazia entre a lista e o input do usuario
    }

    public static Scanner getLeitor() {
        return leitor;
    }

    /** exibe uma mensagem e espera qualquer input do usuario
     * @param mensagem : a mensagem a ser exibida antes de esperar o input
     */
    public static void esperar(String mensagem) {
        System.out.println(mensagem);
        
        try {
            System.in.read();          
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (Exception e) {
        }
    }

    public static void esperar(){
        esperar(Cor.txtCinza("\n[ Pressione ENTER para continuar ]"));
    }


    /** recebe uma lista e printa, se for de objetos ele vai usar o metodo .toString(), se for string ela so printa.
    valida a escolha e retorna o numero escolhido, se tiver a opçao exit e o usuario escolher vai retonar -1.
    @param lista : lista de opções
    @param exit : opção de voltar
    @param mensagemInicial : mensagem a ser exibida acima das opções
    @param exitMsg : substitui a opção voltar pela string desejada*/
    public static <T> int selecionar(List<T> lista, boolean exit, String mensagemInicial, String exitMsg, boolean naoLimpaTela) { 
        if (lista.isEmpty() && !exit) { // se a lista vier vazia e nao tiver nada pra voltar retorna
            Cor.printaVermelho(">> ERRO << LISTA VAZIA E NAO TEM EXIT\n");
            esperar();
            return -1;
        }

        int escolha;
        int tamanhoEfetivo = lista.size() - 1; // se nao tiver exit a ultima opçao e um a menos q o size, se tiver e igual o size.
        if (exit) tamanhoEfetivo++;

        // fiz isso aqui por motivo de estetica, toda lista vai ser imprimida durando o msm tempo, a velocidade depende da quantidade de itens.
        int tempoSleep = (lista.size() != 0) ? 100 / lista.size() : 300;
        boolean[] ancoraMenuDefinida = { false };

        while (true){
            prepararRedesenhoMenu(naoLimpaTela, ancoraMenuDefinida);
            imprimirMenuSelecao(lista, exit, mensagemInicial, exitMsg, tempoSleep);

            try {
                escolha = leitor.nextInt();
            } catch (Exception e) {
                escolha = -2;
            }

            // pra nao dar pra pessoa escolher -1 de proposito ou sem querer tb
            if (escolha == -1)
                escolha = -2;

            // se tiver exit, tem q subtrair 1 da escolha. se a pessoa escolher 0 retorna -1 (codigo de voltar)
            if (exit)
                escolha--;

            if (leitor.hasNextLine()) {
                leitor.nextLine();
            }

            System.out.println();

            if ((escolha >= 0 && escolha < lista.size()) || (escolha == -1 && exit)) { // valida a escolha
                break;         
            }
            
            System.out.println(Textos.escolhaInvalida(tamanhoEfetivo));
            esperar();
        }
        return escolha;
    }

    /** exibe o menu com o mesmo layout do selecionar, mas nao le input nem retorna opcao. */
    public static <T> void exibirMenuSelecao(List<T> lista, boolean exit, String mensagemInicial, String exitMsg, boolean naoLimpaTela) {
        if (lista.isEmpty() && !exit) {
            Cor.printaVermelho(">> ERRO << LISTA VAZIA E NAO TEM EXIT\n");
            esperar();
            return;
        }

        int tempoSleep = (lista.size() != 0) ? 300 / lista.size() : 500;
        boolean[] ancoraMenuDefinida = { false };

        prepararRedesenhoMenu(naoLimpaTela, ancoraMenuDefinida);
        imprimirMenuSelecao(lista, exit, mensagemInicial, exitMsg, tempoSleep);
    }

    /** Recebe uma lista de objetos e divide em páginas com 7 elementos cada e opções voltar
     * e próxima página e retorna uma matriz de strings, cada linha é uma pagina.
     */
    public static <T> List<List<String>> montaPaginas(List<T> lista) {
        List<List<String>> listaCompleta = new ArrayList<>();

        // cria uma lista com o nome das cartas
        List<String> listaString = new ArrayList<>();

        for (T objeto : lista) {
            if (objeto != null)
                listaString.add(objeto.toString());
            else{
                System.out.println("erro: tentaram colocar um objeto nulo na lista");
                esperar();
            }
        }
        
        for (int i = 0; i < listaString.size(); i += 7) {
            // garante que nao vai passar do tamanho da lista qnd chega na ultima pagina
            int fimPagina = Math.min(i + 7, listaString.size()); 

            // cria a pagina com os itens de i ate fimPagina da lista de nome de cartas
            List<String> paginaAtual = new ArrayList<>(listaString.subList(i, fimPagina));

            // tinha um if aqui pra deixar mais bonito e so printar quando precisa mas atrapalha depois na hora de lidar com a escolha do usuario
            // if (i != 0)
                paginaAtual.add(Cor.txtCinza("Página anterior"));
            // if (i + 7 < listaString.size())
                paginaAtual.add(Cor.txtCinza("Próxima página"));
            
            listaCompleta.add(paginaAtual);
        }
        
        return listaCompleta;
    }

    /** imprime um menu utilizando a matriz de paginas e retorna a opção escolhida
     *  se for pra sair retorna -1.
     * @param mensagem : mensagem a ser exibida acima do menu
     * @param exit : se false, não permite voltar do menu sem escolher.
     */
    public static int menu(List<List<String>> matrizPaginas, AtomicInteger pagina, String mensagem, boolean exit){
        while (true) { 
            // quando tirar o ultimo item de uma pagina ele volta sozinho pra anterior
            if (matrizPaginas.size() <= 0) 
                return -1;

            // se vc acabar com os itens de uma pagina ele volta pra anterior.
            if (pagina.get() >= matrizPaginas.size())
                pagina.decrementAndGet();

            List<String> paginaAtual = matrizPaginas.get(pagina.get());
            int opcao = InputHandler.selecionar(paginaAtual, true, mensagem + Cor.txtCinza("[ Página (" + (pagina.get() + 1) + ") ]"));

            // -1: exit -> todas as cartas -> penultima e ultima: voltar e proxima
            if (opcao == -1) {
                if (exit)
                    return -1;
                else{
                    System.out.println("Você nao pode voltar desse menu!");
                    esperar();
                    continue;
                }
            }

            else if (opcao == paginaAtual.size() - 2) {
                if (pagina.get() > 0)
                    pagina.decrementAndGet();
                Textos.sleep(50);
                continue;
            }
            else if (opcao == paginaAtual.size() - 1){
                if (pagina.get() < matrizPaginas.size() - 1)
                    pagina.incrementAndGet();
                Textos.sleep(50);
                continue;
            }

            return opcao + (pagina.get() * 7);
        }
    }

    /** imprime um menu utilizando a matriz de paginas e retorna a opção escolhida
     *  se for pra sair retorna -1.
     * @param exit : se false, não permite voltar do menu sem escolher.
     */
    public static int menu(List<List<String>> matrizPaginas, AtomicInteger pagina){
        return menu(matrizPaginas, pagina, "", true);
    }

    /** imprime um menu utilizando a matriz de paginas e retorna a opção escolhida
     *  se for pra sair retorna -1.
     * @param exit : se false, não permite voltar do menu sem escolher.
     */
    public static int menu(List<List<String>> matrizPaginas, AtomicInteger pagina, String mensagem){
        return menu(matrizPaginas, pagina, mensagem, true);
    }

    /** imprime um menu utilizando a matriz de paginas e retorna a opção escolhida
     *  se for pra sair retorna -1.
     * @param exit : se false, não permite voltar do menu sem escolher.
     */
    public static int menu(List<List<String>> matrizPaginas, AtomicInteger pagina, boolean exit){
        return menu(matrizPaginas, pagina, "", exit);
    }

    /** le um inteiro (input) e retorna */
    public static int lerInt(){
        int escolha;
        while (true){
            try {
                escolha = leitor.nextInt();
                break;
            } catch (Exception e) {
                esperar("digite um número válido!");
                if (leitor.hasNextLine()) {
                    leitor.nextLine();
                }
            }
        }
        return escolha;
    }

    /** le uma string (input) e retorna */
    public static String lerString(){
        String input;
        while (true){
            try {
                input = leitor.nextLine();
                break;
            } catch (Exception e) {
                esperar("digite um texto válido!");
                if (leitor.hasNextLine()) {
                    leitor.nextLine();
                }
            }
        }
        return input;
    }

    // SUPERPOSIÇOES DO SELECIONAR (TINHA MUITOS GUARDEI AQUI EMBAIXO)

    // versoes do selecionar (o unico parametro obrigatorio é a lista).

     /** recebe uma lista e printa, se for de objetos ele vai usar o metodo .toString(), se for string ela so printa.
    valida a escolha e retorna o numero escolhido, se tiver a opçao exit e o usuario escolher vai retonar -1.
    @param lista : lista de opções
    @param exit : opção de voltar
    @param mensagemInicial : mensagem a ser exibida acima das opções
    @param exitMsg : substitui a opção voltar pela string desejada*/
    public static <T> int selecionar(List<T> lista) {
        return selecionar(lista, false, "", "", false);
    }

     /** recebe uma lista e printa, se for de objetos ele vai usar o metodo .toString(), se for string ela so printa.
    valida a escolha e retorna o numero escolhido, se tiver a opçao exit e o usuario escolher vai retonar -1.
    @param lista : lista de opções
    @param exit : opção de voltar
    @param mensagemInicial : mensagem a ser exibida acima das opções
    @param exitMsg : substitui a opção voltar pela string desejada*/
    public static <T> int selecionar(List<T> lista, String mensagem) {
        return selecionar(lista, false, mensagem, "", false);
    }

    /** recebe uma lista e printa, se for de objetos ele vai usar o metodo .toString(), se for string ela so printa.
    valida a escolha e retorna o numero escolhido, se tiver a opçao exit e o usuario escolher vai retonar -1.
    @param lista : lista de opções
    @param mensagem : mensagem a ser exibida acima das opções
    @param naoLimpaTela : se true nao limpa a tela antes de mostrar o menu
    */
    public static <T> int selecionar(List<T> lista, String mensagem, boolean naoLimpaTela) {
        return selecionar(lista, false, mensagem, "", naoLimpaTela);
    }

     /** recebe uma lista e printa, se for de objetos ele vai usar o metodo .toString(), se for string ela so printa.
    valida a escolha e retorna o numero escolhido, se tiver a opçao exit e o usuario escolher vai retonar -1.
    @param lista : lista de opções
    @param exit : opção de voltar
    @param mensagemInicial : mensagem a ser exibida acima das opções
    @param exitMsg : substitui a opção voltar pela string desejada*/
    public static <T> int selecionar(List<T> lista, boolean exit) {
        return selecionar(lista, exit, "", "", false);
    }

    /** recebe uma lista e printa, se for de objetos ele vai usar o metodo .toString(), se for string ela so printa.
    valida a escolha e retorna o numero escolhido, se tiver a opçao exit e o usuario escolher vai retonar -1.
    @param lista : lista de opções
    @param exit : opção de voltar
    @param naoLimpaTela : se true nao limpa a tela antes de mostrar o menu
    */
    public static <T> int selecionar(List<T> lista, boolean exit, boolean naoLimpaTela) {
        return selecionar(lista, exit, "", "", naoLimpaTela);
    }

     /** recebe uma lista e printa, se for de objetos ele vai usar o metodo .toString(), se for string ela so printa.
    valida a escolha e retorna o numero escolhido, se tiver a opçao exit e o usuario escolher vai retonar -1.
    @param lista : lista de opções
    @param exit : opção de voltar
    @param mensagemInicial : mensagem a ser exibida acima das opções
    @param exitMsg : substitui a opção voltar pela string desejada*/
    public static <T> int selecionar(List<T> lista, boolean exit, String mensagemInicial) {
        return selecionar(lista, exit, mensagemInicial, "", false);
    }

    /** recebe uma lista e printa, se for de objetos ele vai usar o metodo .toString(), se for string ela so printa.
    valida a escolha e retorna o numero escolhido, se tiver a opçao exit e o usuario escolher vai retonar -1.
    @param lista : lista de opções
    @param exit : opção de voltar
    @param mensagemInicial : mensagem a ser exibida acima das opções
    @param naoLimpaTela : se true nao limpa a tela antes de mostrar o menu
    */
    public static <T> int selecionar(List<T> lista, boolean exit, String mensagemInicial, boolean naoLimpaTela) {
        return selecionar(lista, exit, mensagemInicial, "", naoLimpaTela);
    }

    /** recebe uma lista e printa, se for de objetos ele vai usar o metodo .toString(), se for string ela so printa.
    valida a escolha e retorna o numero escolhido, se tiver a opçao exit e o usuario escolher vai retonar -1.
    @param lista : lista de opções
    @param exit : opção de voltar
    @param mensagemInicial : mensagem a ser exibida acima das opções
    @param exitMsg : substitui a opção voltar pela string desejada
    */
    public static <T> int selecionar(List<T> lista, boolean exit, String mensagemInicial, String exitMsg) {
        return selecionar(lista, exit, mensagemInicial, exitMsg, false);
    }

    public static <T> void exibirMenuSelecao(List<T> lista) {
        exibirMenuSelecao(lista, false, "", "", false);
    }

    public static <T> void exibirMenuSelecao(List<T> lista, String mensagem) {
        exibirMenuSelecao(lista, false, mensagem, "", false);
    }

    public static <T> void exibirMenuSelecao(List<T> lista, String mensagem, boolean naoLimpaTela) {
        exibirMenuSelecao(lista, false, mensagem, "", naoLimpaTela);
    }

    public static <T> void exibirMenuSelecao(List<T> lista, boolean exit) {
        exibirMenuSelecao(lista, exit, "", "", false);
    }

    public static <T> void exibirMenuSelecao(List<T> lista, boolean exit, boolean naoLimpaTela) {
        exibirMenuSelecao(lista, exit, "", "", naoLimpaTela);
    }

    public static <T> void exibirMenuSelecao(List<T> lista, boolean exit, String mensagemInicial) {
        exibirMenuSelecao(lista, exit, mensagemInicial, "", false);
    }

    public static <T> void exibirMenuSelecao(List<T> lista, boolean exit, String mensagemInicial, boolean naoLimpaTela) {
        exibirMenuSelecao(lista, exit, mensagemInicial, "", naoLimpaTela);
    }

    public static <T> void exibirMenuSelecao(List<T> lista, boolean exit, String mensagemInicial, String exitMsg) {
        exibirMenuSelecao(lista, exit, mensagemInicial, exitMsg, false);
    }
}

