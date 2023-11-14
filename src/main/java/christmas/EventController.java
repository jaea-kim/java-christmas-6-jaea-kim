package christmas;

import christmas.config.EventMessage;
import christmas.order.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;

public class EventController {
    private final InputView inputView;
    private final OutputView outputView;

    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void execute() {
        Order order = orderWithUserInput();
    }

    private Order orderWithUserInput() {
        outputView.printMessage(EventMessage.GREETINGS.getMessage());
        LocalDate date = reservationForDate();
        return reserveWith(date);
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

    private String[] reservationForMenu() throws IllegalArgumentException {
        return inputView.readOrderMenus();
    }

    private Order reserveWith(LocalDate date) {
        while (true) {
            try {
                String[] menu = reservationForMenu();
                return new Order(date, menu);
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }
}
