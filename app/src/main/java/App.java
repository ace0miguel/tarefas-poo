import Entidades.Heroi;
import Entidades.Inimigos.Inimigo;
import Telas.Mapa;
import Util.Arte;
import Util.InputHandler;
import Util.Textos;

public class App {
    public static void main(String[] args) throws Exception {

        // GERENCIADOR DE CARTAS Q ERA PRA CUIDAR DO JSON MAS NAO FUNCIONA ENTAO TA COMENTADO VAI TER Q FICAR DANDO NEW TODA HORA NESSA MERDA 
        // GerenciadorCartas gerenciadorCartas = new GerenciadorCartas();
        // gerenciadorCartas.carregarCartas(false);

        // instancias padrao pra teste por enquanto
        Inimigo inimigo = new Inimigo("Capitão Hector Barbossa", 30, 4);
        Inimigo inimigo2 = new Inimigo("LOUD Coringa", 15, 3);    
        Inimigo inimigo3 = new Inimigo("Endrick", 12, 4);

        // heroi -----------
        Heroi heroi = new Heroi("Capitão Jack Sparrow", 60, 5);
      
        Textos.limpaTela();
        Textos.printaBonito((Arte.tituloSombreado + "\n"), 2, 2);
        System.out.println();
        Thread.sleep(500);

        InputHandler.esperar();

        // se quiser pular o mapa e ir direto pra uma batalha teste descomenta aqui
        // Batalha batalha = new Batalha(heroi, inimigo, inimigo2, inimigo3);
        // batalha.iniciar();
        
        Mapa mapa = new Mapa(heroi);
        mapa.explorar(); // por enquanto ta programado pra ter 3 lutas, a primeira facil a 2 meio aleatorio a 3 contra os 7 inimigos

        // se chegou ate aq e pq vc venceu o jogo todo parabens!!
        Textos.printaLinhaDevagar(Arte.PEROLANEGRA);
        System.out.println();
        InputHandler.esperar();
    }
}
