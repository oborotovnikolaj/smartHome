package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.events.sensors.SensorEvent;
import ru.sbt.mipt.oop.action_executors.SmartHome;
import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class TriggerAlarmEventProcessor implements EventProcessorForSmartHome {
    private EventProcessorForSmartHome wrappedEventProcessor;

    // TODO: 29.11.2018 можно здесь отсылать смс
    public TriggerAlarmEventProcessor(EventProcessorForSmartHome wrappedEventProcessor) {
        this.wrappedEventProcessor = wrappedEventProcessor;
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (isDoorOrLightEvent(event)) {
            if (!smartHome.getAlarm().isInactiveAlarm()) {
                smartHome.getAlarm().getState().triggerAlarm();
            } else {
                wrappedEventProcessor.processEvent(smartHome, event);
            }
        }
    }

    private boolean isDoorOrLightEvent(SensorEvent event) {
        if (event.getType() == LIGHT_ON ||
                event.getType() == LIGHT_OFF ||
                event.getType() == DOOR_OPEN ||
                event.getType() == DOOR_CLOSED) {
            return true;
        } else {
            return false;
        }
    }
}

