package practice.production.features.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practice.production.features.DTO.CurrencyDTO;
import practice.production.features.service.CurrencyService;
import practice.production.features.service.CurrencyServiceInterface;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/convertCurrency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    public List<CurrencyDTO> getAllCurrency(){
        return currencyService.getConversionHistory();
    }
    @PostMapping()
    public CurrencyDTO convertCurrency(@RequestParam String fromCurrency,@RequestParam String toCurrency,@RequestParam BigDecimal units){
        return currencyService.convertCurrency(fromCurrency,toCurrency,units);
    }
    @PutMapping()
    public CurrencyDTO updateCurrencyConversion(@RequestParam Long Id,@RequestParam String fromCurrency,@RequestParam String toCurrency,@RequestParam BigDecimal amount){
        return currencyService.updateCurrencyConversion(Id,fromCurrency,toCurrency,amount);
    }
}
