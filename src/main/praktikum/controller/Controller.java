package praktikum.controller;

import praktikum.command.Command;
import praktikum.view.View;

public interface Controller {
    View process(Command command);
}
