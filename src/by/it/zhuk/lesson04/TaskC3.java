package by.it.zhuk.lesson04;

/*
Вывести таблицу умножения 10 x 10 (двоныйм циклом),
но вывод должен быть словами:

Ожидаемый вывод:
два умножить на два равно четыре
два умножить на три равно шесть
два умножить на четыре равно восемь
два умножить на пять равно десять
два умножить на шесть равно двенадцать
два умножить на семь равно четырнадцать
два умножить на восемь равно шестнадцать
два умножить на девять равно восемнадцать
два умножить на десять равно двадцать
три умножить на два равно шесть
три умножить на три равно девять
три умножить на четыре равно двенадцать
...
и т.д. до фразы
...
десять умножить на десять равно сто
*/

public class TaskC3 {
    private static String intToString(int i) {
        if (i < 20)
            switch (i) {
                case 1:
                    return "один";
                case 2:
                    return "два";
                case 3:
                    return "три";
                case 4:
                    return "четыре";
                case 5:
                    return "пять";
                case 6:
                    return "шесть";
                case 7:
                    return "семь";
                case 8:
                    return "восемь";
                case 9:
                    return "девять";
                case 10:
                    return "десять";
                case 11:
                    return "одиннадцать";
                case 12:
                    return "двенадцать";
                case 13:
                    return "тринадцать";
                case 14:
                    return "четырнадцать";
                case 15:
                    return "пятнадцать";
                case 16:
                    return "шестнадцать";
                case 17:
                    return "семнадцать";
                case 18:
                    return "восемнадцать";
                case 19:
                    return "девятнадцать";
            }
        else {
            int d = i / 10;
            String end = ((i % 10) == 0) ? " " : " " + intToString(i % 10);
            switch (d) {
                case 2:
                    return "двадцать" + end;
                case 3:
                    return "тридцать" + end;
                case 4:
                    return "сорок" + end;
                case 5:
                    return "пятьдесят" + end;
                case 6:
                    return "шестьдесят" + end;
                case 7:
                    return "семьдесят" + end;
                case 8:
                    return "восемдевят" + end;
                case 9:
                    return "девяносто" + end;
                case 10:
                    return "сто" + end;

            }
        }
        return "ничего не понятно";
    }

    public static void main(String[] args) {
        for (int i = 2; i <= 10; i++) {
            for (int j = 2; i <= 10; i++) {
                System.out.println(intToString(i) + " умножить на " +
                        intToString(j) + " равно " + intToString(i + j));
            }
        }
    }
}

// 0. Ввести с консоли n целых чисел и поместить их в массив.

