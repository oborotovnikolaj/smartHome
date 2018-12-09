package ru.sbt.mipt.oop.counter;

import ru.sbt.mipt.oop.action_executors.*;

import java.util.concurrent.atomic.AtomicInteger;

public class CommonCounter {
    public static int countRooms(ActionExecutor actionExecutor) {
        AtomicInteger roomCounter = new AtomicInteger();
        actionExecutor.executeAction(object -> {
            if (object instanceof Room) {
                roomCounter.addAndGet(1);
            }
        });
        return roomCounter.get();
    }

    public static int countLights(ActionExecutor actionExecutor) {
        AtomicInteger lightCounter = new AtomicInteger();
        actionExecutor.executeAction(object -> {
            if(object instanceof Light) {
                lightCounter.addAndGet(1);
            }
        });
        return lightCounter.get();
    }

    public static int countDoors(ActionExecutor actionExecutor) {
        AtomicInteger doorsCounter = new AtomicInteger();
        actionExecutor.executeAction(object -> {
            if(object instanceof Door) {
                doorsCounter.addAndGet(1);
            }
        });
        return doorsCounter.get();
    }
}
