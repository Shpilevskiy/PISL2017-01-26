package by.it.group473601.solodkin.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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

    class ArrayWithInversions {
        int inversions;
        int[] array;

        public ArrayWithInversions(int inversions, int[] array) {
            this.inversions = inversions;
            this.array = array;
        }
    }

    ArrayWithInversions merge(ArrayWithInversions left, ArrayWithInversions right){
        int inversions = 0;
        int[] first = left.array;
        int[] second = right.array;
        int[] result = new int[first.length + second.length];
        int firstIndex = 0;
        int secondIndex = 0;
        for (int i = 0; i < result.length; i++) {
            if (firstIndex >= first.length){
                result[i] = second[secondIndex];
                secondIndex++;
            } else if (secondIndex >= second.length) {
                result[i] = first[firstIndex];
                firstIndex++;
            } else if (first[firstIndex] <= second[secondIndex]) {
                result[i] = first[firstIndex];
                firstIndex++;
            } else {
                result[i] = second[secondIndex];
                secondIndex++;
                inversions++;
            }
        }
        return new ArrayWithInversions(inversions + left.inversions + right.inversions, result);
    }

    ArrayWithInversions mergeSort(ArrayWithInversions arrayWithInversions){
        if (arrayWithInversions.array.length < 2)
            return arrayWithInversions;
        int[] array = arrayWithInversions.array;
        int[] left = Arrays.copyOfRange(array, 0, array.length/2);
        int[] right = Arrays.copyOfRange(array, array.length/2, array.length);
        return merge(mergeSort(new ArrayWithInversions(0, left)), mergeSort(new ArrayWithInversions(0, right)));
    }

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
        result = mergeSort(new ArrayWithInversions(0, a)).inversions;
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/solodkin/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
