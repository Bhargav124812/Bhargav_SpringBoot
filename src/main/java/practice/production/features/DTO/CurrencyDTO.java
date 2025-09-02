package practice.production.features.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDTO {
    private Long Id;

    private String fromCurrency;

    private String toCurrency;

    private BigDecimal units;

    private BigDecimal convertedAmount;

    private BigDecimal conversionRate;
}
