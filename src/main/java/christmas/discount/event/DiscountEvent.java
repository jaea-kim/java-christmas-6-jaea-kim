package christmas.discount.event;

import christmas.discount.DateDTO;

public interface DiscountEvent {
    boolean isExecute(DateDTO dateDTO);

    int calculate();
}
