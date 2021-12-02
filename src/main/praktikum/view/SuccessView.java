package praktikum.view;

public class SuccessView implements View {
    private final String message;

    public SuccessView(String message) {
        this.message = message;
    }

    @Override
    public String render() {
        return message;
    }
}
