package by.it.group473602.rozin.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j]A[i]>A[j].

    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )

Sample Input:
5
2 3 9 2 9
Sample Output:
2

Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {

    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int result = 0;
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!


        mergeSort(a, 0, a.length - 1);
        //  System.out.println(inversion);
        result = countInversion;
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    int[] merge(int[] left, int[] right) {
        int max = left.length + right.length;
        int[] result = new int[max];
        int l = 0, r = 0;
        for (int i = 0; i < max; i++) {
            if (l >= left.length & r < right.length) {
                result[i] = right[r];
                r++;
            } else if (r >= right.length & l < left.length) {
                result[i] = left[l];
                l++;
            } else if (left[l] <= right[r] & l < left.length) {
                result[i] = left[l];
                l++;
            } else {

                if (left[l] > right[r]) {
                    countInversion += left.length - l;
                }
                result[i] = left[r];
                r++;
            }
        }
        return result;
    }

    private int countInversion = 0;


    int[] mergeSort(int[] arr, int left, int right) {
        int[] result = new int[1];
        int middle = (int) (left + right) / 2;
        if (left < right) {
            return merge(mergeSort(arr, left, middle), mergeSort(arr, middle + 1, right));
        } else {
            result[0] = arr[left];
            return result;
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473602/rozin/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
