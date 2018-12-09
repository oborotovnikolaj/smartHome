package ru.sbt.mipt.oop.loaders_smart_home;

import ru.sbt.mipt.oop.action_executors.SmartHome;

import java.io.IOException;

public interface SmartHomeLoader {
    SmartHome loadSmartHome() throws IOException;
}
