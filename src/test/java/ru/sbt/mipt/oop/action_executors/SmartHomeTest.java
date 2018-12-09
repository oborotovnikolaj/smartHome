package ru.sbt.mipt.oop.action_executors;

import org.junit.Test;
import ru.sbt.mipt.oop.action_executors.Alarm.Alarm;
import ru.sbt.mipt.oop.util;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SmartHomeTest {


    @Test//перемести в смарт хом
    public void setAlarm() throws IOException {
        SmartHome testSmartHome = util.loadSmartHome("src/test/resources/sh_doors_open_lights_off.json");
        Alarm alarm = new Alarm(1);
        testSmartHome.setAlarm(alarm);

        assertEquals("неверная первичная установка сигнализации",testSmartHome.getAlarm().getState(), alarm.getState());

        Alarm alarmForReset = new Alarm(2);
        testSmartHome.setAlarm(alarmForReset);

        assertEquals("неверный допуск к изменеию  сигнализации",testSmartHome.getAlarm().getState(), alarm.getState());
    }

}