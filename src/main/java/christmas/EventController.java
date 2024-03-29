package christmas;

import christmas.config.EventMessage;
import christmas.discount.DiscountService;
import christmas.discount.domain.Badge;
import christmas.discount.domain.DiscountDetails;
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
        discountDetails.ifPresentOrElse(
                d -> printBenefitDetailInformation(d, order.totalAmountBeforeDiscount()), () -> printNoBenefit(order.totalAmountBeforeDiscount())
        );
    }

    private Order orderWithUserInput() {
        outputView.printMessage(EventMessage.GREETINGS.getMessage());
        LocalDate date = getReservationForDate();
        return getOrderForReservation(date);
    }

    private LocalDate getReservationForDate() {
        while (true) {
            try {
                return inputView.readDate();
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }

    private String[] getReservationForMenu() throws IllegalArgumentException {
        return inputView.readOrderMenus();
    }

    private Order getOrderForReservation(LocalDate date) {
        while (true) {
            try {
                String[] menu = getReservationForMenu();
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

    private void printBenefitDetailInformation(DiscountDetails discountDetails, int totalAmountBeforeDiscount) {
        outputView.printFreeGift(discountDetails.hasFreeGift());
        outputView.printBenefitInformation(discountDetails.getDetail());
        outputView.printTotalBenefitAmount(discountDetails.totalDiscountAmount());
        outputView.printExpectedAmount(totalAmountBeforeDiscount - discountDetails.totalDiscountAmount());
        outputView.printBadge(Badge.getBadgeByAmount(discountDetails.totalDiscountAmount()).getLabel());
    }

    private void printNoBenefit(int totalAmountBeforeDiscount) {
        outputView.printFreeGift(false);
        outputView.printNoBenefit();
        outputView.printExpectedAmount(totalAmountBeforeDiscount);
        outputView.printBadge(Badge.NONE.getLabel());
    }
}
