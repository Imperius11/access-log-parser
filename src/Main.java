import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Получение первого числа
        System.out.println("Введите число 1:");
        int number1 = new Scanner(System.in) .nextInt();
        // Получение второго числа
        System.out.println("Введите число 2:");
        int number2 = new Scanner(System.in) .nextInt();
        // Вычисление сумы, разности, умножения и деления
        int sum = number1 + number2;
        int difference = number1 - number2;
        int product = number1 * number2;
        double quotient;
         {
            quotient = (double) number1 / number2;
            // Отображение результатов
            System.out.println("Сумма: " + sum);
            System.out.println("Разность: " + difference);
            System.out.println("Произведение: " + product);
            System.out.println("Частное: " + quotient);
        }
    }
}