package ru.mironov.currentexchangerate.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mironov.currentexchangerate.httpclient.GiphyFeignClient;
import ru.mironov.currentexchangerate.service.GifsService;

@Service
@RequiredArgsConstructor
public class GifsServiceImpl implements GifsService {

    private final GiphyFeignClient giphyFeignClient;

    @Value("${giphykey}")
    private String key;

    public String getGif(BigDecimal todayRate, BigDecimal yesterdayRate) {

        int ratesUp = todayRate.compareTo(yesterdayRate);

        String gifType = ratesUp >= 0 ? "rich" : "broke";

        JsonNode parent = null;
        try {
            parent = new ObjectMapper().readTree(giphyFeignClient.getGif(gifType, key, "g"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assert parent != null;
        String gifUrl = parent.get("data")
                .get("images")
                .get("fixed_height")
                .get("url").asText();

        return "<img src=\"" + gifUrl + "\" alt=\"there should be gif, please set currency correctly\"  width=250/>";
    }
}
