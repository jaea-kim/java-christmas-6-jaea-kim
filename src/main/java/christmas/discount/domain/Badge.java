package christmas.discount.domain;

import java.util.Arrays;

public enum Badge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000),
    NONE("없음", 0);

    private final String label;
    private final int amount;

    Badge(String label, int amount) {
        this.label = label;
        this.amount = amount;
    }

    public static Badge getBadgeByAmount(int amount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> amount >= badge.amount)
                .reduce((first, second) -> second)
                .orElse(NONE);

    }

    public String getLabel() {
        return label;
    }
}
