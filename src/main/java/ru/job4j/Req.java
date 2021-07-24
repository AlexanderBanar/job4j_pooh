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
        String method = "GET";
        if (text.contains("POST")) {
            method = "POST";
        }
        String[] splitText = text.split("/");
        String mode = splitText[1];
        String theme = splitText[2].replace(" HTTP", "");
        String[] rawMessage = splitText[splitText.length - 1].split("\n");
        String message = rawMessage[2];
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
