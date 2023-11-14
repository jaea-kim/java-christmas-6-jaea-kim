package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.config.ErrorMessage;
import christmas.config.EventConstants;
import christmas.config.EventMessage;

import java.time.DateTimeException;
import java.time.LocalDate;

public class InputView {
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
}
