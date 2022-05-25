package praktikum.app;

import java.io.PrintStream;

public class OutputInterface {
    private final PrintStream output;

    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    public static final String BLUE = "\033[0;34m";

    public OutputInterface(PrintStream output) {
        this.output = output;
    }

    public void println(String value) {
        output.println(value);
    }

    public void success(String value) {
        output.print(BLUE);
        output.print(value);
        output.print(RESET);
        output.println();
    }

    public void error(String value) {
        output.print(RED);
        output.print(value);
        output.print(RESET);
        output.println();
    }
}
