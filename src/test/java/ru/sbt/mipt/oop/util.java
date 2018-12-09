package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.EventProcessors.EventProcessorForSmartHome;
import ru.sbt.mipt.oop.action_executors.Alarm.Alarm;
import ru.sbt.mipt.oop.action_executors.Room;
import ru.sbt.mipt.oop.action_executors.SmartHome;
import ru.sbt.mipt.oop.events.sensors.SensorEvent;
import ru.sbt.mipt.oop.loaders_smart_home.FileSmartHomeLoader;
import ru.sbt.mipt.oop.loaders_smart_home.SmartHomeLoader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class util{

    public static  SensorEvent[] loadEvents (String path ) throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(path)));
        return  gson.fromJson(json, SensorEvent[].class);
    }

    public static SmartHome addAlarm (SmartHome smartHomeWithoutAlert, Integer activationCode ) {
        Alarm alarm = new Alarm(activationCode);
        smartHomeWithoutAlert.setAlarm(alarm);
        return smartHomeWithoutAlert;
    }

    public static SmartHome loadSmartHome ( String path ) throws IOException {
        SmartHomeLoader TestHomeLoader = new FileSmartHomeLoader(path);
        return TestHomeLoader.loadSmartHome();
    }

    public static Room loadSRoom(String path) throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(path)));
        return  gson.fromJson(json, Room.class);
    }

    public static void buildJson(ArrayList<EventProcessorForSmartHome> o, String name) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(o);
        System.out.println(jsonString);
        Path path = Paths.get(name + ".json");
        try (
                BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
    }

    public static void buildJsonofProseccors (String name) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(EventProcessorForSmartHome.class);
        System.out.println(jsonString);
        Path path = Paths.get(name + ".json");
        try (
                BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
    }
}
