package christmas.discount;

import christmas.discount.event.*;

public enum DiscountType {
    WEEKDAY("평일 할인", new WeekDayDiscountEvent()),
    WEEKEND("주말 할인", new WeekendDiscountEvent()),
    SPECIAL("특별 할인", new SpecialDiscountEvent()),
    CHRISTMAS("크리스마스 디데이 할인", new ChristmasDiscountEvent()),
    FREE_GIFT("증정 이벤트", null),
    NONE("없음", null);


    private final String message;
    private final DiscountEvent discountEvent;

    DiscountType(String message, DiscountEvent discountEvent) {
        this.message = message;
        this.discountEvent = discountEvent;
    }
}
