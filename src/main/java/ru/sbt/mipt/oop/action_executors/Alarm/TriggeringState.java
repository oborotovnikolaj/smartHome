package ru.sbt.mipt.oop.action_executors.Alarm;

public class TriggeringState extends StateOfAlarm {

    public TriggeringState(Alarm alarm) {
        this.alarm = alarm;
        this.type = AlarmStateType.TRIGGERING;
    }

    public TriggeringState() {
        this.type = AlarmStateType.TRIGGERING;
    }

    @Override
    public void inactivateAlarm(Integer codeToActivate) {
        alarm.setState( new InactiveState(alarm), codeToActivate);
    }

    @Override
    public void activateAlarm(Integer codeToActivate) {
        System.out.println("firstly inactivate alarm system");
    }//

    @Override
    public void triggerAlarm() {
        System.out.println("Already has been triggered");

    }
}
