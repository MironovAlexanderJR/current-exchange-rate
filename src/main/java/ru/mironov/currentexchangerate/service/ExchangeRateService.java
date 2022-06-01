package ru.mironov.currentexchangerate.service;

import java.math.BigDecimal;
import ru.mironov.currentexchangerate.exceptions.InvalidHTTPParamException;

public interface ExchangeRateService {

    BigDecimal getRates(String data, String currency) throws InvalidHTTPParamException;
}
