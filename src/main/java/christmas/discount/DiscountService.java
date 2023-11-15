package christmas.discount;

import christmas.discount.domain.DiscountDetails;
import christmas.discount.domain.DiscountType;
import christmas.discount.domain.FreeGift;
import christmas.order.Order;

import java.util.*;

public class DiscountService {
    private final Order order;

    public DiscountService(Order order) {
        this.order = order;
    }

    public Optional<DiscountDetails> calculate() {
        try {
            FreeGift freeGift = calculateFreeGift();
            List<DiscountType> matchedDiscountTypes = matchDiscountType(freeGift);
            Map<DiscountType, Integer> map = calculateDiscountAmount(matchedDiscountTypes);

            return Optional.of(new DiscountDetails(map));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
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

    private Map<DiscountType, Integer> calculateDiscountAmount(List<DiscountType> matchedDiscountTypes) {
        Map<DiscountType, Integer> details = new HashMap<>();
        matchedDiscountTypes.forEach(t -> details.put(t, details.getOrDefault(t, 0) + t.calculateDiscountAmount(order)));

        return details;
    }
}
