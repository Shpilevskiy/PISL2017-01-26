package by.it.group473601.solodkin.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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

    int getDistanceEditing(String first, String second) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int result = 0;
        int[][] distances = new int[first.length() + 1][second.length() + 1];
        int infinity = Integer.MAX_VALUE;
        for (int[] distance : distances) {
            Arrays.fill(distance, infinity);
        }
        for (int i = 0; i < first.length() + 1; i++) {
            for (int j = 0; j < second.length() + 1; j++) {
                result = editDistance(i, j, distances, infinity, first, second);
            }

        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private int editDistance(int i, int j, int[][] distances, int infinity, String first, String second) {

        if (distances[i][j] == infinity) {
            if (i == 0) {
                distances[i][j] = j;
            } else {
                if (j == 0) {
                    distances[i][j] = i;
                } else {
                    int toInsert = editDistance(i, j - 1, distances, infinity, first, second) + 1;
                    int toDelete = editDistance(i - 1, j, distances, infinity, first, second) + 1;
                    int toReplace = editDistance(i - 1, j - 1, distances, infinity, first, second)
                            + getDifference(first.charAt(i - 1), second.charAt(j - 1));

                    distances[i][j] = getMinimum(toInsert, toDelete, toReplace);
                }
            }
        }
        return distances[i][j];
    }


    private int getDifference(char first, char second) {
        return first != second ? 1 : 0;
    }

    private int getMinimum(int first, int second, int third) {
        return Math.min(first, Math.min(second, third));
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/solodkin/lesson08/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEditing(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEditing(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEditing(scanner.nextLine(), scanner.nextLine()));
    }
}

