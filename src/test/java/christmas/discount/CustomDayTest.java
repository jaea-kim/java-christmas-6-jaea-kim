package christmas.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class CustomDayTest {
    @Test
    @DisplayName("2023년 12월 25일 입력되면 크리스마스가 반환되어야한다.")
    void from_christmas() {
        LocalDate localDate = LocalDate.of(2023, 12, 25);

        Assertions.assertThat(CustomDay.from(localDate)).isEqualTo(CustomDay.CHRISTMAS);
    }

    @Test
    @DisplayName("오늘 날짜를 입력하면 수요일이 반환되어야한다.")
    void from_wednesday() {
        LocalDate localDate = LocalDate.now();

        Assertions.assertThat(CustomDay.from(localDate)).isEqualTo(CustomDay.WEDNESDAY);
    }
}
