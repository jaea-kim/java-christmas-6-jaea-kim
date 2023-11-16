package christmas.order;

import christmas.config.Delimiter;
import christmas.config.ErrorMessage;
import christmas.config.EventConstants;

import java.util.Objects;

public class OrderMenu {
    private final Menu menu;
    private final int quantity;

    public OrderMenu(String input) {
        String[] orderMenu = parseInput(input);
        this.menu = Menu.getMenuByName(orderMenu[0]);
        this.quantity = parseQuantity(orderMenu[1]);
    }

    private String[] parseInput(String input) {
        return input.split(Delimiter.MENU.getSymbol());
    }

    private int parseQuantity(String inputQuantity) {
        int quantity = Integer.parseInt(inputQuantity);
        validateQuantity(quantity);
        return quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity < EventConstants.MIN_QUANTITY.getValue()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    public boolean isBeverage() {
        return menu.hasMenuType(MenuType.BEVERAGE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderMenu orderMenu = (OrderMenu) o;
        return menu == orderMenu.menu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAmount() {
        return menu.getPrice() * quantity;
    }

    public boolean hasMenuType(MenuType menuType) {
        return menu.hasMenuType(menuType);
    }

    public String getInformation() {
        return menu.getLabel() + " " + quantity + "개";
    }
}
