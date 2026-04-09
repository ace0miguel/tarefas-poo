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
    @Override
    public void iniciar(Heroi heroi) {
        this.heroi = heroi;
        // opções: apostar dinheiro, cartas ou vida.
        List<String> opcoes = new ArrayList<>(Arrays.asList(Cor.txtAmarelo("o seu dinheiro?"), Cor.txtAzul("as suas cartas?"), Cor.txtVermelho("a sua vida?")));
        while (true){
            int escolha = InputHandler.selecionar(opcoes, true, Cor.txtLaranja(Arte.tigrinho) + Cor.txtAmareloClaro("\nSeja bem vindo ao tigrinho! O que você deseja apostar?"), "nada.");
            if ( escolha == -1 ) break;
            switch (escolha) 
            {
                case 0 -> 
                {
                    Textos.limpaTela();
                    System.out.println(Cor.txtAmareloClaro("O dobro ou nada! quanto você deseja apostar? " + Cor.cinza +
                        "Saldo atual: ( " + Cor.amarelo + heroi.getDinheiro() + Cor.cinza + " )"
                    ));
                    int valor = InputHandler.lerInt();

                    if (valor <= 1) {
                        InputHandler.esperar("Ta duro dorme fio");
                        break;
                    }
                    else if (valor > heroi.getDinheiro()) {
                        InputHandler.esperar("Você não tem esse valor!");
                        continue;
                    }

                    int valorNecessario = (int) Math.ceil(valor / 2.0);

                    System.out.println(Cor.reset + ("Tire ao menos " + Cor.amarelo + valorNecessario + Cor.reset + " no dado para ganhar!"));
                    InputHandler.esperar("Pressione ENTER para rolar o dado");

                    int dado = RNGHandler.valorAleatorio(valor);

                    Textos.dado(dado, valor);

                    if (dado >= valorNecessario){
                        System.out.println(Cor.txtVerde("\nVocê venceu! você ganhou " + valor + " moedas!"));
                        heroi.setDinheiro(heroi.getDinheiro() + valor);
                    } else {
                        System.out.println(Cor.txtVermelho("\nUma pena! você perdeu " + valor + " moedas!"));
                        heroi.setDinheiro(heroi.getDinheiro() - valor);
                    }

                    System.out.println("\nSaldo atual: " + Cor.txtAmareloClaro(String.valueOf(heroi.getDinheiro())));
                    InputHandler.esperar();
                    break;
                }
                case 1 -> 
                {
                    // apostar cartas
                }
                case 2 -> 
                {
                    Textos.limpaTela();
                    System.out.println(Cor.txtAmareloClaro("O dobro ou nada! quanto você deseja apostar? " + Cor.cinza +
                        "Vida atual: ( " + Cor.amarelo + heroi.getVida() + Cor.cinza + " )"
                    ));
                    int valor = InputHandler.lerInt();

                    if (valor <= 1) {
                        InputHandler.esperar("Ta morto dorme fio");
                        break;
                    }
                    else if (valor > heroi.getDinheiro()) {
                        InputHandler.esperar("Você não tem essa vida!");
                        continue;
                    }

                    int valorNecessario =  600;

                    System.out.println(Cor.reset + ("Tire ao menos " + Cor.amarelo + valorNecessario + Cor.reset + " no dado para ganhar!"));
                    InputHandler.esperar("Pressione ENTER para rolar o dado");

                    int dado = RNGHandler.valorAleatorio(valor);

                    Textos.dado(dado, valor);

                    if (dado >= valorNecessario){
                        heroi.ganhaVida(valor);
                    } else {
                        heroi.receberDanoDireto(valor);
                        if (!heroi.estaVivo()){
                            Textos.printaLinhaDevagar(Cor.txtVermelho(Arte.hahaha).repeat(35), 10);
                            System.exit(0);
                        }
                    }

                    System.out.println("\nVida atual: " + Cor.txtAmareloClaro(String.valueOf(heroi.getVida())));
                    InputHandler.esperar();
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        String retorno = Cor.txtLaranja("Tigrinho");
        return retorno;
    }   
}
