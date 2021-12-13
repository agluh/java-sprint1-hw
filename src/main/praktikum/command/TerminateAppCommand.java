package praktikum.command;

import praktikum.app.InputInterface;
import praktikum.app.OutputInterface;

public class TerminateAppCommand implements Command {
    private static final String KEY = "0";
    private static final String DESCRIPTION = "Выйти из приложения";

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public Status execute(InputInterface input, OutputInterface output) {
        output.success("До свидания!");
        return Status.TERMINATE;
    }
}
