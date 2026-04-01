package Cartas;
import Entidades.Entidade;
import Entidades.Heroi;
import Poderes.Poder;
import Telas.Batalha;
/*
Aplica um efeito permanente para todo o encontro de combate, normalmente stackam, so pode ser usada uma vez por copia. (falta implementar essa parte)
*/

public class CartaPoder extends Carta
{
    private Poder poder;

    public CartaPoder(String nome, int custo, Poder poder){
        super(nome, custo);
        this.poder = poder;
    }

    @Override
    public void usar (Heroi heroi, Entidade alvo, Batalha batalha){
        int energiaAtual = heroi.getEnergia();

        if(energiaAtual >= this.getCusto()){
            Poder p = poder.criaCopia();
            batalha.adicionarPoder(p);
            heroi.usarEnergia(this.getCusto());
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
        Poder p = poder.criaCopia();
        batalha.adicionarPoder(p);
    }

    

    public String descricao(){
        return "Usar "+this.getNome()+" [custo: " + this.getCusto() + "]";
    }
}

