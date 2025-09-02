package practice.production.features.service;

import org.springframework.stereotype.Service;
import practice.production.features.DTO.CurrencyDTO;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface CurrencyServiceInterface {
    public CurrencyDTO convertCurrency(String fromCurrency, String toCurrency, BigDecimal amount);

    public CurrencyDTO updateCurrencyConversion(Long Id,String fromCurrency, String toCurrency, BigDecimal amount);

    public List<CurrencyDTO> getConversionHistory();
}
