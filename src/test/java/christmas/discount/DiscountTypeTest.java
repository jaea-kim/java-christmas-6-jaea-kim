package christmas.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}