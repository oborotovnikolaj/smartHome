package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.events.sensors.SensorEvent;
import ru.sbt.mipt.oop.action_executors.SmartHome;
import static ru.sbt.mipt.oop.events.SensorEventType.*;


public class AlarmEventProcessor implements EventProcessorForSmartHome {

    //придумай, что с этим сделать чтобы можно было сделать с  EventProcessorCommon
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if ( isAlarmEvent(event) ) {
            if (smartHome.getAlarm() == null) {
                System.out.println("there is not any alarm");
                return;
            }
            if ( event.getType() == ALARM_ACTIVATE ) {
                smartHome.getAlarm().activateAlarm( Integer.parseInt( event.getObjectId() ));
            }
            else {
                smartHome.getAlarm().inactivateAlarm( Integer.parseInt( event.getObjectId()));
            }
        }

    }

    private boolean isAlarmEvent(SensorEvent event) {
        if (event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_INACTIVATE) {
            return true;
        }
        return false;
    }
}
