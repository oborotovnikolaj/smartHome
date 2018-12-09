package ru.sbt.mipt.oop.counter;

import org.junit.Test;
import ru.sbt.mipt.oop.action_executors.Room;
import ru.sbt.mipt.oop.action_executors.SmartHome;
import ru.sbt.mipt.oop.util;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CommonCounterTest {
    SmartHome testSmartHome = util.loadSmartHome("src/test/resources/sh_doors_closed_lights_off.json");
    Room testHallRoom = util.loadSRoom("src/test/resources/room_hall_doors_closed_lights_off.json");

    public CommonCounterTest() throws IOException {
    }

    @Test
    public void countRooms() {
        assertEquals(CommonCounter.countRooms(testSmartHome), 2);
        assertEquals(CommonCounter.countRooms(testHallRoom), 1);
    }

    @Test
    public void countLights() {
        assertEquals(CommonCounter.countLights(testSmartHome),3);
        assertEquals(CommonCounter.countLights(testHallRoom), 2);
    }

    @Test
    public void countDoors() {
        assertEquals(CommonCounter.countDoors(testSmartHome),2);
        assertEquals(CommonCounter.countDoors(testHallRoom), 1);
    }
}