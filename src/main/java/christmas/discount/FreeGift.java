package christmas.discount;

import christmas.order.Menu;

public enum FreeGift {
    CHAMPAGNE(120000, Menu.CHAMPAGNE),
    NONE(10000, null),
    NO_EVENT(1, null);

    private final int payment;
    private final Menu gift;

    FreeGift(int payment, Menu gift) {
        this.payment = payment;
        this.gift = gift;
    }

    public static FreeGift getFreeGiftBy(int payment) {
        if (payment >= CHAMPAGNE.payment) {
            return CHAMPAGNE;
        }
        if (payment >= NONE.payment) {
            return NONE;
        }
        throw new IllegalArgumentException();
    }
}
