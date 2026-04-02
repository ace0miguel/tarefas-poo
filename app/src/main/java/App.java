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

public class App {
    public static void main(String[] args) throws Exception {


        // GerenciadorCartas gerenciadorCartas = new GerenciadorCartas();
        PilhaCompra pilhaCompra = new PilhaCompra();

        // gerenciadorCartas.carregarCartas(false);

        //instancias padrao pra teste por enquanto

        // heroi -----------
        Heroi heroi = new Heroi("Capitão Jack Sparrow", 40, 5 );

        // inimigos -------------
        Inimigo inimigo = new Inimigo("Capitão Barbossa", 30, 4);
        Inimigo inimigo2 = new Inimigo("LOUD Coringa", 8, 3);    
        Inimigo inimigo3 = new Inimigo("Endrick", 15, 4);

        // efeitos ------------
        Efeito sangramento = new Sangramento("Sangramento", "Causa 1 de dano por rodada ao alvo", 3, 0);
        Efeito veneno = new Veneno("Veneno", "Causa sua duraçao em dano por rodada ao alvo", 1, 0);
        Efeito odioPuro = new AumentaDano("Ódio Puro", "Aumenta o dano causado em 1 por 3 rodadas", 3, 1);
        Efeito escudinho = new Escudo("3 pontos de escudo", "3 pontos de escudo", 0, 3);
        Efeito escudao = new Escudo("7 pontos de escudo", "7 pontos de escudo", 0, 7);
        Efeito purificarEfeito = new Purificar("Purificar", "Remove todos os efeitos aplicados em voce (incluindo bons!)", 0);
        Efeito feridas = new DanoConstante("Feridas", "Causa 1 de dano por rodada ao alvo por 3 rodadas", 3, 1);

        // poderes -------------
        Poder dedoNervoso = new MaosLeves(("DEDO NERVOSO!"), "Sempre que atirar, ATIRE NOVAMENTE! pelo tanto de acumulos desse poder.", 1);
        Poder mestreLaminas = new MaosLeves(("MESTRE DAS LÂMINAS"), "Sempre que cortar, CORTE NOVAMENTE! pelo tanto de acumulos desse poder.", 2);

        // cartas -----------
        Carta tiro = new CartaAtaque("Tiro", "dispara uma bala", 2, 3, 1); tiro.setResenha(Arte.POW);
        Carta espada = new CartaAtaqueComEfeito("Espada", "", 2, 1, sangramento, 2); espada.setResenha(Arte.CORTE);
        Carta corteVenenoso = new CartaAtaqueComEfeito("Corte venenoso", "", 1, 1, veneno, 2); corteVenenoso.setResenha(Arte.CORTEVERDE);
        Carta escudoMadeira = new CartaHabilidade("Postura de defesa", "da escudo", 1, escudinho, true);
        Carta escudoFerro = new CartaHabilidade("Aura de defesa", "da muito escudo", 2, escudao, true);
        Carta purificar = new CartaHabilidade("Receba!", "Remove todos os efeitos aplicados em voce (incluindo positivos)", 2, purificarEfeito, true);
        Carta desprezo = new CartaAtaqueComEfeito("DESPREZO", "causa muito dano porém irrita seu adversario", 3, 10, odioPuro); desprezo.setResenha(Arte.DESPREZO);
        Carta dedoNervosoCarta = new CartaPoder("Dedo nervoso", "Para cada acúmulo, atire novamente sempre que usar uma carta de tiro!", 2, dedoNervoso);
        Carta mestreLaminasCarta = new CartaPoder("Mestre das lâminas", "Para cada acúmulo, corte novamente sempre que usar uma carta de corte!", 1, mestreLaminas);
        
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
            pilhaCompra.addCarta( mestreLaminasCarta );
            pilhaCompra.addCarta( desprezo );
            pilhaCompra.addCarta( dedoNervosoCarta );
        }

        Arte.printTitulo();
        
        Thread.sleep(1500);

        Batalha batalha = new Batalha();
        batalha.iniciar(heroi, pilhaCompra, inimigo, inimigo2, inimigo3);
        
    }
}
