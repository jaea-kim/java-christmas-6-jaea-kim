package christmas.order;

import christmas.config.Delimiter;
import christmas.config.EventConstants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenusTest {
    @Test
    @DisplayName("메뉴가 중복될 경우 IllegalArgumentException 예외가 발생한다.")
    void menus_duplicate() {
        String menu = Menu.BBQ_RIBS.getLabel();
        String orderMenu = menu + Delimiter.MENU.getSymbol() + EventConstants.MIN_QUANTITY.getValue();
        String[] orderMenus = new String[]{orderMenu, orderMenu};

        Assertions.assertThatThrownBy(() -> new OrderMenus(orderMenus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("음료 메뉴만 주문할 경우 IllegalArgumentException 예외가 발생한다.")
    void menus_only_beverage() {
        String menu = Menu.CHAMPAGNE.getLabel();
        String orderMenu = menu + Delimiter.MENU.getSymbol() + EventConstants.MIN_QUANTITY.getValue();
        String[] orderMenus = new String[]{orderMenu};

        Assertions.assertThatThrownBy(() -> new OrderMenus(orderMenus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("음료 메뉴만 여러 종류 주문할 경우 IllegalArgumentException 예외가 발생한다.")
    void menus_only_beverage_two() {
        String orderMenu1 = Menu.CHAMPAGNE.getLabel() + Delimiter.MENU.getSymbol() + EventConstants.MIN_QUANTITY.getValue();
        String orderMenu2 = Menu.RED_WINE.getLabel() + Delimiter.MENU.getSymbol() + EventConstants.MIN_QUANTITY.getValue();
        String[] orderMenus = new String[]{orderMenu1, orderMenu2};

        Assertions.assertThatThrownBy(() -> new OrderMenus(orderMenus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("총 메뉴 수량이 최대 수량을 넘은 경우 IllegalArgumentException 예외가 발생한다.")
    void menus_over_max_quantity() {
        String orderMenu1 = Menu.CHAMPAGNE.getLabel() + Delimiter.MENU.getSymbol() + EventConstants.MAX_ALL_QUANTITY.getValue();
        String orderMenu2 = Menu.BBQ_RIBS.getLabel() + Delimiter.MENU.getSymbol() + EventConstants.MAX_ALL_QUANTITY.getValue();
        String[] orderMenus = new String[]{orderMenu1, orderMenu2};

        Assertions.assertThatThrownBy(() -> new OrderMenus(orderMenus))
                .isInstanceOf(IllegalArgumentException.class);
    }

}