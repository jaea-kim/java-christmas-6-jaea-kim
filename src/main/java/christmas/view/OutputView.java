package christmas.view;

import christmas.config.EventMessage;
import christmas.discount.FreeGift;

import java.util.List;

public class OutputView {
    private static final String MONEY_FORMAT = "%,d원\n";
    private static final String BENEFIT_FORMAT = "-%,d원\n";

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printMessageWithNumber(String message, int value) {
        System.out.printf(message, value);
    }

    public void printOrderMenu(MessageDTO orderMenus) {
        System.out.println(EventMessage.ORDER_MENU.getMessage());
        orderMenus.messages().forEach(System.out::println);
    }

    public void printTotalAmountBeforeDiscount(int totalAmount) {
        System.out.println(EventMessage.TOTAL_ORDER_MENU_BEFORE_DISCOUNT.getMessage());
        System.out.printf(MONEY_FORMAT, totalAmount);
    }

    public void printFreeGift(boolean status) {
        System.out.println(EventMessage.GIFT_MENU.getMessage());
        if (status) {
            System.out.println(FreeGift.CHAMPAGNE.getGift().getLabel() + " 1개");
            return;
        }
        System.out.println(EventMessage.NO_RESULT.getMessage());
    }

    public void printBenefitInformation(List<String> detail) {
        System.out.println(EventMessage.BENEFIT_DETAILS.getMessage());
        detail.forEach(System.out::print);
    }

    public void printNoBenefit() {
        System.out.println(EventMessage.BENEFIT_DETAILS.getMessage());
        System.out.println(EventMessage.NO_RESULT.getMessage());
        System.out.println(EventMessage.TOTAL_BENEFIT_AMOUNT.getMessage());
        System.out.println(EventMessage.NO_RESULT.getMessage());
    }

    public void printTotalBenefitAmount(int total) {
        System.out.println(EventMessage.TOTAL_BENEFIT_AMOUNT.getMessage());
        System.out.printf(BENEFIT_FORMAT, total);
    }
}
