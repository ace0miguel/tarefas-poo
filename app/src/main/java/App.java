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
        for (int i = 0; i < 100; i++){
            Efeito sangramento = new Efeito("Sangramento", "Da dano por 3 turnos", 3, false) {
                @Override
                public void aplicar(Entidade alvo) {
                    alvo.receberDano(1);
                    this.passaTurno();
                }
            };
            pilhaCompra.addCarta( new CartaAtaqueComEfeito("Espada", 3, 3, sangramento ));
            pilhaCompra.addCarta( new CartaAtaque("Tiro", 1, 2) );
            pilhaCompra.addCarta( new CartaHabilidade("Jarro de terra", 1, 3) );
        }

        for(int i=0; i < 20; i++){
            pilhaCompra.addCarta( new CartaAtaque("CANHÃO", 4, 10));
        }

        Textos.principal();
        Thread.sleep(2000);

        Batalha.iniciar(heroi, pilhaCompra, inimigo);
        
    }
}
