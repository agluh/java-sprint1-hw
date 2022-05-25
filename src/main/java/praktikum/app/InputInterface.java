package praktikum.app;

import java.io.InputStream;
import java.util.Scanner;

public class InputInterface {
    private final InputStream input;

    public InputInterface(InputStream input) {
        this.input = input;
    }

    public String ask() {
        Scanner scanner = new Scanner(input);
        return scanner.next();
    }
}
