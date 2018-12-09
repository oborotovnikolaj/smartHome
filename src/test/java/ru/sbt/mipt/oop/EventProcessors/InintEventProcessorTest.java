package ru.sbt.mipt.oop.EventProcessors;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.action_executors.Alarm.ActiveState;
import ru.sbt.mipt.oop.action_executors.Alarm.Alarm;
import ru.sbt.mipt.oop.action_executors.Alarm.InactiveState;
import ru.sbt.mipt.oop.action_executors.Alarm.TriggeringState;
import ru.sbt.mipt.oop.action_executors.SmartHome;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.events.sensors.SensorEvent;
import ru.sbt.mipt.oop.loaders_smart_home.FileSmartHomeLoader;
import ru.sbt.mipt.oop.loaders_smart_home.SmartHomeLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// TODO: 07.12.2018 переделай просто на загрузчик статический
public abstract class InintEventProcessorTest {
    protected SmartHome masterSmartHome;
    protected SmartHome testSmartHome;
    protected SensorEvent[] sensorEvents;

    protected SensorEvent activateAlert = new SensorEvent( SensorEventType.ALARM_ACTIVATE,  "1");
    protected SensorEvent inactivateAlert = new SensorEvent( SensorEventType.ALARM_INACTIVATE,  "1");
    protected SensorEvent alarmInactiveWithWrongKey = new SensorEvent( SensorEventType.ALARM_INACTIVATE,  "2");
    protected SensorEvent alarmActiveWithWrongKey = new SensorEvent( SensorEventType.ALARM_ACTIVATE,  "2");

    protected Alarm alarm = new Alarm(1);
    protected Alarm alarmActive = new Alarm(1, new ActiveState());
    protected Alarm alarmInactive = new Alarm(1, new InactiveState());
    protected Alarm alarmTriggered = new Alarm(1, new TriggeringState());

    protected LightEventProcessor lightEventProcessor = new LightEventProcessor();
    protected DoorEventProcessor doorEventProcessor = new DoorEventProcessor();
    protected HallDoorEventProcessor hallDoorEventProcessor = new HallDoorEventProcessor();
    protected AlarmEventProcessor alarmEventProcessor = new AlarmEventProcessor();

//    protected EventProcessorForSmartHome[] processorForSmartHomes = new  EventProcessorForSmartHome[3]();
//    TriggerAlarmEventProcessor triggerAlarmLightProcessor = new TriggerAlarmEventProcessor(new LightEventProcessor());
//
//    processorForSmartHomes[0] =  triggerAlarmLightProcessor;
////        TriggerAlarmEventProcessor triggerAlarmDoorsProcessor = new TriggerAlarmEventProcessor(new DoorEventProcessor());
////        TriggerAlarmEventProcessor triggerAlarmHallProcessor = new TriggerAlarmEventProcessor(new HallDoorEventProcessor());
//
//    processorForSmartHomes[0] = new TriggerAlarmEventProcessor(lightEventProcessor);
//    processorForSmartHomes[1] = new TriggerAlarmEventProcessor(new DoorEventProcessor());
//    processorForSmartHomes[2] = new TriggerAlarmEventProcessor(new HallDoorEventProcessor());

    @Deprecated
    /**
    @param pathToHomeMaster путь до эталона
    @param pathToEvents путь до желаемой цепочки событий
    @param pathToHomeTest путь до дома, который будет изменяться в ходе теста
     */
    public void inintEventProcessorTest(String pathToHomeMaster, String pathToEvents, String pathToHomeTest) throws IOException {

        SmartHomeLoader MasterHomeLoader = new FileSmartHomeLoader(pathToHomeMaster);
        this.masterSmartHome = MasterHomeLoader.loadSmartHome();

        SmartHomeLoader TestHomeLoader = new FileSmartHomeLoader(pathToHomeTest);
        this.testSmartHome = TestHomeLoader.loadSmartHome();

        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(pathToEvents)));
        sensorEvents =  gson.fromJson(json, SensorEvent[].class);

    }
}
