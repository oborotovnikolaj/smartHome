package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.RemoteControlService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sbt.mipt.oop.EventProcessors.*;
import ru.sbt.mipt.oop.action_executors.Alarm.Alarm;
import ru.sbt.mipt.oop.action_executors.SmartHome;
import ru.sbt.mipt.oop.loaders_smart_home.SmartHomeLoader;
import ru.sbt.mipt.oop.remote.RemoteController;
import ru.sbt.mipt.oop.smartHomeSupporters.EventManager;

import java.io.IOException;

public class Application {

    private static SmartHomeLoader smartHomeLoader;
    private static EventManager smartHomeEventManager;
    private static RemoteControlService remoteControlService;

    public Application(SmartHomeLoader smartHomeLoader, EventManager smartHomeEventManager,
                       RemoteControlService remoteControlService) {
        this.smartHomeLoader = smartHomeLoader;
        this.smartHomeEventManager = smartHomeEventManager;
        this.remoteControlService = remoteControlService;
    }

    public static void main(String... args) throws IOException {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        smartHome.setAlarm(new Alarm(1));

        smartHomeEventManager.setSmartHome(smartHome);

        smartHomeEventManager.addEventProcessor(new TriggerAlarmEventProcessor(new LightEventProcessor()));
        smartHomeEventManager.addEventProcessor(new TriggerAlarmEventProcessor(new DoorEventProcessor()));
        smartHomeEventManager.addEventProcessor(new TriggerAlarmEventProcessor(new HallDoorEventProcessor()));
        smartHomeEventManager.addEventProcessor(new AlarmEventProcessor());

        smartHomeEventManager.runEventsCycle();

        RemoteController remoteController1 = new RemoteController(1);
        RemoteController remoteController2 = new RemoteController(2);

        remoteControlService.registerRemoteControl(remoteController1, remoteController1.getId().toString());
        remoteControlService.registerRemoteControl(remoteController2, remoteController2.getId().toString());
    }
}

