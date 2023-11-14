package christmas.discount.event;

import christmas.discount.DateDTO;

public class FreeGiftDiscountEvent implements DiscountEvent {
    @Override
    public boolean isExecute(DateDTO dateDTO) {
        return false;
    }
}
