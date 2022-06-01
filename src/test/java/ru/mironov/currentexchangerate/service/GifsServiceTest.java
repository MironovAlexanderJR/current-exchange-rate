package ru.mironov.currentexchangerate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.mironov.currentexchangerate.httpclient.GiphyFeignClient;
import ru.mironov.currentexchangerate.service.Impl.GifsServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GifsServiceTest {

    @MockBean
    DataUtilService dataUtilService;

    @MockBean
    GiphyFeignClient giphyFeignClient;

    @MockBean
    ExchangeRateService exchangeRateService;

    @Autowired
    GifsServiceImpl gifsService;

    @Test
    public void isGetGifsCurrentWorkTest() {
        final String returnFromGiphyFeignClient = "{\n" +
                "  \"data\": {\n" +
                "    \"images\": {\n" +
                "      \"fixed_height\": {\n" +
                "        \"height\": \"200\",\n" +
                "        \"url\": \"https://media4.giphy.com/media/eEIcH19FSvtWPg8jU8/200.gif?cid=af156d68d678e24b4b85f5b81c2c9d9641e241d5a609bb68&rid=200.gif&ct=g\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        final String gifUrl =
                "https://media4.giphy.com/media/eEIcH19FSvtWPg8jU8/200.gif?cid=af156d68d678e24b4b85f5b81c2c9d9641e241d5a609bb68&rid=200.gif&ct=g";

        final BigDecimal todayRate = new BigDecimal("65.50");
        final BigDecimal yesterdayRate = new BigDecimal("65.00");

        Mockito.when(giphyFeignClient.getGif("rich", "SDWz29ky74CxdzN8P04pv71kA0K6zADQ", "g"))
                .thenReturn(returnFromGiphyFeignClient);

        assertThat(gifsService.getGif(todayRate, yesterdayRate))
                .isEqualTo("<img src=\"" + gifUrl + "\" alt=\"there should be gif, please set currency correctly\"  width=250/>");
    }
}
