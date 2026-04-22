package fabricas;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import entidades.Inimigo;
import static fabricas.FabricaCartas.beberVeneno;
import static fabricas.FabricaCartas.ferida;
import static fabricas.FabricaCartas.nada;
import static fabricas.FabricaCartas.sangrar;
import static fabricas.FabricaEfeitos.aumentaResistencia;
import static fabricas.FabricaEfeitos.ego;
import static fabricas.FabricaEfeitos.escudo10;
import static fabricas.FabricaEfeitos.sangramento;
import static fabricas.FabricaEfeitos.veneno;
import static fabricas.FabricaEfeitos.veneno1;
import util.Acao;

public class FabricaInimigos {
    public static Inimigo barbossa;
    public static Inimigo loudCoringa;
    public static Inimigo endrick;
    public static Inimigo drake;
    public static Inimigo paulAtreides;
    public static Inimigo paulAtreidesSupremo;
    public static Inimigo sabrinaCarpenter;
    public static Inimigo tripleT;
    public static Inimigo loudSacy;
    public static Inimigo amoeba;
    public static Inimigo kungFuPanda;

    public static List<Inimigo> listaTodosInimigos = new ArrayList<>();

    private static void atualizarListaTodosInimigos() {
        listaTodosInimigos.clear();

        for (Field field : FabricaInimigos.class.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            if (!Inimigo.class.isAssignableFrom(field.getType())) {
                continue;
            }

            try {
                Inimigo inimigo = (Inimigo) field.get(null);
                if (inimigo != null) {
                    listaTodosInimigos.add(inimigo);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Erro ao montar lista de moldes de cartas", e);
            }
        }
    }

    public static void carregar(){
        listaTodosInimigos.clear();

        // tier 1 -----------------
        endrick = new Inimigo("Endrick", 30, 8,
            new Acao.Atacar(), new Acao.AtacarEfeito(veneno)
        ); endrick.setTier(1);

        // suporte
        drake = new Inimigo("Drake", 20, 6,
            new Acao.AplicarEfeitoAliadoMaisForte(ego), new Acao.AplicarEfeitoAliadoMaisForte(escudo10)
        ); drake.setTier(1);

        // duplica ao chegar na metade da vida
        amoeba = new Inimigo("Amoeba", 21, 6,
            new Acao.AtacarEfeito(veneno1), new Acao.AtacarEAdicionarCarta(nada)
        ); amoeba.setTier(1); amoeba.setAcaoMeiaVida(new Acao.multiplicar(2));

        // tier 2 -----------------
        // ganha escudo ao chegar na metade da vida 
        barbossa = new Inimigo("Capitão Hector Barbossa", 50, 10,
            new Acao.Atacar(), new Acao.AtacarEfeito(sangramento), new Acao.AtacarEAdicionarCarta(ferida)
        ); barbossa.setTier(2); barbossa.setAcaoMeiaVida(new Acao.ReceberEfeito(escudo10));

        // assassino
        loudCoringa = new Inimigo("LOUD Coringa", 40, 8, 
            new Acao.AtacarVidaPerdida(), new Acao.AtacarEfeito(sangramento), new Acao.AdicionarCarta(sangrar)
        ); loudCoringa.setTier(2);

        // suporte, mas ataca 1 vez quando chega na meia vida
        loudSacy = new Inimigo("LOUD Sacy", 40, 6, 
            new Acao.AdicionarCarta(sangrar), new Acao.AplicarEfeitoAliadoMaisForte(ego), 
            new Acao.AplicarEfeitoAliadoMaisForte(escudo10), new Acao.AplicarEfeitoAliadoMaisForte(aumentaResistencia)
        ); loudSacy.setTier(2); loudSacy.setAcaoMeiaVida(new Acao.AtacarEfeito(sangramento));

        kungFuPanda = new Inimigo("Kung Fu Panda", 60, 6,
            new Acao.AtacarEfeito(sangramento), new Acao.AtacarEAdicionarCarta(beberVeneno)
        ); kungFuPanda.setTier(2); kungFuPanda.setAcaoMeiaVida(new Acao.ReceberEfeito(escudo10));

        // tier 3 -----------------
        sabrinaCarpenter = new Inimigo("Sabrina Carpenter", 65, 15,
            new Acao.Atacar(), new Acao.AtacarEfeito(sangramento), new Acao.ReceberEfeito(ego)
        ); sabrinaCarpenter.setTier(3); sabrinaCarpenter.setAcaoMeiaVida(new Acao.ReceberEfeito(ego));
        
        // pouca vida mas bate forte
        tripleT = new Inimigo("Tung Tung Tung \"Triple T\" Sahur", 67, 10, 
            new Acao.AtacarEAdicionarCarta(beberVeneno), new Acao.AtacarEfeito(sangramento), new Acao.AtacarVidaPerdida()
        ); tripleT.setTier(3);

        // tier 4 (goats) -----------------
        paulAtreides = new Inimigo("Paul Muad'Dib Atreides", 80, 16, // ESSE AQUI E FORTE VIU MEIO QUE O BOSS
            new Acao.AtacarVidaPerdida(), new Acao.ReceberEfeito(ego), new Acao.AtacarEfeito(sangramento), new Acao.AtacarEAdicionarCarta(ferida)
        ); paulAtreides.setTier(4);

        // tier 5 (boss de area) -----------------
        paulAtreidesSupremo = new Inimigo("PAUL MUAD'DIB ATREIDES, DUKE OF ARRAKIS, LISAN AL GAIB, KWISATZ HADERACH.", 200, 20, // ESSE AQUI E FORTE VIU MEIO QUE O BOSS
            new Acao.ReceberEfeito(aumentaResistencia), new Acao.ReceberEfeito(ego), new Acao.AtacarEfeito(sangramento), new Acao.AtacarVidaPerdida()
        ); paulAtreidesSupremo.setTier(5); paulAtreidesSupremo.setAcaoMeiaVida(new Acao.ReceberEfeito(escudo10));

        atualizarListaTodosInimigos();
    }
}
