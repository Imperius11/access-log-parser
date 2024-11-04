import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Statistics {

    private HashMap<Integer, Integer> visitsPerSecond = new HashMap<>(); // Для пиковой посещаемости
    private HashSet<String> referrerDomains = new HashSet<>(); // Для доменов referer
    private HashMap<String, Integer> userVisits = new HashMap<>(); // Для максимальной посещаемости одним пользователем

    // Метод для добавления записей логов (предполагается, что это добавлено в addEntry)
    public void addEntry(LogEntry entry) {
        String userAgent = entry.getUserAgent().toLowerCase();

        // Исключаем ботов
        if (userAgent.contains("bot")) return;

        // Фиксируем посещение по секундам для расчета пиковой посещаемости
        LocalDateTime dateTime = entry.getDateTime();
        int secondOfDay = dateTime.toLocalTime().toSecondOfDay();
        visitsPerSecond.put(secondOfDay, visitsPerSecond.getOrDefault(secondOfDay, 0) + 1);

        // Сохраняем уникальные домены из referer
        String referer = entry.getReferer();
        if (referer != null && !referer.isEmpty()) {
            String domain = extractDomain(referer);
            if (domain != null) {
                referrerDomains.add(domain);
            }
        }

        // Считаем посещения для каждого пользователя (по IP)
        String ipAddress = entry.getIpAddress();
        userVisits.put(ipAddress, userVisits.getOrDefault(ipAddress, 0) + 1);
    }

    // Метод расчёта пиковой посещаемости сайта в секунду
    public int getPeakVisitsPerSecond() {
        int maxVisits = 0;
        for (int visits : visitsPerSecond.values()) {
            if (visits > maxVisits) {
                maxVisits = visits;
            }
        }
        return maxVisits;
    }

    // Метод возвращающий список доменов, которые ссылаются на текущий сайт
    public HashSet<String> getReferrerDomains() {
        return new HashSet<>(referrerDomains);
    }

    // Метод расчёта максимальной посещаемости одним пользователем
    public int getMaxVisitsPerUser() {
        int maxVisits = 0;
        for (int visits : userVisits.values()) {
            if (visits > maxVisits) {
                maxVisits = visits;
            }
        }
        return maxVisits;
    }

    // Вспомогательный метод для извлечения домена из URL referer
    private String extractDomain(String url) {
        try {
            String domain = url.replaceFirst("^(https?://)?(www\\.)?", "");
            domain = domain.split("/")[0]; // Оставляем только доменное имя
            return domain;
        } catch (Exception e) {
            return null;
        }
    }
}
