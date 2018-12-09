package ru.sbt.mipt.oop.adapters.CoolCompanyLib;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.EventProcessors.EventProcessorForSmartHome;
import ru.sbt.mipt.oop.events.sensors.SensorEvent;
import ru.sbt.mipt.oop.action_executors.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class EventAdaptedProcessor implements EventHandler {
    SmartHome smartHome;
    EventProcessorForSmartHome eventProcessor;

    public EventAdaptedProcessor(SmartHome smartHome, EventProcessorForSmartHome eventProcessor) {
        this.smartHome = smartHome;
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = changeCCEventToSbtEvent(event);
        if (sensorEvent == null) {
            System.out.println("there are not appropriate EventProcessor to process gotten event");
        } else {
            System.out.println("Gotten event: " + sensorEvent);
            eventProcessor.processEvent(this.smartHome, sensorEvent);
        }
    }
    private SensorEvent changeCCEventToSbtEvent(CCSensorEvent event) {
        if (event.getEventType().equals("LightIsOn")) return new SensorEvent(LIGHT_ON, event.getObjectId());
        if (event.getEventType().equals("LightIsOff")) return new SensorEvent(LIGHT_OFF, event.getObjectId());
        if (event.getEventType().equals("DoorIsOpen")) return new SensorEvent(DOOR_OPEN, event.getObjectId());
        if (event.getEventType().equals("DoorIsClosed")) return new SensorEvent(DOOR_CLOSED, event.getObjectId());
        else return null;
    }
}
