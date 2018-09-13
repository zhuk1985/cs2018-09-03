package by.it._tasks_.lesson06;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

@SuppressWarnings("all") //море warnings. всех прячем.

//поставьте курсор на следующую строку и нажмите Ctrl+Shift+F10
public class Testing06 {

    @Test(timeout = 2500)
    public void testTaskA1() throws Exception {
        run("").include("Шарик 5").include("Тузик 3");

        System.out.println("Проверка класса Dog");
        Class cl = findClass("Dog");
        assert cl != null;

        Object dog = cl.newInstance();
        assert dog != null;

        findMethod(cl, "setName", String.class).invoke(dog, "Шарик");
        assertEquals("\nИмя не прочиталось ", "Шарик", (String) findMethod(cl, "getName").invoke(dog));

        findMethod(cl, "setAge", int.class).invoke(dog, 5);
        assertEquals("\nВозраст не прочитался ", 5, (int) findMethod(cl, "getAge").invoke(dog));
        System.out.println("Проверка класса Dog - OK");
    }


    @Test(timeout = 2500)
    public void testTaskA2() throws Exception {
        run("").include("Кличка: Шарик. Возраст: 5").include("Кличка: Тузик. Возраст: 3");

        System.out.println("Проверка класса Dog");
        Class cl = findClass("Dog");
        assert cl != null;

        Object dog = cl.newInstance();
        assert dog != null;

        findMethod(cl, "setName", String.class).invoke(dog, "Шарик");
        assertEquals("\nИмя не прочиталось ", "Шарик", (String) findMethod(cl, "getName").invoke(dog));

        findMethod(cl, "setAge", int.class).invoke(dog, 5);
        assertEquals("\nВозраст не прочитался ", 5, (int) findMethod(cl, "getAge").invoke(dog));

        assertEquals("\ntoString() не работает как ожидается ", "Кличка: Шарик. Возраст: 5", (String) findMethod(cl, "toString").invoke(dog));
        System.out.println("Проверка класса Dog - OK");
    }

    @Test(timeout = 2500)
    public void testTaskB1() throws Exception {
        System.out.println("Проверка класса Dog");
        Class cl = findClass("Dog");
        assert cl != null;

        //create dogs
        int count = 10;
        Object[] dogs = (Object[]) Array.newInstance(cl, count);
        double controlAge = 0;
        for (int i = 0; i < dogs.length; i++) {
            Object dog = cl.newInstance();
            assert dog != null;
            findMethod(cl, "setName", String.class).invoke(dog, "dog" + i);
            int age = (i + 1);
            findMethod(cl, "setAge", int.class).invoke(dog, age);
            controlAge = controlAge + age;
            dogs[i] = dog;
        }
        controlAge = controlAge / dogs.length;

        Class hlp = findClass("DogHelper");
        assert hlp != null;

        //ищем метод вывода имен
        findMethod(hlp, "printAllNames", dogs.getClass());

        //ищем метод расчета среднего
        Method averageAge = findMethod(hlp, "averageAge", dogs.getClass());
        double avg = (double) averageAge.invoke(null, new Object[]{dogs});
        assertEquals("Ожидается другой возраст для 10 тестовых собак", controlAge, avg, 0.001);

        System.out.println("Проверка класса Dog - OK");
        //проверка запуска на 5 ожидаемых собаках
        run("").include("Шарик Жучка Бобик Барбос Полкан").include("3.0");

    }


    @Test(timeout = 2500)
    public void testTaskC1() throws Exception {
        Class cl = findClass("Dog");
        assert cl != null;

        for (int i = 1; i < 25; i++) {
            //Шансы на победу = 0.2 * возраст + 0.3 * вес + 0.5 * силу укуса.
            String n1 = "dog" + i;
            String n2 = "pup" + i;
            int age1 = 1+(int) (Math.random() * 10);
            int age2 = 1+(int) (Math.random() * 10);
            int w1 = 1+(int) (Math.random() * 10);
            int w2 = 1+(int) (Math.random() * 10);
            double p1 = 1.1+(int)(Math.random() * 15);
            double p2 = 1.1+(int)(Math.random() * 15);
            double win1 = 0.2 * age1 + 0.3 * w1 + 0.5 * p1;
            double win2 = 0.2 * age2 + 0.3 * w2 + 0.5 * p2;
            boolean expected = win1 > win2;

            //делаем собак
            Object dog1 = cl.newInstance();
            Object dog2 = cl.newInstance();
            assert dog1 != null;
            assert dog2 != null;

            findMethod(cl, "setName", String.class).invoke(dog1, n1);
            findMethod(cl, "setAge", int.class).invoke(dog1, age1);
            findMethod(cl, "setWeight", int.class).invoke(dog1, w1);
            findMethod(cl, "setPower", double.class).invoke(dog1, p1);

            findMethod(cl, "setName", String.class).invoke(dog2, n2);
            findMethod(cl, "setAge", int.class).invoke(dog2, age2);
            findMethod(cl, "setWeight", int.class).invoke(dog2, w2);
            findMethod(cl, "setPower", double.class).invoke(dog2, p2);

            System.out.println("--------- Начало эксперимента ----------------");
            //определим победителя
            boolean actual = (boolean) findMethod(cl, "win", cl).invoke(dog1, dog2);
            System.out.println("1) "+n1+ " Шанс:"+win1+"\n2) "+n2+" Шанс:"+win2);
            if (expected)
                System.out.println("По тесту должен победить "+n1);
            else
                System.out.println("По тесту должен победить "+n2);
            assertEquals("Победитель определен неверно", expected, actual);

            //проверим их же через run
            String in = String.format(
                    "%s\n%d\n%d\n%f\n" + "%s\n%d\n%d\n%f\n",
                    n1, age1, w1, p1,
                    n2, age2, w2, p2
            );
            System.out.println("------- Проверка поединка через ввод с клавиатуры ------");
            run(in).include((expected)?n1:n2);
            System.out.println("--------- Конец эксперимента ----------------\n\n");

        }

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
    private static Testing06 run(String in) {
        return run(in, true);
    }

    private static Testing06 run(String in, boolean runMain) {
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
        return new Testing06(clName, in, runMain);
    }

    //-------------------------------  тест ----------------------------------------------------------
    public Testing06() {
        //Конструктор тестов
    }

    //переменные теста
    private String className;
    private Class<?> aClass;
    private PrintStream oldOut = System.out; //исходный поток вывода
    private PrintStream newOut; //поле для перехвата потока вывода
    private StringWriter strOut = new StringWriter(); //накопитель строки вывода

    //Основной конструктор тестов
    private Testing06(String className, String in, boolean runMain) {
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
    private Testing06 is(String str) {
        assertTrue("ERROR:Ожидается такой вывод:\n<---начало---->\n" + str + "<---конец--->",
                strOut.toString().equals(str));
        return this;
    }

    private Testing06 include(String str) {
        assertTrue("ERROR:Строка не найдена: " + str + "\n", strOut.toString().contains(str));
        return this;
    }

    private Testing06 exclude(String str) {
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
