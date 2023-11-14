package christmas.config;

public enum Delimiter {
    ORDER(","),
    MENU("-");

    private final String symbol;

    Delimiter(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
