import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Statistics {

    private int totalTraffic = 0;
    private HashSet<String> pages = new HashSet<>();  // Уникальные страницы с кодом ответа 200
    private HashMap<String, Integer> osCount = new HashMap<>(); // Частота каждой ОС

    // Метод для добавления записи
    public void addEntry(LogEntry entry) {
        // Учитываем трафик
        totalTraffic += entry.getTraffic();

        // Если код ответа 200, добавляем страницу в список существующих
        if (entry.getResponseCode() == 200) {
            pages.add(entry.getRequestPath());
        }

        // Получаем информацию об операционной системе пользователя
        String os = entry.getUserAgent().getOperatingSystem();

        // Обновляем счетчик операционных систем
        osCount.put(os, osCount.getOrDefault(os, 0) + 1);
    }

    // Метод для получения всех существующих страниц с кодом ответа 200
    public List<String> getAllPages() {
        return new ArrayList<>(pages); // Возвращаем список страниц
    }

    // Метод для получения статистики операционных систем (доля от общего числа)
    public HashMap<String, Double> getOperatingSystemStatistics() {
        HashMap<String, Double> osStatistics = new HashMap<>();
        int totalOsCount = osCount.values().stream().mapToInt(Integer::intValue).sum();

        for (Map.Entry<String, Integer> entry : osCount.entrySet()) {
            String os = entry.getKey();
            int count = entry.getValue();
            double percentage = (double) count / totalOsCount;
            osStatistics.put(os, percentage);
        }

        return osStatistics;
    }
}
