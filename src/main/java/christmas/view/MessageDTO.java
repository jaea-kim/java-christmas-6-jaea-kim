package christmas.view;

import java.util.List;

public record MessageDTO(List<String> messages) {
    public MessageDTO(List<String> messages) {
        this.messages = messages;
    }
}
