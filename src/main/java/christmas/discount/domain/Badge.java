package christmas.discount.domain;

import java.util.Arrays;

public enum Badge implements Comparable<Badge> {

    STAR("별", 5000, 10000),
    TREE("트리", 10000, 20000),
    SANTA("산타", 20000, Integer.MAX_VALUE),
    NONE("없음", 0, 5000);
    private final String label;
    private final int minAmount;
    private final int maxAmount;

    Badge(String label, int minAmount, int maxAmount) {
        this.label = label;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public static Badge getBadgeByAmount(int amount) {
        return Arrays.stream(values())
                .filter(badge -> amount >= badge.minAmount && amount < badge.maxAmount)
                .findFirst()
                .orElse(NONE);
    }

    public String getLabel() {
        return label;
    }
}
