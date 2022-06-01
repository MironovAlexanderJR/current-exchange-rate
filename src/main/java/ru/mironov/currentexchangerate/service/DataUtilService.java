package ru.mironov.currentexchangerate.service;

import java.time.ZonedDateTime;

public interface DataUtilService {

    ZonedDateTime getToday();

    ZonedDateTime getYesterday();

    String getTodayWithFormatToString();

    String getYesterdayWithFormatToString();
}
