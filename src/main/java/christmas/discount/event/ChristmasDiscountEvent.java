package christmas.discount.event;

import christmas.config.EventConstants;
import christmas.discount.DateDTO;

public class ChristmasDiscountEvent implements DiscountEvent {
    @Override
    public boolean isExecute(DateDTO dateDTO) {
        return dateDTO.date() <= EventConstants.CHRISTMAS_DAY.getValue();
    }
}
