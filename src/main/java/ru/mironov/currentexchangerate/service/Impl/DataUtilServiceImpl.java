package ru.mironov.currentexchangerate.service.Impl;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mironov.currentexchangerate.service.DataUtilService;

@Service
@Slf4j
public class DataUtilServiceImpl implements DataUtilService {

    private final ZoneId ZONE_ID_EURO_MSK = ZoneId.of("Europe/Moscow");
    private final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZONE_ID_EURO_MSK);

    public ZonedDateTime getToday() {
        Instant today = Instant.now();
        return ZonedDateTime.ofInstant(today, ZONE_ID_EURO_MSK);
    }

    public ZonedDateTime getYesterday() {
        return getToday().minus(1, ChronoUnit.DAYS);
    }

    public String getTodayWithFormatToString() {
        return DATE_TIME_FORMATTER.format(getToday());
    }

    public String getYesterdayWithFormatToString() {
        return DATE_TIME_FORMATTER.format(getYesterday());
    }
}
