package christmas.order;

import christmas.config.Delimiter;
import christmas.config.EventConstants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {
    @Test
    @DisplayName("메뉴판에 없는 메뉴를 찾으면 IllegalArgumentException 예외가 발생한다.")
    void get_menu_not_valid() {
        Assertions.assertThatThrownBy(() -> Menu.getMenuByName("바밤바"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("메뉴판의 라벨과 같은 라벨의 메뉴를 반환해야한다.")
    void get_menu_success() {
        String label = "초코케이크";
        Menu menu = Menu.getMenuByName(label);
        Assertions.assertThat(menu.getLabel().equals(label)).isTrue();
    }

    @Test
    @DisplayName("메뉴 타입이 음료가 아닌 메뉴는 false가 나와야한다.")
    void is_not_beverage() {
        Menu menu = Menu.BBQ_RIBS;
        Assertions.assertThat(menu.isBeverage()).isFalse();
    }

    @Test
    @DisplayName("메뉴 타입이 음료가 아닌 메뉴는 false가 나와야한다.")
    void isBeverage() {
        Menu menu = Menu.CHAMPAGNE;
        Assertions.assertThat(menu.isBeverage()).isTrue();
    }
}
