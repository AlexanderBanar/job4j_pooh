package ru.job4j;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class TopicService implements Service {
    ConcurrentHashMap<String, ArrayList<String>> queue = new ConcurrentHashMap<>();
    ConcurrentHashMap<Integer, ConcurrentHashMap<String, Integer>> mapOfIdAndMessageToRead =
            new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        int status = 200;
        String text;
        if (req.getMethod().equals("POST")) {
            ArrayList<String> arrayList;
            if (queue.containsKey(req.getTheme())) {
                arrayList = queue.get(req.getTheme());
            } else {
                arrayList = new ArrayList<>();
            }
            arrayList.add(req.getMessage());
            queue.put(req.getTheme(), arrayList);
            text = "message posted successfully";
        } else {
            int numberToRead = 0;
            ConcurrentHashMap<String, Integer> map;
            if (!mapOfIdAndMessageToRead.containsKey(req.getId())) {
                map = new ConcurrentHashMap<>();
                map.put(req.getTheme(), 0);
            } else {
                map = mapOfIdAndMessageToRead.get(req.getId());
                if (!map.containsKey(req.getTheme())) {
                    map.put(req.getTheme(), 0);
                } else {
                    numberToRead = map.get(req.getTheme()) + 1;
                    map.put(req.getTheme(), numberToRead);
                }
            }
            mapOfIdAndMessageToRead.put(req.getId(), map);
            ArrayList<String> listOfMessages = queue.get(req.getTheme());
            if (listOfMessages.size() <= numberToRead) {
                text = "no message posted yet";
            } else {
                text = listOfMessages.get(numberToRead);
            }
        }
        return new Resp(text, status);
    }
}
