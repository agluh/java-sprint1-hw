package praktikum.view;

public class ErrorView implements View {
    private final String message;

    public ErrorView(String message) {
        this.message = message;
    }

    @Override
    public String render() {
        return "ОШИБКА: " + message;
    }
}
