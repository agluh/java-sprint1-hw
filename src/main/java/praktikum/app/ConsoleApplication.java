package praktikum.app;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import praktikum.command.Command;
import praktikum.command.Command.Status;

public class ConsoleApplication {
    private final InputInterface input;
    private final OutputInterface output;
    private final Map<String, Command> commands;

    public ConsoleApplication(InputStream input, PrintStream output) {
        this.input = new InputInterface(input);
        this.output = new OutputInterface(output);
        commands = new LinkedHashMap<>();
    }

    public void registerCommand(Command command) {
        commands.put(command.getKey(), command);
    }

    public void run() {
        Status status = Status.CONTINUE;

        while (status == Status.CONTINUE) {
            output.println("Пожалуйста, выберите действие:");
            output.println(formatMenu());

            String command = input.ask();
            if (commands.containsKey(command)) {
                status = commands.get(command).execute(input, output);
            } else {
                output.error("Нет такой команды!");
            }
        }
    }

    private String formatMenu() {
        return commands.values().stream()
            .map(e -> e.getKey() + " - " + e.getDescription())
            .collect(Collectors.joining(System.getProperty("line.separator")));
    }
}
