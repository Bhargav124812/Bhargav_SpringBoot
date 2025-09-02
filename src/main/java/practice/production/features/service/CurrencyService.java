package practice.production.features.service;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import practice.production.features.DTO.CurrencyDTO;
import practice.production.features.Repo.CurrencyRepo;
import practice.production.features.client.RestClientForCurrency;
import practice.production.features.entity.CurrencyEntity;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService implements CurrencyServiceInterface{

    private final CurrencyRepo currencyRepo;

    private final RestClientForCurrency restClientForCurrency;

    private final ModelMapper modelMapper;

    @Override
    public List<CurrencyDTO> getConversionHistory() {
        List<CurrencyEntity> currencyEntityList=currencyRepo.findAll();
        return currencyEntityList.stream().map(currencyEntity -> modelMapper.map(currencyEntity,CurrencyDTO.class)).toList();
    }

    @Override
    public CurrencyDTO convertCurrency(String fromCurrency, String toCurrency, BigDecimal units) {
       BigDecimal conversionRate=restClientForCurrency.getConversionRate(toCurrency);
        CurrencyEntity currencyEntity=CurrencyEntity.builder()
                .fromCurrency(fromCurrency)
                .toCurrency(toCurrency)
                .units(units)
                .conversionRate(conversionRate)
                .convertedAmount(units.multiply(conversionRate))
                .build();
         currencyRepo.save(currencyEntity);
        return modelMapper.map(currencyEntity,CurrencyDTO.class);
    }

    @Override
    public CurrencyDTO updateCurrencyConversion(Long Id,String fromCurrency, String toCurrency, BigDecimal amount) {
        CurrencyEntity currencyEntity=currencyRepo.findById(Id).orElseThrow(()->new RuntimeException("Currency conversion not found with id "+Id));
        BigDecimal conversionRate=restClientForCurrency.getConversionRate(toCurrency);
        currencyEntity.setFromCurrency(fromCurrency);
        currencyEntity.setToCurrency(toCurrency);
        currencyEntity.setUnits(amount);
        currencyEntity.setConversionRate(conversionRate);
        currencyEntity.setConvertedAmount(amount.multiply(conversionRate));
        CurrencyEntity savedCurrencyEntity=currencyRepo.save(currencyEntity);
        return modelMapper.map(savedCurrencyEntity,CurrencyDTO.class);
    }

}
