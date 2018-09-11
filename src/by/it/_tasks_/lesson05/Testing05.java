package by.it._tasks_.lesson05;

import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@SuppressWarnings("all") //море warnings. всех прячем.

//поставьте курсор на следующую строку и нажмите Ctrl+Shift+F10
public class Testing05 {

    @Test(timeout = 2500)
    public void testTaskA1() throws Exception {
        run("1\n2\n3\n4\n5\n6\n7\n8\n9\n0\n").
                include("0\n9\n8\n7\n6\n5\n4\n3\n2\n1\n");
        run("1\n-2\n3\n4\n5\n666\n7\n8\n9\n0\n").
                include("0\n9\n8\n7\n666\n5\n4\n3\n-2\n1\n");
    }


    @Test(timeout = 2500)
    public void testTaskA2() throws Exception {
        run("ONE\nTWO\nTHREE\nFOUR\nFIVE\nEND\n").include("[ONE, TWO, THREE, FOUR, FIVE]");
        run("ONE\nEND\n").include("[ONE]");
    }

    @Test(timeout = 2500)
    public void testTaskB1() throws Exception {
        String[] lines=run("").strOut.toString().split("\n");
        assertTrue("Неверный размер",lines.length==6 && lines[0].trim().equals("5"));
    }


    @Test(timeout = 2500)
    public void testTaskB2() throws Exception {
        run("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n22\n33\n44\n55\n66\n77\n88\n99\n0")
                .include("a=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]\n" +
                        "b=[11, 22, 33, 44, 55, 66, 77, 88, 99, 0]\n");
    }


    @Test(timeout = 2500)
    public void testTaskC1() throws Exception {
        run("1\n2\n3\n4\n5\n6\n7\n8\n9\n44\n55\n11\n11\n12\n14\n12\n45\n38\n88\n77").
                include("3\n6\n9\n12\n12\n45\n2\n4\n6\n8\n44\n12\n14\n12\n38\n88\n1\n5\n7\n55\n11\n11\n77");
    }

    @Test(timeout = 2500)
    public void testTaskC2() throws Exception {
        run("1\n-2\n3\n4\n5\n666\n7\n8\n9\n0\n1\n-1\n3\n4\n5\n665\n7\n8\n9\n0\n").
                include("666\n665\n9\n9\n8\n8\n7\n7\n5\n5\n4\n4\n3\n3\n1\n1\n0\n0\n-1\n-2");
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
    private static Testing05 run(String in) {
        return run(in, true);
    }

    private static Testing05 run(String in, boolean runMain) {
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
        return new Testing05(clName, in, runMain);
    }

    //-------------------------------  тест ----------------------------------------------------------
    public Testing05() {
        //Конструктор тестов
    }

    //переменные теста
    private String className;
    private Class<?> aClass;
    private PrintStream oldOut = System.out; //исходный поток вывода
    private PrintStream newOut; //поле для перехвата потока вывода
    private StringWriter strOut = new StringWriter(); //накопитель строки вывода

    //Основной конструктор тестов
    private Testing05(String className, String in, boolean runMain) {
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
    private Testing05 is(String str) {
        assertTrue("ERROR:Ожидается такой вывод:\n<---начало---->\n" + str + "<---конец--->",
                strOut.toString().equals(str));
        return this;
    }

    private Testing05 include(String str) {
        assertTrue("ERROR:Строка не найдена: " + str + "\n", strOut.toString().contains(str));
        return this;
    }

    private Testing05 exclude(String str) {
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
