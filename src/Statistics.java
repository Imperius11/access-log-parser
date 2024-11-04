import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;

public class Statistics {
    // Поля для статистики
    private int totalVisits = 0; // Общее количество визитов
    private int realUserVisits = 0; // Количество визитов реальными пользователями (не ботами)
    private int errorRequests = 0; // Количество запросов с ошибочным кодом ответа
    private int totalTraffic = 0; // Общий трафик
    private LocalDateTime minTime = null; // Время самого раннего запроса
    private LocalDateTime maxTime = null; // Время самого позднего запроса
    private HashSet<String> uniqueRealUserIPs = new HashSet<>(); // Уникальные IP-адреса реальных пользователей (не ботов)

    public Statistics() {
        // Конструктор по умолчанию
    }

    public void addEntry(LogEntry entry) {
        // Обновление общего количества посещений
        totalVisits++;

        // Устанавка минимального и максимального времени запроса
        LocalDateTime entryTime = entry.getDateTime();
        if (minTime == null || entryTime.isBefore(minTime)) {
            minTime = entryTime;
        }
        if (maxTime == null || entryTime.isAfter(maxTime)) {
            maxTime = entryTime;
        }

        // Учет трафика
        totalTraffic += entry.getTraffic();

        // Проверка на бота
        boolean isBot = entry.getUserAgent().toLowerCase().contains("bot");

        // Обработка реальных пользователей
        if (!isBot) {
            realUserVisits++;
            uniqueRealUserIPs.add(entry.getIpAddress());
        }

        // Проверка на ошибочный код ответа
        int responseCode = entry.getResponseCode();
        if (responseCode >= 400 && responseCode < 600) {
            errorRequests++;
        }
    }

    // Метод подсчёта среднего количества посещений сайта за час (только для реальных пользователей)
    public double getAverageVisitsPerHour() {
        if (minTime == null || maxTime == null || realUserVisits == 0) {
            return 0;
        }
        long hours = ChronoUnit.HOURS.between(minTime, maxTime);
        if (hours == 0) hours = 1; // Чтобы избежать деления на ноль, если время очень короткое
        return (double) realUserVisits / hours;
    }

    // Метод подсчёта среднего количества ошибочных запросов в час
    public double getAverageErrorRequestsPerHour() {
        if (minTime == null || maxTime == null || errorRequests == 0) {
            return 0;
        }
        long hours = ChronoUnit.HOURS.between(minTime, maxTime);
        if (hours == 0) hours = 1; // Чтобы избежать деления на ноль
        return (double) errorRequests / hours;
    }

    // Метод расчёта средней посещаемости одним пользователем (реальные пользователи)
    public double getAverageVisitsPerRealUser() {
        if (uniqueRealUserIPs.isEmpty()) {
            return 0;
        }
        return (double) realUserVisits / uniqueRealUserIPs.size();
    }
}
