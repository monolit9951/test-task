package com.IntegrityCheckTeam.JavaTechnicalChallenge.enums;

public enum SortDirection {
    ASC("ASC"),
    DESC("DESC");

    private String name;

    SortDirection(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static SortDirection getSortDirectionByName(String name) {
        for (SortDirection value : values()) {
            if (name.equals(value.name)) {
                return value;
            }
        }
        throw new IllegalArgumentException("SortDirection not found");
    }
}
