package ru.sbt.mipt.oop.action_executors;

import ru.sbt.mipt.oop.actions.Action;

public class Light implements ActionExecutor {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
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
        if (object instanceof Light) {
            Light otherLight = (Light) object;
            if (otherLight.isOn == this.isOn && otherLight.id.equals(this.id)) {
                return true;
            }
        }
        return  false;
    }

}
