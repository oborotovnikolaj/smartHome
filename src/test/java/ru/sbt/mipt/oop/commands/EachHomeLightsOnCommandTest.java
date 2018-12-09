package ru.sbt.mipt.oop.commands;

import org.junit.Test;
import ru.sbt.mipt.oop.action_executors.Alarm.ActiveState;
import ru.sbt.mipt.oop.action_executors.Alarm.Alarm;
import ru.sbt.mipt.oop.action_executors.Alarm.InactiveState;
import ru.sbt.mipt.oop.action_executors.Alarm.TriggeringState;
import ru.sbt.mipt.oop.util;

import java.io.IOException;

import static org.junit.Assert.*;

public class EachHomeLightsOnCommandTest extends InitCommandTest {
    protected Alarm alarmInactive = new Alarm(1, new InactiveState());
    Alarm alarmActive = new Alarm(1, new ActiveState());
    protected Alarm alarmTriggered = new Alarm(1, new TriggeringState());
    @Test
    public void execute () throws IOException {
        testSmartHome = util.loadSmartHome("src/test/resources/sh_doors_open_lights_off.json");
        masterSmartHome = util.loadSmartHome("src/test/resources/sh_doors_open_lights_on.json");
        //либо здесь либо хом сразу заполни
        Command eachHomeLightsOnCommand = new EachHomeLightsOnCommand (testSmartHome);

        testSmartHome.setAlarm(alarmInactive);
        eachHomeLightsOnCommand.execute();
        assertEquals("Дом должен измениться", testSmartHome, masterSmartHome);

        testSmartHome = util.loadSmartHome("src/test/resources/sh_doors_open_lights_off.json");
        testSmartHome.setAlarm(alarmTriggered);
        eachHomeLightsOnCommand = new EachHomeLightsOnCommand (testSmartHome);
        eachHomeLightsOnCommand.execute();
        // TODO: 09.12.2018 так быть не должно
        assertEquals("Дом должен измениться даже в состоянии тревоги", testSmartHome, masterSmartHome);

    }

}