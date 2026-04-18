package cartas;
import java.util.ArrayList;
import java.util.List;

import batalhaListeners.poderes.Poder;
import entidades.Entidade;
import entidades.Heroi;
import telas.eventos.combate.Batalha;
import visual.Cor;

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

        return fecharColchete(retorno);
    }

    @Override
    public Carta criaCopia() {
        return new CartaPoder(this);
    }
}

