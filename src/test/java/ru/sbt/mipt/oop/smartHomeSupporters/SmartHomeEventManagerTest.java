package ru.sbt.mipt.oop.smartHomeSupporters;

import org.junit.Test;
import ru.sbt.mipt.oop.EventProcessors.*;
import ru.sbt.mipt.oop.action_executors.SmartHome;
import ru.sbt.mipt.oop.events.providers.RandomSensorEventProvider;
import ru.sbt.mipt.oop.loaders_smart_home.FileSmartHomeLoader;
import ru.sbt.mipt.oop.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class SmartHomeEventManagerTest {

    private FileSmartHomeLoader fileSmartHomeLoader = new FileSmartHomeLoader("src/test/resources/sh_doors_open_lights_off.json");
    private SmartHome smartHome;
    private RandomSensorEventProvider randomSensorEventProvider = new RandomSensorEventProvider();
    private SmartHomeEventManager smartHomeEventManager = new SmartHomeEventManager (randomSensorEventProvider );

    @Test
    public void addEventProcessor() throws IOException {
//        SensorEvent[] masterEvents = new SensorEvent[3];
//        masterEvents = util.loadEvents("src/test/resources/unchanging_sensor_events.json");
//        masterEvents2 = new ArrayList<>()
        smartHome = util.loadSmartHome("src/test/resources/sh_doors_open_lights_off.json");
        smartHomeEventManager.addEventProcessor(new TriggerAlarmEventProcessor(new LightEventProcessor()));
        smartHomeEventManager.addEventProcessor(new TriggerAlarmEventProcessor(new DoorEventProcessor()));
        smartHomeEventManager.addEventProcessor(new TriggerAlarmEventProcessor(new HallDoorEventProcessor()));
        smartHomeEventManager.addEventProcessor(new AlarmEventProcessor());
        Collection<EventProcessorForSmartHome> MasterEventProcessors = new ArrayList<EventProcessorForSmartHome>();
        //util.buildJson(smartHomeEventManager.getEventProcessors(), "processors");
        //util.loadProcessors()
    }

    @Test
    public void runEventsCycle() {
    }

    @Test
    public void deleteEventProcessor() {
    }
}