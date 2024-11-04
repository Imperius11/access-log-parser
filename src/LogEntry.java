import java.time.LocalDateTime;

public class LogEntry {
    private final String ipAddress;
    private final LocalDateTime dateTime; // Дата и время запроса
    private final String httpMethod;
    private final String requestPath;
    private final int responseCode;
    private final int traffic;
    private final String referer;
    private final String userAgent;

    // Конструктор класса LogEntry
    public LogEntry(String ipAddress, LocalDateTime dateTime, String httpMethod,
                    String requestPath, int responseCode, int traffic,
                    String referer, String userAgent) {
        this.ipAddress = ipAddress;
        this.dateTime = dateTime;
        this.httpMethod = httpMethod;
        this.requestPath = requestPath;
        this.responseCode = responseCode;
        this.traffic = traffic;
        this.referer = referer;
        this.userAgent = userAgent;
    }

    // Геттер для dateTime
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // Остальные геттеры, например:
    public String getIpAddress() {
        return ipAddress;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public int getTraffic() {
        return traffic;
    }

    public String getUserAgent() {
        return userAgent;
    }
    public String getReferer() {
        return referer;
    }
    // Другие геттеры
}
