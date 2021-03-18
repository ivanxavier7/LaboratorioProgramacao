public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}

/*
Problema
A sua tarefa consiste na implementação de um programa que, dadas as promoções
pensadas pela Joana, e o número de pacotes de sumo comprados por ela, permita no final
da noite calcular o valor total das vendas realizadas. De notar que cada uma das promoções
só pode ser utilizada no máximo uma única vez.

Dados de entrada
A entrada do programa é constituída por uma linha contendo dois números inteiros
N e M representando, respetivamente, o número de promoções pensadas pela Joana e a
quantidade de pacotes de sumo disponíveis para venda. Depois seguem-se N linhas, cada
uma delas contendo dois números inteiros Q e V especificando que Q pacotes podem ser
comprados por V e.

Dados de saída
A saída é constituída por uma linha contendo um número inteiro representando o maior
valor total de vendas que a Joana foi capaz de realizar com as promoções pensadas.

• 0 < N < 20
• 0 < M < 5000
• 0 < Q < 100
• 0 < V < 1000
INPUT
4 9
4 7
5 5
3 4
2 3
OUTPUT
14
INPUT
3 3
1 6
3 13
2 9
OUTPUT
15
*/