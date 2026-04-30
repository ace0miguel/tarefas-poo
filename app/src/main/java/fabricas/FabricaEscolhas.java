package fabricas;

import java.util.ArrayList;
import java.util.List;

import telas.eventos.Escolha;
import util.Recompensas;
import visual.Cor;

public class FabricaEscolhas {
    public static List<Escolha> escolhasDisponiveis = new ArrayList<>();

    public static Escolha maldicaoDinheiro; 
    public static Escolha danoDinheiro;

    public static void carregar(){
        escolhasDisponiveis.clear();

        maldicaoDinheiro = new Escolha(Cor.amarelo + 
            "Você encontrou um altar sagrado com um amuleto de ouro. Ele parece valioso, mas pegá-lo pode irritar as forças místicas que protegem o local." + 
            Cor.reset, 
        List.of(Cor.txtVermelho("Pegar o amuleto: 150 dinheiros e receber uma maldição."), Cor.txtCinza("Ignorar o amuleto e seguir em frente."))) {

            @Override
            public void realizarEscolha(int escolha) {
                if (escolha == 0) {
                    Recompensas.ganharDinheiro(150, heroi);
                    Recompensas.ganharMaldicao(FabricaCartas.culpa, heroi);
                } else {
                    // não faz nada, o jogador ignora a escolha
                }
            }
        };

        danoDinheiro = new Escolha(Cor.amarelo + 
            "Você encontrou um baú misterioso. Dentro dele, há uma grande quantidade de dinheiro entre lâminas extremamente afiadas. Você pode pegá-lo, mas com certeza irá se machucar." +
            Cor.reset, 
        List.of(Cor.txtVermelho("Pegar o dinheiro: 150 dinheiros e perder 15 pontos de vida."), Cor.txtCinza("Ignorar o dinheiro e seguir em frente."))) {

            @Override
            public void realizarEscolha(int escolha) {
                if (escolha == 0) {
                    Recompensas.ganharDinheiro(150, heroi);
                    Recompensas.ganharVida(-15, heroi);
                } else {
                    // não faz nada, o jogador ignora a escolha
                }
            }
        };

        escolhasDisponiveis.add(maldicaoDinheiro);
        escolhasDisponiveis.add(danoDinheiro);
    }
}
