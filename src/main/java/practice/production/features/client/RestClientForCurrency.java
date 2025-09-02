package practice.production.features.client;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RestClientForCurrency {

    private final RestClient restClient;

    Logger log = LoggerFactory.getLogger(RestClientForCurrency.class);

    public BigDecimal getConversionRate(String toCurrency){
        try {
            log.trace("Trying To Get All the Excange Rates");
            Map<String, Map<String, Double>> response = restClient.get()
                    .uri("v1/latest?apikey=fca_live_x9WedxlqquU9fWTjBfJdkU7GC7sJkTbOGyVzsM2f")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error(new String(res.getBody().readAllBytes()));
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });

            Map<String, Double> rates = response.get("data");
            if (rates == null || !rates.containsKey(toCurrency)) {
                log.trace("Get the Currency Value of the County");
                throw new IllegalArgumentException("Currency rate not found for: " + toCurrency);
            }
            log.trace("Successfullly Retriced the Employees");
            return BigDecimal.valueOf(rates.get(toCurrency));
        } catch (Exception e) {
            log.error("Error While Retiving the data :",e)  ;
            throw new RuntimeException(e);
        }
    }

}
