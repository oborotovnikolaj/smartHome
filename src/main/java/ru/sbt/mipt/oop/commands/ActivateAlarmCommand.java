package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.action_executors.SmartHome;

public class ActivateAlarmCommand implements Command {
    private CommandType type = CommandType.ALARM_ACTIVATE;
    private SmartHome smartHome;
    Integer activationCode;

    public ActivateAlarmCommand(SmartHome smartHome, Integer activationCode) {
        this.smartHome = smartHome;
        this.activationCode = activationCode;
    }

    @Override
    public boolean execute() {
        if ( smartHome.getAlarm() != null) {
            smartHome.getAlarm().activateAlarm(activationCode);
            return true;
        }
        return false;
    }
}
