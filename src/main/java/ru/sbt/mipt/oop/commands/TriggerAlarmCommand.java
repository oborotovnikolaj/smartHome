package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.action_executors.SmartHome;

public class TriggerAlarmCommand implements Command {
    private SmartHome smartHome;
    private CommandType type = CommandType.ALARM_TRIGGER;

    public TriggerAlarmCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public boolean execute() {
        if (smartHome.getAlarm() == null) {
            return false;
        }
        smartHome.getAlarm().triggerAlarm();
        return true;
    }

    public CommandType getType() {
        return type;
    }
}