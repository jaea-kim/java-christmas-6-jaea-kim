package christmas.config;

public enum EventConstants {
    EVENT_YEAR(2023),
    EVENT_MONTH(12),
    MIN_QUANTITY(1),
    MAX_ALL_QUANTITY(20),
    CHRISTMAS_DAY(25),
    LAST_DAY(31);

    private final int value;

    EventConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}