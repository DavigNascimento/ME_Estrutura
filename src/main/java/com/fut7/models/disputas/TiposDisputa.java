package com.fut7.models.disputas;

public enum TiposDisputa {
    GRUPOS("Grupos"),
    OITAVAS("Oitavas"),
    QUARTAS("Quartas"),
    SEMI_FINAL("Semi-Final"),
    TERCEIRO_COLOCADO("Terceiro Colocado"),
    FINAL("Final");

    private final String label;

    TiposDisputa(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
