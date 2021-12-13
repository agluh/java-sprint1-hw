package praktikum.command;

import praktikum.app.InputInterface;
import praktikum.app.OutputInterface;

public interface Command {
    String getKey();

    String getDescription();

    Status execute(InputInterface input, OutputInterface output);

    enum Status {
        TERMINATE,
        CONTINUE
    }
}
