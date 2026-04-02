package Util;
import java.util.Scanner;

public class InputHandler {
    private static Scanner leitor = new Scanner(System.in);

    public static Scanner getLeitor() {
        return leitor;
    }

    public static void esperar() {
        System.out.println(Cor.txtCinza("\n[ Pressione ENTER para continuar ]"));
        
        try {
            System.in.read();          
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (Exception e) {
        }
    }

    /* selecionar recebe uma lista, exibe no terminal em forma de um menu de escolha
    pelo terminal, e retorna o numero escolhido pelo usuário
    (NÃO TA FUNCIONANDO AINDA OK)*/
    /*
    public static int selecionar(List<String> lista) {
        int i = 0;
        for (String item : lista) {
            System.out.println("["+i+"] - "+item+"");
            i++;
        }  
        int opcao;
        try {
            opcao = leitor.nextInt();
            leitor.nextLine();
        } catch (Exception e) {
            leitor.nextLine();
            System.out.println();
            System.out.println("Valor inválido. Digite novamente.");
            System.out.println();
        }
        return opcao;
    }
    */
   }

