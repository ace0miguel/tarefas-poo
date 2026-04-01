import Cartas.Carta;
import Cartas.CartaAtaque;
import Cartas.CartaAtaqueComEfeito;
import Cartas.CartaHabilidade;
import Cartas.CartaPoder;
import Deck.PilhaCompra;
import EfeitosDeStatus.AumentaDano;
import EfeitosDeStatus.DanoConstante;
import EfeitosDeStatus.Efeito;
import EfeitosDeStatus.Escudo;
import EfeitosDeStatus.Purificar;
import Entidades.Heroi;
import Entidades.Inimigo;
import Poderes.MaosLeves;
import Poderes.Poder;
import Telas.Batalha;
import Util.Art;
// import Util.JSON.GerenciadorCartas;


public class App {
    public static void main(String[] args) throws Exception {
        // GerenciadorCartas gerenciadorCartas = new GerenciadorCartas();
        PilhaCompra pilhaCompra = new PilhaCompra();

        // gerenciadorCartas.carregarCartas(false);

        //instancias padrao pra teste por enquanto
        Heroi heroi = new Heroi("Capitão Jack Sparrow", 232340, 5 );
        Inimigo inimigo = new Inimigo("Capitão Barbossa", 20, 3);
        Inimigo inimigo2 = new Inimigo("Figurante randola 1", 8, 1);    
        Inimigo inimigo3 = new Inimigo("Figurante randola 2", 8, 1);
        Efeito sangramento = new DanoConstante("Sangramento", "Causa 1 de dano por rodada ao alvo", 3, 2);
        Efeito odioPuro = new AumentaDano("Odio Puro", "Aumenta o dano causado em 1 por 3 rodadas", 3, 1);
        Efeito escudinho = new Escudo("Escudinho", "3 pontos de escudo", 0, 3);
        Efeito escudao = new Escudo("Escudao", "7 pontos de escudo", 0, 7);
        Efeito purificarEfeito = new Purificar("Purificar", "Remove todos os efeitos aplicados em voce (incluindo bons!)", 0);
        Poder dedoNervoso = new MaosLeves("dedo nervoso","Sempre que usar um ataque de disparo, dispare novamente pelo tanto de acumulos desse poder.");
        Carta tiro = new CartaAtaque("Tiro", 2, 3, 1);
        tiro.setResenha("POW");
        Carta espada = new CartaAtaqueComEfeito("Espada - aplica sangramento (2 de dano por turno por 3 turnos)", 1, 2, sangramento, 2);
        Carta escudoMadeira = new CartaHabilidade("Escudo de madeira", 1, escudinho, true);
        Carta escudoFerro = new CartaHabilidade("Escudo de ferro", 2, escudao, true);
        Carta purificar = new CartaHabilidade("Purificar", 2, purificarEfeito, true);

        //cartas padrao pra teste por enquanto
        for (int i = 0; i < 3; i++){
            pilhaCompra.addCarta( tiro );
            pilhaCompra.addCarta( espada );
            pilhaCompra.addCarta( escudoMadeira );
            pilhaCompra.addCarta( escudoFerro );
            pilhaCompra.addCarta( purificar );
        }

        for(int i=0; i < 2; i++){
            pilhaCompra.addCarta( new CartaAtaqueComEfeito("DESPREZO - causa muito dano porém irrita seu adversario (1 de dano extra por 3 rodadas)", 3, 10, odioPuro));
            pilhaCompra.addCarta( new CartaPoder("Dedo nervoso - Para cada acúmulo, atire novamente sempre que usar uma carta de tiro!", 2, dedoNervoso));
        }

        Art.printTitulo();
        Thread.sleep(1500);

        Batalha batalha = new Batalha();
        batalha.iniciar(heroi, pilhaCompra, inimigo, inimigo2, inimigo3);
        
    }
}
