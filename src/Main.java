import java.io.*;

// Класс собственного исключения
class LineTooLongException extends RuntimeException {
    public LineTooLongException(String message) {
        super(message);
    }
}

class FileLineReader {
    public static void main(String[] args) {
        String path = "C:\\Users\\OLEG\\IdeaProjects\\Netology\\src\\AccessLogParser\\Logs\\access.log"; // Путь к файлу

        File file = new File(path);

        // Проверка существования и типа файла
        if (!file.exists()) {
            System.out.println("Файл не существует: " + path);
            return;
        }

        if (!file.isFile()) {
            System.out.println("Указанный путь не является файлом: " + path);
            return;
        }

        // Инициализация переменных для подсчета строк и длины
        int lineCount = 0;
        int maxLength = 0;
        int minLength = Integer.MAX_VALUE;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            // Построчное чтение файла
            while ((line = reader.readLine()) != null) {
                lineCount++; // Увеличение счетчика строк

                int length = line.length();

                // Проверка на максимальную допустимую длину строки
                if (length > 1024) {
                    throw new LineTooLongException("Строка номер " + lineCount + " превышает допустимую длину (1024 символа)");
                }

                if (length > maxLength) {
                    maxLength = length; // Обновление максимальной длины строки
                }
                if (length < minLength) {
                    minLength = length; // Обновление минимальной длины строки
                }
            }

            // Если файл пустой, установка минимальной длины в 0
            if (lineCount == 0) {
                minLength = 0;
            }

            // Вывод результатов
            System.out.println("Общее количество строк в файле: " + lineCount);
            System.out.println("Длина самой длинной строки: " + maxLength);
            System.out.println("Длина самой короткой строки: " + minLength);

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (LineTooLongException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
