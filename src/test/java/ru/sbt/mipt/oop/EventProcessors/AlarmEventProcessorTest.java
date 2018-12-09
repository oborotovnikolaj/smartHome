package ru.sbt.mipt.oop.EventProcessors;

import org.junit.Test;
import ru.sbt.mipt.oop.events.sensors.SensorEvent;
import ru.sbt.mipt.oop.util;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AlarmEventProcessorTest extends InintEventProcessorTest {

    public AlarmEventProcessor alarmEventProcessor = new AlarmEventProcessor();

    @Test
    public void houseWithoutAlarm() throws IOException {
        masterSmartHome = util.loadSmartHome("src/test/resources/sh_doors_closed_lights_off.json");
        testSmartHome = util.loadSmartHome("src/test/resources/sh_doors_closed_lights_off.json");
        sensorEvents = util.loadEvents("src/test/resources/alarm_sensor_events.json");

        for (SensorEvent event : sensorEvents) {
            alarmEventProcessor.processEvent(testSmartHome, event);
        }
        assertEquals(testSmartHome, masterSmartHome);
    }

    @Test
    public void changeStateTest() throws IOException {
        testSmartHome = util.loadSmartHome( "src/test/resources/sh_doors_open_lights_off.json" );
        testSmartHome.setAlarm(alarm);

        alarmEventProcessor.processEvent(testSmartHome, alarmActiveWithWrongKey);
        assertEquals("Состояние не должно переходить их неактивного в активное из-за события с неверным кодом",
                testSmartHome.getAlarm().getState(), alarmInactive.getState());

        alarmEventProcessor.processEvent(testSmartHome, activateAlert);
        assertEquals("небыло переведено состояние из неактивного в состояние активной защиты",
                testSmartHome.getAlarm().getState(), alarmActive.getState());

        alarmEventProcessor.processEvent(testSmartHome, inactivateAlert);
        assertEquals("небыло переведено состояние из активного в состояние неактивное",
                testSmartHome.getAlarm().getState(), alarmInactive.getState());

        alarmEventProcessor.processEvent(testSmartHome, activateAlert);
        alarmEventProcessor.processEvent(testSmartHome, alarmInactiveWithWrongKey);
        assertEquals("небыло переведено состояние из активного в состояние тревоги при попытки дезактивации по неверному коду",
                testSmartHome.getAlarm().getState(), alarmTriggered.getState());
    }
}

