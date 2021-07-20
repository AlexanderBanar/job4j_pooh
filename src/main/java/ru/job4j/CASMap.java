package ru.job4j;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentHashMap;

public class CASMap {
    public Resp doLogic(ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue, Req req) {
        Resp result = new Resp("incorrect request", 400);
        if (req.getMethod().equals("POST")) {
            result = new CASMap().doPostLogic(queue, req);
        }
        if (req.getMethod().equals("GET")) {
            if (queue.get(req.getTheme()) == null) {
                result = new Resp("no message found for the request", 400);
            } else {
                String text = queue.get(req.getTheme()).poll();
                result = new Resp(text, 200);
            }
        }
        return result;
    }

    private Resp doPostLogic(ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue, Req req) {
        String text = "message posted successfully";
        int status = 200;
        if (queue.get(req.getTheme()) == null) {
            ConcurrentLinkedQueue<String> linkedQueue = new ConcurrentLinkedQueue<>();
            linkedQueue.add(req.getMessage());
            queue.putIfAbsent(req.getTheme(), linkedQueue);
        } else {
            queue.get(req.getTheme()).add(req.getMessage());
        }
        return new Resp(text, status);
    }
}
