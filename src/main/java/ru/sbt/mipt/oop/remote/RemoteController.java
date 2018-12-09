package ru.sbt.mipt.oop.remote;

import ru.sbt.mipt.oop.action_executors.SmartHome;
import ru.sbt.mipt.oop.commands.*;

import java.util.HashMap;
import java.util.Map;

public class RemoteController implements RemoteControl {
    private final String[] buttonIDs = { "1", "2", "3", "4","A", "B", "C", "D"};
    private Integer iD;
    private Map<String, Button> buttons = new HashMap<>();
    //private Map<Button, Command> commandMap = new HashMap<>();

    public RemoteController(Integer iD) {
        this.iD = iD;
        this.initButtons();
    }

    public void bindRemoteToHome(SmartHome smartHome) {

        Command closeHallDoorCommand = new EachHallDoorCloseCommand(smartHome);
        Command onHallLights = new EachHallLightsOnCommand(smartHome);
        Command onHomeLights = new EachHomeLightsOnCommand(smartHome);
        Command offHomeLights = new EachHomeLightsOffCommand(smartHome);
        Command activeAlarmCommand = new ActivateAlarmCommand(smartHome, 1);
        Command triggerAlarmCommand = new TriggerAlarmCommand(smartHome);

        Command emptyCommand = new EmptyCommand();

        Command[] commands = new Command[]{closeHallDoorCommand, onHallLights,onHomeLights,
                                            offHomeLights,activeAlarmCommand, triggerAlarmCommand};
        //по идее лишняя проверка, но на будущее
        if (commands.length > buttonIDs.length) {
            System.out.println(String.format("your remote can bind only %s commands", buttonIDs.length ));
            return;
        }

        for (int i = 0; i < commands.length; i++) { // остальные копки остануться пустыми
            //по идее лишняя проверка, но на будущее
            if (buttons.get(buttonIDs[i]) == null) {
                System.out.println("there is not such button");
                continue;
            }
            buttons.get(buttonIDs[i]).setCommand(commands[i]);
        }
    }

//    public String getInformationAboutButton(String id) {
//        buttons.get(buttonIDs[i])
//    }


    @Override
    public void onButtonPressed(String buttonId) {
        if(buttons.get(buttonId) != null) {
            Button button = buttons.get(buttonId);
            button.onPressed();
        }
        else System.out.println("there is not such button");
    }

    private void initButtons() {
        for (String buttonID : buttonIDs) {
            buttons.put(buttonID, new Button(buttonID));
        }
    }
    // TODO: 29.11.2018 добавь историю

    public Integer getId() { return this.iD;}
}
