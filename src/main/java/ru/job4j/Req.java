package ru.job4j;

public class Req {
    private final String method;
    private final String mode;
    private final String theme;
    private final String message;
    private final int id;

    public Req(String method, String mode, String theme, String message, int id) {
        this.method = method;
        this.mode = mode;
        this.theme = theme;
        this.message = message;
        this.id = id;
    }

    public Req(String text) {
        String[] splitByHttp = text.split("HTTP/1.1");
        String[] splitByHttpAndSlash = splitByHttp[0].split("/");
        String method = "GET";
        if (splitByHttpAndSlash[0].contains("POST")) {
            method = "POST";
        }
        String mode = "queue";
        if (splitByHttpAndSlash[1].contains("topic")) {
            mode = "topic";
        }
        String theme = splitByHttpAndSlash[2].trim();
        int id = 0;
        if (splitByHttpAndSlash.length > 3) {
            id = Integer.parseInt(splitByHttpAndSlash[3].trim());
        }
        String message = "";
        if (method.equals("POST")) {
            String[] splitText = text.split("/");
            String[] rawMessage = splitText[splitText.length - 1].split("\n");
            message = rawMessage[2];
        }
        this.method = method;
        this.mode = mode;
        this.theme = theme;
        this.message = message;
        this.id = id;
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

    public int getId() {
        return id;
    }
}
