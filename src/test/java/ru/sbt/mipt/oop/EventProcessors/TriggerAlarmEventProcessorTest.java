package ru.sbt.mipt.oop.EventProcessors;

import org.junit.Test;
import ru.sbt.mipt.oop.action_executors.Alarm.Alarm;
import ru.sbt.mipt.oop.action_executors.Alarm.InactiveState;
import ru.sbt.mipt.oop.events.sensors.SensorEvent;
import ru.sbt.mipt.oop.util;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TriggerAlarmEventProcessorTest extends InintEventProcessorTest{

    @Test
    public void triggerAlert() throws IOException {
        testSmartHome = util.loadSmartHome( "src/test/resources/sh_doors_open_lights_off.json" );
        testSmartHome.setAlarm(alarmTriggered);

        alarmEventProcessor.processEvent(testSmartHome, activateAlert);
        assertEquals("Активация сигнализации невозможна во время тревоги",
                testSmartHome.getAlarm().getState(), alarmTriggered.getState());

        alarmEventProcessor.processEvent(testSmartHome, alarmInactiveWithWrongKey);
        assertEquals("Тревога была откючена по неверному ключу",
                testSmartHome.getAlarm().getState(), alarmTriggered.getState());

        alarmEventProcessor.processEvent(testSmartHome, inactivateAlert);
        assertEquals("не работает корректно отключение тревоги",
                testSmartHome.getAlarm().getState(), alarmInactive.getState());
    }

    @Test
    public void workWithTriggerAlert() throws IOException {
        masterSmartHome = util.loadSmartHome("src/test/resources/sh_doors_closed_lights_off.json");
        testSmartHome = util.loadSmartHome("src/test/resources/sh_doors_closed_lights_off.json");
        sensorEvents = util.loadEvents("src/test/resources/unchanging_sensor_events.json");

        masterSmartHome.setAlarm(alarmTriggered);
        testSmartHome.setAlarm(alarmTriggered);

        for (SensorEvent event : sensorEvents) {
            alarmEventProcessor.processEvent(testSmartHome, event);
        }
        assertEquals("Из состояния тревоги нельзя менять состояние дома, перед этим не перейдя в неакивное состяние",
                testSmartHome, masterSmartHome);
    }

    @Test
    public void workWithActiveAlert() throws IOException {
        EventProcessorForSmartHome[] processorForSmartHomes = new  EventProcessorForSmartHome[3];

        processorForSmartHomes[0] = new TriggerAlarmEventProcessor(new LightEventProcessor());
        processorForSmartHomes[1] = new TriggerAlarmEventProcessor(new DoorEventProcessor());
        processorForSmartHomes[2] = new TriggerAlarmEventProcessor(new HallDoorEventProcessor());

        masterSmartHome = util.loadSmartHome("src/test/resources/sh_doors_closed_lights_off.json");
        testSmartHome = util.loadSmartHome("src/test/resources/sh_doors_closed_lights_off.json");
        sensorEvents = util.loadEvents("src/test/resources/unchanging_sensor_events.json");

        masterSmartHome.setAlarm(alarmTriggered);
        testSmartHome.setAlarm(alarmActive);

        for (SensorEvent event : sensorEvents) {
            for ( EventProcessorForSmartHome processor : processorForSmartHomes)
                processor.processEvent(testSmartHome, event);
        }
        assertEquals("Попытка открыть дверь или переключить свет в доме с активной сигнализацией только поднимает тревогу",
                testSmartHome, masterSmartHome);
    }

    @Test
    public void workWithInactiveAlert() throws IOException {
        EventProcessorForSmartHome[] processorForSmartHomes = new  EventProcessorForSmartHome[3];
//        TriggerAlarmEventProcessor triggerAlarmLightProcessor = new TriggerAlarmEventProcessor(new LightEventProcessor());
//        TriggerAlarmEventProcessor triggerAlarmDoorsProcessor = new TriggerAlarmEventProcessor(new DoorEventProcessor());
//        TriggerAlarmEventProcessor triggerAlarmHallProcessor = new TriggerAlarmEventProcessor(new HallDoorEventProcessor());

        masterSmartHome = util.loadSmartHome("src/test/resources/sh_doors_closed_lights_on.json");
        testSmartHome = util.loadSmartHome("src/test/resources/sh_doors_open_lights_off.json");
        sensorEvents = util.loadEvents("src/test/resources/all_changing_sensor_events.json");

        processorForSmartHomes[0] = new TriggerAlarmEventProcessor(new LightEventProcessor());
        processorForSmartHomes[1] = new TriggerAlarmEventProcessor(new DoorEventProcessor());
        processorForSmartHomes[2] = new TriggerAlarmEventProcessor(new HallDoorEventProcessor());

        Alarm alarmInactive = new Alarm(1, new InactiveState());
        masterSmartHome.setAlarm(alarmInactive);
        testSmartHome.setAlarm(alarmInactive);

        for (SensorEvent event : sensorEvents) {
            for ( EventProcessorForSmartHome processor : processorForSmartHomes)
                processor.processEvent(testSmartHome, event);
        }
        assertEquals("Завернутые процессоры в декоратор должны нормально работать из неакивного состояния тревоги",
                testSmartHome, masterSmartHome);
    }
}