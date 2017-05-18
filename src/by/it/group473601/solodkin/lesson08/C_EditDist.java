package by.it.group473601.solodkin.lesson08;

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
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {

    String getDistanceEditing(String first, String second) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        String result = "";

        int[][] distances = new int[first.length() + 1][second.length() + 1];
        for (int i = 0; i < first.length() + 1; i++) {
            distances[i][0] = i;
        }
        for (int i = 0; i < second.length() + 1; i++) {
            distances[0][i] = i;
        }
        for (int i = 0; i < first.length(); i++) {
            for (int j = 0; j < second.length(); j++) {
                int cost = getDifference(first.charAt(i), second.charAt(j));
                distances[i + 1][j + 1] = getMinimum(
                        distances[i][j + 1] + 1,
                        distances[i + 1][j] + 1,
                        distances[i][j] + cost);
            }
        }

        int firstLength = first.length();
        int secondLength = second.length();

        while (firstLength >= 1) {
            while (secondLength >= 1) {
                int toInsert = distances[firstLength][secondLength - 1];
                int toDelete = distances[firstLength - 1][secondLength];
                int toReplace = distances[firstLength - 1][secondLength - 1];
                int minimum = getMinimum(toDelete, toInsert, toReplace);

                if (minimum == toReplace) {
                    if (getDifference(first.charAt(firstLength - 1), second.charAt(secondLength - 1)) == 0) {
                        result += "#,";
                    } else {
                        result += "~" + second.charAt(secondLength - 1) + ",";
                    }
                    firstLength--;
                    secondLength--;
                }
                if (minimum == toDelete) {
                    result += "-" + first.charAt(firstLength - 1) + ",";
                    firstLength--;
                } else if (minimum == toInsert) {
                    result += "+" + second.charAt(secondLength - 1) + ",";
                    secondLength--;
                }
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
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
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEditing(scanner.nextLine(),scanner.nextLine()));
    }

}
