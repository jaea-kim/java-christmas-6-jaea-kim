package christmas.discount;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiscountDetails {
    private final Map<DiscountType, Integer> discountDetails;

    public DiscountDetails(Map<DiscountType, Integer> discountDetails) {
        this.discountDetails = discountDetails;
    }

    public boolean hasFreeGift() {
        return discountDetails.containsKey(DiscountType.FREE_GIFT);
    }

    public List<String> getDetail() {
        List<String> message = new ArrayList<>();
        discountDetails.forEach((t, i) -> message.add(String.format(t.getMessage(), i)));
        return message;
    }

    public int totalDiscountAmount(){
        return discountDetails.values().stream().mapToInt(Integer::intValue).sum();
    }
}
