package ru.sbt.mipt.oop.smartHomeSupporters;
import ru.sbt.mipt.oop.EventProcessors.EventProcessorForSmartHome;
import ru.sbt.mipt.oop.events.providers.SensorEventProvider;
import ru.sbt.mipt.oop.events.sensors.SensorEvent;

import java.util.ArrayList;

public class SmartHomeEventManager extends EventManager{
    private ArrayList<EventProcessorForSmartHome> eventProcessors = new ArrayList<EventProcessorForSmartHome>();
    //private Collection<EventProcessorForSmartHome> eventProcessors = new ArrayList<EventProcessorForSmartHome>();
    //private SmartHome smartHome;
    private SensorEventProvider eventProvider;

    public SmartHomeEventManager(SensorEventProvider eventProvider) {
        //this.smartHome = smartHome;
        this.eventProvider = eventProvider;
    }
    @Override
    public void addEventProcessor ( EventProcessorForSmartHome eventProcessor ) {
        this.eventProcessors.add(eventProcessor);
    }

    @Override
    public void runEventsCycle() {
        SensorEvent event = eventProvider.getNextSensorEvent();
        while ( event != null ) {
            System.out.println("Got event:" + event);
            for ( EventProcessorForSmartHome eventProcessor : eventProcessors) {
                eventProcessor.processEvent(smartHome, event);
            }
            event = eventProvider.getNextSensorEvent();
        }
    }
    @Override
    public void deleteEventProcessor(EventProcessorForSmartHome eventProcessor) {
        eventProcessors.remove(eventProcessor);
    }

    public ArrayList<EventProcessorForSmartHome> getEventProcessors(){
        return eventProcessors;
    }

}


