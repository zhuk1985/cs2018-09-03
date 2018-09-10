package by.it._tasks_.lesson04;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@SuppressWarnings("all") //море warnings. всех прячем.

//поставьте курсор на следующую строку и нажмите Ctrl+Shift+F10
public class Testing04 {

    @Test(timeout = 2500)
    public void testTaskA1() throws Exception {
        run("").include(
                "Я изучаю программирование.\n" +
                        "Я изучаю программирование.\n" +
                        "Меня интересует высокая зарплата!\n" +
                        "Я изучаю программирование.\n" +
                        "Я изучаю программирование.\n" +
                        "Я изучаю программирование.\n" +
                        "Я изучаю программирование.\n" +
                        "Я изучаю программирование.\n" +
                        "Меня интересует высокая зарплата!\n" +
                        "Я изучаю программирование.");
    }

    @Test(timeout = 2500)
    public void testTaskA2() throws Exception {
        run("1").include("понедельник");
        run("2").include("вторник");
        run("3").include("среда");
        run("4").include("четверг");
        run("5").include("пятница");
        run("6").include("суббота");
        run("7").include("воскресенье");
        run("0").include("такого дня недели не существует");
        run("8").include("такого дня недели не существует");
        run("-17").include("такого дня недели не существует");
        run("9917").include("такого дня недели не существует");
    }

    @Test(timeout = 2500)
    public void testTaskA3() throws Exception {
        run("1 2 3").exclude("1").exclude("2").exclude("3");
        run("2 2 3").include("2 2").exclude("3");
        run("2 3 2").include("2 2").exclude("3");
        run("3 2 2").include("2 2").exclude("3");
        run("2 2 2").include("2 2 2");
    }

    @Test(timeout = 2500)
    public void testTaskB1() throws Exception {
        run("0").include("количество дней в году: 366").exclude("365");
        run("2000").include("количество дней в году: 366").exclude("365");
        run("2004").include("количество дней в году: 366").exclude("365");
        run("1980").include("количество дней в году: 366").exclude("365");
        run("1001").include("количество дней в году: 365").exclude("366");
        run("2001").include("количество дней в году: 365").exclude("366");
        run("1700").include("количество дней в году: 365").exclude("366");
    }


    @Test(timeout = 2500)
    public void testTaskB2() throws Exception {
        run("3")
                .include("1 2 3")
                .include("2 4 6")
                .include("3 6 9")
                .exclude("3 4");
        run("10")
                .include("1 2 3 4 5 6 7 8 9 10")
                .include("2 4 6 8 10 12 14 16 18 20")
                .include("10 20 30 40 50 60 70 80 90 100")
                .exclude("10 11");
        run("11")
                .include("1 2 3 4 5 6 7 8 9 10 11")
                .exclude("11 12");
    }

    @Test(timeout = 2500)
    public void testTaskB3() throws Exception {
        run("122").include(",120").exclude(",136").exclude(",121");
        run("120").include(",120").exclude(",136").exclude(",121");
        run("50").include(",45").exclude(",46").exclude(",55");
        run("3").include(",3").exclude(",6").exclude(",1");
    }


    @Test(timeout = 2500)
    public void testTaskC1() throws Exception {
        run("299").include("Мы вам перезвоним!");
        run("3001").include("Мы вам перезвоним!");
        run("2000").include(
                "За месяц 0 начислено $0.0\n" +
                        "За январь начислено $3000.0\n" +
                        "За февраль начислено $3000.0\n" +
                        "За март начислено $3000.0\n" +
                        "За апрель начислено $3000.0\n" +
                        "За май начислено $3000.0\n" +
                        "За июнь начислено $2000.0\n" +
                        "За июль начислено $2000.0\n" +
                        "За август начислено $2000.0\n" +
                        "За сентябрь начислено $3000.0\n" +
                        "За октябрь начислено $3000.0\n" +
                        "За ноябрь начислено $3000.0\n" +
                        "За декабрь начислено $3000.0\n" +
                        "За месяц 13 начислено $0.0\n" +
                        "За месяц 14 начислено $0.0");
        run("444").include(
                "За месяц 0 начислено $0.0\n" +
                        "За январь начислено $666.0").exclude("март");
        run("666").include(
                "За месяц 0 начислено $0.0\n" +
                        "За январь начислено $999.0\n" +
                        "За февраль начислено $999.0\n" +
                        "За март начислено $999.0\n" +
                        "За апрель начислено $999.0\n" +
                        "За май начислено $999.0\n" +
                        "За июнь начислено $666.0").exclude("июль");
    }

    @Test(timeout = 2500)
    public void testTaskC2() throws Exception {
        run("1 1").include("1");
        run("31 12").include("365");
        run("13 9").include("256");
        run("0 1").include("0");
        run("8 3").include("67");
    }

    @Test(timeout = 2500)
    public void testTaskC3() throws Exception {
        run("")
        .include("два умножить на два равно четыре")
        .include("два умножить на три равно шесть")
        .include("два умножить на четыре равно восемь")
        .include("два умножить на пять равно десять")
        .include("два умножить на шесть равно двенадцать")
        .include("два умножить на семь равно четырнадцать")
        .include("два умножить на восемь равно шестнадцать")
        .include("два умножить на девять равно восемнадцать")
        .include("два умножить на десять равно двадцать")
        .include("три умножить на два равно шесть")
        .include("три умножить на три равно девять")
        .include("три умножить на четыре равно двенадцать")
        .include("три умножить на пять равно пятнадцать")
        .include("три умножить на шесть равно восемнадцать")
        .include("три умножить на семь равно двадцать один")
        .include("три умножить на восемь равно двадцать четыре")
        .include("три умножить на девять равно двадцать семь")
        .include("три умножить на десять равно тридцать")
        .include("четыре умножить на два равно восемь")
        .include("четыре умножить на три равно двенадцать")
        .include("четыре умножить на четыре равно шестнадцать")
        .include("четыре умножить на пять равно двадцать")
        .include("четыре умножить на шесть равно двадцать четыре")
        .include("четыре умножить на семь равно двадцать восемь")
        .include("четыре умножить на восемь равно тридцать два")
        .include("четыре умножить на девять равно тридцать шесть")
        .include("четыре умножить на десять равно сорок")
        .include("пять умножить на два равно десять")
        .include("пять умножить на три равно пятнадцать")
        .include("пять умножить на четыре равно двадцать")
        .include("пять умножить на пять равно двадцать пять")
        .include("пять умножить на шесть равно тридцать")
        .include("пять умножить на семь равно тридцать пять")
        .include("пять умножить на восемь равно сорок")
        .include("пять умножить на девять равно сорок пять")
        .include("пять умножить на десять равно пятьдесят")
        .include("шесть умножить на два равно двенадцать")
        .include("шесть умножить на три равно восемнадцать")
        .include("шесть умножить на четыре равно двадцать четыре")
        .include("шесть умножить на пять равно тридцать")
        .include("шесть умножить на шесть равно тридцать шесть")
        .include("шесть умножить на семь равно сорок два")
        .include("шесть умножить на восемь равно сорок восемь")
        .include("шесть умножить на девять равно пятьдесят четыре")
        .include("шесть умножить на десять равно шестьдесят")
        .include("семь умножить на два равно четырнадцать")
        .include("семь умножить на три равно двадцать один")
        .include("семь умножить на четыре равно двадцать восемь")
        .include("семь умножить на пять равно тридцать пять")
        .include("семь умножить на шесть равно сорок два")
        .include("семь умножить на семь равно сорок девять")
        .include("семь умножить на восемь равно пятьдесят шесть")
        .include("семь умножить на девять равно шестьдесят три")
        .include("семь умножить на десять равно семьдесят")
        .include("восемь умножить на два равно шестнадцать")
        .include("восемь умножить на три равно двадцать четыре")
        .include("восемь умножить на четыре равно тридцать два")
        .include("восемь умножить на пять равно сорок")
        .include("восемь умножить на шесть равно сорок восемь")
        .include("восемь умножить на семь равно пятьдесят шесть")
        .include("восемь умножить на восемь равно шестьдесят четыре")
        .include("восемь умножить на девять равно семьдесят два")
        .include("восемь умножить на десять равно восемьдесят")
        .include("девять умножить на два равно восемнадцать")
        .include("девять умножить на три равно двадцать семь")
        .include("девять умножить на четыре равно тридцать шесть")
        .include("девять умножить на пять равно сорок пять")
        .include("девять умножить на шесть равно пятьдесят четыре")
        .include("девять умножить на семь равно шестьдесят три")
        .include("девять умножить на восемь равно семьдесят два")
        .include("девять умножить на девять равно восемьдесят один")
        .include("девять умножить на десять равно девяносто")
        .include("десять умножить на два равно двадцать")
        .include("десять умножить на три равно тридцать")
        .include("десять умножить на четыре равно сорок")
        .include("десять умножить на пять равно пятьдесят")
        .include("десять умножить на шесть равно шестьдесят")
        .include("десять умножить на семь равно семьдесят")
        .include("десять умножить на восемь равно восемьдесят")
        .include("десять умножить на девять равно девяносто")
        .include("десять умножить на десять равно сто");

    }


    /*
===========================================================================================================
НИЖЕ ВСПОМОГАТЕЛЬНЫЙ КОД ТЕСТОВ. НЕ МЕНЯЙТЕ В ЭТОМ ФАЙЛЕ НИЧЕГО.
Но изучить как он работает - можно, это всегда будет полезно.
===========================================================================================================
 */
    //-------------------------------  методы ----------------------------------------------------------
    private Class findClass(String SimpleName) {
        String full = this.getClass().getName();
        String dogPath = full.replace(this.getClass().getSimpleName(), SimpleName);
        try {
            return Class.forName(dogPath);
        } catch (ClassNotFoundException e) {
            fail("\nERROR:Тест не пройден. Класс " + SimpleName + " не найден.");
        }
        return null;
    }

    private Method checkMethod(String className, String methodName, Class<?>... parameters) throws Exception {
        Class aClass = this.findClass(className);
        try {
            methodName = methodName.trim();
            Method m;
            if (methodName.startsWith("static")) {
                methodName = methodName.replace("static", "").trim();
                m = aClass.getDeclaredMethod(methodName, parameters);
                if ((m.getModifiers() & Modifier.STATIC) != Modifier.STATIC) {
                    fail("\nERROR:Метод " + m.getName() + " должен быть статическим");
                }
            } else
                m = aClass.getDeclaredMethod(methodName, parameters);
            m.setAccessible(true);
            return m;

        } catch (NoSuchMethodException e) {
            System.err.println("\nERROR:Не найден метод " + methodName + " либо у него неверная сигнатура");
            System.err.println("ERROR:Ожидаемый класс: " + className);
            System.err.println("ERROR:Ожидаемый метод: " + methodName);
            return null;
        }
    }

    private Method findMethod(Class<?> cl, String name, Class... param) {
        try {
            return cl.getDeclaredMethod(name, param);
        } catch (NoSuchMethodException e) {
            fail("\nERROR:Тест не пройден. Метод " + cl.getName() + "." + name + " не найден\n");
        }
        return null;
    }

    private Object invoke(Method method, Object o, Object... value) {
        try {
            method.setAccessible(true);
            return method.invoke(o, value);
        } catch (Exception e) {
            System.out.println(e.toString());
            fail("\nERROR:Не удалось вызвать метод " + method.getName() + "\n");
        }
        return null;
    }


    //метод находит и создает класс для тестирования
    //по имени вызывающего его метода, testTaskA1 будет работать с TaskA1
    private static Testing04 run(String in) {
        return run(in, true);
    }

    private static Testing04 run(String in, boolean runMain) {
        Throwable t = new Throwable();
        StackTraceElement trace[] = t.getStackTrace();
        StackTraceElement element;
        int i = 0;
        do {
            element = trace[i++];
        }
        while (!element.getMethodName().contains("test"));

        String[] path = element.getClassName().split("\\.");
        String nameTestMethod = element.getMethodName();
        String clName = nameTestMethod.replace("test", "");
        clName = clName.replaceFirst(".+__", "");
        clName = element.getClassName().replace(path[path.length - 1], clName);
        System.out.println("\n---------------------------------------------");
        System.out.println("Старт теста для " + clName);
        if (!in.isEmpty()) System.out.println("input:" + in);
        System.out.println("---------------------------------------------");
        return new Testing04(clName, in, runMain);
    }

    //-------------------------------  тест ----------------------------------------------------------
    public Testing04() {
        //Конструктор тестов
    }

    //переменные теста
    private String className;
    private Class<?> aClass;
    private PrintStream oldOut = System.out; //исходный поток вывода
    private PrintStream newOut; //поле для перехвата потока вывода
    private StringWriter strOut = new StringWriter(); //накопитель строки вывода

    //Основной конструктор тестов
    private Testing04(String className, String in, boolean runMain) {
        //this.className = className;
        aClass = null;
        try {
            aClass = Class.forName(className);
            this.className = className;

        } catch (ClassNotFoundException e) {
            fail("ERROR:Не найден класс " + className + "\n");
        }
        InputStream reader = new ByteArrayInputStream(in.getBytes());
        System.setIn(reader);   //перехват стандартного ввода
        System.setOut(newOut);  //перехват стандартного вывода

        if (runMain) //если нужно запускать, то запустим, иначе оставим только вывод
            try {
                Class[] argTypes = new Class[]{String[].class};
                Method main = aClass.getDeclaredMethod("main", argTypes);
                main.invoke(null, (Object) new String[]{});
                System.setOut(oldOut); //возврат вывода, нужен, только если был запуск
            } catch (Exception x) {
                x.printStackTrace();
            }
    }

    //проверка вывода
    private Testing04 is(String str) {
        assertTrue("ERROR:Ожидается такой вывод:\n<---начало---->\n" + str + "<---конец--->",
                strOut.toString().equals(str));
        return this;
    }

    private Testing04 include(String str) {
        assertTrue("ERROR:Строка не найдена: " + str + "\n", strOut.toString().contains(str));
        return this;
    }

    private Testing04 exclude(String str) {
        assertTrue("ERROR:Лишние данные в выводе: " + str + "\n", !strOut.toString().contains(str));
        return this;
    }


    //логический блок перехвата вывода
    {
        newOut = new PrintStream(new OutputStream() {
            private byte bytes[] = new byte[1];
            private int pos = 0;

            @Override
            public void write(int b) throws IOException {
                if (pos == 0 && b == '\r') //пропуск \r (чтобы win mac и linux одинаково работали
                    return;
                if (pos == 0) { //определим кодировку https://ru.wikipedia.org/wiki/UTF-8
                    if ((b & 0b11110000) == 0b11110000) bytes = new byte[4];
                    else if ((b & 0b11100000) == 0b11100000) bytes = new byte[3];
                    else if ((b & 0b11000000) == 0b11000000) bytes = new byte[2];
                    else bytes = new byte[1];
                }
                bytes[pos++] = (byte) b;
                if (pos == bytes.length) { //символ готов
                    String s = new String(bytes); //соберем весь символ
                    strOut.append(s); //запомним вывод для теста
                    oldOut.append(s); //копию в обычный вывод
                    pos = 0; //готовим новый символ
                }

            }
        });
    }
}
