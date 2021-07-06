package ru.job4j;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SecondTest {
    @Test
    public void whenGetTwo() {
        assertThat(new Second().justGetTwo(), is(2));
    }
}