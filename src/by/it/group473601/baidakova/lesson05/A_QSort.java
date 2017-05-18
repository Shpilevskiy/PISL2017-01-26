package by.it.group473601.baidakova.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь.
На площади установлена одна или несколько камер.
Известны данные о том, когда каждая из них включалась и выключалась (отрезки работы)
Известен список событий на площади (время начала каждого события).
Вам необходимо определить для каждого события сколько камер его записали.

В первой строке задано два целых числа:
    число включений камер (отрезки) 1<=n<=50000
    число событий (точки) 1<=m<=50000.

Следующие n строк содержат по два целых числа ai и bi (ai<=bi) -
координаты концов отрезков (время работы одной какой-то камеры).
Последняя строка содержит m целых чисел - координаты точек.
Все координаты не превышают 10E8 по модулю (!).

Точка считается принадлежащей отрезку, если она находится внутри него или на границе.

Для каждой точки в порядке их появления во вводе выведите,
скольким отрезкам она принадлежит.
    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/

public class A_QSort {

    //отрезок
    private class Segment  implements Comparable{
        int start;
        int stop;

        Segment(int start, int stop){
            if(start <= stop){
                this.start = start;
                this.stop = stop;
            } else{
                this.start = stop;
                this.stop = start;
            }
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Object obj) {
            //подумайте, что должен возвращать компаратор отрезков
            if(((Segment)obj).start > this.start)
                return 1;
            else if(this.start >((Segment)obj).start)
                return -1;
            else
                return 0;
        }
    }


    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];
        int i;

        //читаем сами отрезки
        for (i = 0; i < n; i++) {
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }

        qSort(segments,0,segments.length-1);
        //читаем точки
        for (i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
            int j=0;
            while(j<segments.length)
            {
                if(points[i]>=segments[j].start && points[i]<=segments[j].stop)
                    result[i]++;
                j++;
            }
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        return result;
    }

    private void qSort(Segment[] numbers, int low, int high) {
        if (low >= high)
            return;
        int i = low;
        int j = high;
        // берём элемент с середины
        Segment pivot = numbers[low + (high-low)/2];

        // разделяем на два листа
        while (j>=i) {
            // если слева меньше, берём след. элемент с левого листа
            while (numbers[i].compareTo(pivot)==-1) {
                i++;
            }
            // если элемент справа больше, то след. основной будет справа
            while (numbers[j].compareTo(pivot)==1){
                j--;
            }

            // если значение слева больше элемента, а справа меньше элемента,
            // то меняем значения
            if (i <= j) {
                swap(numbers,i, j);
                i++;
                j--;
            }
        }
        // Рекурсия
        if (low < j)
            qSort(numbers,low, j);
        if (i < high)
            qSort(numbers,i, high);
    }

    private void swap(Segment[] numbers, int i, int j) {
        Segment temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/baidakova/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
