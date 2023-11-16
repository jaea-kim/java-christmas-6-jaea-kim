package christmas.discount;

import christmas.discount.domain.DiscountDetails;
import christmas.order.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

class DiscountServiceTest {
    @Test
    @DisplayName("할인이 적용되어 할인 내용이 있는 경우 DiscountDetails가 반환되어야 한다.")
    void calculate_discount_apply() {
        String[] orderMenuInput = new String[]{"타파스-5", "제로콜라-1"};
        LocalDate orderDate = LocalDate.of(2023, 12, 5);
        Order order = new Order(orderDate, orderMenuInput);
        DiscountService discountService = new DiscountService(order);
        Optional<DiscountDetails> result = discountService.calculate();

        Assertions.assertThat(result).isPresent();
    }

    @Test
    @DisplayName("할인이 적용되지 않으면 빈 Optinoal이 반환되어야 한다.")
    void calculate_no_discount() {
        String[] orderMenuInput = new String[]{"타파스-1", "제로콜라-1"};
        LocalDate orderDate = LocalDate.of(2023, 12, 5);
        Order order = new Order(orderDate, orderMenuInput);
        DiscountService discountService = new DiscountService(order);

        Optional<DiscountDetails> result = discountService.calculate();
        Assertions.assertThat(result).isEmpty();
    }
}
