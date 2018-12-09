package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.actions.LightsOnAction;
import ru.sbt.mipt.oop.action_executors.SmartHome;

public class EachHomeLightsOnCommand implements Command {
    private SmartHome smartHome;
    private CommandType type = CommandType.ON_HOME_LIGHTS;

    public EachHomeLightsOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public boolean execute() {
        smartHome.executeAction(new LightsOnAction());
        return true;
    }

    public CommandType getType() {
        return type;
    }
}
