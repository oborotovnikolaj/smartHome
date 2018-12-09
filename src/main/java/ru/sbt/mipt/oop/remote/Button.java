package ru.sbt.mipt.oop.remote;

import ru.sbt.mipt.oop.commands.Command;
import ru.sbt.mipt.oop.commands.EmptyCommand;

public class Button {
    private final String iD;
    private Command command;

    public Button(String iD) {
        this.iD = iD;
        this.command = new EmptyCommand();
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void onPressed() {
        command.execute();
    }
}
