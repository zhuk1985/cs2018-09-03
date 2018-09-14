package by.it.zhuk.lesson04;
import java.util.Scanner;
/*

На входе вводится число и месяц 2018 года.
Программа должна выводить номер дня года, считая 1 января первым днем,
а 31 декабря 365-м.

Пример:
Ввод:
1 1

Вывод
1

Ввод:
31 12

Вывод
365

Ввод:
13 9

Вывод
256

 */
public class TaskC2 {

    public static class Main {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int function = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            if (a == b && b == c)
                System.out.println(a + " " + b + " " + c);
            else if (a == b)
                System.out.println(a + " " + b);
            else if (b == c)
                System.out.println(b + " " + c);
            else if (a ==c)
                System.out.println(a + " " + c);

        }

    }
}
