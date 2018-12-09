package ru.sbt.mipt.oop.action_executors.Alarm;

public abstract class StateOfAlarm {
    protected Alarm alarm;

    protected void setType(AlarmStateType type) {
        this.type = type;
    }

    protected AlarmStateType  type;
    public abstract void inactivateAlarm(Integer codeToActivate);
    public abstract void activateAlarm(Integer codeToActivate);
    public abstract void triggerAlarm();

    public AlarmStateType getType() {
        return type;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    // TODO: 09.12.2018 перенеси это в Alarm
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof StateOfAlarm) {
            StateOfAlarm other = (StateOfAlarm) object;

            if (this.getType() == other.getType() &&
                    this.getAlarm().getCodeToActivate() == other.getAlarm().getCodeToActivate()) {
                return true;
            }
        }

        return false;
    }

    protected boolean checkPresentsAlarm() {
        if (this.alarm == null){
            System.out.println("state without alarm");
            return false;
        }
        return true;
    }
}
