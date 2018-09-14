package by.it.zhuk.lesson05;




/*
Создайте 5 различных строк в списке ArrayList:

1. Создайте список строк.
2. Добавьте в него 5 различных строк.
3. Выведите его размер на экран.
4. Используя цикл выведите его содержимое на экран, каждое значение с новой строки.

*/


public class TaskB1 {
    public static class TaskA2 {
        public static void main(String[] args) {

            String greeting = "Сегодня последнее занятие на курсах";
            int len1 = greeting.length();
            String a = "Я так и не поняла - поняла ли я";
            int len2 = a.length();
            String b = "Очень надеюсь, что после перечитывания всех лекций";
            int len3 = b.length();
            String c = "До меня все таки дойдет материал";
            int len4 = c.length();
            int len5 = b.length();
            System.out.println("Размер строки: " + len1 + " символов  -  Сегодня последнее занятие на курсах");
            System.out.println("Размер строки: " + len2 + " символов - Я так и не поняла - поняла ли я");
            System.out.println("Размер строки: " + len3 + " символов -  Но после перечитывания всех лекций");
            System.out.println("Размер строки: " + len4 + " символов - я смогу понять лучше материал");
            System.out.println("Размер строки: " + len5 + " символов  - и  у меня все получится ");

        }
    }
}
