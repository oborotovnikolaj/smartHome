package ru.sbt.mipt.oop.action_executors;

import ru.sbt.mipt.oop.actions.Action;

import java.util.Collection;

public class Room implements ActionExecutor {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    @Override
    public void executeAction(Action action) {

        action.execute(this);

        for (Light light : lights){
            light.executeAction(action);
        }
        for (Door door : doors) {
            door.executeAction(action);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof Room) {
            Room otherRoom = (Room) object;
            Collection<Door> otherDoors = otherRoom.getDoors();
            Collection<Light> otherLights = otherRoom.getLights();
            for (Door otherDoor : otherDoors) {
                if (!doors.contains(otherDoor)) {
                    return false;
                }
            }
            for (Light otherLight : otherLights) {
                if (!lights.contains(otherLight)) {
                    return false;
                }
            }
            return true;
        }
        return  false;
    }
}
