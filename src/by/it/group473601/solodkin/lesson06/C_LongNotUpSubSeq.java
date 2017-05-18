package by.it.group473601.solodkin.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/
public class C_LongNotUpSubSeq {

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int elementsLength = scanner.nextInt();
        int[] elements = new int[elementsLength];
        //читаем всю последовательность
        for (int i = 0; i < elementsLength; i++) {
            elements[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        int result = 0;

        int[] notGrowingSequenceLength = new int[elementsLength];
        int[] previousIndexes = new int[elementsLength];
        Arrays.fill(notGrowingSequenceLength, 1);
        Arrays.fill(previousIndexes, -1);
        for (int i = 0; i < elementsLength; i++) {
            for (int j = 0; j < i; j++) {
                if (elements[i] <= elements[j] && notGrowingSequenceLength[j] + 1 > notGrowingSequenceLength[i]) {
                    notGrowingSequenceLength[i] = notGrowingSequenceLength[j] + 1;
                    previousIndexes[i] = j;
                }
            }
        }

        for (int i = 0; i < elementsLength; i++) {
            if (notGrowingSequenceLength[i] > result) {
                result = notGrowingSequenceLength[i];
            }
        }

        int[] resultIndexes = new int[result];
        int k = 1;
        for (int i = 1; i < elementsLength; i++) {
            if (notGrowingSequenceLength[i] > notGrowingSequenceLength[k]) {
                k = i;
            }
        }

        int j = result - 1;
        while (k >= 0) {
            resultIndexes[j] = k + 1;
            j--;
            k = previousIndexes[k];
        }

        for(int index : resultIndexes) {
            System.out.print(index + " ");
        }
        System.out.println();

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/solodkin/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }
}
