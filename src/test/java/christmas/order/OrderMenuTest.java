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

    @Test
    @DisplayName("메뉴의 가격은 메뉴 하나 당 가격 x 수량이 반환되어야 한다.")
    void calculate_amount() {
        String menuName = Menu.SEAFOOD_PASTA.getLabel();
        int quantity = 2;
        String input = menuName + Delimiter.MENU.getSymbol() + quantity;
        OrderMenu orderMenu = new OrderMenu(input);

        Assertions.assertThat(orderMenu.getAmount()).isEqualTo(Menu.SEAFOOD_PASTA.getPrice() * quantity);
    }

    @Test
    @DisplayName("OrderMenu 객체의 메뉴가 같고 수량이 다르더라도 동일한 객체로 판단해야한다.")
    void equals() {
        int quantity1 = 1;
        int quantity2 = 5;
        String input1 = Menu.CHRISTMAS_PASTA.getLabel() + Delimiter.MENU.getSymbol() + quantity1;
        String input2 = Menu.CHRISTMAS_PASTA.getLabel() + Delimiter.MENU.getSymbol() + quantity2;
        OrderMenu orderMenu1 = new OrderMenu(input1);
        OrderMenu orderMenu2 = new OrderMenu(input2);

        Assertions.assertThat(orderMenu1.equals(orderMenu2)).isTrue();
    }
}
