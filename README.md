# Piratas do Caribe - Card Game

Jogo de cartas em turnos no terminal, com foco em combate, efeitos de status, construção de deck e batalhas contra múltiplos inimigos.

## Descrição

Você joga como Jack Sparrow e enfrenta encontros no mapa utilizando cartas. A instancia base das cartas, dos efeitos e dos inimigos está centralizada em `Moldes`. Ganhe dinheiro e cartas ao vencer encontros, e gaste em eventos aleatórios!

## Mecanicas especiais

- Ao usar todas as cartas da sua mao, receba uma nova mao completa e 2 pontos de energia!
- Pague 5 dinheiros pra remover uma carta do seu baralho no deckBuilder™.   
- [CONSUMIR] - Cartas que ao serem utilizadas nao voltam pra sua mao até o fim do combate
- Eventos aleatórios a cada encontro! (mas tem q ganhar duas batalhas seguidas antes).

## Eventos aleatórios

- Fogueira: escolha entre se curar ou receber cartas!
- Loja: mesma coisa, só que pagando! (Compensa se tiver com os dolares)
- Cassino: aposte vida ou dinheiro em um jogo de dados, em busca do dobro ou nada!
- GOLDEN FREDDY: sempre, ao viajar no mapa, voce pode receber uma surpresa...

## Cartas 

#### Tags:
- Consumir: só pode ser utilizada uma vez por encontro.
- Área: a carta afeta TODOS os inimigos no encontro.

### Ataque
- Tiro de revolver
- Tiro de escopeta
- Tiro de canhão (aplica Feridas)
- Corte profundo (aplica Sangramento)
- Corte venenoso (aplica Veneno)
- Corte defensivo (dano + escudo)
- Corte rapido (dano + energia)
- Desprezo. (dano alto + aplica Ego no alvo)
- BOMBA! (dano em area)
- BOMBA DE VENENO! (dano em area + aplica Veneno)
- Clubex ([CONSUMIR], dano em area)
- Aposte vida ou morte no cassino e talvez voce encontre mais uma...

### Habilidade
- Aura (Aumento de resistencia)
- Postura de defesa (Escudo)
- Shieldao (Escudo alto)
- RECEBA! (Purificar)
- Ego. (Aumento de dano)
- Ganancia (Puxe duas cartas)
- ENERGIZAR! (Receba 2 pontos de energia no proximo turno)
- Energia! (Recebe 1 ponto de energia)
- Chocolex ([CONSUMIR], adiciona um Resenhax na sua pilha de compras)
- Resenhax ([CONSUMIR], adiciona um Clubex na sua pilha de compras)
- Pacto de sangue (sacrifica vida para escolher uma carta da pilha de compras)

### Poder
- [CONSUMIR] - JOHN WICK (sempre que usar uma carta de tiro, use novamente)
- [CONSUMIR] - Mestre das laminas (sempre que usar uma carta de corte, use novamente)
- [CONSUMIR] - Contrato de sangue (perca 1 de vida a cada inicio de rodada e receba uma carta extra)

### Maldiçoes
- Descubra...

## Efeitos

### Dano constante
- Sangramento - A cada início de rodada, aplica um ponto de dano por acúmulo. Ao acumular cinco acúmulos, causa todo o dano restante instantaneamente (numero de acumulos x duraçao) e zera os acumulos. 
- Veneno - A cada início de rodada, aplica sua duraçao restante em dano. Ao matar um alvo afetado por Veneno, o veneno se espalha para todos os outros.
- Feridas - A cada início de rodada, aplica um ponto de dano.

### Buffs
- Ego (Aumenta o dano causado)
- Aura (Reduz o dano recebido)
- Pacto Sinistro (Aumenta o dano causado em 2)

### Instantaneos e utilitarios
- Ganho de escudo
- Purificar - remove todos os efeitos aplicados em si mesmo. (incluindo positivos)
- Ganho de energia
- Energizado

## Compilacao (Gradle)

Execute na raiz do projeto.

```bash
./gradlew build
```
## Execucao

### Rodar pelo Gradle
```bash
./gradlew run
```

## Como jogar
1. Inicie o jogo e escolha seu deck inicial.
2. Em cada turno, use energia para jogar cartas.
3. Combine ataques, buffs, debuffs e poderes para controlar a luta.
4. Vença os encontros do mapa para finalizar a campanha.

