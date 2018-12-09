package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.events.sensors.SensorEvent;
import ru.sbt.mipt.oop.action_executors.Door;
import ru.sbt.mipt.oop.action_executors.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessorForSmartHome {
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (isDoorEvent(event)) {
            smartHome.executeAction(object -> {
                if (object instanceof Door) {
                    Door door = (Door) object;
                    if (event.getObjectId().equals(door.getId())) {
                        if (event.getType() == DOOR_OPEN) {
                            door.setOpen(true);
                        } else {
                            door.setOpen(false);
                        }
                    }
                }
            });
        }
    }


    private boolean isDoorEvent(SensorEvent event) {
        return (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED);
    }
}
