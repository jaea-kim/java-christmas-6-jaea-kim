package christmas.discount;

import christmas.order.Order;

import java.util.ArrayList;
import java.util.List;

public class DiscountService {
    private final Order order;

    public DiscountService(Order order) {
        this.order = order;
    }

    public DiscountDetails calculate() {
        try {
            FreeGift freeGift = calculateFreeGift();
            List<DiscountType> matchedDiscountType = matchDiscountType(freeGift);
            return new DiscountDetails();
        } catch (IllegalArgumentException e) {
            return new DiscountDetails();
        }
    }

    private FreeGift calculateFreeGift() {
        return FreeGift.getFreeGiftBy(order.totalAmountBeforeDiscount());
    }

    private List<DiscountType> matchDiscountType(FreeGift freeGift) {
        List<DiscountType> discountTypes = new ArrayList<>();
        if (freeGift.hasFreeGift()) {
            discountTypes.add(DiscountType.FREE_GIFT);
        }
        discountTypes.addAll(findDiscountTypeWith(order.getDate()));

        return discountTypes;
    }

    private List<DiscountType> findDiscountTypeWith(DateDTO dateDTO) {
        return DiscountType.getDiscountTypes(dateDTO);
    }
}
