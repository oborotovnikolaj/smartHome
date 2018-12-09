package ru.sbt.mipt.oop.loaders_smart_home;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.action_executors.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSmartHomeLoader implements SmartHomeLoader {
    private String path;

    public FileSmartHomeLoader(String path) {
        this.path = path;
    }

    public FileSmartHomeLoader() {
        this("smart-home-1.js");
    }

    @Override
    public SmartHome loadSmartHome() throws IOException {
        // считываем состояние дома из файла
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(path)));
        return gson.fromJson(json, SmartHome.class);
    }
}
