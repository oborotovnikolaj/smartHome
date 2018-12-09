package ru.sbt.mipt.oop.action_executors.Alarm;

import org.junit.Test;
import ru.sbt.mipt.oop.EventProcessors.InintEventProcessorTest;
import ru.sbt.mipt.oop.util;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AlarmTest extends InintEventProcessorTest {

    @Test
    public void checkAlarmConstructors() throws IOException {
        testSmartHome = util.loadSmartHome("src/test/resources/sh_doors_open_lights_off.json");
        sensorEvents = util.loadEvents("src/test/resources/alarm_sensor_events.json");
        Alarm alarmNew = new Alarm(1);
        Alarm alarmMaster = new Alarm(1, new InactiveState());

        assertEquals("неверно работает конструкоры Alarm", alarmNew.getState(), alarmMaster.getState()); // хорошо бы по одному проверить
    }

    @Test
    public void triggerAlarmTest() throws IOException {
        testSmartHome = util.loadSmartHome( "src/test/resources/sh_doors_open_lights_off.json" );
        testSmartHome.setAlarm(alarmInactive);
        testSmartHome.getAlarm().triggerAlarm();
        assertEquals("не было переведено состояние из неактивного в состояние тревоги",
                testSmartHome.getAlarm().getState(), alarmTriggered.getState());
    }

    @Test
    public void inactivateAlarmTest() throws IOException {
        testSmartHome = util.loadSmartHome( "src/test/resources/sh_doors_open_lights_off.json" );
        testSmartHome.setAlarm(alarmActive);
        testSmartHome.getAlarm().inactivateAlarm(1);
        assertEquals("не было переведено состояние из активного в неактивное",
                testSmartHome.getAlarm().getState(), alarmInactive.getState());

        testSmartHome.getAlarm().activateAlarm(1);
        testSmartHome.getAlarm().inactivateAlarm(2);
        assertEquals("Дезактивация с неверным годом должна тригерить сигналку",
                testSmartHome.getAlarm().getState(), alarmTriggered.getState());
    }

    @Test
    public void activateAlarmTest() throws IOException {
        testSmartHome = util.loadSmartHome( "src/test/resources/sh_doors_open_lights_off.json" );
        testSmartHome.setAlarm(alarmInactive);
        testSmartHome.getAlarm().activateAlarm(2);
        assertEquals("без верного ключа нельзя активировать сигналку",
                testSmartHome.getAlarm().getState(), alarmInactive.getState());

        testSmartHome.getAlarm().activateAlarm(1);
        assertEquals("не было переведено состояние из неактивного в состояние тревоги",
                testSmartHome.getAlarm().getState(), alarmActive.getState());
    }

}