package ru.sbt.mipt.oop.EventProcessors;

import org.junit.Test;
import ru.sbt.mipt.oop.events.sensors.SensorEvent;
import ru.sbt.mipt.oop.util;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class DoorEventProcessorTest extends InintEventProcessorTest {
    public DoorEventProcessor doorEventProcessor = new DoorEventProcessor();

    @Test
    //повторное откырие двери не меняет ее состояния
    public void UnchangingEvent() throws IOException {
        masterSmartHome = util.loadSmartHome("src/test/resources/sh_doors_open_lights_off.json");
        testSmartHome = util.loadSmartHome("src/test/resources/sh_doors_open_lights_off.json");
        sensorEvents = util.loadEvents("src/test/resources/unchanging_sensor_events.json");
        for (SensorEvent event : sensorEvents) {
            doorEventProcessor.processEvent(testSmartHome, event);
        }
        assertEquals(testSmartHome, masterSmartHome);
    }

    @Test
    //повторное откырие двери не меняет ее состояния
    public void ChangingEvent() throws IOException {
        masterSmartHome = util.loadSmartHome("src/test/resources/sh_doors_closed_lights_off.json");
        testSmartHome = util.loadSmartHome("src/test/resources/sh_doors_open_lights_off.json");
        sensorEvents = util.loadEvents("src/test/resources/doors_changing_sensor_events.json");
        for (SensorEvent event : sensorEvents) {
            doorEventProcessor.processEvent(testSmartHome, event);
        }
        assertEquals(testSmartHome, masterSmartHome);
    }

    @Test
    public void IgnoreAntherProcessors() throws IOException{}
}
