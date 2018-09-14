package by.it.zhuk.lesson05;

/* Задачка на сортировку
Задача: Написать программу, которая вводит с клавиатуры 20 чисел и выводит их в убывающем порядке.
*/

import java.util.Scanner;

public class TaskC2 {
    //Напишите тут ваш код





    public static void sort(int[] array) {
        //Напишите тут ваш код
    }

    public static class TaskA1 {
        public static void main(String[] args) {
            int[]mas = new int [15];
            Scanner sc = new Scanner(System.in);
            for (int i=14; i >=0; i--) {
                mas[i] = sc.nextInt();
            }
            for (int element : mas){
                System.out.println(element);
            }
        }

    }
}
