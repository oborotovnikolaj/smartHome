package ru.sbt.mipt.oop.smartHomeSupporters;

import ru.sbt.mipt.oop.EventProcessors.EventProcessorForSmartHome;
import ru.sbt.mipt.oop.action_executors.SmartHome;

public abstract class EventManager {
    protected SmartHome smartHome = null;

    public abstract void runEventsCycle();

    public abstract void addEventProcessor(EventProcessorForSmartHome eventProcessor);

    public abstract void deleteEventProcessor(EventProcessorForSmartHome eventProcessor);

    public void setSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
}