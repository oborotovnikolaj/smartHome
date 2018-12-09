package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.actions.LightsOffAction;
import ru.sbt.mipt.oop.action_executors.SmartHome;

public class EachHomeLightsOffCommand implements Command {
    private SmartHome smartHome;
    private CommandType type = CommandType.OFF_HOME_LIGHTS;

    public EachHomeLightsOffCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    // TODO: 29.11.2018 сделай чтобы можно было в одной комнате выкл свет 
    @Override
    public boolean execute() {
        smartHome.executeAction(new LightsOffAction());
        return true;
    }

    public CommandType getType() {
        return type;
    }
}
