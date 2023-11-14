package christmas.config;

public enum EventConstants {
    EVENT_YEAR(2023),
    EVENT_MONTH(12);

    private final int value;

    EventConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}