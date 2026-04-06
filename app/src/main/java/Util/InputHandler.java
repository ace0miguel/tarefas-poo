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
        esperar(Cor.txtCinza("\n[ Pressione ENTER para continuar ]"));
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

    /* recebe uma lista e printa, se for de objetos ele vai usar o metodo .toString(), se for string ela so printa 
    depois valida a escolha e retorna o numero escolhido, se tiver a opçao exit e o usuario escolher vai retonar -1.*/
    public static <T> int selecionar(List<T> lista, boolean exit, String mensagemInicial) { 
        if (lista.isEmpty() && !exit) { // se a lista vier vazia e nao tiver nada pra voltar retorna
            Cor.printaVermelho(">> ERRO << LISTA VAZIA E NAO TEM EXIT\n");
            esperar();
            return -1;
        }

        int escolha;
        int tamanhoEfetivo = lista.size() - 1; // se nao tiver exit a ultima opçao e um a menos q o size, se tiver e igual o size.
        if (exit) tamanhoEfetivo++;

        // fiz isso aqui por motivo de estetica, toda lista vai ser imprimida durando o msm tempo, a velocidade depende da quantidade de itens.
        int tempoSleep = (lista.size() != 0) ? 300 / lista.size() : 500; 

        while (true){
            Textos.limpaTela();

            if (!mensagemInicial.equals("")){
                Textos.printaLinhaDevagar(mensagemInicial);
                System.out.println();   
            }

            if (exit) {
                System.out.println(Cor.txtCinza(0 + " > Voltar.")); Textos.sleep(tempoSleep);
            }
            
            // se tiver exit, ele vai ser o 0 entao tem q printar a partir do 1
            int corretor = exit? 1 : 0;

            for(int i = 0; i < lista.size(); i++) {
                System.out.println(((i + corretor) + " > " + lista.get(i))); Textos.sleep(tempoSleep);
            }


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
                String linha = leitor.nextLine();
            }

            System.out.println();

            if ((escolha >= 0 && escolha < lista.size()) || (escolha == -1 && exit)) // valida a escolha
                break;         
            
            System.out.println(Textos.escolhaInvalida(tamanhoEfetivo));
            esperar();
        }
        return escolha;
    }
}

