package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.events.sensors.SensorEvent;
import ru.sbt.mipt.oop.action_executors.Door;
import ru.sbt.mipt.oop.action_executors.Light;
import ru.sbt.mipt.oop.action_executors.Room;
import ru.sbt.mipt.oop.action_executors.SmartHome;
import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class HallDoorEventProcessor implements EventProcessorForSmartHome {
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if ( event.getType() == DOOR_CLOSED) {
            for ( Room room : smartHome.getRooms() ) {
                for ( Door door : room.getDoors() ) {
                    if (door.getId().equals(event.getObjectId()) && room.getName().equals("hall")) {
                        offAllLights(smartHome);
                        door.setOpen(false);
                        }
                    }
                }
            }
        }

    private void offAllLights(SmartHome smartHome) {
        smartHome.executeAction(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setOn(false);
            }
        });
    }
}
