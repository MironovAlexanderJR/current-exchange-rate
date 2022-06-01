package ru.mironov.currentexchangerate.controller;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.mironov.currentexchangerate.exceptions.InvalidHTTPParamException;
import ru.mironov.currentexchangerate.service.DataUtilService;
import ru.mironov.currentexchangerate.service.ExchangeRateService;
import ru.mironov.currentexchangerate.service.GifsService;

@RequiredArgsConstructor
@Slf4j
@RestController
public class CurrentExchangeRateController {

    private final GifsService giphyFeign;
    private final DataUtilService dataUtilService;
    private final ExchangeRateService exchangeRateService;

    @GetMapping("/")
    public String mainPage() {
        return "Specify one of the currencies in the format: http://localhost:8080/RUB . " +
                "The base currency is USD.";
    }

    @GetMapping("/{currency}")
    public String getCurrentGif(@PathVariable String currency) {
        String returnGif;

        String today = dataUtilService.getTodayWithFormatToString();
        log.info("today data: " + today);
        String yesterday = dataUtilService.getYesterdayWithFormatToString();
        log.info("yesterday data: " + yesterday);

        try {
            BigDecimal todayRates = exchangeRateService.getRates(today, currency);
            log.info("today rate is: " + todayRates);
            BigDecimal yesterdayRates = exchangeRateService.getRates(yesterday, currency);
            log.info("yesterday rate is: " + yesterdayRates);
            returnGif = giphyFeign.getGif(todayRates, yesterdayRates);
        } catch (InvalidHTTPParamException e) {
            return e.getMessage();
        }
        return returnGif;
    }
}
