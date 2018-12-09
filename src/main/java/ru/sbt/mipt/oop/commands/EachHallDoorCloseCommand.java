package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.actions.DoorsCloseAction;
import ru.sbt.mipt.oop.actions.LightsOffAction;
import ru.sbt.mipt.oop.action_executors.Room;
import ru.sbt.mipt.oop.action_executors.SmartHome;

public class EachHallDoorCloseCommand implements Command {
    private SmartHome smartHome;
    private CommandType type = CommandType.CLOSE_HALL_DOOR;

    public EachHallDoorCloseCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public boolean execute() {
        smartHome.executeAction(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    room.executeAction(new DoorsCloseAction());
                }
            }
        });
        smartHome.executeAction(new LightsOffAction());
        return true;
    }

    public CommandType getType() {
        return type;
    }
}
