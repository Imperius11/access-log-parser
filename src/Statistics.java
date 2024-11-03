import java.time.LocalDateTime;
import java.time.Duration;

public class Statistics {
    // Поля класса
    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    
    // Конструктор без параметров
    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = null;
        this.maxTime = null;
    }
    
    // Метод для добавления записи лог-файла
    public void addEntry(LogEntry entry) {
        int entryTraffic = entry.getTraffic(); // Получаем объём данных из записи
        LocalDateTime entryTime = entry.getRequestTime(); // Получаем время запроса
        
        // Добавляем трафик в общий счётчик
        totalTraffic += entryTraffic;

        // Обновляем minTime и maxTime
        if (minTime == null || entryTime.isBefore(minTime)) {
            minTime = entryTime;
        }
        if (maxTime == null || entryTime.isAfter(maxTime)) {
            maxTime = entryTime;
        }
    }
    
    // Метод для вычисления средней скорости трафика за час
    public double getTrafficRate() {
        // Проверяем, что у нас есть минимум и максимум времени
        if (minTime == null || maxTime == null || minTime.equals(maxTime)) {
            return 0; // Возвращаем 0, если недостаточно данных для расчёта
        }
        
        // Вычисляем разницу во времени в часах
        long hours = Duration.between(minTime, maxTime).toHours();
        
        // Рассчитываем среднюю скорость трафика за час
        return (double) totalTraffic / hours;
    }

    // Геттеры для проверки значений (при необходимости)
    public int getTotalTraffic() {
        return totalTraffic;
    }

    public LocalDateTime getMinTime() {
        return minTime;
    }

    public LocalDateTime getMaxTime() {
        return maxTime;
    }
}
