package ru.sbt.mipt.oop.action_executors;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.action_executors.Alarm.Alarm;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements ActionExecutor {
    Collection<Room> rooms;
    private Alarm alarm;

    // TODO: 07.12.2018 нужно сразу иницилизировать код аларма, что делать если он null

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void setAlarm(Alarm alarm){
            if ( this.alarm == null) {
                this.alarm = alarm;
            }
            else {
                System.out.println("to change your code, make order to central office");
            }
    }

    public Alarm getAlarm() { return this.alarm; }

    @Override
    public void executeAction(Action action) {
        for (Room room : rooms) {
            room.executeAction(action);
        }
    }

    // TODO: 09.12.2018 не учитывает разницу в алармах, добавь alarm.getState = alarm.getState
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof SmartHome) {
            SmartHome other = (SmartHome) object;
            Collection<Room> otherRooms = other.getRooms();
            for (Room otherRoom : otherRooms) {
                if (!rooms.contains(otherRoom)) {
                    return false;
                }
            }
            return true;
        }
        return  false;
    }
}

