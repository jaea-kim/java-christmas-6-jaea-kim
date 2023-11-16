package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.config.Delimiter;
import christmas.config.ErrorMessage;
import christmas.config.EventConstants;
import christmas.config.EventMessage;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;

public class InputView {
    public static final String MENU_REGEX = "^[가-힣]+" + Delimiter.MENU.getSymbol() + "[1-9][0-9]*$";

    public LocalDate readDate() {
        try {
            System.out.println(EventMessage.ASK_EATING_OUT.getMessage());
            String input = Console.readLine();

            int date = Integer.parseInt(input);

            return LocalDate.of(EventConstants.EVENT_YEAR.getValue(), EventConstants.EVENT_MONTH.getValue(), date);
        } catch (NumberFormatException | DateTimeException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }

    public String[] readOrderMenus() {
        System.out.println(EventMessage.ASK_TO_ORDER.getMessage());
        String[] input = Console.readLine().split(Delimiter.ORDER.getSymbol());

        if (validateOrderPattern(input)) {
            return input;
        }

        throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
    }

    private boolean validateOrderPattern(String[] input) {
        return Arrays.stream(input).allMatch(i -> i.matches(MENU_REGEX));
    }
}
