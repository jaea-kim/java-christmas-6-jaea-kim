package christmas.domain;

import java.time.LocalDate;
import java.util.Locale;

public class Order {
    private final LocalDate orderDate;
    private final OrderMenus orderMenus;

    public Order(LocalDate orderDate, String[] orderMenus) throws IllegalArgumentException {
        this.orderDate = orderDate;
        this.orderMenus = new OrderMenus(orderMenus);
    }
}
