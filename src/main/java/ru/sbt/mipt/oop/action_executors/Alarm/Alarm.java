package ru.sbt.mipt.oop.action_executors.Alarm;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.action_executors.ActionExecutor;

public class Alarm implements ActionExecutor {
    private final Integer codeToActivate;
    private StateOfAlarm state;

    public Alarm(Integer codeToActivate) {
        this.codeToActivate = codeToActivate;
        this.state = new InactiveState(this);
    }

    public Alarm(Integer codeToActivate, StateOfAlarm initalState) {
        this.codeToActivate = codeToActivate;
        initalState.alarm = this;
        this.state = initalState;
    }

    // TODO: 07.12.2018 спрячь стейт, по хорошему должно тригрить
    public void setState(StateOfAlarm state, Integer codeToCheck ) {
        if (codeToCheck.equals(codeToActivate)) {
            this.state = state;
        }
        else {
            System.out.println("For setting state give correct code");
        }
    }

    public void inactivateAlarm(Integer codeToActivate){state.inactivateAlarm(codeToActivate);}
    public void activateAlarm(Integer codeToActivate){state.activateAlarm(codeToActivate);}
    public Integer getCodeToActivate() {return this.codeToActivate; }
    public StateOfAlarm getState() { return this.state; }
    public void triggerAlarm(){
        state.triggerAlarm();
    }

    @Override
    public void executeAction(Action action) { action.execute(this); }

    public boolean isInactiveAlarm() {
        return state instanceof InactiveState;
    }
}
