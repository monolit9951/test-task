package com.IntegrityCheckTeam.JavaTechnicalChallenge.domain.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

@Document
@Data
public class ItemDocument implements Serializable {
    private String id;
    private String name;
    private int amountItem;
    private Currency currency;
    private BigDecimal price;
    private String itemDescription;
}
