package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.action_executors.ActionExecutor;
import ru.sbt.mipt.oop.events.sensors.SensorEvent;

public interface EventProcessorCommon {
    void processEvent(ActionExecutor actionExecutor, SensorEvent event);
}
