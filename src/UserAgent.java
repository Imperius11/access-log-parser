public class UserAgent {
    private final String operatingSystem;
    private final String browser;

    public UserAgent(String userAgentString) {
        // Инициализируем поля значениями по умолчанию
        String os = "Unknown OS";
        String browser = "Unknown Browser";
        
        // Извлекаем тип операционной системы
        if (userAgentString.contains("Windows")) {
            os = "Windows";
        } else if (userAgentString.contains("Linux")) {
            os = "Linux";
        } else if (userAgentString.contains("Mac OS X")) {
            os = "Mac OS X";
        } else if (userAgentString.contains("Android")) {
            os = "Android";
        } else if (userAgentString.contains("iOS")) {
            os = "iOS";
        }

        // Извлекаем тип браузера
        if (userAgentString.contains("Chrome")) {
            int startIndex = userAgentString.indexOf("Chrome");
            int endIndex = userAgentString.indexOf(" ", startIndex);
            browser = endIndex != -1 
                    ? userAgentString.substring(startIndex, endIndex)
                    : userAgentString.substring(startIndex);
        } else if (userAgentString.contains("Firefox")) {
            int startIndex = userAgentString.indexOf("Firefox");
            browser = userAgentString.substring(startIndex);
        } else if (userAgentString.contains("Safari") && !userAgentString.contains("Chrome")) {
            int startIndex = userAgentString.indexOf("Safari");
            browser = userAgentString.substring(startIndex);
        } else if (userAgentString.contains("MSIE")) {
            int startIndex = userAgentString.indexOf("MSIE");
            int endIndex = userAgentString.indexOf(";", startIndex);
            browser = endIndex != -1 
                    ? userAgentString.substring(startIndex, endIndex)
                    : userAgentString.substring(startIndex);
        } else if (userAgentString.contains("Trident")) {
            browser = "Internet Explorer";
        }

        // Устанавливаем значения для полей
        this.operatingSystem = os;
        this.browser = browser;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getBrowser() {
        return browser;
    }

    @Override
    public String toString() {
        return "UserAgent{" +
                "operatingSystem='" + operatingSystem + '\'' +
                ", browser='" + browser + '\'' +
                '}';
    }
}
