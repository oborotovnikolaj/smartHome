package ru.sbt.mipt.oop.commands;

import org.junit.Test;
import ru.sbt.mipt.oop.action_executors.Alarm.ActiveState;
import ru.sbt.mipt.oop.action_executors.Alarm.Alarm;
import ru.sbt.mipt.oop.action_executors.Alarm.InactiveState;
import ru.sbt.mipt.oop.util;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ActivateAlarmCommandTest extends InitCommandTest {
    protected Alarm alarmInactive = new Alarm(1, new InactiveState());
    Alarm alarmActive = new Alarm(1, new ActiveState());
    @Test
    public void execute() throws IOException {
        testSmartHome = util.loadSmartHome("src/test/resources/sh_doors_closed_lights_off.json");
        //либо здесь либо хом сразу заполни
        Command ActivateCommand = new ActivateAlarmCommand(testSmartHome, 1);
        Command ActivateCommandWithWrongKey = new ActivateAlarmCommand(testSmartHome, 2);

        assertFalse("Должно быть невохможно изменить состояние сигнализации, если ее нет",
                     ActivateCommand.execute());

        testSmartHome.setAlarm(alarmInactive);
        ActivateCommandWithWrongKey.execute();
        assertEquals("Попытка активации с неверным кодом",
                testSmartHome.getAlarm().getState(), alarmInactive.getState() );


        assertTrue("Должна выполниться команда", ActivateCommand.execute());

        assertEquals("Команда должна корректно выполниться изменив состояние",
                testSmartHome.getAlarm().getState(), alarmActive.getState() );
    }
}