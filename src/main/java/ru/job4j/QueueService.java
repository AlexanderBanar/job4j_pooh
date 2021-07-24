package ru.job4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String text = req.getMessage();
        int status = 200;
        ConcurrentLinkedQueue<String> linkedQueue;
        if (req.getMethod().equals("POST")) {
            if (queue.containsKey(req.getTheme())) {
                linkedQueue = queue.get(req.getTheme());
            } else {
                linkedQueue = new ConcurrentLinkedQueue<>();
            }
            linkedQueue.add(req.getMessage());
            queue.put(req.getTheme(), linkedQueue);
        } else {
            linkedQueue = queue.get(req.getTheme());
            if (linkedQueue == null) {
                text = "incorrect GET request, no POST found";
                status = 400;
            } else {
                text = linkedQueue.poll();
            }
        }
        return new Resp(text, status);
    }
}
