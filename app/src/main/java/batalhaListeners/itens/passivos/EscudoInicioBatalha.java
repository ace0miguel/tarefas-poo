package batalhaListeners.itens.passivos;

/** da escudo no inicio da batalha
 * @param valor a quantidade de escudo que o item da no inicio da batalha
 */
public class EscudoInicioBatalha extends ItemPassivo {
    int valor;

    public EscudoInicioBatalha(String nome, String descricao, int valor, int custo) {
        super(nome, descricao, custo);
        this.valor = valor;
    }

    public EscudoInicioBatalha(EscudoInicioBatalha copiado){
        super(copiado);
        this.valor = copiado.valor;
    }

    @Override
    public EscudoInicioBatalha criaCopia() {
        return new EscudoInicioBatalha(this);
    }

    @Override
     public void onBattleStart(telas.eventos.combate.Batalha batalha, entidades.Heroi heroi) {
        heroi.setEscudoBonus(heroi.getEscudoBonus() + this.valor);
    }
}
