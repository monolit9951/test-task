package com.IntegrityCheckTeam.JavaTechnicalChallenge.enums;

import com.IntegrityCheckTeam.JavaTechnicalChallenge.exception.FieldProcessingException;

public enum Currency {
        USD("USD"),
        EUR("EUR"),
        PLN("PLN"),
        UAH("UAH"),
        JPY("JPY"),
        GBP("GBP"),
        AUD("AUD"),
        CAD("CAD"),
        CHF("CHF"),
        CNY("CNY"),
        SEK("SEK"),
        NZD("NZD"),
        MXN("MXN"),
        SGD("SGD"),
        HKD("HKD"),
        NOK("NOK"),
        KRW("KRW"),
        TRY("TRY"),
        RUB("RUB"),
        INR("INR"),
        BRL("BRL"),
        ZAR("ZAR"),
        DKK("DKK"),
        MYR("MYR"),
        THB("THB");

    private String name;

    Currency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Currency getCurrencyByName(String name) {
        for (Currency value : values()) {
            if (name.equals(value.name)) {
                return value;
            }
        }
        throw new FieldProcessingException("Currency not found for name: " + name);
    }
}
