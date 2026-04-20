// package cartas;

// import batalhaListeners.efeitos.Efeito;

// /** ainda nao ta implementado, mas tive a ideia e criei a classe aqui ia ser daora */
// public class CartaAtaqueEfeitoCondicional extends CartaAtaqueComEfeito {
//     private boolean condicao;

//     public CartaAtaqueEfeitoCondicional(String nome, String descricao, int custo, int dano, Efeito efeito,Boolean selfCast, Boolean condicao) {
//         super(nome, descricao, custo, dano, efeito, selfCast);
//         this.condicao = condicao;
//     }

//     public CartaAtaqueEfeitoCondicional(CartaAtaqueEfeitoCondicional copia) {
//         super(copia);
//         this.condicao = copia.condicao;
//     }

//     @Override
//     public Carta criaCopia() {
//         return new CartaAtaqueEfeitoCondicional(this);
//     }
// }
