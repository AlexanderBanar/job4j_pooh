package ru.job4j;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TopicServiceTest {
    @Test
    public void testOfPosting2Messages() {
        Service service = new TopicService();
        Req req = new Req("POST", "topic", "weather", "temp=30", 0);
        Req req2 = new Req("POST", "topic", "weather", "temp=35", 0);
        Resp resp = service.process(req);
        Resp resp2 = service.process(req2);
        assertThat(resp.text(), is("message posted successfully"));
        assertThat(resp2.text(), is("message posted successfully"));
    }

    @Test
    public void testOfPosting2MessagesAnd2GetRequestsWithSameId() {
        Service service = new TopicService();
        service.process(new Req("POST", "topic", "weather", "temp=30", 0));
        service.process(new Req("POST", "topic", "weather", "temp=35", 0));
        Resp resp3 = service.process(new Req("GET", "topic", "weather", "", 1));
        Resp resp4 = service.process(new Req("GET", "topic", "weather", "", 1));
        assertThat(resp3.text(), is("temp=30"));
        assertThat(resp4.text(), is("temp=35"));
    }

    @Test
    public void testOfPosting2MessagesAnd2GetRequestsWithDiffId() {
        Service service = new TopicService();
        service.process(new Req("POST", "topic", "weather", "temp=30", 0));
        service.process(new Req("POST", "topic", "weather", "temp=35", 0));
        Resp resp3 = service.process(new Req("GET", "topic", "weather", "", 1));
        Resp resp4 = service.process(new Req("GET", "topic", "weather", "", 2));
        assertThat(resp3.text(), is("temp=30"));
        assertThat(resp4.text(), is("temp=30"));
    }

    @Test
    public void testOfPosting3MessagesDiffThemesAnd4GetRequestsWithDiffId() {
        Service service = new TopicService();
        service.process(new Req("POST", "topic", "weather", "temp=30", 0));
        service.process(new Req("POST", "topic", "weather", "temp=35", 0));
        service.process(new Req("POST", "topic", "season", "summer", 0));
        Resp resp3 = service.process(new Req("GET", "topic", "weather", "", 1));
        Resp resp4 = service.process(new Req("GET", "topic", "weather", "", 2));
        Resp resp5 = service.process(new Req("GET", "topic", "weather", "", 1));
        Resp resp6 = service.process(new Req("GET", "topic", "season", "", 2));
        assertThat(resp3.text(), is("temp=30"));
        assertThat(resp4.text(), is("temp=30"));
        assertThat(resp5.text(), is("temp=35"));
        assertThat(resp6.text(), is("summer"));
    }
}