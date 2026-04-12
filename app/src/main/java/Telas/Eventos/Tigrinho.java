package Telas.Eventos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Entidades.Heroi;
import Util.InputHandler;
import Util.Moldes;
import Util.RNGHandler;
import Util.Recompensas;
import Visual.Arte;
import Visual.Cor;
import Visual.Textos;

/** evento aleatório de cassino, onde o jogador pode apostar vida ou dinheiro. Em breve poderá apotar as cartas tambem, mas nao esta pronto. */
public class Tigrinho extends Evento{
    String tituloColorido = Textos.colorirPartes(Arte.cassino5, Cor.amareloClaro, Cor.laranja, 5) + "\n";
    
    @Override
    public void iniciar(Heroi heroi) {
        this.heroi = heroi;
        
        // opções: apostar dinheiro, cartas ou vida.
        List<String> opcoes = new ArrayList<>(Arrays.asList
            (Cor.txtReset("o seu dinheiro?"), 
            Cor.txtReset("as suas cartas..."), 
            Cor.txtVermelho("OU A SUA VIDA?")));

        while (true){
            int escolha = InputHandler.selecionar(opcoes, true, tituloColorido + Cor.txtVermelho("\nSeja bem vindo ao meu cassino! o que deseja apostar? "), "nada.");
            if ( escolha == -1 ) break;
            switch (escolha) 
            {
                case 0 -> 
                {
                    cassinoDinheiro(heroi);
                    break;
                }
                case 1 -> 
                {
                    InputHandler.esperar(Cor.txtVermelho("Sala em manutenção! Pedimos desculpa pelo transtorno."));
                }
                case 2 -> 
                {
                    cassinoVida(heroi);
                    break;
                }
            }
        }
    }

    public void cassinoDinheiro(Heroi heroi) {
        Textos.limpaTela();
        System.out.println(tituloColorido + "\n" + Cor.txtVermelho("O dobro ou nada! quanto você deseja apostar? " + Cor.cinza +
            "[ Saldo atual: ( " + Cor.amarelo + heroi.getDinheiro() + Cor.cinza + " ) ]\n"
        ));
        int valor = InputHandler.lerInt();

        if (valor <= 1) {
            InputHandler.esperar(Cor.txtVermelho("\nTa duro dorme fio."));
            return;
        }
        else if (valor > heroi.getDinheiro()) {
            InputHandler.esperar(Cor.txtVermelho("\nVocê não tem esse valor!"));
            return;
        }

        int valorNecessario = (int) Math.ceil(valor / 2.0);

        System.out.println(Cor.vermelho + ("\nTire ao menos " + Cor.amarelo + valorNecessario + Cor.vermelho + " no dado para ganhar!\n" + Cor.cinza));
        InputHandler.esperar("Pressione ENTER para rolar o dado");

        int dado = RNGHandler.valorAleatorio(valor);

        Textos.dado(dado, valor);

        if (dado >= valorNecessario){
            System.out.println(Cor.txtVerde("\nVocê venceu! você ganhou " + valor + " moedas!"));
            heroi.setDinheiro(heroi.getDinheiro() + valor);
        } else {
            System.out.println(Cor.txtVermelho("\nUma pena! você perdeu " + valor + " moedas!" + Cor.txtVermelho(" :D")));
            heroi.setDinheiro(heroi.getDinheiro() - valor);
        }

        System.out.println("\nSaldo atual: " + Cor.txtAmareloClaro(String.valueOf(heroi.getDinheiro())));
        InputHandler.esperar();
    }

    public void cassinoVida(Heroi heroi){
        boolean apostaArriscada = false;

        Textos.limpaTela();
        System.out.println(tituloColorido + "\n" + Cor.txtVermelho("O dobro ou nada! quanto você deseja apostar? " + Cor.cinza +
            "[ Vida atual: ( " + heroi.getVida()+ " / " + Cor.reset + heroi.getVidaMax() + Cor.cinza + " ) ]" +
            Cor.vermelho + "\nSe voce colocar tudo a perder, talvez eu tenha algo a te oferecer... \n"
        ));

        int valor = InputHandler.lerInt();

        if (valor == heroi.getVida()){
            Textos.printaBonito(Cor.txtVermelho("\nInteressante..."),10 ,0);
            apostaArriscada = true;
        }

        if (valor <= 1) {
            InputHandler.esperar(Cor.txtVermelho("\nTa morto dorme fio."));
            return;
        }
        else if (valor > heroi.getVida()) {
            InputHandler.esperar(Cor.txtVermelho("\nVocê não tem vida suficiente!"));
            return;
        }

        int valorNecessario = (int) Math.ceil(valor / 2.0);

        System.out.println(Cor.vermelho + ("\nTire ao menos " + Cor.amarelo + valorNecessario + Cor.vermelho + " no dado para ganhar!\n") + Cor.cinza);
        InputHandler.esperar("Pressione ENTER para rolar o dado");

        int dado = RNGHandler.valorAleatorio(valor);

        Textos.dado(dado, valor);

        if (dado >= valorNecessario){
            System.out.println();
            heroi.ganhaVida(valor);
        } else {
            heroi.receberDanoDireto(valor);
            if (!heroi.estaVivo()){
                Textos.printaLinhaDevagar(Cor.txtVermelho(Arte.hahaha).repeat(35), 5);
                System.exit(0);
            }
            System.out.println("\n" + Cor.vermelho + "Obrigado! Você perdeu " + valor + " pontos de vida!" + Cor.reset + Cor.txtVermelho(" :D"));
            System.out.println();
        }

        System.out.println("Vida atual: " + Cor.txtAmareloClaro(String.valueOf(heroi.getVida())));
        InputHandler.esperar();

        if (apostaArriscada){
            Textos.printaBonito(Cor.txtVermelho("Eu aprecio sua coragem. Muito bem, eu vou te dar um presente.\n"),5 ,2);
            Recompensas.ganharCartaEsp(Moldes.presenteMaldito, heroi);
        }
    }


    @Override
    public String toString() {
        String retorno = Cor.txtLaranja("Cassino!");
        return retorno;
    }   

    @Override
    public Tigrinho criaCopia() {
        return new Tigrinho();
    }

    
}
