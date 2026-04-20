package cartas;
import java.util.ArrayList;
import java.util.List;

import batalhaListeners.poderes.Poder;
import entidades.Entidade;
import entidades.Heroi;
import telas.eventos.combate.Batalha;

/** cartas que aplicam poderes (buff permanente durante o combate atual, que normalmente acumulam). Por definiçao, sempre possuem a tag Consumir */
public class CartaPoder extends Carta
{
    private Poder poder;

    { // definindo as tags removiveis e compraveis para cartas de poder. sao obrigatoriamente consumir e nao causam efeito em area
        this.tagsRemoviveis = new ArrayList<>(List.of("Manter", "Inata")); 
        this.tagsCompraveis = new ArrayList<>(List.of("Manter", "Inata"));
    }

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
        p.setAlvo(heroi);
        batalha.adicionarSubscriber(p);
        
        printaResenha();
    }

    public String descricao(){
        StringBuilder retorno = iniciarDescricao();

        appendTagsDescricao(retorno);
        appendDescricao(retorno);
        appendSacrificio(retorno);

        appendCustoDescricao(retorno);

        return finalizarDescricao(retorno);
    }

    @Override
    public Carta criaCopia() {
        return new CartaPoder(this);
    }
}

