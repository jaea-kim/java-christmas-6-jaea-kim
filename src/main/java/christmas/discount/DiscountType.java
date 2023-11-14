package christmas.discount;

import christmas.discount.event.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DiscountType {
    WEEKDAY("평일 할인", new WeekDayDiscountEvent()),
    WEEKEND("주말 할인", new WeekendDiscountEvent()),
    SPECIAL("특별 할인", new SpecialDiscountEvent()),
    CHRISTMAS("크리스마스 디데이 할인", new ChristmasDiscountEvent()),
    FREE_GIFT("증정 이벤트", new FreeGiftDiscountEvent());

    private final String message;
    private final DiscountEvent discountEvent;

    DiscountType(String message, DiscountEvent discountEvent) {
        this.message = message;
        this.discountEvent = discountEvent;
    }

    public static List<DiscountType> getDiscountTypes(DateDTO dateDTO) {
        return Arrays.stream(DiscountType.values()).filter(d -> d.discountEvent.isExecute(dateDTO))
                .collect(Collectors.toList());
    }
}
