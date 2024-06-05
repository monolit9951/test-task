package com.IntegrityCheckTeam.JavaTechnicalChallenge.api.dto;

import com.IntegrityCheckTeam.JavaTechnicalChallenge.enums.Currency;
import jakarta.validation.constraints.*;
import lombok.Data;
import jakarta.persistence.Id;
import java.math.BigDecimal;


@Data
public class ItemDTO {
    @Id
    private String id;
    @NotNull
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;
    @PositiveOrZero
    private int amountItem;
    @NotNull
    private Currency currency;
    @Positive
    private BigDecimal price;
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 500)
    private String itemDescription;
}
