package christmas.order;

import christmas.config.ErrorMessage;
import christmas.config.EventConstants;
import christmas.view.MessageDTO;

import java.util.*;
import java.util.stream.Collectors;

public class OrderMenus {
    private final List<OrderMenu> orderMenus;

    public OrderMenus(String[] orderMenus) {
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
