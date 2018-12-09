package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.events.sensors.SensorEvent;
import ru.sbt.mipt.oop.action_executors.Light;
import ru.sbt.mipt.oop.action_executors.SmartHome;
import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class LightEventProcessor implements EventProcessorForSmartHome {
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if ( isLightEvent(event)) {
            smartHome.executeAction( object -> {
                if ( object instanceof Light) {
                    Light light = (Light) object;
                    if (event.getObjectId().equals(light.getId())) {
                        if ( event.getType() == LIGHT_OFF) {
                            light.setOn(false);
                        }
                        else {
                            light.setOn(true);
                        }
                    }
                }
            });
        }
    }

    private boolean isLightEvent(SensorEvent event) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            return true;
        } else {
            return false;
        }
    }
}
