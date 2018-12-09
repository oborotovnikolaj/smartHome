package ru.sbt.mipt.oop.remote;

import org.junit.Test;
import ru.sbt.mipt.oop.action_executors.Alarm.ActiveState;
import ru.sbt.mipt.oop.action_executors.Alarm.Alarm;
import ru.sbt.mipt.oop.action_executors.Alarm.InactiveState;
import ru.sbt.mipt.oop.action_executors.SmartHome;
import ru.sbt.mipt.oop.util;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RemoteControllerTest {
    private RemoteController testRemoteController = new RemoteController(1);
    private SmartHome testSmartHome;
    private SmartHome masterSmartHome;

    @Test
    public void onButtonPressed() throws IOException {

//        {closeHallDoorCommand, onHallLights,onHomeLights,
//                offHomeLights,ActiveAlarmCommand, triggerAlarmCommand};

        testSmartHome = util.loadSmartHome("src/test/resources/sh_doors_open_lights_on.json");
        testRemoteController.bindRemoteToHome(testSmartHome);

        testRemoteController.onButtonPressed("1");
        assertEquals(testSmartHome, util.loadSmartHome("src/test/resources/sh_hall_door_closed_lights_off.json"));

        testRemoteController.onButtonPressed("2");
        assertEquals(testSmartHome, util.loadSmartHome("src/test/resources/sh_hall_door_closed_lights_hall_on.json"));

        testRemoteController.onButtonPressed("3");
        assertEquals(testSmartHome, util.loadSmartHome("src/test/resources/sh_hall_door_closed_lights_on.json"));

        testRemoteController.onButtonPressed("4");
        assertEquals(testSmartHome, util.loadSmartHome("src/test/resources/sh_hall_door_closed_lights_off.json"));


        testRemoteController.onButtonPressed("A");
        assertEquals(testSmartHome, util.loadSmartHome("src/test/resources/sh_hall_door_closed_lights_off.json"));

        Alarm alarm = new Alarm(1, new InactiveState());
        testSmartHome.setAlarm(alarm);
        testRemoteController.onButtonPressed("A");
        masterSmartHome = util.loadSmartHome("src/test/resources/sh_hall_door_closed_lights_off.json");
        Alarm activeAlarm = new Alarm(1, new ActiveState());
        masterSmartHome.setAlarm(activeAlarm);
        assertEquals(testSmartHome.getAlarm().getState(), masterSmartHome.getAlarm().getState());

        testRemoteController.onButtonPressed("B");
        masterSmartHome.getAlarm().triggerAlarm();
        assertEquals(testSmartHome, masterSmartHome);
        assertEquals(testSmartHome.getAlarm().getState(), masterSmartHome.getAlarm().getState());

        testRemoteController.onButtonPressed("C");
        testRemoteController.onButtonPressed("D");
        assertEquals(testSmartHome, masterSmartHome);
        assertEquals(testSmartHome.getAlarm().getState(), masterSmartHome.getAlarm().getState());



    }
}