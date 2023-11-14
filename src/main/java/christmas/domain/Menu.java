package christmas.domain;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", MenuType.STARTER, 6000),
    TAPAS("타파스", MenuType.STARTER, 5500),
    CAESAR_SALAD("시저샐러드", MenuType.STARTER, 8000),
    T_BONE_STEAK("티본스테이크", MenuType.MAIN_COURSE, 55000),
    BBQ_RIBS("바비큐립", MenuType.MAIN_COURSE, 54000),
    SEAFOOD_PASTA("해산물파스타", MenuType.MAIN_COURSE, 35000),
    CHRISTMAS_PASTA("크리스마스파스타", MenuType.MAIN_COURSE, 25000),
    CHOCOLATE_CAKE("초코케이크", MenuType.DESSERT, 15000),
    ICE_CREAM("아이스크림", MenuType.DESSERT, 5000),
    ZERO_COLA("제로콜라", MenuType.BEVERAGE, 3000),
    RED_WINE("레드와인", MenuType.BEVERAGE, 60000),
    CHAMPAGNE("샴페인", MenuType.BEVERAGE, 25000);

    private final String name;
    private final MenuType menuType;
    private final int price;

    Menu(String name, MenuType menuType, int price) {
        this.name = name;
        this.menuType = menuType;
        this.price = price;
    }
}