package com.Enum;

public enum Seme {
    FORCHETTE("forchetta"),
    CUCCHIAI("cucchiaio"),
    COLTELLI("coltello");
    

    private String valore;

    // Costruttore
    private Seme(String valore) {
        this.valore = valore;
    }

    // Metodo getter
    public String getValore() {
        return valore;
    }
}