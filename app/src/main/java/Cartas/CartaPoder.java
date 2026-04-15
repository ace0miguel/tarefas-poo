package Cartas;
import java.util.ArrayList;
import java.util.List;

import Entidades.Entidade;
import Entidades.Heroi;
import Subscribers.Poderes.Poder;
import Telas.Eventos.Batalha;
import Visual.Cor;

/** cartas que aplicam poderes (efeitos especiais, normalmente buffs permanentes dentro do combate atual) */
public class CartaPoder extends Carta
{
    private Poder poder;

    { this.tagsRemoviveis = new ArrayList<>(List.of("Area", "Manter", "Sacrificio", "Inata")); }

    public CartaPoder(String nome, String descricao, int custo, Poder poder){
        super(nome, descricao, custo);
        this.poder = poder;
        this.setConsumir(true);
        this.setSelfCast(true);
    }

    public CartaPoder(CartaPoder copia) {
        super(copia);
        this.poder = copia.poder;
        this.setConsumir(true);
        this.setSelfCast(true);
    }

    @Override
    public void usar (Heroi heroi, Entidade alvo, Batalha batalha){
        int energiaAtual = heroi.getEnergia();

        if(energiaAtual >= this.getCusto()){
            heroi.receberDanoDireto(this.sacrificio);

            aplicarEfeito(heroi, alvo, batalha);

            heroi.usarEnergia(this.getCusto());
        }
    }

    @Override
    public void aplicarEfeito(Heroi heroi, Entidade alvo, Batalha batalha) {
        Poder p = poder.criaCopia();

        batalha.adicionarSubscriber(p);
        
        printaResenha();
    }

    public String descricao(){
        String retorno = this.getNome();

        if (!tags.isEmpty()) {
            retorno += " - <" + String.join(", ",  tags) + ">";
        }

        if (!this.getDescricao().equals("")) {
            retorno += " - " + this.getDescricao();
        }

        if (this.getSacrificio() != 0){
            retorno += " - " + Cor.txtVermelho("[Sacrifício: " + this.getSacrificio() + "]");
        }

        retorno += Cor.cinza + " (" + this.poder.getNome() + ")"  +  Cor.txtAmareloClaro(" < custo: " + this.getCusto());
        return retorno;
    }

    @Override
    public Carta criaCopia() {
        return new CartaPoder(this);
    }
}

