import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Statistics {

    private int totalTraffic = 0;
    private HashSet<String> pages = new HashSet<>();          // Уникальные существующие страницы (код 200)
    private HashSet<String> missingPages = new HashSet<>();    // Уникальные несуществующие страницы (код 404)
    private HashMap<String, Integer> osCount = new HashMap<>(); // Частота операционных систем
    private HashMap<String, Integer> browserCount = new HashMap<>(); // Частота браузеров

    // Метод для добавления записи
    public void addEntry(LogEntry entry) {
        // Учитываем трафик
        totalTraffic += entry.getTraffic();

        // Обрабатываем статус-код ответа
        if (entry.getResponseCode() == 200) {
            pages.add(entry.getRequestPath()); // Добавляем существующую страницу
        } else if (entry.getResponseCode() == 404) {
            missingPages.add(entry.getRequestPath()); // Добавляем несуществующую страницу
        }

        // Получаем информацию об операционной системе пользователя
        String os = entry.getUserAgent().getOperatingSystem();
        osCount.put(os, osCount.getOrDefault(os, 0) + 1); // Обновляем счетчик для ОС

        // Получаем информацию о браузере пользователя
        String browser = entry.getUserAgent().getBrowser();
        browserCount.put(browser, browserCount.getOrDefault(browser, 0) + 1); // Обновляем счетчик для браузера
    }

    // Метод для получения всех несуществующих страниц с кодом ответа 404
    public List<String> getMissingPages() {
        return new ArrayList<>(missingPages); // Возвращаем список несуществующих страниц
    }

    // Метод для получения статистики по браузерам (доля каждого браузера от общего числа)
    public HashMap<String, Double> getBrowserStatistics() {
        HashMap<String, Double> browserStatistics = new HashMap<>();
        int totalBrowserCount = browserCount.values().stream().mapToInt(Integer::intValue).sum();

        for (Map.Entry<String, Integer> entry : browserCount.entrySet()) {
            String browser = entry.getKey();
            int count = entry.getValue();
            double percentage = (double) count / totalBrowserCount;
            browserStatistics.put(browser, percentage);
        }

        return browserStatistics;
    }
}
