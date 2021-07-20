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

    public static void main(String[] args) {
        String s = "/GET/queue/topic HTTP/1.1";
        String[] splitted = s.split("/");
        System.out.println(splitted[0]);

//        System.out.println(splitted[1]);
//        System.out.println(splitted[2]);
    }

    public Req(String text) {
        String[] parsedContent = text.split("/");
        String method = parsedContent[1];
        String mode = parsedContent[2];
        String rawText = parsedContent[3];
        String theme;
        String message = "";
        if (rawText.contains("-d")) {
            String[] parsedRawText = rawText.split("-d");
            theme = parsedRawText[0].trim();
            message = parsedRawText[1].trim();
        } else {
            theme = rawText;
        }
        this.method = method;
        this.mode = mode;
        this.theme = theme;
        this.message = message;
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
