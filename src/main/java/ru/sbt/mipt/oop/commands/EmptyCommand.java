package ru.sbt.mipt.oop.commands;

public class EmptyCommand implements Command {
    @Override
    public boolean execute() {
        System.out.println("nothing done");
        return true;
    }
    private CommandType type = CommandType.EMPTY;

    public CommandType getType() {
        return type;
    }
}
