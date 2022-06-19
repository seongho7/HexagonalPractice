package com.sh.hexagonal.common.util;

import com.sh.hexagonal.common.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HangulMoneyFormatterTest {

    @DisplayName("한글 포맷으로 변경")
    @Test
    void format() {
        assertEquals("68만 4천원", new HangulMoneyFormatter(Money.of(684000)).format());
        assertEquals("68만 4천 123원", new HangulMoneyFormatter(Money.of(684123)).format());
        assertEquals("1억 2368만 4천원", new HangulMoneyFormatter(Money.of(123684000)).format());
    }
}