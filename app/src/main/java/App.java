import Cartas.*;
import Entidades.*;
import Telas.*;
import Deck.*;
//import java.util.Scanner;
//import EfeitosDeStatus.DanoConstante;
import EfeitosDeStatus.Efeito;

public class App {
    public static void main(String[] args) throws Exception {
        GerenciadorCartas gerenciadorCartas = new GerenciadorCartas();
        PilhaCompra pilhaCompra = new PilhaCompra();

        gerenciadorCartas.carregarCartas(false);

        //instancias padrao pra teste por enquanto
        Heroi heroi = new Heroi("Capitão Jack Sparrow", 20, 5 );
        Inimigo inimigo = new Inimigo("Capitão Barbossa", 20, 3);

        //cartas padrao pra teste por enquanto
        for (int i = 0; i < 3; i++){
            Efeito sangramento = new Efeito("Sangramento", "O inimigo perdeu 1 ponto de vida por sangramento !", 3, false) {
                @Override
                public void aplicar(Entidade alvo) {
                    alvo.receberDano(1);
                    this.passaTurno();
                }
            };
            pilhaCompra.addCarta( new CartaAtaqueComEfeito("Espada", 1, 2, sangramento ));
            pilhaCompra.addCarta( new CartaAtaque("Tiro", 2, 3) );
            pilhaCompra.addCarta( new CartaHabilidade("Escudo de madeira", 1, 2));
            pilhaCompra.addCarta( new CartaHabilidade("Escudo de ferro", 3, 5));
        }

        for(int i=0; i < 1; i++){
            pilhaCompra.addCarta( new CartaAtaque("CANHÃO", 4, 10));
        }

        Textos.principal();
        Thread.sleep(1500);

        Batalha.iniciar(heroi, pilhaCompra, inimigo);
        
    }
}
