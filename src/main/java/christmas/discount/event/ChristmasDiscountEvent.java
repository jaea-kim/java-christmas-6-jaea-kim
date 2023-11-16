package christmas.discount.event;

import christmas.config.EventConstants;
import christmas.discount.DateDTO;

public class ChristmasDiscountEvent implements DiscountEvent {
    private int date;

    @Override
    public boolean isExecute(DateDTO dateDTO) {
        this.date = dateDTO.date();
        return dateDTO.date() <= EventConstants.CHRISTMAS_DAY.getValue();
    }

    @Override
    public int calculate() {
        return (1000 + (date - 1) * 100);
    }
}
