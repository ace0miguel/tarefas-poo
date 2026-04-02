import Cartas.Carta;
import Cartas.CartaAtaque;
import Cartas.CartaAtaqueComEfeito;
import Cartas.CartaHabilidade;
import Cartas.CartaPoder;
import Deck.PilhaCompra;
import EfeitosDeStatus.AumentaDano;
import EfeitosDeStatus.DanosConstantes.DanoConstante;
import EfeitosDeStatus.DanosConstantes.Sangramento;
import EfeitosDeStatus.DanosConstantes.Veneno;
import EfeitosDeStatus.Efeito;
import EfeitosDeStatus.Escudo;
import EfeitosDeStatus.GanhaEnergia;
import EfeitosDeStatus.Purificar;
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

        // instancias padrao pra teste por enquanto

        // heroi -----------
        Heroi heroi = new Heroi("Capitão Jack Sparrow", 40, 5 );

        // inimigos -------------
        Inimigo inimigo = new Inimigo("Capitão Hector Barbossa", 30, 4);
        Inimigo inimigo2 = new Inimigo("LOUD Coringa", 10, 3);    
        Inimigo inimigo3 = new Inimigo("Endrick", 15, 4);

        // efeitos ------------
        Efeito sangramento = new Sangramento("Sangramento", "Causa 1 de dano por rodada ao alvo", 3, 0);
        Efeito veneno = new Veneno("Veneno", "Causa sua duraçao em dano por rodada ao alvo", 2, 0);
        Efeito odioPuro = new AumentaDano("Ódio Puro", "Aumenta o dano causado em 1 por 3 rodadas", 3, 1);
        Efeito escudinho = new Escudo("Ganho de escudo(3)", "3 pontos de escudo", 0, 3);
        Efeito escudao = new Escudo("Ganho de escudo(7)", "7 pontos de escudo", 0, 7);
        Efeito purificarEfeito = new Purificar("Purificar", "Remove todos os efeitos aplicados em voce (incluindo bons!)", 0);
        Efeito feridas = new DanoConstante("Feridas", "Causa 1 de dano por rodada ao alvo por 2 rodadas", 2, 1);
        Efeito ganhaEnergia2 = new GanhaEnergia("Ganho de energia(2)", "Ganha 2 ponto de energia", 0, 2);

        // poderes -------------
        Poder dedoNervoso = new MaosLeves(("JOHN WICK!"), "Sempre que atirar, ATIRE NOVAMENTE! pelo tanto de acumulos desse poder.", 1);
        Poder mestreLaminas = new MaosLeves(("MESTRE DAS LÂMINAS"), "Sempre que cortar, CORTE NOVAMENTE! pelo tanto de acumulos desse poder.", 2);

        // cartas -----------
        Carta tiro = new CartaAtaque("Tiro de revolver", "dispara uma bala", 2, 3, 1); tiro.setResenha(Arte.POW);
        Carta tiroEscopeta = new CartaAtaque("Tiro de escopeta", "dispara uma bala de 12", 4, 7, 1); tiroEscopeta.setResenha(Arte.POW);

        Carta tiroCanhao = new CartaAtaqueComEfeito("Tiro de canhão", "dispara uma bala de canhão", 5, 9, feridas, 1); tiroCanhao.setResenha(Arte.POW);
        Carta espada = new CartaAtaqueComEfeito("Lamina afiada", "", 1, 1, sangramento, 2); espada.setResenha(Arte.CORTE);
        Carta corteVenenoso = new CartaAtaqueComEfeito("Lamina venenosa", "", 1, 1, veneno, 2); corteVenenoso.setResenha(Arte.CORTEVERDE);
        Carta desprezo = new CartaAtaqueComEfeito("DESPREZO", "causa muito dano porém irrita seu adversario", 4, 10, odioPuro); desprezo.setResenha(Arte.DESPREZO);
        Carta corteDefensivo = new CartaAtaqueComEfeito("Corte defensivo", "bate e ganha escudo!", 1, 1, escudinho, 2); corteDefensivo.setResenha(Arte.CORTE); corteDefensivo.setSelfCast(true);
        Carta corteRapido = new CartaAtaqueComEfeito("Corte rapido", "bate e ganha 2 pontos de energia!", 1, 1, ganhaEnergia2, 2); corteRapido.setResenha(Arte.CORTE); corteRapido.setSelfCast(true);

        Carta escudoMadeira = new CartaHabilidade("Postura de defesa", "da escudo", 1, escudinho, true);
        Carta escudoFerro = new CartaHabilidade("Aura de defesa", "da MUITO escudo", 2, escudao, true);
        Carta purificar = new CartaHabilidade("Receba!", "Remove todos os efeitos aplicados em voce (incluindo positivos)", 2, purificarEfeito, true);

        
        Carta dedoNervosoCarta = new CartaPoder("JOHN WICK", "Para cada acúmulo, atire novamente sempre que usar uma carta de tiro!", 2, dedoNervoso);
        Carta mestreLaminasCarta = new CartaPoder("Mestre das lâminas", "Para cada acúmulo, corte novamente sempre que usar uma carta de corte!", 1, mestreLaminas);
        
        // -------------------------

        for (int i = 0; i < 3; i++){ // cartas comuns
            pilhaCompra.addCarta( tiro );
            pilhaCompra.addCarta( espada );
            pilhaCompra.addCarta( tiroEscopeta );
            pilhaCompra.addCarta( corteVenenoso );
            pilhaCompra.addCarta( escudoMadeira );
            pilhaCompra.addCarta( escudoFerro );
            pilhaCompra.addCarta( purificar );
            pilhaCompra.addCarta( corteDefensivo );
            pilhaCompra.addCarta( corteRapido );
        }

        for(int i=0; i < 2; i++){ // cartas meio raras sla
            pilhaCompra.addCarta( tiroCanhao );
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
