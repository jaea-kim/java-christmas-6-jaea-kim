package christmas.discount;

import christmas.config.Delimiter;
import christmas.config.EventConstants;
import christmas.order.Menu;
import christmas.order.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class DiscountTypeTest {
    @Test
    @DisplayName("요일이 월요일이고 날짜가 25일이면 평일 할인, 특별할인, 크리스마스 디데이 할인이 적용 되어야 한다.")
    void monday_christmas() {
        List<DiscountType> prediction = List.of(DiscountType.CHRISTMAS, DiscountType.WEEKDAY, DiscountType.SPECIAL);
        DateDTO dateDTO = new DateDTO(CustomDay.CHRISTMAS, 25);
        List<DiscountType> discountTypes = DiscountType.getDiscountTypes(dateDTO);

        Assertions.assertThat(prediction.containsAll(discountTypes)).isTrue();
    }

    @Test
    @DisplayName("요일이 월요일이고 날짜가 25일이 이전이면 평일 할인, 크리스마스 디데이 할인이 적용 되어야 한다.")
    void monday_not_christmas() {
        List<DiscountType> prediction = List.of(DiscountType.CHRISTMAS, DiscountType.WEEKDAY);
        DateDTO dateDTO = new DateDTO(CustomDay.MONDAY, 18);
        List<DiscountType> discountTypes = DiscountType.getDiscountTypes(dateDTO);

        Assertions.assertThat(prediction.containsAll(discountTypes)).isTrue();
    }

    @Test
    @DisplayName("요일이 금요일이고 날짜가 25일 이전이면 주말 할인, 크리스마스 디데이 할인이 적용 되어야 한다.")
    void friday_before_christmas() {
        List<DiscountType> prediction = List.of(DiscountType.CHRISTMAS, DiscountType.WEEKEND);
        DateDTO dateDTO = new DateDTO(CustomDay.FRIDAY, 1);
        List<DiscountType> discountTypes = DiscountType.getDiscountTypes(dateDTO);

        Assertions.assertThat(prediction.containsAll(discountTypes)).isTrue();
    }

    @Test
    @DisplayName("요일이 금요일이고 날짜가 25일이 이후면 주말 할인이 적용 되어야 한다.")
    void friday_after_christmas() {
        List<DiscountType> prediction = List.of(DiscountType.WEEKEND);
        DateDTO dateDTO = new DateDTO(CustomDay.FRIDAY, 29);
        List<DiscountType> discountTypes = DiscountType.getDiscountTypes(dateDTO);

        Assertions.assertThat(prediction.containsAll(discountTypes)).isTrue();
    }

    @Test
    @DisplayName("요일이 일요일이고 날짜가 25일 이후면 평일 할인, 특별할인이 적용 되어야 한다.")
    void sunday_after_christmas() {
        List<DiscountType> prediction = List.of(DiscountType.WEEKDAY, DiscountType.SPECIAL);
        DateDTO dateDTO = new DateDTO(CustomDay.SUNDAY, 31);
        List<DiscountType> discountTypes = DiscountType.getDiscountTypes(dateDTO);

        Assertions.assertThat(prediction.containsAll(discountTypes)).isTrue();
    }

    @Test
    @DisplayName("평일 할인에 디저트 메뉴가 없다면 평일 할인 금액은 0이 되어야한다.")
    void calculate_weekday_no_dessert() {
        String menu1 = getOrderMenuString(Menu.BBQ_RIBS, 1);
        String menu2 = getOrderMenuString(Menu.TAPAS, 3);
        LocalDate weekday = getLocalDate(4);
        Order order = new Order(weekday, new String[]{menu1, menu2});

        Assertions.assertThat(DiscountType.WEEKDAY.calculateDiscountAmount(order)).isEqualTo(0);
    }

    @Test
    @DisplayName("평일 할인에 디저트 메뉴가 여러 개라면 평일 할인 금액은 2023 x 개수가 되어야한다.")
    void calculate_weekday_dessert() {
        int quantity = 2;
        String menu1 = getOrderMenuString(Menu.ICE_CREAM, quantity);
        LocalDate weekday = getLocalDate(4);
        Order order = new Order(weekday, new String[]{menu1});

        Assertions.assertThat(DiscountType.WEEKDAY.calculateDiscountAmount(order)).isEqualTo(2023 * quantity);
    }

    @Test
    @DisplayName("크리스마스 디데이 할인은 1일에 할인 금액은 1000원이 되어야한다.")
    void calculate_christmas_one() {
        int quantity = 2;
        String menu1 = getOrderMenuString(Menu.ICE_CREAM, quantity);
        String menu2 = getOrderMenuString(Menu.TAPAS, quantity);
        LocalDate weekend = getLocalDate(1);
        Order order = new Order(weekend, new String[]{menu1, menu2});

        List<DiscountType> types = DiscountType.getDiscountTypes(new DateDTO(CustomDay.FRIDAY, 1));
        int index = types.indexOf(DiscountType.CHRISTMAS);

        Assertions.assertThat(types.get(index).calculateDiscountAmount(order)).isEqualTo(1000);
    }

    @Test
    @DisplayName("크리스마스 디데이 할인은 25일에 할인 금액은 3400원이 되어야한다.")
    void calculate_christmas() {
        int quantity = 2;
        String menu1 = getOrderMenuString(Menu.ICE_CREAM, quantity);
        String menu2 = getOrderMenuString(Menu.TAPAS, quantity);
        LocalDate weekday = getLocalDate(25);
        Order order = new Order(weekday, new String[]{menu1, menu2});

        List<DiscountType> types = DiscountType.getDiscountTypes(new DateDTO(CustomDay.MONDAY, 25));
        int index = types.indexOf(DiscountType.CHRISTMAS);

        Assertions.assertThat(types.get(index).calculateDiscountAmount(order)).isEqualTo(3400);
    }

    private String getOrderMenuString(Menu menu, int quantity) {
        return menu.getLabel() + Delimiter.MENU.getSymbol() + quantity;
    }

    private LocalDate getLocalDate(int date) {
        return LocalDate.of(EventConstants.EVENT_YEAR.getValue(), EventConstants.EVENT_MONTH.getValue(), date);
    }
}
