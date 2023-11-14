package christmas.discount;

import christmas.order.Order;

public class DiscountService {
    private final Order order;

    public DiscountService(Order order) {
        this.order = order;
    }

    public DiscountDetails calculate() {
        try {
            FreeGift freeGift = calculateFreeGift();

        } catch (IllegalArgumentException e) {
            return new DiscountDetails();
        }
    }


    private FreeGift calculateFreeGift() {
       return FreeGift.getFreeGiftBy(order.totalAmountBeforeDiscount());
    }
}
