package Util;
import java.util.List;
import java.util.Scanner;

public class InputHandler {
    private static Scanner leitor = new Scanner(System.in);

    public static Scanner getLeitor() {
        return leitor;
    }

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
        esperar("\n[ Pressione ENTER para continuar ]");
    }

    // versoes do selecionar pra vc poder passar so a lista ou so lista e msg ou so lista e exit.
    public static <T> int selecionar(List<T> lista) {
        return selecionar(lista, false, "");
    }

    public static <T> int selecionar(List<T> lista, String mensagem) {
        return selecionar(lista, false, mensagem);
    }

    public static <T> int selecionar(List<T> lista, boolean exit) {
        return selecionar(lista, exit, "");
    }

    /* recebe uma lista e printa, se for de objetos ele vai usar o metodo .toString(), se for string ela so printa */
    public static <T> int selecionar(List<T> lista, boolean exit, String mensagemInicial) { 
        if (lista.isEmpty() && !exit) { // se a lista vier vazia e nao tiver nada pra voltar retorna
            Cor.printaVermelho(">> ERRO << LISTA VAZIA E NAO TEM EXIT\n");
            esperar();
            return -1;
        }

        int escolha;
        int tamanhoEfetivo = lista.size() - 1; // se nao tiver exit a ultima opçao e um a menos q o size, se tiver e igual o size.
        if (exit) tamanhoEfetivo++;

        // fiz isso aqui por motivo de estetica, toda lista vai ser imprimida em 1 segundo e a velocidade depende da quantidade de itens.
        int tempoSleep = 1000 / lista.size(); 

        while (true){
            Textos.limpaTela();

            if (!mensagemInicial.equals("")){
                Textos.printaLinhaDevagar(mensagemInicial);
                System.out.println();   
            }
            for(int i = 0; i < lista.size(); i++) {
                Textos.printaBonito(((i) + " > " + lista.get(i)),2 ,0); Textos.sleep(tempoSleep);
            }

            if (exit) {
                Textos.printaBonito(Cor.txtCinza((lista.size()) + " > Voltar."), 2, 0); Textos.sleep(tempoSleep);
            }

            try {
                escolha = leitor.nextInt();
            } catch (Exception e) {
                escolha = -1;
            }

            leitor.nextLine();
            System.out.println();

            if (escolha >= 0 && escolha <= tamanhoEfetivo) // valida a escolha
                break;         
            
            System.out.println(Textos.escolhaInvalida(tamanhoEfetivo));
            esperar();
        }
        return escolha;
    }
}

