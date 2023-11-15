package christmas;

import christmas.config.EventMessage;
import christmas.discount.DiscountDetails;
import christmas.discount.DiscountService;
import christmas.order.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;
import java.util.Optional;

public class EventController {
    private final InputView inputView;
    private final OutputView outputView;

    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void execute() {
        Order order = orderWithUserInput();
        printOrderInformation(order);
        printBenefitInformation(order);
    }

    private void printBenefitInformation(Order order) {
        Optional<DiscountDetails> discountDetails = calculateDiscount(order);
        if (discountDetails.isPresent()) {
            printBenefitDetailInformation(discountDetails.get());
            return;
        }
        printNoBenefit();
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

    private Optional<DiscountDetails> calculateDiscount(Order order) {
        DiscountService discountService = new DiscountService(order);
        return discountService.calculate();
    }

    private void printOrderInformation(Order order) {
        outputView.printMessageWithNumber(EventMessage.PREVIEW_EVENT.getMessage(), order.getDate().date());
        outputView.printOrderMenu(order.getOrderMenus());
        outputView.printTotalAmountBeforeDiscount(order.totalAmountBeforeDiscount());
    }

    private void printBenefitDetailInformation(DiscountDetails discountDetails) {
        outputView.printFreeGift(discountDetails.hasFreeGift());
        outputView.printBenefitInformation(discountDetails.getDetail());
        outputView.printTotalBenefitAmount(discountDetails.totalDiscountAmount());
    }


    private void printNoBenefit() {
        outputView.printFreeGift(false);
        outputView.printNoBenefit();
    }
}
