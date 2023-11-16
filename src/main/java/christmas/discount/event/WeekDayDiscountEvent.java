package christmas.discount.event;

import christmas.discount.DateDTO;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

public class WeekDayDiscountEvent implements DiscountEvent {
    private static final List<DayOfWeek> DISCOUNT_DAY = Arrays.asList(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY);

    @Override
    public boolean isExecute(DateDTO dateDTO) {
        return DISCOUNT_DAY.contains(dateDTO.day().getDayOfWeek());
    }

    @Override
    public int calculate() {
        return 2023;
    }
}

