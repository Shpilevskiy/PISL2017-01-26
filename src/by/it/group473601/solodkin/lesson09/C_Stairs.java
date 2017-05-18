package by.it.group473601.solodkin.lesson09;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Даны число 1<=n<=100 ступенек лестницы и
целые числа −10000<=a[1],…,a[n]<=10000, которыми помечены ступеньки.
Найдите максимальную сумму, которую можно получить, идя по лестнице
снизу вверх (от нулевой до n-й ступеньки), каждый раз поднимаясь на
одну или на две ступеньки.

Sample Input 1:
2
1 2
Sample Output 1:
3

Sample Input 2:
2
2 -1
Sample Output 2:
1

Sample Input 3:
3
-1 2 1
Sample Output 3:
3

*/

public class C_Stairs {

    int getMaxSum(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int stairsAmount = scanner.nextInt();
        int stairs[] = new int[stairsAmount];
        for (int i = 0; i < stairsAmount; i++) {
            stairs[i] = scanner.nextInt();
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int result;

        int[] resultingStairs = new int[stairsAmount];
        for (int i = 0; i < stairsAmount; i++) {
            switch (i) {
                case 0:
                    resultingStairs[0] = stairs[0];
                    break;
                case 1:
                    resultingStairs[i] = Math.max(resultingStairs[0] + stairs[i], stairs[i]);
                    break;
                default:
                    resultingStairs[i] = Math.max(resultingStairs[i - 1] + stairs[i], resultingStairs[i - 2] + stairs[i]);
                    break;
            }
        }
        result = resultingStairs[resultingStairs.length - 1];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/solodkin/lesson09/dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res = instance.getMaxSum(stream);
        System.out.println(res);
    }
}

