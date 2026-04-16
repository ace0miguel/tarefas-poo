package visual;

/** varias variaveis e métodos estáticos pra implementar cores no terminal */
public class Cor {

    public static String reset = "\u001B[0m";
    public static String vermelho = "\u001B[31m";
    public static String vinho = "\u001B[38;5;52m";
    public static String verde = "\u001B[32m";
    public static String verdeClaro = "\u001B[92m";
    public static String verdeEscuro = "\u001B[38;5;22m";
    public static String azul = "\u001B[34m";
    public static String azulClaro = "\u001B[94m";
    public static String azulEscuro = "\u001B[38;5;18m";
    public static String ciano = "\u001B[36m";
    public static String amarelo = "\u001B[33m";
    public static String amareloClaro = "\u001B[93m";
    public static String preto = "\u001B[30m";
    public static String cinza = "\u001B[90m"; 
    public static String roxo = "\u001B[35m"; 
    public static String rosa = "\u001B[95m"; 
    public static String laranja = "\u001B[38;5;208m"; 
    public static String marrom = "\u001B[38;5;130m";
    public static String marromClaro = "\u001B[38;5;137m";

    // - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - =

    public static void printaReset(String printa) {
        System.out.print(reset + printa + reset);
    }

    public static void printaVermelho(String printa) {
        System.out.print(vermelho + printa + reset);
    }

    public static void printaVinho(String printa) {
        System.out.print(vinho + printa + reset);
    }

    public static void printaVerde(String printa) {
        System.out.print(verde + printa + reset);
    }

    public static void printaVerdeClaro(String printa) {
        System.out.print(verdeClaro + printa + reset);
    }

    public static void printaVerdeEscuro(String printa) {
        System.out.print(verdeEscuro + printa + reset);
    }

    public static void printaAzul(String printa) {
        System.out.print(azul + printa + reset);
    }

    public static void printaAzulClaro(String printa) {
        System.out.print(azulClaro + printa + reset);
    }

    public static void printaAzulEscuro(String printa) {
        System.out.print(azulEscuro + printa + reset);
    }

    public static void printaCiano(String printa) {
        System.out.print(ciano + printa + reset);
    }

    public static void printaAmarelo(String printa) {
        System.out.print(amarelo + printa + reset);
    }

    public static void printaAmareloClaro(String printa) {
        System.out.print(amareloClaro + printa + reset);
    }

    public static void printaPreto(String printa) {
        System.out.print(preto + printa + reset);
    }

    public static void printaCinza(String printa) {
        System.out.print(cinza + printa + reset);
    }

    public static void printaRoxo(String printa) {
        System.out.print(roxo + printa + reset);
    }

    public static void printaRosa(String printa) {
        System.out.print(rosa + printa + reset);
    }

    public static void printaLaranja(String printa) {
        System.out.print(laranja + printa + reset);
    }

    public static void printaMarrom(String printa) {
        System.out.print(marrom + printa + reset);
    }

    public static void printaMarromClaro(String printa) {
        System.out.print(marromClaro + printa + reset);
    }

    // - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - =

    public static void printaResetLn(String printa) {
        System.out.println(reset + printa + reset);
    }

    public static void printaVermelhoLn(String printa) {
        System.out.println(vermelho + printa + reset);
    }

    public static void printaVinhoLn(String printa) {
        System.out.println(vinho + printa + reset);
    }

    public static void printaVerdeLn(String printa) {
        System.out.println(verde + printa + reset);
    }

    public static void printaVerdeClaroLn(String printa) {
        System.out.println(verdeClaro + printa + reset);
    }

    public static void printaVerdeEscuroLn(String printa) {
        System.out.println(verdeEscuro + printa + reset);
    }

    public static void printaAzulLn(String printa) {
        System.out.println(azul + printa + reset);
    }

    public static void printaAzulClaroLn(String printa) {
        System.out.println(azulClaro + printa + reset);
    }

    public static void printaAzulEscuroLn(String printa) {
        System.out.println(azulEscuro + printa + reset);
    }

    public static void printaCianoLn(String printa) {
        System.out.println(ciano + printa + reset);
    }

    public static void printaAmareloLn(String printa) {
        System.out.println(amarelo + printa + reset);
    }

    public static void printaAmareloClaroLn(String printa) {
        System.out.println(amareloClaro + printa + reset);
    }

    public static void printaPretoLn(String printa) {
        System.out.println(preto + printa + reset);
    }

    public static void printaCinzaLn(String printa) {
        System.out.println(cinza + printa + reset);
    }

    public static void printaRoxoLn(String printa) {
        System.out.println(roxo + printa + reset);
    }

    public static void printaRosaLn(String printa) {
        System.out.println(rosa + printa + reset);
    }

    public static void printaLaranjaLn(String printa) {
        System.out.println(laranja + printa + reset);
    }

    public static void printaMarromLn(String printa) {
        System.out.println(marrom + printa + reset);
    }

    public static void printaMarromClaroLn(String printa) {
        System.out.println(marromClaro + printa + reset);
    }

    // - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - =

    public static void reset() {
        System.out.print(reset);
    }

    public static void setVermelho() {
        System.out.print(vermelho);
    }

    public static void setVinho() {
        System.out.print(vinho);
    }

    public static void setVerde() {
        System.out.print(verde);
    }

    public static void setVerdeClaro() {
        System.out.print(verdeClaro);
    }

    public static void setVerdeEscuro() {
        System.out.print(verdeEscuro);
    }

    public static void setAzul() {
        System.out.print(azul);
    }

    public static void setAzulClaro() {
        System.out.print(azulClaro);
    }

    public static void setAzulEscuro() {
        System.out.print(azulEscuro);
    }

    public static void setCiano() {
        System.out.print(ciano);
    }

    public static void setAmarelo() {
        System.out.print(amarelo);
    }

    public static void setAmareloClaro() {
        System.out.print(amareloClaro);
    }

    public static void setPreto() {
        System.out.print(preto);
    }

    public static void setCinza() {
        System.out.print(cinza);
    }

    public static void setRoxo() {
        System.out.print(roxo);
    }

    public static void setRosa() {
        System.out.print(rosa);
    }

    public static void setLaranja() {
        System.out.print(laranja);
    }

    public static void setMarrom() {
        System.out.print(marrom);
    }

    public static void setMarromClaro() {
        System.out.print(marromClaro);
    }

    // - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - = - =

    public static String txtReset(String txt) {
        return reset + txt + reset;
    }

    public static String txtVermelho(String txt) {
        return vermelho + txt + reset;
    }

    public static String txtVinho(String txt) {
        return vinho + txt + reset;
    }

    public static String txtVerde(String txt) {
        return verde + txt + reset;
    }

    public static String txtVerdeClaro(String txt) {
        return verdeClaro + txt + reset;
    }

    public static String txtVerdeEscuro(String txt) {
        return verdeEscuro + txt + reset;
    }

    public static String txtAzul(String txt) {
        return azul + txt + reset;
    }

    public static String txtAzulClaro(String txt) {
        return azulClaro + txt + reset;
    }

    public static String txtAzulEscuro(String txt) {
        return azulEscuro + txt + reset;
    }

    public static String txtCiano(String txt) {
        return ciano + txt + reset;
    }

    public static String txtAmarelo(String txt) {
        return amarelo + txt + reset;
    }

    public static String txtAmareloClaro(String txt) {
        return amareloClaro + txt + reset;
    }

    public static String txtPreto(String txt) {
        return preto + txt + reset;
    }

    public static String txtCinza(String txt) {
        return cinza + txt + reset;
    }

    public static String txtRoxo(String txt) {
        return roxo + txt + reset;
    }

    public static String txtRosa(String txt) {
        return rosa + txt + reset;
    }

    public static String txtLaranja(String txt) {
        return laranja + txt + reset;
    }

    public static String txtMarrom(String txt) {
        return marrom + txt + reset;
    }

    public static String txtMarromClaro(String txt) {
        return marromClaro + txt + reset;
    }
}
