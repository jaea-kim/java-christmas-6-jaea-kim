package christmas.domain;

import christmas.config.Delimiter;

public class OrderMenu {
    private final Menu menu;
    private final int quantity;

    public OrderMenu(String input) throws IllegalArgumentException {
        String[] orderMenu = input.split(Delimiter.MENU.getSymbol());
        this.menu = Menu.getMenuBy(orderMenu[0]);
        this.quantity = Integer.parseInt(orderMenu[1]);
    }

    private void validateMenu(String orderMenu) {

    }
}
