# LaboratorioProgramacao
Exercícios e trabalhos realizados no âmbito da unidade curricular "Laboratório de Programação"

# Jogo do Loto

### Cartão
* Composto por uma matriz 3x9 (3D)
* Cada linha tem sempre 5 números
* Cada coluna tem entre 0 e 3 números
* 1ª coluna de 1 a 9
* 2ª até á 8ª coluna em intervalos de x0 a x9
* 8ª coluna de 80 a 90

### Jogo
* Apenas um cartão
* Representação na consola
* Sorteio independente do jogo
* Assinalar o cartão com o número obtido
* Avisar sempre que a linha fica completa
* No final apresentar mensagem de conclusão
* Após a conclusão pode reiniciar ou continuar com o mesmo cartão

### Documentação
* Documentação JavaDoc do programa
* Indicar como executar a aplicação sem recurso ao IDE

## Implementação

### Jogo

Variáveis

    Array 2D                                                 
    Número escolhido

Métodos

    Gerar o cartão
    Receber número
    Validar resultado da linha
    Devolver cartão modificado
    Reiniciar Jogo
    Reiniciar com o mesmo cartão
Validação simples e de exceções

2D

```java
int[][] arr = { { 1, 2 }, { 3, 4 } };
for (int i = 0; i < 2; i++)
    for (int j = 0; j < 2; j++)
        System.out.println("arr[" + i + "][" + j + "] = " + arr[i][j]);
```

### Jogador
* Menu
* Implementa objeto do jogo
* Recebe cartão
* Introduz números
* Reinicia
