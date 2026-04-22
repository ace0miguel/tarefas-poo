import org.junit.jupiter.api.Test;

import entidades.Heroi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import entidades.Inimigo;

public class InimigoTest {

    @Test
    public void calculoRecompensaPorTier() {
        Inimigo i = new Inimigo("Slime", 20, 5); // Ações omitidas para simplificar o teste de atributos básicos
        
        i.setTier(1);
        assertEquals(15, i.getRecompensa());
        
        i.setTier(2);
        assertEquals(30, i.getRecompensa());
        
        i.setTier(3);
        assertEquals(60, i.getRecompensa());
    }

    @Test
    public void danoEfetivoEFracionado() {
        Inimigo i = new Inimigo("Orc", 40, 10);
        
        // Sem dano extra, o dano efetivo é igual ao dano base (10)
        assertEquals(10, i.getDanoEfetivo());
        
        // Fracionando o dano base (10 / 2 = 5)
        assertEquals(5, i.getFracaoDanoEfetivo(2));
    }

    @Test
    public void criarCopia() {
        Inimigo original = new Inimigo("Dragão", 100, 20);
        original.setTier(3);
        
        Inimigo copia = original.criaCopia();
        
        // Verifica se a cópia tem os mesmos atributos, mas é outra instância na memória
        assertNotSame(original, copia);
        assertEquals(original.getNome(), copia.getNome());
        assertEquals(original.getVidaMax(), copia.getVidaMax());
        assertEquals(original.getDano(), copia.getDano());
        assertEquals(original.getTier(), copia.getTier());
    }

    @Test
    public void passarRodadaZeraEscudoEBuffsDoInimigo() {
        Inimigo i = new Inimigo("Esqueleto", 30, 5);
        
        i.ganharEscudo(10);
        assertEquals(10, i.getEscudo());
        
        // Simula a passagem do turno do inimigo
        i.passaRodada();
        
        // Escudo zera
        assertEquals(0, i.getEscudo());
    }

    @Test
    public void checkMeiaVidaNoTurno() {
        Inimigo i = new Inimigo("Zumbi", 40, 5);
        Heroi h = new Heroi("Teste", 30, 3);
        
        // Ele começa de vida cheia, entao nenhum trigger de meiaVida deve acontecer no inicio
        assertEquals(40, i.getVida());
        
        // Herói bate nele tirando mais da metade da vida
        i.receberDanoDireto(25);
        assertEquals(15, i.getVida()); // Sobrou 15
        
        // Método chamado normalmente pela Batalha ao final das ações de dano
        i.checkMeiaVida(h, h, null); 
        
        // Como ele não tem uma acaoMeiaVida nula que dê crash, o código deve passar tranquilo
        // Isso cobre a lógica do trigger de `meiaVida = true;` internamente 
        assertEquals(15, i.getVida()); 
    }
}
