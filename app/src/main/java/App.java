import Entidades.Heroi;
import Telas.Mapa;
import Util.Arte;
import Util.InputHandler;
import Util.Moldes;
import Util.Textos;

public class App {
    public static void main(String[] args) throws Exception {

        // GERENCIADOR DE CARTAS Q ERA PRA CUIDAR DO JSON MAS NAO FUNCIONA
        // GerenciadorCartas gerenciadorCartas = new GerenciadorCartas();
        // gerenciadorCartas.carregarCartas(false);

        // instancias padrao pra teste por enquanto
        Moldes.carregar();

        // heroi -----------
        Heroi heroi = new Heroi("Capitão Jack Sparrow", 55, 25);
      
        // pra entrar direto no deckBuilder descomenta aqui
        // DeckBuilder.iniciar(heroi); 

        Textos.limpaTela();
        Textos.printaBonito((Arte.tituloSombreado + "\n"), 2, 2);
        System.out.println();
        Thread.sleep(500);

        InputHandler.esperar();

        // se quiser pular o mapa e ir direto pra uma batalha teste descomenta aqui
        // Batalha batalha = new Batalha(heroi, inimigo, inimigo2, inimigo3);
        // batalha.iniciar();
        
        Mapa mapa = new Mapa(heroi);
        mapa.explorar(); // por enquanto ta programado pra ter 3 lutas, a primeira facil a 2 meio aleatorio a 3 contra todos os inimigos ao msm tempo

        // se chegou ate aq e pq vc venceu o jogo todo parabens!!
        Textos.printaLinhaDevagar(Arte.PEROLANEGRA);
        System.out.println();
        InputHandler.esperar();
    }
}
