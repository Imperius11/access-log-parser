import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int fileCounter = 0; // Переменная для подсчета указанных файлов

        while (true) { // Бесконечный цикл
            // Путь к файлу
            System.out.println("Please enter the file path: ");
            String path = new Scanner(System.in).nextLine(); // Путь с консоли

            // Создание объекта File на основе указанного пути
            File file = new File(path);

            // Проверка, существует ли файл и является ли это директорией
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            // Если файл не существует или путь указывает на директорию, цикл продолжается
            if (!fileExists) {
                System.out.println("The file does not exist.");
                continue; // Продолжение цикла
            } else if (isDirectory) {
                System.out.println("The path points to a directory, not a file.");
                continue; // Продолжение цикла
            }

            // Если это существующий файл, счетчик увеливиется и выводит информацию
            fileCounter++; // Увеличение счетчика верно указанных файлов
            System.out.println("Путь указан верно");
            System.out.println("Это файл номер " + fileCounter);

            // Условие выхода из программы
            System.out.println("Do you want to exit? (yes/no): ");
            String answer = new Scanner(System.in).nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                break; // Выход из бесконечного цикла
            }
        }
    }
}
