package Telas.Eventos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;

import Entidades.Heroi;
import Util.Arte;
import Util.Cor;
import Util.InputHandler;
import Util.RNGHandler;
import Util.Textos;

/** cassino */
public class Tigrinho extends Evento{
    String tituloColorido = Textos.colorirPartes(Arte.cassino, Cor.laranja, Cor.marrom, 5) + "\n";
        // String hudCompleta = tituloColorido + "\n\n" + Cor.cinza + Arte.bordaHud1; 
    
    @Override
    public void iniciar(Heroi heroi) {
        this.heroi = heroi;
        
        // opções: apostar dinheiro, cartas ou vida.
        List<String> opcoes = new ArrayList<>(Arrays.asList
            (Cor.txtAmarelo("o seu dinheiro?"), 
            Cor.txtAzul("as suas cartas?"), 
            Cor.txtVermelho("OU A SUA VIDA?")));

        while (true){
            int escolha = InputHandler.selecionar(opcoes, true, tituloColorido + Cor.txtVermelho("\nBoas vindas ao meu cassino! o que deseja apostar? "), "nada.");
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
                    InputHandler.esperar(Cor.txtVermelho("Sala em manutenção! Pedimos desculpa pelo transtorno. Volte em breve."));
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
            "Saldo atual: ( " + Cor.amarelo + heroi.getDinheiro() + Cor.cinza + " )\n"
        ));
        int valor = InputHandler.lerInt();

        if (valor <= 1) {
            InputHandler.esperar("\nTa duro dorme fio");
            return;
        }
        else if (valor > heroi.getDinheiro()) {
            InputHandler.esperar("\nVocê não tem esse valor!");
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
    Textos.limpaTela();
        System.out.println(tituloColorido + "\n" + Cor.txtVermelho("O dobro ou nada! quanto você deseja apostar? " + Cor.cinza +
            "Vida atual: ( " + Cor.cinza + heroi.getVida() + Cor.reset + " / " + heroi.getVidaMax() + " )\n"
        ));
        int valor = InputHandler.lerInt();

        if (valor <= 1) {
            InputHandler.esperar("\nTa morto dorme fio");
            return;
        }
        else if (valor > heroi.getVida()) {
            InputHandler.esperar("\nVocê não tem vida suficiente!");
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
                Textos.printaLinhaDevagar(Cor.txtVermelho(Arte.hahaha).repeat(35), 10);
                System.exit(0);
            }
            System.out.println("\n" + Cor.vermelho + "Obrigado! Você perdeu " + valor + " pontos de vida!" + Cor.reset + Cor.txtVermelho(" :D"));
        }

        System.out.println("\nVida atual: " + Cor.txtAmareloClaro(String.valueOf(heroi.getVida())));
        InputHandler.esperar();
    }
    @Override
    public String toString() {
        String retorno = Cor.txtLaranja("Cassino! ") + "- O dobro ou nada!";
        return retorno;
    }   
}
