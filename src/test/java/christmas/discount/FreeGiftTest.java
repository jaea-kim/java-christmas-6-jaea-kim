package christmas.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FreeGiftTest {
    @Test
    @DisplayName("구매금액이 120000원 이상이면 샴페인을 증정해야한다.")
    void gift_champagne() {
        int payment = FreeGift.CHAMPAGNE.getPayment() + 1000;
        Assertions.assertThat(FreeGift.getFreeGiftBy(payment)).isEqualTo(FreeGift.CHAMPAGNE);
    }

    @Test
    @DisplayName("구매금액이 120000원보다 작고 10000원보다 크면 증정 상품이 없어야한다.")
    void gift_none() {
        int payment = FreeGift.CHAMPAGNE.getPayment() - 1000;
        Assertions.assertThat(FreeGift.getFreeGiftBy(payment).getGift()).isNull();
    }

    @Test
    @DisplayName("구매금액이 10000보다 작으면 IllegalArgumentException 예외가 발생한다.")
    void gift_no_event() {
        int payment = FreeGift.NONE.getPayment() - 1000;
        Assertions.assertThatThrownBy(() -> FreeGift.getFreeGiftBy(payment))
                .isInstanceOf(IllegalArgumentException.class);
    }
}