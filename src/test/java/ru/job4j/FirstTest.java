package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class FirstTest {
    @Test
    public void whenGotOne() {
        assertThat(new First().getOne(), is(1));
    }
}