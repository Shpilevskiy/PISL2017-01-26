package by.it.group473601.solodkin.lesson09;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак без повторов

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        число золотых слитков
                    (каждый можно использовать только один раз).
Следующая строка содержит n целых чисел, задающих веса каждого из слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.

Sample Input:
10 3
1 4 8
Sample Output:
9

*/

public class B_Knapsack {

    int getMaxWeight(InputStream stream) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int capacity = scanner.nextInt();
        int bullionsAmount = scanner.nextInt();
        int bullions[] = new int[bullionsAmount];
        for (int i = 0; i < bullionsAmount; i++) {
            bullions[i] = scanner.nextInt();
        }
        int result;

        int[][] resultingBullions = new int[capacity + 1][bullionsAmount + 1];
        for (int i = 0; i < capacity + 1; i++) {
            resultingBullions[i] = new int[bullionsAmount + 1];
        }

        for (int i = 0; i < capacity; i++) {
            resultingBullions[i][0] = 0;
        }
        for (int j = 0; j < bullionsAmount; j++) {
            resultingBullions[0][j] = 0;
        }

        for (int i = 1; i < bullionsAmount + 1; i++) {
            for (int j = 1; j < capacity + 1; j++) {
                resultingBullions[j][i] = resultingBullions[j][i - 1];
                if (bullions[i - 1] <= j) {
                    int w = bullions[i - 1];
                    int c = bullions[i - 1];
                    int sum = resultingBullions[j - w][i - 1] + c;
                    resultingBullions[j][i] = Math.max(resultingBullions[j][i], sum);
                }
            }
        }
        result = resultingBullions[capacity][bullionsAmount];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/solodkin/lesson09/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }

}

