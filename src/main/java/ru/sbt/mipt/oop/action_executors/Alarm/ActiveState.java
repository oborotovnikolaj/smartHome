package ru.sbt.mipt.oop.action_executors.Alarm;

public class ActiveState extends StateOfAlarm {

    // TODO: 09.12.2018 у тебя на один аларм может ссылаться несколько стейтов, это конечно дому не вредит так как аларм может ссылаться только на один стейт но все равно хрень
    // если использовать этот конструктор, то состояние должно обязательно привязовываться в конструкторе
    // соотвествующем Alert ( code, initialState )
    public ActiveState() {
        this.type = AlarmStateType.ACTIVATE;
    }

    public ActiveState(Alarm alarm) {
        this.alarm = alarm;
        this.type = AlarmStateType.ACTIVATE;
    }

    @Override
    public void activateAlarm(Integer codeToActivate) {
        System.out.println("Already has been activate");
    }// do nothing

    @Override
    public void inactivateAlarm(Integer codeToActivate) {
        if (codeToActivate.equals(this.alarm.getCodeToActivate())) {
            alarm.setState( new InactiveState(alarm), codeToActivate);
        }
        else {
            System.out.println("Alarm trigger!");
            alarm.setState( new TriggeringState(alarm), alarm.getCodeToActivate());
        }
    }

    @Override
    public void triggerAlarm() {
        alarm.setState( new TriggeringState(alarm), alarm.getCodeToActivate());
        System.out.println("Alarm triggered");
    }
}
