import java.time.LocalDateTime;

public class LogEntry {
    private final String ipAddress;
    private final LocalDateTime requestTime;
    private final HttpMethod httpMethod;
    private final String requestPath;
    private final int responseCode;
    private final int responseSize; // Поле для объёма данных
    private final String referer;
    private final UserAgent userAgent;

    // Конструктор
    public LogEntry(String ipAddress, LocalDateTime requestTime, HttpMethod httpMethod,
                    String requestPath, int responseCode, int responseSize,
                    String referer, String userAgentString) {
        this.ipAddress = ipAddress;
        this.requestTime = requestTime;
        this.httpMethod = httpMethod;
        this.requestPath = requestPath;
        this.responseCode = responseCode;
        this.responseSize = responseSize; // Инициализация поля
        this.referer = referer;
        this.userAgent = new UserAgent(userAgentString);
    }
    // Создайте enum для HTTP-методов
    public enum HttpMethod {
        GET,
        POST,
        PUT,
        DELETE,
        // Добавьте другие HTTP-методы по необходимости
    }
    // Геттеры для всех полей
    public String getIpAddress() {
        return ipAddress;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public int getResponseCode() {
        return responseCode;
    }

    // Метод getTraffic, который возвращает размер данных
    public int getTraffic() {
        return responseSize;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }
}
