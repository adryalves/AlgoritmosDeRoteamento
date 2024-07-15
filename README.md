# Simulação de Algoritmos de Roteamento em Java

Este repositório contém três projetos que simulam diferentes algoritmos de roteamento. Todos os projetos são implementados em Java e utilizam uma interface gráfica para facilitar a visualização e a interação.

## Projetos

1. [Algoritmo de Inundação](#algoritmo-de-inundação)
2. [Algoritmo de Dijkstra](#algoritmo-de-dijkstra)
3. [Vetor de Distância](#vetor-de-distância)

## Algoritmo de Inundação

### Descrição

Este projeto simula o algoritmo de inundação, onde todas as mensagens recebidas são enviadas para todas as direções possíveis, exceto de onde vieram.

### Funcionalidades

- Envio de mensagens a todos os nós vizinhos.
- Evita o reenvio de mensagens ao nó de origem.
- Interface gráfica para visualização do processo de inundação.
- Tem-se a opção que envia para todos os vizinhos
- Tem-se a opção que envia para todos os vizinho exceto pelo qual chegou
- Tem-se a opção em que usa o TTL com valor definido e quando esse TTL chegar a zero o pacote é morto

## Algoritmo de Dijkstra

### Descrição

Este projeto simula o algoritmo de Dijkstra para encontrar o caminho mais curto em uma rede de nós.

### Funcionalidades

- Cálculo do caminho mais curto entre dois nós.
- Visualização gráfica dos nós e das arestas.
- Interação com a interface gráfica para definir nós e conexões.


## Vetor de Distância

### Descrição

Este projeto simula o algoritmo de vetor de distância, onde cada nó mantém uma tabela de distância para todos os outros nós na rede.

### Funcionalidades

- Atualização de tabelas de distância baseada em informações dos nós vizinhos.
- Visualização gráfica das tabelas de distância.
- Interação com a interface gráfica para modificar a rede e observar as atualizações.


## Requisitos

- Java 8 ou superior.

## Estrutura do Repositório

- `AlgoritmoDeInundacao/`: Contém o código fonte do algoritmo de inundação.
- `AlgoritmoDijkstra/`: Contém o código fonte do algoritmo de Dijkstra.
- `AlgoritmoVetorDeDistancia/`: Contém o código fonte do algoritmo de vetor de distância.

## Observações

- Certifique-se de que todas as dependências necessárias estão instaladas.
- Para uma melhor experiência, execute cada projeto separadamente.
- Especialmente se abrir pelo VSCode, caso de algum erro, abra cada pasta de forma separada, visto que as vezes pode haver confusões, por conta
  de algumas classes dos diferentes projetos com o mesmo nome.

