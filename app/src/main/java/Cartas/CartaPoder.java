package Cartas;
import Entidades.Entidade;
import Entidades.Heroi;
import Poderes.Poder;
import Telas.Eventos.Batalha;
import Util.Cor;

public class CartaPoder extends Carta
{
    private Poder poder;
    
    public CartaPoder(String nome, String descricao, int custo, Poder poder){
        super(nome, descricao, custo);
        this.poder = poder;
        this.consumir = true;
    }

    public CartaPoder(CartaPoder copia) {
        super(copia);
        this.poder = copia.poder;
        this.consumir = true;
    }

    @Override
    public void usar (Heroi heroi, Entidade alvo, Batalha batalha){
        int energiaAtual = heroi.getEnergia();

        if(energiaAtual >= this.getCusto()){
            Poder p = poder.criaCopia();
            batalha.adicionarPoder(p);
            heroi.usarEnergia(this.getCusto());

            printaResenha();
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
        Poder p = poder.criaCopia();
        batalha.adicionarPoder(p);
        
        printaResenha();
    }

    @Override
    public String descricao(){
        return ""+this.getNome()+" - "+this.getDescricao() + Cor.txtAmareloClaro(" < custo: " + this.getCusto());
    }

    @Override
    public Carta criaCopia() {
        return new CartaPoder(this);
    }
}

