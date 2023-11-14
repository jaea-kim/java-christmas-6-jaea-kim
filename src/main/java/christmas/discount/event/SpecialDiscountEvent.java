package christmas.discount.event;

import christmas.discount.CustomDay;
import christmas.discount.DateDTO;

import java.util.Arrays;
import java.util.List;

public class SpecialDiscountEvent implements DiscountEvent {
    private static final List<CustomDay> DISCOUNT_DAY = Arrays.asList(CustomDay.SUNDAY, CustomDay.CHRISTMAS);

    @Override
    public boolean isExecute(DateDTO dateDTO) {
        return DISCOUNT_DAY.contains(dateDTO.day());
    }

    @Override
    public int calculate() {
        return 1000;
    }
}
