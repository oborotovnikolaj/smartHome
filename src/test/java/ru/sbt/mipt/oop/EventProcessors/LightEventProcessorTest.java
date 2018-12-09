package ru.sbt.mipt.oop.EventProcessors;

import org.junit.Test;
import ru.sbt.mipt.oop.events.sensors.SensorEvent;
import ru.sbt.mipt.oop.util;

import java.io.IOException;

import static org.junit.Assert.*;

public class LightEventProcessorTest extends InintEventProcessorTest {
    public LightEventProcessor lightEventProcessor = new LightEventProcessor();
    @Test
    //повторное откючение ламп ничего не меняет
    public void UnchangingEvent() throws IOException {
        masterSmartHome = util.loadSmartHome("src/test/resources/sh_doors_open_lights_off.json");
        testSmartHome = util.loadSmartHome("src/test/resources/sh_doors_open_lights_off.json");
        sensorEvents = util.loadEvents("src/test/resources/unchanging_sensor_events.json");
        for (SensorEvent event : sensorEvents) {
            lightEventProcessor.processEvent(testSmartHome, event);
        }
        assertEquals(testSmartHome, masterSmartHome);
    }

    @Test
    //включение ламп меняет дом
    public void ChangingEvent() throws IOException {
        masterSmartHome = util.loadSmartHome("src/test/resources/sh_doors_open_lights_on.json");
        testSmartHome = util.loadSmartHome("src/test/resources/sh_doors_open_lights_off.json");
        sensorEvents = util.loadEvents("src/test/resources/lights_changing_sensor_events.json");
        for (SensorEvent event : sensorEvents) {
            lightEventProcessor.processEvent(testSmartHome, event);
        }
        assertEquals(testSmartHome, masterSmartHome);
    }

    @Test
    public void IgnoreAntherProcessors() throws IOException{}
}