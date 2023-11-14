package christmas.discount;

import java.util.Map;

public class DiscountDetails {
    private final Map<DiscountType, Integer> discountDetails;

    public DiscountDetails(Map<DiscountType, Integer> discountDetails) {
        this.discountDetails = discountDetails;
    }
}
