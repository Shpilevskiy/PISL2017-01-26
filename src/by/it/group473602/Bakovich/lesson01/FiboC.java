package by.it.group473602.Bakovich.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */
import java.util.ArrayList;
import java.util.List;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n =10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано

        List<Integer> pzn = new ArrayList<Integer>();
        pzn.add(0);
        pzn.add(1);
        for(int i = 2; i < n * 6; i++){
            pzn.add((pzn.get(i - 1) + pzn.get(i - 2)) % m);
            if(pzn.get(i) == 1 && pzn.get(i-1) == 0){
                break;
            }
        }
        int period = pzn.size() - 2;
        int answ = (int)(n%period);
        return pzn.get(answ);
    }
}

