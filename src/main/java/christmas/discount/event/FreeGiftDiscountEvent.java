package christmas.discount.event;

import christmas.discount.DateDTO;
import christmas.discount.domain.FreeGift;

public class FreeGiftDiscountEvent implements DiscountEvent {
    @Override
    public boolean isExecute(DateDTO dateDTO) {
        return false;
    }

    @Override
    public int calculate() {
        return FreeGift.CHAMPAGNE.getGift().getPrice();
    }
}
