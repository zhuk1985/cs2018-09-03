package by.it.zhuk.lesson02;

import java.util.Scanner;

/* Нужно написать программу, которая вводит два числа с клавиатуры
и 4 раза выводит их сумму с обозначением системы счисления на экран в
ДЕСЯТИЧНОМ ДВОИЧНОМ ШЕСТНАДЦАТИРИЧНОМ ВОСЬМИРИЧНОМ виде

Вот пример ввода с клавиатуры:
34 26

Тогда вывод ожидается такой (обратите внимание на регистр букв):
DEC:34+26=60
BIN:100010+11010=111100
HEX:22+1a=3c
OCT:42+32=74
*/
class TaskC2 {
      public static void main (String[] args) {
          Scanner sc = new Scanner (System.in);
          int i = sc.nextInt();
          int j = sc.nextInt();
          int k = i+j;
          System.out.println("DEC:" + i + "+" + j + "=" + k);

          // Формат двоичный
          System.out.print ("BIN:");
          String convert =Integer.toBinaryString(i);
          System.out.print(convert);
          System.out.print("+");

          String convert1=Integer.toBinaryString(j);
          System.out.print(convert1);
          System.out.print("=");
          String convert2 =Integer.toBinaryString(k);
          System.out.println(convert2);

          //формат шестнадцатиричный
          System.out.print("HEX:");
          String convert3 =Integer.toHexString(i);
          System.out.print(convert3);
          System.out.print("+");
          String convert4 =Integer.toHexString(j);
          System.out.print(convert4);
          System.out.print("=");
          String convert5 =Integer.toHexString(k);
          System.out.println(convert5);

          //Восьмиричный формат
          System.out.print("OCT:");
          String convert6 =Integer.toOctalString(i);
          System.out.print(convert6);
          System.out.print("+");
          String convert7 =Integer.toOctalString(j);
          System.out.print(convert7);
          System.out.print("=");
          String convert8 =Integer.toOctalString(k);
          System.out.println(convert8);
      }

}
