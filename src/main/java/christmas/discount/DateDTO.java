package christmas.discount;

public record DateDTO(CustomDay day, int date) {
    public DateDTO(CustomDay day, int date) {
        this.day = day;
        this.date = date;
    }
}
