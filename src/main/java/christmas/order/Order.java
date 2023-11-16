package christmas.order;

import christmas.discount.CustomDay;
import christmas.discount.DateDTO;
import christmas.view.MessageDTO;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Order {
    private final LocalDate orderDate;
    private final OrderMenus orderMenus;

    public Order(LocalDate orderDate, String[] orderMenuInput) throws IllegalArgumentException {
        this.orderDate = orderDate;
        List<OrderMenu> orderMenus = Arrays.stream(orderMenuInput).map(OrderMenu::new).toList();
        this.orderMenus = new OrderMenus(orderMenus);
    }

    public int totalAmountBeforeDiscount() {
        return orderMenus.calculateTotalAmount();
    }

    public DateDTO getDate() {
        return new DateDTO(CustomDay.from(orderDate), orderDate.getDayOfMonth());
    }

    public int getMenuCount(MenuType menuType) {
        return orderMenus.getMenuCount(menuType);
    }

    public MessageDTO getOrderMenus() {
        return orderMenus.getOrderMenu();
    }
}
