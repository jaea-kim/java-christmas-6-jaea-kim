package christmas.order;

import christmas.config.ErrorMessage;
import christmas.config.EventConstants;
import christmas.view.MessageDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderMenus {
    private final List<OrderMenu> orderMenus;

    public OrderMenus(List<OrderMenu> orderMenus) {
        validateDuplicate(orderMenus);
        validateMenuType(orderMenus);
        validateMenuQuantity(orderMenus);
        this.orderMenus = orderMenus;
    }

    private void validateDuplicate(List<OrderMenu> orderMenu) {
        Set<OrderMenu> uniqueOrderMenus = new HashSet<>(orderMenu);
        if (uniqueOrderMenus.size() != orderMenu.size()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }


    private void validateMenuType(List<OrderMenu> orderMenu) {
        if (orderMenu.stream().allMatch(OrderMenu::isBeverage)) {
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

    public int calculateTotalAmount() {
        int totalAmount = 0;
        for (OrderMenu menu : orderMenus) {
            totalAmount += menu.getAmount();
        }
        return totalAmount;
    }

    public int getMenuCount(MenuType menuType) {
        return orderMenus.stream().filter(m -> m.hasMenuType(menuType))
                .mapToInt(OrderMenu::getQuantity).sum();
    }

    public MessageDTO getOrderMenu() {
        ArrayList<String> orderMenuInfo = new ArrayList<>();
        for (OrderMenu menu : orderMenus) {
            orderMenuInfo.add(menu.getInformation());
        }
        return new MessageDTO(orderMenuInfo);
    }
}
