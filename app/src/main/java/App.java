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
import EfeitosDeStatus.Sangramento;
import EfeitosDeStatus.Veneno;
import Entidades.Heroi;
import Entidades.Inimigo;
import Poderes.MaosLeves;
import Poderes.Poder;
import Telas.Batalha;
import Util.Arte;
import Util.Cor;

public class App {
    public static void main(String[] args) throws Exception {


        // GerenciadorCartas gerenciadorCartas = new GerenciadorCartas();
        PilhaCompra pilhaCompra = new PilhaCompra();

        // gerenciadorCartas.carregarCartas(false);

        //instancias padrao pra teste por enquanto

        // heroi -----------
        Heroi heroi = new Heroi("Capitão Jack Sparrow", 40, 5 );

        // inimigos -------------
        Inimigo inimigo = new Inimigo("Capitão Barbossa", 20, 4);
        Inimigo inimigo2 = new Inimigo("Figurante random 1", 8, 2);    
        Inimigo inimigo3 = new Inimigo("Figurante random 2", 8, 2);

        // efeitos ------------
        Efeito sangramento = new Sangramento("Sangramento", "Causa 1 de dano por rodada ao alvo", 3, 0);
        Efeito veneno = new Veneno("Veneno", "Causa sua duraçao em dano por rodada ao alvo", 1, 0);
        Efeito odioPuro = new AumentaDano( Cor.txtVermelho("Ódio Puro"), "Aumenta o dano causado em 1 por 3 rodadas", 3, 1);
        Efeito escudinho = new Escudo("3 pontos de escudo", "3 pontos de escudo", 0, 3);
        Efeito escudao = new Escudo("7 pontos de escudo", "7 pontos de escudo", 0, 7);
        Efeito purificarEfeito = new Purificar("Purificar", "Remove todos os efeitos aplicados em voce (incluindo bons!)", 0);
        Efeito feridas = new DanoConstante("Feridas", "Causa 1 de dano por rodada ao alvo por 3 rodadas", 3, 1);

        // poderes -------------
        Poder dedoNervoso = new MaosLeves(("DEDO NERVOSO!"), "Sempre que usar um ataque de disparo, dispare novamente pelo tanto de acumulos desse poder.");

        // cartas -----------
        Carta tiro = new CartaAtaque("Tiro", "dispara uma bala", 2, 3, 1); tiro.setResenha(Arte.POW);
        Carta espada = new CartaAtaqueComEfeito("Espada", "aplica sangramento (2 de dano por turno por 3 turnos)", 2, 1, sangramento, 2);
        Carta corteVenenoso = new CartaAtaqueComEfeito("Corte venenoso", "aplica veneno (causa sua duraçao em dano por rodada ao alvo)", 1, 1, veneno, 2);
        Carta escudoMadeira = new CartaHabilidade("Postura de defesa", "da escudo", 1, escudinho, true);
        Carta escudoFerro = new CartaHabilidade("Aura de defesa", "da muito escudo", 2, escudao, true);
        Carta purificar = new CartaHabilidade("Receba!", "Remove todos os efeitos aplicados em voce (incluindo positivos)", 2, purificarEfeito, true);
        Carta desprezo = new CartaAtaqueComEfeito("DESPREZO", "causa muito dano porém irrita seu adversario", 3, 10, odioPuro);
        Carta dedoNervosoCarta = new CartaPoder("Dedo nervoso", "Para cada acúmulo, atire novamente sempre que usar uma carta de tiro!", 2, dedoNervoso);
        
        // -------------------------

        for (int i = 0; i < 4; i++){ // cartas comuns
            pilhaCompra.addCarta( tiro );
            pilhaCompra.addCarta( espada );
            pilhaCompra.addCarta( corteVenenoso );
            pilhaCompra.addCarta( escudoMadeira );
            pilhaCompra.addCarta( escudoFerro );
            pilhaCompra.addCarta( purificar );
        }

        for(int i=0; i < 5; i++){ // cartas meio raras sla
            pilhaCompra.addCarta( desprezo );
            pilhaCompra.addCarta( dedoNervosoCarta );
        }

        Arte.printTitulo();
        Thread.sleep(1500);

        Batalha batalha = new Batalha();
        batalha.iniciar(heroi, pilhaCompra, inimigo, inimigo2, inimigo3);
        
    }
}
