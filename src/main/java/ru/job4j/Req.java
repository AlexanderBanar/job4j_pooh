package ru.job4j;

public class Req {
    private final String method;
    private final String mode;
    private final String theme;
    private final String message;

    private Req(String method, String mode, String theme, String message) {
        this.method = method;
        this.mode = mode;
        this.theme = theme;
        this.message = message;
    }

    public Req(String text) {
        Req temp = Req.of(text);
        this.method = temp.method;
        this.mode = temp.mode;
        this.theme = temp.theme;
        this.message = temp.message;
    }

    public static Req of(String content) {
        String[] parsedContent = content.split("/");
        String method = parsedContent[0];
        String mode = parsedContent[1];
        String rawText = parsedContent[2];
        String theme;
        String message = "";
        if (rawText.contains("-d")) {
            String[] parsedRawText = rawText.split("-d");
            theme = parsedRawText[0].trim();
            message = parsedRawText[1].trim();
        } else {
            theme = rawText;
        }
        return new Req(method, mode, theme, message);
    }

    public String getMethod() {
        return method;
    }

    public String getMode() {
        return mode;
    }

    public String getTheme() {
        return theme;
    }

    public String getMessage() {
        return message;
    }
}
