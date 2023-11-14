package christmas.discount.event;

import christmas.discount.CustomDay;
import christmas.discount.DateDTO;

import java.util.Arrays;
import java.util.List;

public class WeekDayDiscountEvent implements DiscountEvent {
    private static final List<CustomDay> DISCOUNT_DAY = Arrays.asList(CustomDay.SUNDAY, CustomDay.MONDAY, CustomDay.TUESDAY, CustomDay.WEDNESDAY, CustomDay.THURSDAY);

    @Override
    public boolean isExecute(DateDTO dateDTO) {
        return DISCOUNT_DAY.contains(dateDTO.day());
    }
}

