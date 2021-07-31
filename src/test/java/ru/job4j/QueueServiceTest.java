package ru.job4j;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class QueueServiceTest {
    @Test
    public void testOfPosting2Messages() {
        Service service = new QueueService();
        Req req = new Req("POST", "queue", "weather", "temp=30", 0);
        Req req2 = new Req("POST", "queue", "weather", "temp=35", 0);
        Resp resp = service.process(req);
        Resp resp2 = service.process(req2);
        assertThat(resp.text(), is("temp=30"));
        assertThat(resp2.text(), is("temp=35"));
    }

    @Test
    public void testOfPosting3MessagesDiffThemesAnd3GetRequests() {
        Service service = new QueueService();
        service.process(new Req("POST", "queue", "weather", "temp=30", 0));
        service.process(new Req("POST", "queue", "weather", "temp=35", 0));
        service.process(new Req("POST", "queue", "season", "summer", 0));
        Resp resp3 = service.process(new Req("GET", "queue", "weather", "", 1));
        Resp resp4 = service.process(new Req("GET", "queue", "weather", "", 1));
        Resp resp5 = service.process(new Req("GET", "queue", "season", "", 1));
        assertThat(resp3.text(), is("temp=30"));
        assertThat(resp4.text(), is("temp=35"));
        assertThat(resp5.text(), is("summer"));
    }
}