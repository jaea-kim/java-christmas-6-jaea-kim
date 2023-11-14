package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

class InputViewTest {
    static InputView inputView;

    @BeforeAll
    static void settingInputView() {
        inputView = new InputView();
    }

    @Test
    @DisplayName("입력받은 값이 정수가 아닌 경우 IllegalArgumentException 예외가 발생한다.")
    void input_not_number() {
        assertReadLineTest(
                () -> {
                    Assertions.assertThatThrownBy(() -> inputView.readDate())
                            .isInstanceOf(IllegalArgumentException.class);
                },
                "오일"
        );
    }

    @Test
    @DisplayName("입력받은 값이 1보다 작은 경우 IllegalArgumentException 예외가 발생한다.")
    void input_under_1() {
        assertReadLineTest(
                () -> {
                    Assertions.assertThatThrownBy(() -> inputView.readDate())
                            .isInstanceOf(IllegalArgumentException.class);
                },
                "0"
        );
    }

    @Test
    @DisplayName("입력받은 값이 31보다 큰 경우 IllegalArgumentException 예외가 발생한다.")
    void input_over_31() {
        assertReadLineTest(
                () -> {
                    Assertions.assertThatThrownBy(() -> inputView.readDate())
                            .isInstanceOf(IllegalArgumentException.class);
                },
                "32"
        );
    }

    @Test
    @DisplayName("정상적으로 입력받은 값은 2023년 12월 입력받은 날짜와 같아야한다.")
    void input_date_correct() {
        int number = 30;
        LocalDate date = LocalDate.of(2023, 12, number);
        assertReadLineTest(
                () -> {
                    LocalDate inputDate = inputView.readDate();
                    Assertions.assertThat(inputDate.equals(date)).isTrue();
                },
                String.valueOf(number)
        );
    }

    @Test
    @DisplayName("입력받은 주문 내용의 구분자가 아니면 IllegalArgumentException 예외가 발생한다.")
    void read_order_wrong_delimiter() {
        assertReadLineTest(
                () -> {
                    Assertions.assertThatThrownBy(() -> inputView.readOrderMenus())
                            .isInstanceOf(IllegalArgumentException.class);
                },
                "소나무-3/아이스티-5"
        );
    }

    @Test
    @DisplayName("입력받은 주문 내용이 주문 패턴과 일치 하지 않으면 IllegalArgumentException 예외가 발생한다.")
    void read_order_not_match_pattern() {
        assertReadLineTest(
                () -> {
                    Assertions.assertThatThrownBy(() -> inputView.readOrderMenus())
                            .isInstanceOf(IllegalArgumentException.class);
                },
                "3-소나무"
        );
    }

    @Test
    @DisplayName("입력받은 주문의 수량이 숫자가 아니면 IllegalArgumentException 예외가 발생한다.")
    void read_order_not_match_quantity() {
        assertReadLineTest(
                () -> {
                    Assertions.assertThatThrownBy(() -> inputView.readOrderMenus())
                            .isInstanceOf(IllegalArgumentException.class);
                },
                "소나무-세개"
        );
    }

    @Test
    @DisplayName("입력받은 주문의 수량이 0이면 IllegalArgumentException 예외가 발생한다.")
    void read_order_quantity_zero() {
        assertReadLineTest(
                () -> {
                    Assertions.assertThatThrownBy(() -> inputView.readOrderMenus())
                            .isInstanceOf(IllegalArgumentException.class);
                },
                "소나무-0개"
        );
    }

    private void assertReadLineTest(
            final Executable executable,
            final String value
    ) {
        assertConsoleTest(
                Console::readLine,
                executable,
                value
        );
    }

    private <T> void assertConsoleTest(
            final MockedStatic.Verification verification,
            final Executable executable,
            final T value
    ) {
        Duration SIMPLE_TEST_TIMEOUT = Duration.ofSeconds(1L);
        assertTimeoutPreemptively(SIMPLE_TEST_TIMEOUT, () -> {
            try (final MockedStatic<Console> mock = Mockito.mockStatic(Console.class)) {
                mock.when(verification).thenReturn(value);
                executable.execute();
            }
        });
    }
}