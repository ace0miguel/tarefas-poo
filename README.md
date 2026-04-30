# Piratas do Caribe - Card Game

Jogo de cartas em turnos no terminal, com foco em combate, efeitos de status, construção de deck e batalhas contra múltiplos inimigos.

## Descrição

Você joga como Jack Sparrow e enfrenta encontros no mapa utilizando cartas. A instancia base das cartas, dos efeitos e dos inimigos está centralizada nas fábricas. Ganhe dinheiro e cartas ao vencer encontros, e gaste em eventos aleatórios!

## Mecanicas especiais

- Ao usar todas as cartas da sua mao, receba uma nova mao completa e 2 pontos de energia!
- Pague 5 dinheiros pra remover uma carta do seu baralho no deckBuilder™.   
- Cartas com tags especiais que afetam a sua interaçao com o baralho!
- Eventos aleatórios no meio do mapa!
- Ganhe cartas e itens ao final de cada batalha, dependendo da dificuldade!

## Eventos aleatórios

- Fogueira: escolha entre se curar ou receber cartas! 
 - Baseado no padrão de design "Façade", do site refactoring.guru
 
- Loja: mesma coisa, só que pagando! (Tambem tem itens e edição de tags de cartas) (Compensa se tiver com os dolares)
 - Baseado no padrão de design "Façade", do site refactoring.guru

- Cassino: aposte vida ou dinheiro em um jogo de dados, em busca do dobro ou nada!
 - Não é tão baseado em um padrão de design.

- Tesouro: ganhe muitas cartas e itens!
 - Baseado no padrão de design "Façade", do site refactoring.guru

- GOLDEN FREDDY: sempre, ao viajar no mapa, voce pode receber uma surpresa...

## Cartas 

#### Tags:
- Consumir: só pode ser utilizada uma vez por encontro.
- Área: a carta afeta TODOS os inimigos no encontro.
- Manter: a carta fica na sua mão até ser utilizada.
- Inata: sempre receba a carta na primeira rodada da partida.
- Sacrificio: a carta cobra um custo em vida para ser usada.

### Ataque
- Espada
- Disparo de revolver
- Disparo de escopeta
- Disparo de canhão
- Espada muito afiada (dano + Sangramento)
- Espada envenenada (dano + Veneno)
- BOMBA! (dano em area)
- BOMBA DE VENENO! (dano em area + aplica Veneno)
- Clubex ([CONSUMIR], dano em area)
- Aposte vida ou morte no cassino e talvez voce encontre mais uma...

### Habilidade
- Shieldinho (Escudo)
- Sobrevivência (Escudo + descarte)
- Shieldao (Escudo alto)
- RECEBA! (Purificar)
- Ganancia (Descarta a mão e recompra na mesma quantidade)
- Cafeína (energia bonus no proximo turno)
- Energia! (Recebe 1 ponto de energia)
- Chocolex ([CONSUMIR], adiciona uma carta especial na pilha de compras)
- Clarividencia ([CONSUMIR], escolhe uma carta da pilha de compras)
- Eco Dolor (guarda dano e devolve parte dele no fim)
- Reciclagem (descarta e ganha energia)
- Freestyle (puxa cartas e descarta da mão)
- Chuva de lâminas (recebe 3 adagas)

### Poder
- Mestre das laminas (sempre que usar uma carta de corte, use novamente)
- Contrato de sangue (perca 1 de vida a cada inicio de rodada e receba uma carta extra)

### Maldiçoes
- Descubra...

## Efeitos

### Dano constante
- Sangramento - A cada início de rodada, aplica um ponto de dano por acúmulo. Ao acumular cinco acúmulos, causa todo o dano restante instantaneamente (numero de acumulos x duraçao) e zera os acumulos. 
- Veneno - A cada início de rodada, aplica sua duraçao restante em dano. Ao matar um alvo afetado por Veneno, o veneno se espalha para todos os outros.
- Feridas - A cada início de rodada, causa 1 de dano por rodada durante a duraçao.

### Buffs
- Ego - Aumenta o dano causado em porcentagem por algumas rodadas.
- Aura - Reduz o dano recebido em porcentagem por algumas rodadas.
- Energizado - Guarda energia bonus para a próxima rodada.

## Itens

### Itens passivos
- Faca de açougueiro (ataques de baixo custo aplicam Sangramento)
- Marmita (cura no fim da batalha)
- Amuleto velho (cartas extras no inicio da batalha)
- Jarro de terra (dano reduzido)
- Colete a prova de tudo (dano reduzido alto)
- Espada curta (dano extra)
- Espada longa (dano extra alto)
- Banana Prata (mantém energia entre rodadas)
- Estandarte (escudo no inicio da batalha)
- Boneco do luva de pedreiro (mantém escudo entre rodadas)

### Itens ativos
- Poção de cura P
- Poção de cura M
- Poção de cura G
- Pocão de energia P
- Pocão de energia G
- Marlboro red (recupera carta da pilha de descarte)
- Vape (puxa carta específica da pilha de compra)

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

