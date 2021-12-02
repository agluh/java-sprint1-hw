package praktikum.view;

public class InvalidCommandView implements View {
    private final static String INVALID_COMMAND = "Извините, такой команды нет.";

    @Override
    public String render() {
        return INVALID_COMMAND;
    }
}
