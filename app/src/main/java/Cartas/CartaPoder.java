package Cartas;
import Entidades.*;
import Telas.Batalha;
import Poderes.Poder;
/*
Aplica um efeito permanente para todo o encontro de combate, normalmente stackam
*/

public class CartaPoder extends Carta
{
    private Poder poder;

    public CartaPoder(String nome, int custo, Poder escudo){
        super(nome, custo);
        this.poder = poder;
    }

    @Override
    public void usar (Heroi heroi, Entidade alvo, Batalha batalha){
        int energiaAtual = heroi.getEnergia();

        if(energiaAtual >= this.getCusto()){
            Poder p = poder.criaCopia();
            batalha.adicionarPoder(p);
        }
    }

    public String descricao(){
        return "Usar "+this.getNome()+" [custo: " + this.getCusto() + "]";
    }
}

