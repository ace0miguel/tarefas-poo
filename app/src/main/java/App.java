

import Entidades.Heroi;
import Telas.Mapa;
import Util.InputHandler;
import Util.Moldes;
import Visual.Arte;
import Visual.Cor;
import Visual.Textos;

public class App {
    public static void main(String[] args) throws Exception {
        // opcoes do menu inicial
        // List<String> opcoesMenuInicial = new ArrayList<>();
        // opcoesMenuInicial.add("Iniciar avebtura");
        // opcoesMenuInicial.add("Sair");

        // GERENCIADOR DE CARTAS Q ERA PRA CUIDAR DO JSON MAS NAO FUNCIONA
        // GerenciadorCartas gerenciadorCartas = new GerenciadorCartas();
        // gerenciadorCartas.carregarCartas(false);

        // instancias padrao pra teste por enquanto
        Moldes.carregar();

        // heroi -----------
        Heroi heroi = new Heroi("Capitão Jack Sparrow", 70, 5);
      
        // pra entrar direto no deckBuilder descomenta aqui
        // DeckBuilder.mostrarDecksPadrao(heroi);
        // DeckBuilder.iniciar(heroi); 


        // tela de titulo ------------
        Textos.limpaTela();
        Textos.printaBonito((Arte.tituloSombreado + "\n"), 2, 2);

        Textos.sleep(500);
        System.out.println();
        
        InputHandler.esperar(Cor.txtAmareloClaro("Pressione ENTER para partir em busca do Pérola Negra!"));

        // ----------------------------

        // se quiser pular o mapa e ir direto pra uma batalha teste descomenta aqui
        //Batalha batalha = new Batalha(heroi, inimigo, inimigo2, inimigo3);
        //batalha.iniciar();
        
        // menu inicial (tava meio redundante ai eu comentei) ------------

        // int escolhaMenuInicial = InputHandler.selecionar(opcoesMenuInicial, Arte.tituloSombreado);
    
        // switch (escolhaMenuInicial) {
        //     case 0: //inicia o jogo
        //         break;
        //     case 1:
        //         System.out.println("Saindo do jogo...");
        //         Textos.sleep(500);
        //         System.exit(0);
        //         return;
        // }
    
        // inicio do jogo  ------------
        Mapa mapa = new Mapa(heroi);
        mapa.explorar(); // por enquanto ta programado pra ter 3 lutas, a primeira facil a 2 meio aleatorio a 3 contra todos os inimigos ao msm tempo

        // se chegou ate aq e pq vc venceu o jogo todo parabens!!
        Textos.printaLinhaDevagar(Arte.PEROLANEGRA);
        System.out.println();
        InputHandler.esperar();
    }
}
