package christmas;

import christmas.config.EventMessage;
import christmas.domain.OrderMenu;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;
import java.util.List;

public class EventController {
    private final InputView inputView;
    private final OutputView outputView;

    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void execute() {
        promptForReservation();
    }

    private void promptForReservation() {
        outputView.printMessage(EventMessage.GREETINGS.getMessage());
        LocalDate date = reservationForDate();
        String[] orderMenus = reservationForMenu();
    }

    private LocalDate reservationForDate() {
        while (true) {
            try {
                return inputView.readDate();
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }

    private String[] reservationForMenu() {
        while (true) {
            try {
                return inputView.readOrderMenus();
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }
}
