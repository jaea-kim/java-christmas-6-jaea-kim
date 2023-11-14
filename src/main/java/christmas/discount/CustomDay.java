package christmas.discount;

import christmas.config.EventConstants;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public enum CustomDay {
    MONDAY(DayOfWeek.MONDAY),
    TUESDAY(DayOfWeek.TUESDAY),
    WEDNESDAY(DayOfWeek.WEDNESDAY),
    THURSDAY(DayOfWeek.THURSDAY),
    FRIDAY(DayOfWeek.FRIDAY),
    SATURDAY(DayOfWeek.SATURDAY),
    SUNDAY(DayOfWeek.SUNDAY),
    CHRISTMAS(DayOfWeek.MONDAY);

    private final DayOfWeek dayOfWeek;

    CustomDay(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public static CustomDay from(LocalDate date) {
        if (date.getDayOfMonth() == EventConstants.CHRISTMAS_DAY.getValue()) {
            return CHRISTMAS;
        }

        return findCustomDay(date.getDayOfWeek());
    }

    private static CustomDay findCustomDay(DayOfWeek dayOfWeek) {
        return Arrays.stream(values())
                .filter(customDay -> customDay.dayOfWeek == dayOfWeek)
                .findFirst()
                .orElse(null);
    }
}
