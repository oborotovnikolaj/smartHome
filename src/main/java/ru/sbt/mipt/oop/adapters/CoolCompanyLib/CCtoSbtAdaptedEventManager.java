package ru.sbt.mipt.oop.adapters.CoolCompanyLib;

import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.EventProcessors.EventProcessorForSmartHome;
import ru.sbt.mipt.oop.smartHomeSupporters.EventManager;

public class CCtoSbtAdaptedEventManager extends EventManager {
    private SensorEventsManager sensorEventsManager;

    public CCtoSbtAdaptedEventManager(SensorEventsManager sensorEventsManager) {
        this.sensorEventsManager = sensorEventsManager;
    }

    @Override
    public void runEventsCycle() {
        sensorEventsManager.start();
    }

    @Override
    public void addEventProcessor(EventProcessorForSmartHome eventProcessorForSmartHome) {
        EventHandler eventAdaptedProcessor = new EventAdaptedProcessor(this.smartHome, eventProcessorForSmartHome);
        sensorEventsManager.registerEventHandler(eventAdaptedProcessor);
    }

    @Override
    public void deleteEventProcessor(EventProcessorForSmartHome eventProcessor) {
        System.out.println("CCEventManager does not support delete operation of event processors.");
    }
}
