package ru.mironov.currentexchangerate.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DataUtilServiceTest {

    @Autowired
    DataUtilService dataUtilService;

    @Test
    public void isDataUtilServiceCorrectWork() {

        ZoneId ZONE_ID_EURO_MSK = ZoneId.of("Europe/Moscow");
        DateTimeFormatter DATE_TIME_FORMATTER =
                DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZONE_ID_EURO_MSK);

        Instant now = Instant.now();
        ZonedDateTime today = ZonedDateTime.ofInstant(now, ZONE_ID_EURO_MSK);
        ZonedDateTime yesterday = today.minus(1, ChronoUnit.DAYS);
        String todayDateString = DATE_TIME_FORMATTER.format(today);
        String yesterdayDateString = DATE_TIME_FORMATTER.format(yesterday);

        assertThat(dataUtilService.getTodayWithFormatToString())
                .isEqualTo(todayDateString);

        assertThat(dataUtilService.getYesterdayWithFormatToString())
                .isEqualTo(yesterdayDateString);
    }
}
