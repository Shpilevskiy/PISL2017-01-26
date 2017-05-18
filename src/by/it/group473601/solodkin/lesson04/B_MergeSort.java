package by.it.group473601.solodkin.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    int[] merge(int[] firstArray, int[] secondArray){
        int[] result = new int[firstArray.length + secondArray.length];
        int firstIndex = 0;
        int secondIndex = 0;
        for (int i = 0; i < result.length; i++) {
            if (firstIndex >= firstArray.length){
                result[i] = secondArray[secondIndex];
                secondIndex++;
            } else if (secondIndex >= secondArray.length) {
                result[i] = firstArray[firstIndex];
                firstIndex++;
            } else if (firstArray[firstIndex] < secondArray[secondIndex]) {
                result[i] = firstArray[firstIndex];
                firstIndex++;
            } else {
                result[i] = secondArray[secondIndex];
                secondIndex++;
            }
        }
        return result;
    }

    int[] mergeSort(int[] array){
        return array.length < 2 ?
                array :
                merge(mergeSort(Arrays.copyOfRange(array, 0, array.length / 2)),
                        mergeSort(Arrays.copyOfRange(array, array.length / 2, array.length )));
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массива
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);

        }

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием

        a = mergeSort(a);




        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/solodkin/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
