package christmas.discount;

import christmas.discount.event.*;
import christmas.order.MenuType;
import christmas.order.Order;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DiscountType {
    WEEKDAY("평일 할인", MenuType.DESSERT, new WeekDayDiscountEvent()),
    WEEKEND("주말 할인", MenuType.MAIN_COURSE, new WeekendDiscountEvent()),
    SPECIAL("특별 할인", null, new SpecialDiscountEvent()),
    CHRISTMAS("크리스마스 디데이 할인", null, new ChristmasDiscountEvent()),
    FREE_GIFT("증정 이벤트", null, new FreeGiftDiscountEvent());

    private final String message;
    private final MenuType discountItemType;
    private final DiscountEvent discountEvent;

    DiscountType(String message, MenuType discountItemType, DiscountEvent discountEvent) {
        this.message = message;
        this.discountItemType = discountItemType;
        this.discountEvent = discountEvent;
    }

    public static List<DiscountType> getDiscountTypes(DateDTO dateDTO) {
        return Arrays.stream(DiscountType.values()).filter(d -> d.discountEvent.isExecute(dateDTO))
                .collect(Collectors.toList());
    }

    public Integer calculateDiscountAmount(Order order) {
        if (discountItemType == null) {
            return discountEvent.calculate();
        }
        return calculateAllAmount(order);
    }

    private Integer calculateAllAmount(Order order) {
        int itemCount = order.getMenuCount(discountItemType);

        return itemCount * discountEvent.calculate();
    }
}
