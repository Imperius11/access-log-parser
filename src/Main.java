import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class LogParser {

    public static void main(String[] args) {
        String path = "C:\\\\Users\\\\OLEG\\\\IdeaProjects\\\\Netology\\\\src\\\\AccessLogParser\\\\Logs\\\\access.log"; // Укажите путь к вашему лог-файлу
        File file = new File(path);

        if (!file.exists() || !file.isFile()) {
            System.out.println("Файл не существует или указан путь к папке.");
            return;
        }

        int totalRequests = 0;
        int googleBotCount = 0;
        int yandexBotCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                totalRequests++;
                int botType = parseLogLine(line);
                if (botType == 1) {
                    googleBotCount++;
                } else if (botType == 2) {
                    yandexBotCount++;
                }
            }

            // Вывод долей запросов от Googlebot и YandexBot
            System.out.println("Общее количество запросов: " + totalRequests);
            System.out.println("Количество запросов от Googlebot: " + googleBotCount);
            System.out.println("Количество запросов от YandexBot: " + yandexBotCount);
            System.out.printf("Доля запросов от Googlebot: %.2f%%%n", (googleBotCount * 100.0) / totalRequests);
            System.out.printf("Доля запросов от YandexBot: %.2f%%%n", (yandexBotCount * 100.0) / totalRequests);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для парсинга строки лога и определения типа бота.
     * @param line строка лога
     * @return 1 для Googlebot, 2 для YandexBot, 0 если это не бот
     */
    public static int parseLogLine(String line) {
        try {
            // Извлечение User-Agent
            int userAgentStart = line.indexOf('"', line.lastIndexOf('"') - 1) + 1;
            int userAgentEnd = line.indexOf('"', userAgentStart);
            String userAgent = line.substring(userAgentStart, userAgentEnd);

            // Обработка User-Agent
            int firstBracketStart = userAgent.indexOf('(');
            int firstBracketEnd = userAgent.indexOf(')');
            if (firstBracketStart != -1 && firstBracketEnd != -1) {
                String firstBrackets = userAgent.substring(firstBracketStart + 1, firstBracketEnd);
                String[] userAgentParts = firstBrackets.split(";");

                if (userAgentParts.length >= 2) {
                    String secondFragment = userAgentParts[1].trim();

                    // Отделение части до слэша и проверка на Googlebot или YandexBot
                    int slashIndex = secondFragment.indexOf('/');
                    String botName = (slashIndex != -1) ? secondFragment.substring(0, slashIndex) : secondFragment;

                    // Возвращение 1 для Googlebot, 2 для YandexBot, 0 если не бот
                    if (botName.equals("Googlebot")) {
                        return 1;  // Googlebot
                    } else if (botName.equals("YandexBot")) {
                        return 2; // YandexBot
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка при разборе строки: " + line);
            e.printStackTrace();
        }
        return 0; // Не бот
    }
}
