package christmas.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMenus {
    private final List<OrderMenu> orderMenus;

    public OrderMenus(String[] orderMenus) throws IllegalArgumentException {
        List<OrderMenu> orderMenu = createOrderMenuWith(orderMenus);

        this.orderMenus = orderMenu;
    }

    private List<OrderMenu> createOrderMenuWith(String[] orderMenus) throws IllegalArgumentException {
        return Arrays.stream(orderMenus)
                .map(OrderMenu::new)
                .collect(Collectors.toList());
    }
}
