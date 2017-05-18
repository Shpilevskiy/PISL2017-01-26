package by.it.group473602.Bakovich.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсионные вызовы должны проводится на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска,
        помните при реализации, что поиск множественный
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {

    //отрезок
    private class Segment  implements Comparable{
        int start;
        int stop;

        Segment(int start, int stop){
            if(start<=stop)
            {
                this.start = start;
                this.stop = stop;
            }
            else
            {
                this.start = stop;
                this.stop = start;

            }
        }

        @Override
        public int compareTo(Object o) {
            //подумайте, что должен возвращать компаратор отрезков
            if(this.stop < ((Segment)o).stop) return -1;
            else if(this.stop > ((Segment)o).stop) return 1;
            return 0;
        }
    }

    private void myQuickSort(Segment[] segments,  int leftBorder, int rightBorder) {
        if (leftBorder >= rightBorder)
            return;
        int i = leftBorder, j = rightBorder;
        int m = leftBorder + (rightBorder-leftBorder)/2;
        Segment pivot = segments[m];

        while (i < j) {
            while (segments[i].compareTo(pivot) == -1) {
                i++;
            }
            while (segments[j].compareTo(pivot) == 1) {
                j--;
            }
            if (i < j) {
                swap(segments, i, j);
                i++;
                j--;
            }
            else  if (i == j) {
                i++;
                j--;
            }
        }
        myQuickSort(segments, leftBorder, m-1);
        myQuickSort(segments, m+1, rightBorder);
    }

    private void swap(Segment [] segments, int i, int j) {
        Segment temp = segments[i];
        segments[i] = segments[j];
        segments[j] = temp;
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        myQuickSort(segments,0,segments.length - 1);
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
            for(int k = 0; k < segments.length; k++){
                if(points[i] >= segments[k].start && points[i] <= segments[k].stop){
                    result[i]++;
                }
            }
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473602/Bakovich/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
