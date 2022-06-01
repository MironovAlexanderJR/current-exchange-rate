package ru.mironov.currentexchangerate.service;

import java.math.BigDecimal;

public interface GifsService {

    String getGif(BigDecimal todayRate, BigDecimal yesterdayRate);
}
