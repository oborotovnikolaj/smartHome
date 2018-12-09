package ru.sbt.mipt.oop.action_executors;

import ru.sbt.mipt.oop.actions.Action;

public class Door implements ActionExecutor {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof Door) {
            Door otherDoor = (Door) object;
            if (otherDoor.isOpen == this.isOpen && otherDoor.id.equals(this.id)) {
                return true;
            }
        }
        return  false;
    }

}
