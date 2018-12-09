package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.action_executors.SmartHome;
import ru.sbt.mipt.oop.events.sensors.SensorEvent;

public interface EventProcessorForSmartHome  {
    void processEvent(SmartHome smartHome, SensorEvent event);
}
