package by.it.group473602.drushits.lesson01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        if (n <= 0)
            return BigInteger.ZERO;
        else if (n == 1)
            return BigInteger.ONE;
        else if (n == 2)
            return BigInteger.ONE;
        else {
            List<BigInteger> fb = new ArrayList<BigInteger>(n + 1);

            fb.add(BigInteger.ZERO);
            fb.add(BigInteger.ONE);
            fb.add(BigInteger.ONE);

            for (int i = 3; i <= n; i++) {
                fb.add(i, fb.get(i - 1).add(fb.get(i - 2)));
            }
            return fb.get(n);
        }

    }
}

