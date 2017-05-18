package by.it.group473601.baidakova.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

    int[] merge(int[] argLeft, int[] argRight){
        int max = argLeft.length + argRight.length;
        int[] result = new int[max];
        int leftCounter = 0;
        int rightCounter = 0;
        for (int i = 0; i < max; i++){
            if (leftCounter >= argLeft.length & rightCounter < argRight.length){
                result[i] = argRight[rightCounter];
                rightCounter++;
            }else if(rightCounter >= argRight.length & leftCounter < argLeft.length){
                result[i] = argLeft[leftCounter];
                leftCounter++;
            }else if (argLeft[leftCounter] <= argRight[rightCounter] & leftCounter < argLeft.length){
                result[i] = argLeft[leftCounter];
                leftCounter++;
            }else {
                result[i] = argRight[rightCounter];
                rightCounter++;
            }
        }
        return result;
    }

    int[] mergeSort(int[] array, int leftIndex, int rightIndex){
        int[] result = new int[1];
        int index = (int)(leftIndex + rightIndex) / 2;
        if (leftIndex < rightIndex){
           return merge(mergeSort(array, leftIndex, index), mergeSort(array, index + 1, rightIndex));
        }else {
            result[0] = array[leftIndex];
            return result;
        }
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        //размер массива
        int n = scanner.nextInt();
        //сам массива
        int[] array=new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
            System.out.println(array[i]);
        }

        array = mergeSort(array, 0, array.length - 1);
        return array;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        int[] result=instance.getMergeSort(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
