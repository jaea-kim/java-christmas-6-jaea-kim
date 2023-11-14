package christmas.domain;

import christmas.config.ErrorMessage;
import christmas.config.EventConstants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderMenus {
    private final List<OrderMenu> orderMenus;

    public OrderMenus(String[] orderMenus) throws IllegalArgumentException {
        List<OrderMenu> orderMenu = createOrderMenuWith(orderMenus);
        validateDuplicate(orderMenu);
        validateMenuType(orderMenu);
        validateMenuQuantity(orderMenu);
        this.orderMenus = orderMenu;
    }

    private List<OrderMenu> createOrderMenuWith(String[] orderMenus) throws IllegalArgumentException {
        return Arrays.stream(orderMenus)
                .map(OrderMenu::new)
                .collect(Collectors.toList());
    }

    private void validateDuplicate(List<OrderMenu> orderMenu) {
        Set<OrderMenu> uniqueOrderMenus = new HashSet<>(orderMenu);
        if (uniqueOrderMenus.size() != orderMenu.size()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }


    private void validateMenuType(List<OrderMenu> orderMenu) {
        if (!orderMenu.stream().allMatch(OrderMenu::isBeverage)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private void validateMenuQuantity(List<OrderMenu> orderMenu) {
        if (calculateTotalQuantity(orderMenu) > EventConstants.MAX_ALL_QUANTITY.getValue()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private int calculateTotalQuantity(List<OrderMenu> orderMenu) {
        return orderMenu.stream().mapToInt(OrderMenu::getQuantity).sum();
    }
}
