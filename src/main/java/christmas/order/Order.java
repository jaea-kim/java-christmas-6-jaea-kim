package christmas.order;

import christmas.discount.CustomDay;
import christmas.discount.DateDTO;

import java.time.LocalDate;

public class Order {
    private final LocalDate orderDate;
    private final OrderMenus orderMenus;

    public Order(LocalDate orderDate, String[] orderMenus) throws IllegalArgumentException {
        this.orderDate = orderDate;
        this.orderMenus = new OrderMenus(orderMenus);
    }

    public int totalAmountBeforeDiscount() {
        return orderMenus.calculateTotalAmount();
    }

    public DateDTO getDate() {
        return new DateDTO(CustomDay.from(orderDate), orderDate.getDayOfMonth());
    }
}
