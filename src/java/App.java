import Cartas.CartaAtaque;
import Cartas.CartaAtaqueComEfeito;
import Cartas.CartaHabilidade;
import Deck.PilhaCompra;
import EfeitosDeStatus.AumentaDano;
import EfeitosDeStatus.DanoConstante;
import EfeitosDeStatus.Efeito;
import Entidades.Heroi;
import Entidades.Inimigo;
import Telas.Batalha;
import Telas.Textos;

public class App {
    public static void main(String[] args) throws Exception {
        PilhaCompra pilhaCompra = new PilhaCompra();

        //instancias padrao pra teste por enquanto
        Heroi heroi = new Heroi("Capitão Jack Sparrow", 20, 5 );
        Inimigo inimigo = new Inimigo("Capitão Barbossa", 20, 3);
        Inimigo inimigo2 = new Inimigo("Figurante randola 1", 8, 1);
        Inimigo inimigo3 = new Inimigo("Figurante randola 2", 8, 1);
        Efeito sangramento = new DanoConstante("Sangramento", "Causa 1 de dano por rodada ao alvo", 3, 2);
        Efeito odioPuro = new AumentaDano("Odio Puro", "Aumenta o dano causado em 1 por 3 rodadas", 3, 1);

        //cartas padrao pra teste por enquanto
        for (int i = 0; i < 3; i++){
            pilhaCompra.addCarta( new CartaAtaqueComEfeito("Espada - aplica sangramento (2 de dano por turno por 3 turnos)", 1, 2, sangramento));
            pilhaCompra.addCarta( new CartaAtaque("Tiro", 2, 3) );
            pilhaCompra.addCarta( new CartaHabilidade("Escudo de madeira", 1, 2));
            pilhaCompra.addCarta( new CartaHabilidade("Escudo de ferro", 3, 5));
        }

        for(int i=0; i < 2; i++){
            pilhaCompra.addCarta( new CartaAtaqueComEfeito("DESPREZO - causa muito dano porém irrita seu adversario (1 de dano extra por 3 rodadas)", 3, 10, odioPuro));
        }

        Textos.principal();
        Thread.sleep(1500);

        Batalha batalha = new Batalha();
        batalha.iniciar(heroi, pilhaCompra, inimigo, inimigo2, inimigo3);
        
    }
}
