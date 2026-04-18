package fabricas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import batalhaListeners.efeitos.Efeito;
import batalhaListeners.efeitos.buffs.AumentaDano;
import batalhaListeners.efeitos.buffs.AumentaResistencia;
import batalhaListeners.efeitos.danosConstantes.DanoConstante;
import batalhaListeners.efeitos.danosConstantes.Sangramento;
import batalhaListeners.efeitos.danosConstantes.Veneno;
import batalhaListeners.efeitos.instantaneos.AdicionaCarta;
import batalhaListeners.efeitos.instantaneos.EscolheCarta;
import batalhaListeners.efeitos.instantaneos.Escudo;
import batalhaListeners.efeitos.instantaneos.GanhaEnergia;
import batalhaListeners.efeitos.instantaneos.Purificar;
import batalhaListeners.efeitos.instantaneos.PuxaCarta;
import batalhaListeners.efeitos.latentes.EcoaDano;
import batalhaListeners.efeitos.latentes.Energizar;
import static fabricas.FabricaCartas.clubex;
import static fabricas.FabricaCartas.resenhax;

public class FabricaEfeitos {
    public static List<Efeito> listaEfeitosMoldes = new ArrayList<>();

    public static Efeito sangramento = new Sangramento("Sangramento", "Causa 1 de dano por rodada ao alvo", 3, 1);

    public static Efeito veneno1 = new Veneno("Veneno", "Causa sua duraçao em dano por rodada ao alvo", 1, 1);

    public static Efeito veneno = new Veneno("Veneno", "Causa sua duraçao em dano por rodada ao alvo", 2, 1);

    public static Efeito veneno4 = new Veneno("Veneno", "Causa sua duraçao em dano por rodada ao alvo", 4, 1);

    public static Efeito ego = new AumentaDano("Ego", "Aumenta o dano causado em 25% por 4 rodadas", 4, 25);

    public static Efeito aumentaResistencia = new AumentaResistencia("Aura", "Reduz o dano recebido em 25%", 3, 25);

    public static Efeito escudo2 = new Escudo("Ganho de escudo (2)", "2 pontos de escudo", 2);

    public static Efeito escudo4 = new Escudo("Ganho de escudo (4)", "4 pontos de escudo", 4);

    public static Efeito escudo10 = new Escudo("Ganho de escudo (10)", "10 pontos de escudo", 10);

    public static Efeito purificarEfeito = new Purificar("Purificar", "Remove todos os efeitos aplicados em voce (incluindo bons!)");

    public static Efeito feridas = new DanoConstante("Feridas", "Causa 1 de dano por rodada ao alvo por 2 rodadas", 2, 1);

    public static Efeito ganhaEnergia2 = new GanhaEnergia("Ganho de energia (2)", "Ganha 2 pontos de energia", 2);

    public static Efeito ganhaEnergia1 = new GanhaEnergia("Ganho de energia (1)", "Ganha 1 ponto de energia", 1);

    public static Efeito ganhaEnergiaTest = new GanhaEnergia("Ganho de energia (60)", "Ganha 60 pontos de energia", 60);
    // novos efeitos
    public static Efeito efeitoPuxaCarta2 = new PuxaCarta("Puxa duas cartas", "Puxa duas cartas", 2);

    public static Efeito efeitoEnergizado = new Energizar("Energizado", "Ganhe dois pontos de energia na proxima rodada", 1, 2);

    public static AdicionaCarta ganhaResenhax = new AdicionaCarta("Resenhax", "Recebe um resenhax", null); 
       
    public static AdicionaCarta ganhaClubex = new AdicionaCarta("Clubex", "Recebe um clubex", null);

    public static EscolheCarta escolheCarta = new EscolheCarta("Escolhe uma carta", "Escolha uma carta da pilha de compra");

    public static Efeito ecoaDano50 = new EcoaDano("Eco Dolor", "Causa uma parte do dano acumulado no fim da duraçao", 4, 50);

    public static void carregar(){
        listaEfeitosMoldes.clear();

        ganhaResenhax.setCarta(resenhax);
        ganhaClubex.setCarta(clubex);

        listaEfeitosMoldes.addAll(Arrays.asList(sangramento, veneno, veneno1, ego, aumentaResistencia, 
            escudo4, escudo10, purificarEfeito, feridas, ganhaEnergia2, 
            ganhaEnergia1, ganhaEnergiaTest, efeitoPuxaCarta2, efeitoEnergizado, 
            ganhaResenhax, ganhaClubex, escolheCarta, veneno4)); 
    }
}
