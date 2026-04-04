import Cartas.Carta;
import Cartas.CartaAtaque;
import Cartas.CartaAtaqueComEfeito;
import Cartas.CartaHabilidade;
import Cartas.CartaPoder;
import EfeitosDeStatus.Buffs.AumentaDano;
import EfeitosDeStatus.Buffs.AumentaResistencia;
import EfeitosDeStatus.DanosConstantes.DanoConstante;
import EfeitosDeStatus.DanosConstantes.Sangramento;
import EfeitosDeStatus.DanosConstantes.Veneno;
import EfeitosDeStatus.Efeito;
import EfeitosDeStatus.Instantaneos.Escudo;
import EfeitosDeStatus.Instantaneos.GanhaEnergia;
import EfeitosDeStatus.Instantaneos.Purificar;
import Entidades.Heroi;
import Entidades.Inimigo;
import Poderes.MaosLeves;
import Poderes.Poder;
import Telas.Mapa;
import Util.Arte;
import Util.Cor;
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
        Heroi heroi = new Heroi("Capitão Jack Sparrow", 55, 5);

        // efeitos ------------
        Efeito sangramento = new Sangramento("Sangramento", "Causa 1 de dano por rodada ao alvo", 3, 1);
        Efeito veneno = new Veneno("Veneno", "Causa sua duraçao em dano por rodada ao alvo", 2, 1);
        Efeito odioPuro = new AumentaDano("Ódio Puro", "Aumenta o dano causado em 1 por 3 rodadas", 3, 1);
        Efeito aumentaResistencia = new AumentaResistencia("Armadura", "Reduz o dano recebido", 3, 1);
        Efeito escudinho = new Escudo("Ganho de escudo (3)", "3 pontos de escudo", 0, 3);
        Efeito escudao = new Escudo("Ganho de escudo (7)", "7 pontos de escudo", 0, 7);
        Efeito purificarEfeito = new Purificar("Purificar", "Remove todos os efeitos aplicados em voce (incluindo bons!)", 0);
        Efeito feridas = new DanoConstante("Feridas", "Causa 1 de dano por rodada ao alvo por 2 rodadas", 2, 1);
        Efeito ganhaEnergia2 = new GanhaEnergia("Ganho de energia (2)", "Ganha 2 ponto de energia", 0, 2);

        // poderes -------------
        Poder dedoNervoso = new MaosLeves(("JOHN WICK!"), "Sempre que atirar, ATIRE NOVAMENTE! pelo tanto de acumulos desse poder.", 1);
        Poder mestreLaminas = new MaosLeves(("MESTRE DAS LÂMINAS"), "Sempre que cortar, CORTE NOVAMENTE! pelo tanto de acumulos desse poder.", 2);

        // cartas -----------
        Carta tiro = new CartaAtaque("Tiro de revolver", "", 2, 3, 1); tiro.setResenha(Arte.TIRO5);
        Carta tiroEscopeta = new CartaAtaque("Tiro de escopeta", "", 4, 7, 1); tiroEscopeta.setResenha(Arte.TIRO1);

        Carta tiroCanhao = new CartaAtaqueComEfeito("Tiro de canhão", "dispara uma bala de canhão", 5, 9, feridas, false, 1); tiroCanhao.setResenha(Arte.TIRO4);
        Carta espada = new CartaAtaqueComEfeito("Corte profundo", "", 1, 2, sangramento, false, 2); espada.setResenha(Cor.txtVermelho(Arte.CORTE));
        Carta corteVenenoso = new CartaAtaqueComEfeito("Corte venenoso", "", 1, 1, veneno, false, 2); corteVenenoso.setResenha(Cor.txtVerdeClaro(Arte.CORTE2));
        Carta desprezo = new CartaAtaqueComEfeito("DESPREZO", "causa muito dano porém irrita seu adversario", 4, 10, odioPuro, false); desprezo.setResenha(Arte.DESPREZO);
        Carta corteDefensivo = new CartaAtaqueComEfeito("Corte defensivo", "bate e ganha escudo!", 1, 1, escudinho, true, 2); corteDefensivo.setResenha(Cor.txtAzulClaro(Arte.CORTE5));
        Carta corteRapido = new CartaAtaqueComEfeito("Corte rapido", "bate e ganha 2 pontos de energia!", 1, 1, ganhaEnergia2, true, 2); corteRapido.setResenha(Cor.txtAmareloClaro(Arte.CORTE4));

        Carta armadura = new CartaHabilidade("armadura", "reduz o dano recebido", 2, aumentaResistencia, true);
        Carta escudoMadeira = new CartaHabilidade("Postura de defesa", "da escudo", 1, escudinho, true);
        Carta escudoFerro = new CartaHabilidade("Aura de defesa", "da MUITO escudo", 2, escudao, true);
        Carta purificar = new CartaHabilidade("Receba!", "Remove todos os efeitos aplicados em voce (incluindo positivos)", 2, purificarEfeito, true);

        
        Carta dedoNervosoCarta = new CartaPoder("JOHN WICK", "Para cada acúmulo, atire novamente sempre que usar uma carta de tiro!", 2, dedoNervoso);
        Carta mestreLaminasCarta = new CartaPoder("Mestre das lâminas", "Para cada acúmulo, corte novamente sempre que usar uma carta de corte!", 1, mestreLaminas);
        
        // ------------------------- adicionando umas cartas ai pro baralho do heroi so pra teste, depois tem q deixa bonito tb

        for (int i = 0; i < 3; i ++){ // cartas comunzinhas
            heroi.addCarta( espada );
            heroi.addCarta( corteDefensivo );
            heroi.addCarta( corteRapido );
            heroi.addCarta( corteVenenoso );
            heroi.addCarta( tiro );
            heroi.addCarta( escudoFerro );
            // essas de baixo eram pra ser muito raras mas o jogo ta tao impossivel q eu vo bota elas aqui pra ve se fica ganhavel
            heroi.addCarta( dedoNervosoCarta );
            heroi.addCarta( mestreLaminasCarta );       
        }

        for (int i = 0; i < 2; i++){ // cartas meio raras       
            heroi.addCarta( tiroEscopeta );
            heroi.addCarta( escudoMadeira );
            heroi.addCarta( armadura );
            heroi.addCarta( purificar );   
        }

        for(int i=0; i < 1; i++){ // cartas ultra raras supremas
            heroi.addCarta( tiroCanhao );
            heroi.addCarta( desprezo );
        }
        
        Textos.limpaTela();
        Textos.printaBonito((Arte.tituloSombreado + "\n"), 2, 2);
        System.out.println();
        Thread.sleep(500);

        InputHandler.esperar();

        // se quiser pular o mapa e ir direto pra uma batalha teste descomenta aqui
        // Batalha batalha = new Batalha(heroi, inimigo, inimigo2, inimigo3);
        // batalha.iniciar();
        
        Mapa mapa = new Mapa(heroi);
        mapa.explorar();

        // se chegou ate aq e pq vc venceu o jogo todo parabens!!
        Textos.printaLinhaDevagar(Arte.PEROLANEGRA);
        System.out.println();
        InputHandler.esperar();
    }
}
