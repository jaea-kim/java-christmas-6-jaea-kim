package christmas.discount;

import christmas.config.EventConstants;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public enum DiscountType {
    WEEKDAY("평일 할인", new WeekDayDiscountEvent(), Arrays.asList(CustomDay.SUNDAY, CustomDay.MONDAY, CustomDay.TUESDAY, CustomDay.WEDNESDAY, CustomDay.THURSDAY), EventConstants.LAST_DAY.getValue()),
    WEEKEND("주말 할인",new WeekendDiscountEvent(), Arrays.asList(CustomDay.FRIDAY, CustomDay.SATURDAY), EventConstants.LAST_DAY.getValue()),
    SPECIAL("특별 할인", new SpecialDiscountEvent(), Arrays.asList(CustomDay.SUNDAY, CustomDay.CHRISTMAS), EventConstants.LAST_DAY.getValue()),
    FREE_GIFT("증정 이벤트", new FreeGiftDiscountEvent(), CustomDay.getAllDay(), EventConstants.LAST_DAY.getValue()),
    CHRISTMAS("크리스마스 디데이 할인", new ChristmasDiscountEvent(), CustomDay.getAllDay(), EventConstants.CHRISTMAS_DAY.getValue());


    private final String message;
    private final DiscountEvent discountEvent;
    private final List<CustomDay> discountDay;

    private final LocalDate endDay;

    DiscountType(String message, DiscountEvent discountEvent, List<CustomDay> discountDay, int endDay) {
        this.message = message;
        this.discountEvent = discountEvent;
        this.discountDay = discountDay;
        this.endDay = LocalDate.of(EventConstants.EVENT_YEAR.getValue(), EventConstants.EVENT_MONTH.getValue(), endDay);
    }
}
