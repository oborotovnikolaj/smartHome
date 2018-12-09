package ru.sbt.mipt.oop.action_executors.Alarm;

public class InactiveState extends StateOfAlarm {

    // TODO: 09.12.2018 make the same with ohter states
    public InactiveState(Alarm alarm) {
        this.type = AlarmStateType.INACTIVATE;
        this.alarm = alarm;
    }

    public InactiveState() {
        this.type = AlarmStateType.INACTIVATE;
    }

    @Override
    public void inactivateAlarm(Integer codeToActivate) {
        if (checkPresentsAlarm()) {
            System.out.println("Already has been inactivate");
        }
    }

    @Override
    public void activateAlarm(Integer codeToActivate) {
        if (checkPresentsAlarm()) {
            alarm.setState(new ActiveState(alarm), codeToActivate);
        }
    }

    @Override
    public void triggerAlarm() {
        if (checkPresentsAlarm()) {
            alarm.setState(new TriggeringState(alarm), alarm.getCodeToActivate());
            System.out.println("Alarm triggered");
        }
    }

}
