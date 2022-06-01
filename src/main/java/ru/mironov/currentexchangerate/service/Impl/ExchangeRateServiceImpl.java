package ru.mironov.currentexchangerate.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mironov.currentexchangerate.exceptions.InvalidHTTPParamException;
import ru.mironov.currentexchangerate.httpclient.OpenExchangeRatesFeignClient;
import ru.mironov.currentexchangerate.service.ExchangeRateService;

@RequiredArgsConstructor
@Slf4j
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final OpenExchangeRatesFeignClient openExchangeRatesFeignClient;

    @Value("${openexchangekey}")
    String key;

    public BigDecimal getRates(String data, String currency) throws InvalidHTTPParamException {
        BigDecimal bigDecimal = null;

        try {
            JsonNode response
                    = new ObjectMapper().readTree(openExchangeRatesFeignClient.getRates(key, data));

            JsonNode responseBody = response.get("rates").get(currency);

            if (responseBody == null)
                throw new InvalidHTTPParamException("Invalid currency param in http request");

            bigDecimal = new BigDecimal(responseBody.asText());

        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return bigDecimal;
    }
}