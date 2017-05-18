package by.it.group473602.rozin.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int result = 0;
        int[][] array = new int [one.length() + 1][two.length() + 1];
        int infinity = 1000;
        for(int i = 0; i < one.length() + 1; i++) {
            for (int j = 0; j < two.length() + 1; j++) {
                array[i][j] = infinity;
            }
        }

        for(int i = 0; i < one.length() + 1; i++) {
            for (int j = 0; j < two.length() + 1; j++) {
                result = editDist(i, j, array, infinity, one, two);
            }

        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private static int editDist(int i, int j, int[][] distance, int infinity,String one,String two) {

        if (distance[i][j] == infinity){
            if (i == 0){
                distance[i][j] = j;
            }
            else {
                if (j == 0) {
                    distance[i][j] = i;
                }
                else {
                    int insert = editDist(i, j - 1, distance, infinity, one, two) + 1;
                    int delete = editDist(i - 1 , j, distance, infinity, one, two) + 1;
                    int replace = editDist(i - 1 , j - 1, distance, infinity, one, two) + dif(one.charAt(i-1), two.charAt(j-1));

                    distance[i][j] = min(insert, delete, replace);
                }
            }
        }
        return distance[i][j];
    }


    private static int dif(char one, char two){
        if (one == two) {
            return 0;
        }
        else {
            return 1;
        }
    }

    private static int min(int x, int y, int z){
        int min = -1;
        if(y > x){
            min = x;
        }else{
            min = y;
        }
        if(z < min){
            min = z;
        }
        return min;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473602/rozin/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}

