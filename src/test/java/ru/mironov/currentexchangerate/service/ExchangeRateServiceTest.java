package ru.mironov.currentexchangerate.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.mironov.currentexchangerate.exceptions.InvalidHTTPParamException;
import ru.mironov.currentexchangerate.httpclient.GiphyFeignClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ExchangeRateServiceTest {

    @MockBean
    DataUtilService dataUtilService;

    @MockBean
    GifsService gifsService;

    @MockBean
    GiphyFeignClient giphyFeignClient;

    @Autowired
    ExchangeRateService exchangeRateService;

    @Test
    public void isGetRateCorrectWorkTest() throws InvalidHTTPParamException {

        final String currency = "RUB";

        Mockito.when(dataUtilService.getTodayWithFormatToString())
                .thenReturn("2022-05-31");

        Mockito.when(dataUtilService.getYesterdayWithFormatToString())
                .thenReturn("2022-05-30");

        assertThat(exchangeRateService.getRates(dataUtilService.getTodayWithFormatToString(), currency))
                .isEqualTo("62.75");
    }
}
