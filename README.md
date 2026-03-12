# Pirata do Caribe - Jogo de Duelo

## Descrição

Um jogo de duelo por turnos onde você controla o **Capitão Jack Sparrow** em uma batalha contra o **Capitão Barbossa**. Use cartas de ataque e defesa para derrotar seu inimigo antes que ele o derrote!

### Mecânica do Jogo
- **Cartas de Dano**: Ataque o inimigo gastando energia
- **Cartas de Escudo**: Defenda-se contra ataques
- **Sistema de Energia**: Recupere energia ao encerrar seu turno
- **Objetivo**: Reduzir a vida do inimigo a 0 antes que ele reduza a sua

## Compilação

Execute o seguinte comando, da raíz do projeto:
```bash
javac -d bin $(find src -name "*.java")
```

## Execução

Execute o programa:
```bash
java -cp bin App
```

### Como Jogar
1. O jogo exibe o status de ambos os personagens
2. Escolha uma ação:
   - **1** - Usar Carta de Dano (Tiro)
   - **2** - Usar Carta de Escudo (Jarro de terra)
   - **3** - Encerrar turno (recupera energia e recebe dano)
3. Continue até que um dos personagens morra
4. O jogo exibe o resultado final