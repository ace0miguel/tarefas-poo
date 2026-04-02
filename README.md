# Pirata do Caribe - Jogo de Duelo

## Descrição

Um jogo de duelo por turnos onde você controla o **Capitão Jack Sparrow** em uma batalha contra o **Capitão Barbossa** e seus marujos figurantes. Use cartas de ataque e defesa para derrotar seu inimigo antes que ele o derrote!

### Mecânica do Jogo
- **Cartas de Dano**: Ataque o inimigo gastando energia
- **Cartas de Escudo**: Defenda-se contra ataques
- **Sistema de Energia**: Recupere energia ao encerrar seu turno
- **Objetivo**: Reduzir a vida dos inimigos a 0 antes que eles reduzam a sua

## Efeitos de status
- **Ódio puro**: Aumenta o dano causado pelo alvo
- **Sangramento**: Causa dano continuo no alvo

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
1. O jogo exibe o status de ambos os personagens.
2. Escolha uma ação baseada nas cartas disponíveis.
3. Continue até matar o seu inimigo (ou morrer tentando)!

