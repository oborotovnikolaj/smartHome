package ru.sbt.mipt.oop.events.providers;

import ru.sbt.mipt.oop.events.sensors.SensorEvent;

public interface SensorEventProvider {
    SensorEvent getNextSensorEvent();
}
