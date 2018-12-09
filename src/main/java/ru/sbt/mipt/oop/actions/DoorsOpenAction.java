package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.action_executors.Door;

public class DoorsOpenAction implements Action {
    @Override
    public void execute(Object object) {
        if (object instanceof Door) {
            Door door = (Door) object;
            door.setOpen(true);
        }
    }
}
