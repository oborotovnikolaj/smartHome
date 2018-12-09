package ru.sbt.mipt.oop.EventProcessors;

import org.junit.Test;
import ru.sbt.mipt.oop.events.sensors.SensorEvent;
import ru.sbt.mipt.oop.util;

import java.io.IOException;

import static org.junit.Assert.*;

public class HallDoorEventProcessorTest extends InintEventProcessorTest {
    public HallDoorEventProcessor hallDoorEventProcessor = new HallDoorEventProcessor();
    @Test
    //повторное открытие входной двери ничего не меняет
    public void UnchangingEvent() throws IOException {
        masterSmartHome = util.loadSmartHome("src/test/resources/sh_doors_open_lights_off.json");
        testSmartHome = util.loadSmartHome("src/test/resources/sh_doors_open_lights_off.json");
        sensorEvents = util.loadEvents("src/test/resources/unchanging_sensor_events.json");
        for (SensorEvent event : sensorEvents) {
            hallDoorEventProcessor.processEvent(testSmartHome, event);
        }
        assertEquals(testSmartHome, masterSmartHome);
    }

    @Test
    //закрытие входной двери выключает свет
    public void ChangingEvent() throws IOException {
        masterSmartHome = util.loadSmartHome("src/test/resources/sh_hall_door_closed_lights_off.json");
        testSmartHome = util.loadSmartHome("src/test/resources/sh_doors_open_lights_on.json");
        sensorEvents = util.loadEvents("src/test/resources/doors_changing_sensor_events.json");
        for (SensorEvent event : sensorEvents) {
            hallDoorEventProcessor.processEvent(testSmartHome, event);
        }
        assertEquals(testSmartHome, masterSmartHome);
    }

    @Test
    public void IgnoreAntherProcessors() throws IOException{}

}