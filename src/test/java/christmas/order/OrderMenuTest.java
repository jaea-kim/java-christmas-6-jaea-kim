package christmas.order;

import christmas.config.Delimiter;
import christmas.config.EventConstants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenuTest {
    @Test
    @DisplayName("메뉴판에 없는 메뉴를 찾으면 IllegalArgumentException 예외가 발생한다.")
    void init_order_not_in_menu() {
        String input = "닭갈비-5";
        Assertions.assertThatThrownBy(() -> new OrderMenu(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문 메뉴의 개수가 1보다 작으면 IllegalArgumentException 예외가 발생한다.")
    void init_order_zero_quantity() {
        String menuName = Menu.CAESAR_SALAD.getLabel();
        String input = menuName + Delimiter.MENU.getSymbol() + (EventConstants.MIN_QUANTITY.getValue() - 1);
        Assertions.assertThatThrownBy(() -> new OrderMenu(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}