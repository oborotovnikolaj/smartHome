package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.action_executors.Room;
import ru.sbt.mipt.oop.action_executors.SmartHome;
import ru.sbt.mipt.oop.actions.LightsOnAction;

public class EachHallLightsOnCommand implements Command {
    private SmartHome smartHome;
    private CommandType type = CommandType.ON_HALL_LIGHTS;

    public EachHallLightsOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public boolean execute() {
        smartHome.executeAction(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    room.executeAction(new LightsOnAction());
                }
            }
        });
        return false;
    }

    public CommandType getType() {
        return type;
    }
}
